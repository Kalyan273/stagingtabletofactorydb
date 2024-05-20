package com.factory.appraisal.factoryService.services.impl;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.S3ClientOptions;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.factory.appraisal.factoryService.ExceptionHandle.AppraisalException;
import com.factory.appraisal.factoryService.ExceptionHandle.Response;
import com.factory.appraisal.factoryService.constants.AppraisalConstants;
import com.factory.appraisal.factoryService.dto.*;
import com.factory.appraisal.factoryService.mktCheck.model.ECities;
import com.factory.appraisal.factoryService.mktCheck.model.EInventoryVehicles;
import com.factory.appraisal.factoryService.mktCheck.model.EMkDealerRegistration;
import com.factory.appraisal.factoryService.mktCheck.model.EMkScheduler;
import com.factory.appraisal.factoryService.mktCheck.repo.FlCitiesRepo;
import com.factory.appraisal.factoryService.mktCheck.repo.MktDealerRepo;
import com.factory.appraisal.factoryService.mktCheck.repo.MktInventoryRepo;
import com.factory.appraisal.factoryService.mktCheck.repo.MktSchedulerRepo;
import com.factory.appraisal.factoryService.persistence.mapper.AppraisalVehicleMapper;
import com.factory.appraisal.factoryService.persistence.model.EAppraiseVehicle;
import com.factory.appraisal.factoryService.persistence.model.EDealerRegistration;
import com.factory.appraisal.factoryService.persistence.model.EUserRegistration;
import com.factory.appraisal.factoryService.repository.*;
import com.factory.appraisal.factoryService.services.MarketCheckApiServiceDump;
import com.factory.appraisal.factoryService.util.Readfiles;
import com.fasterxml.jackson.databind.ObjectMapper;
import freemarker.template.TemplateException;
import net.sf.jasperreports.engine.JRException;
import org.jdom2.JDOMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class MarketCheckApiServiceDumpImpl implements MarketCheckApiServiceDump {
    Logger log = LoggerFactory.getLogger(MarketCheckApiServiceDumpImpl.class);

    @Value("${api_key}")
    private String api_key;

    @Value("${image_folder_path}")
    private String imageFolderPath;
    @Value("${market_check_url}")
    private String marketCheckUrl;
    @Value("${market_check_dealer_url}")
    private String marketCheckDealerUrl;
    @Value("${market_check_inventory_url}")
    private String marketCheckInvUrl;
    @Value("${market_check_url_suffix}")
    private String urlSuffix;
    @Value("${market_check_dealer_url_suffix}")
    private String dealerUrlSuffix;
    @Value("${market_check_inv_url_suffix}")
    private String invUrlSuffix;
    @Value("${host}")
    private String host;
    @Value("${access_key}")
    private String accesskey;

    @Value(("${secret}"))
    private String secret;

    @Value(("${amazonS3_url}"))
    private String amazonS3Url;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private MktDealerRepo dealerRepo;
    @Autowired
    private MktInventoryRepo inventoryRepo;

    @Autowired
    private Readfiles readfiles;
    @Autowired
    private AppraisalVehicleMapper mapper;
    @Autowired
    private DealerRegistrationServiceImpl dealerRegistrationService;
    @Autowired
    private AppraiseVehicleServiceImpl appraiseVehicleService;

    @Autowired
    private AppraiseVehicleRepo appraiseVehicleRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private UserRegistrationRepo userRepo;
    @Autowired
    private ConfigCodesRepo configCodesRepo;
    @Autowired
    private FlCitiesRepo flCitiesRepo;

    @Autowired
    private DealerRegistrationRepo dlrRegRepo;

    @Autowired
    private MktSchedulerRepo schedulerRepo;


    @Transactional
    @Override
    public void getAllDealersInFlorida() throws IOException {
        List<String> cites = readfiles.processExcelFile();
        for (String city : cites) {
            saveMarketCheckDealer(city);
            log.info("{}", city);
        }

    }

    @Transactional
    @Override
    public void addMktInvVehicles(Long start, Long end) throws AppraisalException, IOException {

        // List<Long> mktIds = dealerRepo.findAllDealerMktId();

        List<String> mktIds = dealerRepo.findInRangeDealerMktId(start, end);
        for (String mkDealerId : mktIds) {
            log.info("finding for id:{}", mkDealerId);

            saveMarketCheckInv(mkDealerId);
        }

    }


    @Override
    @Transactional
    public void saveCitiesInFl() throws IOException {
        List<String> cites = readfiles.processExcelFile();
        List<ECities> eCities = new ArrayList<>();

        for (String city : cites) {

            eCities.add(new ECities(city));
        }

        flCitiesRepo.saveAll(eCities);

    }

    @Override
    public void saveMarketCheckDealer(String city) throws WebClientResponseException {

        MktDealer mktDealer = null;

        WebClient webClient = WebClient.create();

        mktDealer = webClient.get()
                .uri(marketCheckDealerUrl + api_key + AppraisalConstants.AND + AppraisalConstants.CITY + "=" + city + AppraisalConstants.AND + dealerUrlSuffix)
                .header(AppraisalConstants.HOST, host)
                .retrieve()
                .bodyToFlux(DataBuffer.class)
                .reduce(DataBuffer::write)
                .map(buffer -> {
                    try (InputStream inputStream = buffer.asInputStream()) {
// Process the input stream as needed
                        return objectMapper.readValue(inputStream, MktDealer.class);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } finally {
                        DataBufferUtils.release(buffer);
                    }
                })
                .block();

        if (null != mktDealer && !mktDealer.getDealers().isEmpty()) {

            List<EMkDealerRegistration> dealerList = new ArrayList<>();
            int size = mktDealer.getDealers().size();

            for (int i = 0; i < size; i++) {

                LinkedHashMap<?, ?> dealerInfo = (LinkedHashMap<?, ?>) mktDealer.getDealers().get(i);
                EMkDealerRegistration dealerReg = new EMkDealerRegistration();

                Long dealerByMktId = dealerRepo.findDealerByMktId(dealerInfo.get(AppraisalConstants.ID).toString());

                if (null == dealerByMktId) {
                    dealerReg.setMkDealerId(dealerInfo.get(AppraisalConstants.ID).toString());
                    dealerReg.setSellerName(null != dealerInfo.get(AppraisalConstants.SELLER_NAME) ? dealerInfo.get(AppraisalConstants.SELLER_NAME).toString() : null);
                    dealerReg.setWebsite(null != dealerInfo.get(AppraisalConstants.WEBSITE_URL) ? dealerInfo.get(AppraisalConstants.WEBSITE_URL).toString() : null);
                    dealerReg.setDealerType(null != dealerInfo.get(AppraisalConstants.DEALER_TYPE) ? dealerInfo.get(AppraisalConstants.DEALER_TYPE).toString() : null);
                    dealerReg.setStreet(null != dealerInfo.get(AppraisalConstants.STREET) ? dealerInfo.get(AppraisalConstants.STREET).toString() : null);
                    dealerReg.setCity(null != dealerInfo.get(AppraisalConstants.CITY) ? dealerInfo.get(AppraisalConstants.CITY).toString() : null);
                    dealerReg.setState(null != dealerInfo.get(AppraisalConstants.STATE) ? dealerInfo.get(AppraisalConstants.STATE).toString() : null);
                    dealerReg.setCountry(null != dealerInfo.get(AppraisalConstants.COUNTRY) ? dealerInfo.get(AppraisalConstants.COUNTRY).toString() : null);
                    dealerReg.setZip(null != dealerInfo.get(AppraisalConstants.ZIP) ? dealerInfo.get(AppraisalConstants.ZIP).toString() : null);
                    dealerReg.setLatitude(null != dealerInfo.get(AppraisalConstants.LATITUDE) ? dealerInfo.get(AppraisalConstants.LATITUDE).toString() : null);
                    dealerReg.setLongitude(null != dealerInfo.get(AppraisalConstants.LONGITUDE) ? dealerInfo.get(AppraisalConstants.LONGITUDE).toString() : null);
                    dealerReg.setPhone(null != dealerInfo.get(AppraisalConstants.PHONE) ? dealerInfo.get(AppraisalConstants.PHONE).toString() : null);
                    dealerReg.setSellerEmail(null != dealerInfo.get(AppraisalConstants.SELLER_EMAIL) ? dealerInfo.get(AppraisalConstants.SELLER_EMAIL).toString() : null);
                    dealerList.add(dealerReg);
                }
            }

            dealerRepo.saveAll(dealerList);
        } else {
            log.info("{} :having no data", city);
        }

    }


    @Override
    public void saveMarketCheckInv(String mktDealerID) throws WebClientResponseException, AppraisalException, IOException {
        EInventoryVehicles creaPage = null;
        List<EInventoryVehicles> inventoryVehicles = new ArrayList<>();
        MktInventory mktInventory = null;
        List<MktInventory> mktInventoryList = new ArrayList<>();
        WebClient webClient = WebClient.create();

        int start = 0;
        int rows = 50;

        mktInventory = fetchInventoryPage(webClient, mktDealerID, start, rows);
        if (null != mktInventory && null != mktInventory.getListings() && !mktInventory.getListings().isEmpty()) {
            log.info("num of inv:{}", mktInventory.getNoOfInv());
            mktInventoryList.add(mktInventory);
        }

        assert mktInventory != null;
        int numFound = mktInventory.getNoOfInv();
        int noOfTime;
        if (numFound % 50 == 0) {
            noOfTime = (numFound / 50);
        } else {
            noOfTime = (numFound / 50) + 1;
        }


        for (int j = 0; j < noOfTime; j++) {

            if (j > 0) {
                start += rows;
                mktInventory = fetchInventoryPage(webClient, mktDealerID, start, rows);
                if (null != mktInventory && null != mktInventory.getListings() && !mktInventory.getListings().isEmpty()) {
                    mktInventoryList.add(mktInventory);
                }

            }
        }

        if (!mktInventoryList.isEmpty()) {

            for (int i = 0; i < mktInventoryList.size(); i++) {

                List<Object> listings = mktInventoryList.get(i).getListings();
                for (int a = 0; a < listings.size(); a++) {

                    LinkedHashMap<?, ?> invInfo = (LinkedHashMap<?, ?>) listings.get(a);
                    LinkedHashMap<?, ?> build = (LinkedHashMap<?, ?>) invInfo.get("build");
                    LinkedHashMap<?, ?> media = (LinkedHashMap<?, ?>) invInfo.get("media");
                    LinkedHashMap<?, ?> dealer = (LinkedHashMap<?, ?>) invInfo.get("dealer");


                    creaPage = setBuildParam(invInfo, build, dealer);
                    if (null != media) {
                        List<String> picList = (List<String>) media.get("photo_links_cached");

                        creaPage = setMedia(picList, creaPage);
                    }
                    inventoryVehicles.add(creaPage);

                }
            }
        }


        inventoryRepo.saveAll(inventoryVehicles);

    }

    private MktInventory fetchInventoryPage(WebClient webClient, String mktDealerID, int start, int rows) throws IOException {
        return webClient.get()
                .uri(marketCheckInvUrl + api_key + AppraisalConstants.AND + AppraisalConstants.DEALER_ID + "=" + mktDealerID + AppraisalConstants.AND + invUrlSuffix + "&start=" + start + "&rows=" + rows)
                .header(AppraisalConstants.HOST, host)
                .retrieve()
                .bodyToFlux(DataBuffer.class)
                .reduce(DataBuffer::write)
                .map(buffer -> {
                    try (InputStream inputStream = buffer.asInputStream()) {
                        // Process the input stream as needed
                        return objectMapper.readValue(inputStream, MktInventory.class);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } finally {
                        DataBufferUtils.release(buffer);
                    }
                })
                .block();
    }

    @Transactional
    @Override
    public void storeDataFromMkDealerToDealerReg() throws AppraisalException, MessagingException, TemplateException, IOException {
        List<EMkDealerRegistration> dealerWthOutUUID = dealerRepo.findDealerWthOutUUID();
        List<DealerRegistration> dealerRegistrations = mapper.eMkDealerRegistrationToEDealerReg(dealerWthOutUUID);
        for (DealerRegistration dealer : dealerRegistrations) {
            EDealerRegistration dealerByMktDlrID = dlrRegRepo.findDealerByMktDlrID(dealer.getMkDealerId());
            if (null == dealerByMktDlrID) {
                Long roleIdOfD1 = roleRepo.findRoleIdOfD1User();
                dealer.setRoleId(roleIdOfD1);
                String uuid = dealerRegistrationService.createDealer(dealer);
                EMkDealerRegistration dlrByMktId = dealerRepo.findDlrByMktId(dealer.getMkDealerId().toString());
                dlrByMktId.setUserUuid(uuid);
                dealerRepo.save(dlrByMktId);
            }
        }
    }



    @Transactional
    @Override
    public void storeDataFromMkInventoryToAppr() throws AppraisalException, JRException, IOException, JDOMException {

        int pageNumber = 0;
        int pageSize = 500;
        boolean hasNextPage = true;

        while (hasNextPage) {
            Pageable pageable = PageRequest.of(pageNumber, pageSize);
            Page<EInventoryVehicles> page = inventoryRepo.getAllInv(pageable);

            // Process the current page
            List<EInventoryVehicles> content = page.getContent();
            // Add your processing logic here

            for (EInventoryVehicles inv : content) {

                Long dealerId = dlrRegRepo.findDealer(Long.valueOf(inv.getDealerId()));

                Integer count = appraiseVehicleRepo.checkAprr(inv.getVin(),dealerId);
                ApprCreaPage creaPages = null;
                if (1 != count) {

                    UUID userByDlrId = userRepo.findUserByDlrId(dealerId);

                    creaPages = mapper.invToApprCreaPage(inv);

                    creaPages = setColors(creaPages, inv.getExteriorColor(), inv.getInteriorColor());

                    List<String> allPics = new ArrayList<>();
                    allPics.add(inv.getVehiclePic1());
                    allPics.add(inv.getVehiclePic2());
                    allPics.add(inv.getVehiclePic3());
                    allPics.add(inv.getVehiclePic4());
                    allPics.add(inv.getVehiclePic5());
                    allPics.add(inv.getVehiclePic6());
                    allPics.add(inv.getVehiclePic7());
                    allPics.add(inv.getVehiclePic8());
                    allPics.add(inv.getVehiclePic9());
                    creaPages = setMedia(allPics, creaPages);
//                 creaPages.setFromMkt("YES");

                    appraiseVehicleService.addAppraiseVehicle(creaPages, userByDlrId, AppraisalConstants.INVENTORY);
                } else {

                    Long appraisalId = appraiseVehicleRepo.findAppraisal(inv.getVin(),dealerId);
                    creaPages = mapper.invToApprCreaPage(inv);
                    appraiseVehicleService.updateAppraisal(creaPages, appraisalId);

                }

            }
            // Check if there's a next page
            hasNextPage = page.hasNext();
            // Increase pageNumber for the next iteration
            pageNumber++;
        }
    }

    private EInventoryVehicles setBuildParam(LinkedHashMap<?, ?> invInfo, LinkedHashMap<?, ?> build, LinkedHashMap<?, ?> dealer) {
        log.info("setBuildParam started");
        EInventoryVehicles apprvehi = new EInventoryVehicles();

        apprvehi.setVin(null != invInfo.get(AppraisalConstants.MKT_VIN) ? invInfo.get(AppraisalConstants.MKT_VIN).toString() : null);
        apprvehi.setHeading(null != invInfo.get(AppraisalConstants.HEADING) ? invInfo.get(AppraisalConstants.HEADING).toString() : null);
        apprvehi.setPrice(null != invInfo.get(AppraisalConstants.PRICE) ? Integer.valueOf(invInfo.get(AppraisalConstants.PRICE).toString()) : null);
        apprvehi.setRefPrice(null != invInfo.get(AppraisalConstants.REF_PRICE) ? Integer.valueOf(invInfo.get(AppraisalConstants.REF_PRICE).toString()) : null);
        apprvehi.setMiles(null != invInfo.get(AppraisalConstants.MILES) ? Integer.parseInt(invInfo.get(AppraisalConstants.MILES).toString()) : null);
        apprvehi.setMsrp(null != invInfo.get(AppraisalConstants.MSRP) ? Integer.valueOf(invInfo.get(AppraisalConstants.MSRP).toString()) : null);
        apprvehi.setExteriorColor(null != invInfo.get(AppraisalConstants.EXTERIOR_COLOR.toLowerCase()) ? invInfo.get(AppraisalConstants.EXTERIOR_COLOR.toLowerCase()).toString() : null);
        apprvehi.setInteriorColor(null != invInfo.get(AppraisalConstants.INTERIOR_COLOR.toLowerCase()) ? invInfo.get(AppraisalConstants.INTERIOR_COLOR.toLowerCase()).toString() : null);
        apprvehi.setBaseIntColor(null != invInfo.get(AppraisalConstants.BASE_INT_COLOR) ? invInfo.get(AppraisalConstants.BASE_INT_COLOR).toString() : null);
        apprvehi.setBaseExtColor(null != invInfo.get(AppraisalConstants.BASE_EXT_COLOR) ? invInfo.get(AppraisalConstants.BASE_EXT_COLOR).toString() : null);
        apprvehi.setSellerType(null != invInfo.get(AppraisalConstants.SELLER_TYPE) ? invInfo.get(AppraisalConstants.SELLER_TYPE).toString() : null);
        apprvehi.setInventoryType(null != invInfo.get(AppraisalConstants.INVENTORY_TYPE) ? invInfo.get(AppraisalConstants.INVENTORY_TYPE).toString() : null);
        apprvehi.setSource(null != invInfo.get(AppraisalConstants.SOURCE) ? invInfo.get(AppraisalConstants.SOURCE).toString() : null);
        apprvehi.setInTransit(null != invInfo.get(AppraisalConstants.IN_TRANSIT) ? Boolean.valueOf(invInfo.get(AppraisalConstants.IN_TRANSIT).toString()) : null);
        apprvehi.setAvailabilityStatus(null != invInfo.get(AppraisalConstants.AVAILABILITY_STATUS) ? invInfo.get(AppraisalConstants.AVAILABILITY_STATUS).toString() : null);


        apprvehi.setDealerId(null != dealer.get(AppraisalConstants.ID) ? dealer.get(AppraisalConstants.ID).toString() : null);


        apprvehi.setYear(null != build.get(AppraisalConstants.YEAR) ? Integer.parseInt(build.get(AppraisalConstants.YEAR).toString()) : null);
        apprvehi.setMake(null != build.get(AppraisalConstants.MAKE) ? build.get(AppraisalConstants.MAKE).toString() : null);
        apprvehi.setModel(null != build.get(AppraisalConstants.MODEL) ? build.get(AppraisalConstants.MODEL).toString() : null);
        apprvehi.setTrim((null != build.get(AppraisalConstants.TRIM)) ? build.get(AppraisalConstants.TRIM).toString() : null);
        apprvehi.setBodyType((null != build.get(AppraisalConstants.BODY_TYPE)) ? build.get(AppraisalConstants.BODY_TYPE).toString() : null);
        apprvehi.setVehicleType((null != build.get(AppraisalConstants.VEHICLE_TYPE)) ? build.get(AppraisalConstants.VEHICLE_TYPE).toString() : null);
        apprvehi.setTransmission(null != build.get(AppraisalConstants.TRANSMISSION) ? build.get(AppraisalConstants.TRANSMISSION).toString() : null);
        apprvehi.setDrivetrain((null != build.get(AppraisalConstants.DRIVETRAIN)) ? build.get(AppraisalConstants.DRIVETRAIN).toString() : null);
        apprvehi.setFuelType(null != build.get(AppraisalConstants.FUEL_TYPE) ? build.get(AppraisalConstants.FUEL_TYPE).toString() : null);
        apprvehi.setEngine(null != build.get(AppraisalConstants.ENGINE) ? build.get(AppraisalConstants.ENGINE).toString() : null);
        apprvehi.setDoors(null != build.get(AppraisalConstants.DOORS) ? build.get(AppraisalConstants.DOORS).toString() : null);
        apprvehi.setMadeIn(null != build.get(AppraisalConstants.MADE_IN) ? build.get(AppraisalConstants.MADE_IN).toString() : null);
        apprvehi.setOverallHeight(null != build.get(AppraisalConstants.OVERALL_HEIGHT) ? build.get(AppraisalConstants.OVERALL_HEIGHT).toString() : null);
        apprvehi.setOverallLength(null != build.get(AppraisalConstants.OVERALL_LENGTH) ? build.get(AppraisalConstants.OVERALL_LENGTH).toString() : null);
        apprvehi.setOverallWidth(null != build.get(AppraisalConstants.OVERALL_WIDTH) ? build.get(AppraisalConstants.OVERALL_WIDTH).toString() : null);
        apprvehi.setStdSeating(null != build.get(AppraisalConstants.STD_SEATING) ? build.get(AppraisalConstants.STD_SEATING).toString() : null);
        apprvehi.setHighwayMpg(null != build.get(AppraisalConstants.HIGHWAY_MPG) ? build.get(AppraisalConstants.HIGHWAY_MPG).toString() : null);
        apprvehi.setCityMpg(null != build.get(AppraisalConstants.CITY_MPG) ? build.get(AppraisalConstants.CITY_MPG).toString() : null);
        apprvehi.setPowertrainType(null != build.get(AppraisalConstants.POWERTRAIN_TYPE) ? build.get(AppraisalConstants.POWERTRAIN_TYPE).toString() : null);

        return apprvehi;

    }

    private EInventoryVehicles setMedia(List<String> picList, EInventoryVehicles apprvehi) throws AppraisalException, IOException {
        log.info("setMedia started");
        if (null != picList && !picList.isEmpty()) {

            for (int j = 0; j < picList.size(); j++) {

                switch (j) {
                    case 0:
                        apprvehi.setVehiclePic1((picList.get(0)));
                        break;
                    case 1:
                        apprvehi.setVehiclePic2((picList.get(1)));
                        break;
                    case 2:
                        apprvehi.setVehiclePic3((picList.get(2)));
                        break;
                    case 3:
                        apprvehi.setVehiclePic4((picList.get(3)));
                        break;
                    case 4:
                        apprvehi.setVehiclePic5((picList.get(4)));
                        break;
                    case 5:
                        apprvehi.setVehiclePic6((picList.get(5)));
                        break;
                    case 6:
                        apprvehi.setVehiclePic7((picList.get(6)));
                        break;
                    case 7:
                        apprvehi.setVehiclePic8((picList.get(7)));
                        break;
                    case 8:
                        apprvehi.setVehiclePic9((picList.get(8)));
                        break;

                }

            }
        }

        return apprvehi;
    }

    private ApprCreaPage setMedia(List<String> picList, ApprCreaPage apprvehi) {
        if (null != picList && !picList.isEmpty()) {

            for (int j = 0; j < picList.size(); j++) {

                if (null != picList.get(j)) {

                    switch (j) {
                        case 0:
                            apprvehi.setVehiclePic1(getImagesFromMtkApi(picList.get(0)));
                            break;
                        case 1:
                            apprvehi.setVehiclePic2(getImagesFromMtkApi(picList.get(1)));
                            break;
                        case 2:
                            apprvehi.setVehiclePic3(getImagesFromMtkApi(picList.get(2)));
                            break;
                        case 3:
                            apprvehi.setVehiclePic4(getImagesFromMtkApi(picList.get(3)));
                            break;
                        case 4:
                            apprvehi.setVehiclePic5(getImagesFromMtkApi(picList.get(4)));
                            break;
                        case 5:
                            apprvehi.setVehiclePic6(getImagesFromMtkApi(picList.get(5)));
                            break;
                        case 6:
                            apprvehi.setVehiclePic7(getImagesFromMtkApi(picList.get(6)));
                            break;
                        case 7:
                            apprvehi.setVehiclePic8(getImagesFromMtkApi(picList.get(7)));
                            break;
                        case 8:
                            apprvehi.setVehiclePic9(getImagesFromMtkApi(picList.get(8)));
                            break;

                    }
                }

            }
        }

        return apprvehi;
    }

    private String getImagesFromMtkApi(String imageUrl) {
        Mono<String> mono;
        try {
            mono = downloadImageAsMultipartFile(imageUrl);
        } catch (AppraisalException e) {
            return "";
        }

        try {
            String result = mono.block();
            if (result != null) {
                return result;
            }
        } catch (HttpClientErrorException e) {
            return null;
        }

        return "";
    }


    public Mono<String> downloadImageAsMultipartFile(String imageUrl) throws AppraisalException {
        WebClient webClient = WebClient.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(10 * 1024 * 1024)) // Set the buffer size to 10 MB
                .build();

        return webClient.get()
                .uri(imageUrl)
                .accept(MediaType.IMAGE_JPEG, MediaType.IMAGE_PNG)
                .retrieve()
                .onStatus(HttpStatus::isError, response -> {
                    if (response.statusCode().is4xxClientError()) {
                        return Mono.just(new HttpClientErrorException(response.statusCode()));
                    }
                    return Mono.just(new AppraisalException(""));
                })
                .bodyToMono(byte[].class)
                .flatMap(imageData -> {
                    try {
                        if (imageData == null) {
                            return Mono.empty();
                        }

                        String filename = UUID.randomUUID().toString() + ".jpg";
                        File tempFile = File.createTempFile("tempFile", ".tmp");
                        FileOutputStream fos = new FileOutputStream(tempFile);
                        fos.write(imageData);
                        uploadFileInBucket(tempFile, filename);
/*                        Path filePath = Paths.get(imageFolderPath + filename);
                        Files.write(filePath, imageData);*/


                        return Mono.just(filename);
                    } catch (IOException e) {
                        return Mono.error(e);
                    }
                });
    }


    private ApprCreaPage setColors(ApprCreaPage apprvehi, String extColor, String intColor) {
        if (null != extColor) {
            List<Long> extColor1 = configCodesRepo.getCodeForExtColor(extColor.toUpperCase());
            if (null != extColor1 && !extColor1.isEmpty()) {
                apprvehi.setVehicleExtColor(extColor1.get(0));
            }
        }
        if (null != intColor) {
            List<Long> intColor1 = configCodesRepo.getCodeForIntColor(intColor.toUpperCase());
            if (null != intColor1 && !intColor1.isEmpty()) {
                apprvehi.setVehicleInterior(intColor1.get(0));
            }
        }

        return apprvehi;
    }

    public String uploadFileInBucket(File file, String fileName) {
        //object to AmazonS3
        ClientConfiguration config = new ClientConfiguration();
        config.setProtocol(Protocol.HTTPS);
        AmazonS3 s3 = new AmazonS3Client(new BasicAWSCredentials(accesskey, secret), config);
        S3ClientOptions options = new S3ClientOptions();
        options.setPathStyleAccess(true);
        s3.setS3ClientOptions(options);
        s3.setEndpoint(amazonS3Url);  //ECS IP Address
        log.info("Listing buckets");
        PutObjectRequest request = new PutObjectRequest("images", fileName, file);
        request.setCannedAcl(CannedAccessControlList.PublicRead);
        s3.putObject(request);
        return fileName;
    }


    @Transactional
    @Override
    public void getMarketCheckData() throws IOException, AppraisalException {
        List<String> cities = flCitiesRepo.getCityNames();
        for (String city : cities) {
            getInvAndDealer(city);
            log.info("{}", city);
        }
//        for(int i=0;i<cities.size();i++){
//            if(i==0){
//                getInvAndDealer(cities.get(i));
//                log.info("{}", cities.get(i));
//            }
//        }
    }

    public void getInvAndDealer(String city) throws AppraisalException, IOException {
        log.info("getInvAndDealer started");
        EInventoryVehicles creaPage = null;
        List<EInventoryVehicles> inventoryVehicles = new ArrayList<>();
        MktInventory mktInventory = null;
        List<MktInventory> mktInventoryList = new ArrayList<>();
        Map<Long, EMkDealerRegistration> mktChkDealers = new HashMap<>();

        //  WebClient webClient = WebClient.create();

        int start = 0;
        int rows = 50;

        mktInventory = fetchData(city, start, rows);
        if (null != mktInventory && null != mktInventory.getListings() && !mktInventory.getListings().isEmpty()) {
            log.info("num of inv:{}", mktInventory.getNoOfInv());
            mktInventoryList.add(mktInventory);
        }

        assert mktInventory != null;
        int numFound = mktInventory.getNoOfInv();
        int noOfTime;
        if (numFound % 50 == 0) {
            noOfTime = (numFound / 50);
        } else {
            noOfTime = (numFound / 50) + 1;
        }


        for (int j = 0; j < noOfTime; j++) {

            if (j > 0) {
                start += rows;
                mktInventory = fetchData(city, start, rows);
                if (null != mktInventory && null != mktInventory.getListings() && !mktInventory.getListings().isEmpty()) {
                    mktInventoryList.add(mktInventory);
                }

            }
        }

        if (!mktInventoryList.isEmpty()) {

            for (int i = 0; i < mktInventoryList.size(); i++) {

                List<Object> listings = mktInventoryList.get(i).getListings();
                for (int a = 0; a < listings.size(); a++) {

                    LinkedHashMap<?, ?> invInfo = (LinkedHashMap<?, ?>) listings.get(a);
                    LinkedHashMap<?, ?> build = (LinkedHashMap<?, ?>) invInfo.get("build");
                    LinkedHashMap<?, ?> media = (LinkedHashMap<?, ?>) invInfo.get("media");
                    LinkedHashMap<?, ?> dealer = (LinkedHashMap<?, ?>) invInfo.get("dealer");


                    creaPage = setBuildParam(invInfo, build, dealer);
                    if (null != media) {
                        List<String> picList = (List<String>) media.get("photo_links_cached");

                        creaPage = setMedia(picList, creaPage);
                    }
                    inventoryVehicles.add(creaPage);
                    mktChkDealers = settingDealer(dealer, mktChkDealers);

                }
            }
        }


        inventoryRepo.saveAll(inventoryVehicles);
        dealerRepo.saveAll(mktChkDealers.values());


    }

    private Map<Long, EMkDealerRegistration> settingDealer(LinkedHashMap<?, ?> dealer, Map<Long, EMkDealerRegistration> mktChkDealers) {
        log.info("settingDealer started");
        Long mkDealerId = (dealer.get(AppraisalConstants.ID) != null) ? ((Number) dealer.get(AppraisalConstants.ID)).longValue() : null;
        EMkDealerRegistration marketDealer = null;

        if (mkDealerId != null && !mktChkDealers.containsKey(mkDealerId)) {
            marketDealer = new EMkDealerRegistration();
            marketDealer.setMkDealerId(dealer.get(AppraisalConstants.ID).toString());
            marketDealer.setSellerName(null != dealer.get(AppraisalConstants.SELLER_NAME) ? dealer.get(AppraisalConstants.SELLER_NAME).toString() : null);
            marketDealer.setWebsite(null != dealer.get(AppraisalConstants.WEBSITE_URL) ? dealer.get(AppraisalConstants.WEBSITE_URL).toString() : null);
            marketDealer.setDealerType(null != dealer.get(AppraisalConstants.DEALER_TYPE) ? dealer.get(AppraisalConstants.DEALER_TYPE).toString() : null);
            marketDealer.setStreet(null != dealer.get(AppraisalConstants.STREET) ? dealer.get(AppraisalConstants.STREET).toString() : null);
            marketDealer.setCity(null != dealer.get(AppraisalConstants.CITY) ? dealer.get(AppraisalConstants.CITY).toString() : null);
            marketDealer.setState(null != dealer.get(AppraisalConstants.STATE) ? dealer.get(AppraisalConstants.STATE).toString() : null);
            marketDealer.setCountry(null != dealer.get(AppraisalConstants.COUNTRY) ? dealer.get(AppraisalConstants.COUNTRY).toString() : null);
            marketDealer.setZip(null != dealer.get(AppraisalConstants.ZIP) ? dealer.get(AppraisalConstants.ZIP).toString() : null);
            marketDealer.setLatitude(null != dealer.get(AppraisalConstants.LATITUDE) ? dealer.get(AppraisalConstants.LATITUDE).toString() : null);
            marketDealer.setLongitude(null != dealer.get(AppraisalConstants.LONGITUDE) ? dealer.get(AppraisalConstants.LONGITUDE).toString() : null);
            marketDealer.setPhone(null != dealer.get(AppraisalConstants.PHONE) ? dealer.get(AppraisalConstants.PHONE).toString() : null);
            marketDealer.setSellerEmail(null != dealer.get(AppraisalConstants.SELLER_EMAIL) ? dealer.get(AppraisalConstants.SELLER_EMAIL).toString() : null);

            mktChkDealers.put(mkDealerId, marketDealer);
        }

        return mktChkDealers;
    }

    public MktInventory fetchData(String city, int start, int rows) {
        log.info("fetchData started");
        WebClient webClient = WebClient.create();

        return webClient.get()
                .uri(marketCheckInvUrl + api_key + AppraisalConstants.AND + AppraisalConstants.FIRST_SEEN_RANGE + "20240507" + "-" + "20240508" +
                        AppraisalConstants.AND + AppraisalConstants.CITY + AppraisalConstants.EQUAL + city + AppraisalConstants.AND +
                        AppraisalConstants.CAR_TYPE + AppraisalConstants.EQUAL + AppraisalConstants.USED + AppraisalConstants.AND +
                        AppraisalConstants.START + AppraisalConstants.EQUAL + start + AppraisalConstants.AND + AppraisalConstants.ROWS + AppraisalConstants.EQUAL + rows)
                .retrieve()
                .bodyToFlux(DataBuffer.class)
                .reduce(DataBuffer::write)
                .map(buffer -> {
                    try (InputStream inputStream = buffer.asInputStream()) {
                        // Process the input stream as needed
                        return objectMapper.readValue(inputStream, MktInventory.class);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } finally {
                        DataBufferUtils.release(buffer);
                    }
                })
                .block();
    }



    @Override
    public void mkDlrInvDumpSch() {
            schedulerRepo.findByEvent(AppraisalConstants.MK_DLR_INV_DUMP_SCH);


    }


    @Transactional
    @Override
    public Response getMarketCheckDataToSaveDealers() throws IOException, AppraisalException {

        EMkScheduler findEvent = schedulerRepo.findByEvent(AppraisalConstants.MC_DEALER_DUMP_SCH);
        Response response=new Response();
        if(findEvent.getValid() == true){
            List<String> cities = flCitiesRepo.getCityNames();
        for (String city : cities) {
            saveMarketCheckDealer(city);
            log.info("{}", city);
        }
//            for(int i=0;i<cities.size();i++){
//                if(i==0){
//                    saveMrktChkDealerByCity(cities.get(i));
//                    log.info("{}", cities.get(i));
//                }
//            }
            response.setCode(HttpStatus.OK.value());
            response.setMessage("MC_DEALER_DUMP_SCH active state is in true, new dealers added in MktChck DB");
        }else{
            response.setCode(HttpStatus.OK.value());
            response.setMessage("MC_DEALER_DUMP_SCH active state is in false");
        }
        return response;
    }

    public void saveMrktChkDealerByCity(String city) throws WebClientResponseException {

        MktDealer mktDealer = null;

        WebClient webClient = WebClient.create();
        int start = 0;
        int rows = 50;

        int noOfTime = 1;

        for(int iteratePage=0;iteratePage<noOfTime;iteratePage++){

            mktDealer = webClient.get()
                    .uri(marketCheckDealerUrl + api_key + AppraisalConstants.AND + AppraisalConstants.CITY + AppraisalConstants.EQUAL + city + AppraisalConstants.AND + AppraisalConstants.START+AppraisalConstants.EQUAL+start+AppraisalConstants.AND+AppraisalConstants.ROWS+AppraisalConstants.EQUAL+rows)
                    .header(AppraisalConstants.HOST, host)
                    .retrieve()
                    .bodyToFlux(DataBuffer.class)
                    .reduce(DataBuffer::write)
                    .map(buffer -> {
                        try (InputStream inputStream = buffer.asInputStream()) {
                            return objectMapper.readValue(inputStream, MktDealer.class);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        } finally {
                            DataBufferUtils.release(buffer);
                        }
                    })
                    .block();

            int numFound = mktDealer.getNoOfDealers();
            if (numFound % 50 == 0) {
                noOfTime = (numFound / 50);
            } else {
                noOfTime = (numFound / 50) + 1;
            }

            if (null != mktDealer && !mktDealer.getDealers().isEmpty()) {

                List<EMkDealerRegistration> dealerList = new ArrayList<>();
                int size = mktDealer.getDealers().size();

                for (int i = 0; i < size; i++) {

                    LinkedHashMap<?, ?> dealerInfo = (LinkedHashMap<?, ?>) mktDealer.getDealers().get(i);
                    EMkDealerRegistration dealerReg = new EMkDealerRegistration();

                    Long dealerByMktId = dealerRepo.findDealerByMktId(dealerInfo.get(AppraisalConstants.ID).toString());

                    if (null == dealerByMktId) {
                        dealerReg.setMkDealerId(dealerInfo.get(AppraisalConstants.ID).toString());
                        dealerReg.setSellerName(null != dealerInfo.get(AppraisalConstants.SELLER_NAME) ? dealerInfo.get(AppraisalConstants.SELLER_NAME).toString() : null);
                        dealerReg.setWebsite(null != dealerInfo.get(AppraisalConstants.WEBSITE_URL) ? dealerInfo.get(AppraisalConstants.WEBSITE_URL).toString() : null);
                        dealerReg.setDealerType(null != dealerInfo.get(AppraisalConstants.DEALER_TYPE) ? dealerInfo.get(AppraisalConstants.DEALER_TYPE).toString() : null);
                        dealerReg.setStreet(null != dealerInfo.get(AppraisalConstants.STREET) ? dealerInfo.get(AppraisalConstants.STREET).toString() : null);
                        dealerReg.setCity(null != dealerInfo.get(AppraisalConstants.CITY) ? dealerInfo.get(AppraisalConstants.CITY).toString() : null);
                        dealerReg.setState(null != dealerInfo.get(AppraisalConstants.STATE) ? dealerInfo.get(AppraisalConstants.STATE).toString() : null);
                        dealerReg.setCountry(null != dealerInfo.get(AppraisalConstants.COUNTRY) ? dealerInfo.get(AppraisalConstants.COUNTRY).toString() : null);
                        dealerReg.setZip(null != dealerInfo.get(AppraisalConstants.ZIP) ? dealerInfo.get(AppraisalConstants.ZIP).toString() : null);
                        dealerReg.setLatitude(null != dealerInfo.get(AppraisalConstants.LATITUDE) ? dealerInfo.get(AppraisalConstants.LATITUDE).toString() : null);
                        dealerReg.setLongitude(null != dealerInfo.get(AppraisalConstants.LONGITUDE) ? dealerInfo.get(AppraisalConstants.LONGITUDE).toString() : null);
                        dealerReg.setPhone(null != dealerInfo.get(AppraisalConstants.PHONE) ? dealerInfo.get(AppraisalConstants.PHONE).toString() : null);
                        dealerReg.setSellerEmail(null != dealerInfo.get(AppraisalConstants.SELLER_EMAIL) ? dealerInfo.get(AppraisalConstants.SELLER_EMAIL).toString() : null);
                        dealerList.add(dealerReg);
                    }
                }

                dealerRepo.saveAll(dealerList);
            } else {
                log.info("{} :having no data", city);
            }
        }


    }







}
