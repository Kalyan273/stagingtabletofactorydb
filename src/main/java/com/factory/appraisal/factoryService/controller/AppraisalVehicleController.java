package com.factory.appraisal.factoryService.controller;
//@author: Rupesh Khade,kalyan

import com.factory.appraisal.factoryService.ExceptionHandle.AppraisalException;
import com.factory.appraisal.factoryService.ExceptionHandle.Response;
import com.factory.appraisal.factoryService.constants.AppraisalConstants;
import com.factory.appraisal.factoryService.dto.*;
import com.factory.appraisal.factoryService.mktCheck.model.EInventoryVehicles;
import com.factory.appraisal.factoryService.persistence.model.EAppraiseVehicle;
import com.factory.appraisal.factoryService.repository.AppraiseVehicleRepo;
import com.factory.appraisal.factoryService.responseHandler.ApiResponseHandler;
import com.factory.appraisal.factoryService.services.*;
import com.factory.appraisal.factoryService.services.impl.AppraiseVehicleServiceImpl;
import freemarker.template.TemplateException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.jasperreports.engine.JRException;
import org.apache.commons.io.IOUtils;
import org.jdom2.JDOMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import javax.mail.MessagingException;
import javax.validation.Valid;
import javax.validation.constraints.Min;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.NoSuchFileException;
import java.util.List;
import java.util.UUID;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/appraisal")
@Api(tags = "Appraisal vehicle", value = "Appraisal Module")
public class AppraisalVehicleController {
    Logger log = LoggerFactory.getLogger(AppraisalVehicleController.class);
    @Value("${file_size}")
    private Long fileSize;
    @Value("${saved_pdf_Path}")
    private String pdfpath;
    @Autowired
    private FilterSpecificationService filterSpec;

    @Autowired
    private AppraiseVehicleService service;

    @Autowired
    private AppraiseVehicleServiceImpl service1;

    @Autowired
    private EmailService emailService;

    @Autowired
    public MarketCheckApiService marketCheckService;

    @Autowired
    private ApprFormService apprForm;
    @Autowired
    private AppraiseVehicleRepo repo;


    /**
     * This method creates new Appraisal of vehicles
     * @param apprCreaPage This is model of AppraisalCreation page coming from ui
     * @param userId       This  User id coming in header from ui
     * @return Response class
     */
    @ApiOperation(value = "Add Appraisal", response = Response.class)
    @PostMapping("/addAppraiseVehicle")
    public ResponseEntity<Response> addAppraiseVehicle(@RequestBody @Validated ApprCreaPage apprCreaPage, @RequestHeader("userId") UUID userId) throws AppraisalException {


            if ((null != apprCreaPage) && (null != userId)) {
                log.debug("Creating Appraisal {}", apprCreaPage);
                Response response  = service.addAppraiseVehicle(apprCreaPage, userId, AppraisalConstants.CREATED);
                log.info(response.getMessage());
                return new ResponseEntity<>(response, HttpStatus.ACCEPTED);

            } else throw new AppraisalException(AppraisalConstants.INALID_USER_ID);

    }

    @ApiOperation(value = "draft Appraisal", response = Response.class)
    @PostMapping("/draftApprVeh")
    public ResponseEntity<Response> draftApprVeh(@RequestBody ApprCreaPage apprCreaPage, @RequestHeader("userId") UUID userId) throws AppraisalException {


            if ((null != apprCreaPage&&null!=apprCreaPage.getVinNumber()) && (null != userId)) {
                Response response = service.addAppraiseVehicle(apprCreaPage, userId, AppraisalConstants.DRAFT);
                return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
            } else throw new AppraisalException("Invalid user id");



    }

    /**
     * This method updates the vehicle details
     * @param apprCreaPage This is model of AppraisalCreation page coming from ui
     * @param id           This is Appraisal ref id coming in header from ui
     * @return Response class
     */
    @ApiOperation(value = "update Appraisal by AppraisalRef id ", response = Response.class)
    @PostMapping("/updateAppraiseVehicle")
    public ResponseEntity<Response> updateAppraiseVehicle(@Validated @RequestBody ApprCreaPage apprCreaPage, @RequestHeader("id") Long id) throws AppraisalException, IOException, JRException, JDOMException {
        log.info("Updating Appraisal vehicle");

                Response response = service.updateAppraisal(apprCreaPage, id);
                log.debug("Object coming from ApprCreaPage for update {} ", apprCreaPage);
                return new ResponseEntity<>(response, HttpStatus.OK);

    }

    /**
     * This method updates the vehicle details
     * @param apprCreaPage This is model of AppraisalCreation page coming from ui
     * @param id           This is Appraisal ref id coming in header from ui
     * @return Response class
     */
    @ApiOperation(value = "update Draft Appraisal by AppraisalRef id ", response = Response.class)
    @PostMapping("/updateDraftAppraiseVehicle")
    public ResponseEntity<Response> updateDraftAppraiseVehicle( @RequestBody ApprCreaPage apprCreaPage, @RequestHeader("id") Long id) throws AppraisalException, IOException, JRException, JDOMException {
        log.info("Updating Appraisal vehicle");

                Response response = service.updateDraftAppraisal(apprCreaPage, id);
                log.debug("Object coming from ApprCreaPage for update {}", apprCreaPage);
                return new ResponseEntity<>(response, HttpStatus.OK);

    }



    /**
     * This method sends list of Appraisal vehicle cards to ui
     * @param userId   This  User id coming in header from ui
     * @param pageNo   This is page number given by ui
     * @param pageSize This is Number of records per page given by ui
     * @return CardsPage
     */
    @ApiOperation(value = "get Appraisals cards by user id ", response = Response.class)
    @PostMapping("/getAppraisalsCards")
    public ResponseEntity<CardsPage> getAppraisalsCards(@RequestHeader("userId") UUID userId, @RequestParam Integer pageNo, @RequestParam Integer pageSize) throws AppraisalException {
        log.info("GETTING APPRAISAL CARDS");
        CardsPage apv = service.findAllAppraisalCards(userId, pageNo, pageSize);
        return new ResponseEntity<>(apv, HttpStatus.OK);
    }

    /**
     * This method sends the image of a car base on image name
     * @param pic1 This is the name of image send by ui
     * @return byte[]
     */
    @ApiOperation(value = "get Image by image name ", response = VideoAndImageResponse.class)
    // @PostMapping("/getpic1")
    @GetMapping(value = "/getpic1",produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> downloadImageFromFileSystem(@RequestParam String pic1) throws IOException , NoSuchFileException {
        log.info("DOWNLOADING IMAGE FROM FILE");
        VideoAndImageResponse response = service.downloadImageFromFileSystem(pic1);

            return new ResponseEntity<>(response.getImageBytes(), HttpStatus.OK);
    }

    /**
     * This method saves the image coming from ui and returns the uuid.jpg that is file name
     * @param file MultipartFile data coming from ui
     * @return file name in Response class
     */
    @ApiOperation(value = "Upload Image and Returns image name", response = Response.class)
    @PostMapping("/uploadImage")
    public ResponseEntity<Response> uploadImage(@RequestBody MultipartFile file) throws AppraisalException, IOException {
        log.info("RUNNING THIS METHOD FOR UPLOADING THE IMAGE");

            if (null != file) {
                String map = service.imageUpload(file);
                Response response = new Response();
                response.setCode(HttpStatus.OK.value());
                response.setMessage(map);
                response.setStatus(true);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else throw new AppraisalException("image cann't be empty");


    }

    /**
     * This method sends the Appraisal Creation page to ui in Edit page
     * @param appraisalId This is Appraisal ref id coming in header from ui
     * @return Response class
     */
    @ApiOperation(value = "showing data to UI in editing page",response = ApprCreaPage.class)
    @PostMapping("/showToUi")
    public ResponseEntity<ApprCreaPage> showInEdit(@RequestHeader("AppraisalId") Long appraisalId) throws AppraisalException, JRException, IOException, JDOMException {
        log.info("Showing Appraisal to UI");
        ApprCreaPage page = service.showInEditPage(appraisalId);
        return new ResponseEntity<>(page, HttpStatus.OK);

    }

    /**
     * This method saves the video in local folder and returns the file name
     * @param file MultipartFile data coming from ui
     * @return Response class
     */
    @ApiOperation(value = "Upload Video and Returns Video name", response = ApiResponseHandler.class)
    @PostMapping("/uploadVideo")
    public ResponseEntity<Response> uploadVideo(@RequestBody MultipartFile file) throws AppraisalException, IOException {
        log.info("Multipart object for uploading video");
            if (null != file && file.getSize()<fileSize) {
                String map = service.videoUpload(file);
                Response response = new Response();
                response.setCode(HttpStatus.OK.value());
                response.setMessage(map);
                response.setStatus(true);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else throw new AppraisalException("video size must be 1MB to 100MB ");

    }

    /**
     * This method sends the video to ui
     * @param filename This is file name coming from ui
     * @return byte[]
     */
    @ApiOperation(value = "Download Video", response = VideoAndImageResponse.class)
    @GetMapping(value = "/downloadVideo")
    public ResponseEntity<byte[]> downloadVideo(@RequestParam("filename") String filename) throws IOException {

            log.info("This method is used to Download Video");
            VideoAndImageResponse response = service.videoDownload(filename);
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                headers.setContentDisposition(ContentDisposition.builder("inline").filename(filename).build());
                return new ResponseEntity<>(response.getVideoBytes(), headers, HttpStatus.OK);
    }

    /**
     * This method delete the Appraisal cards
     * @param apprRef This is Appraisal ref id coming in header from ui
     * @return Response class
     */
    @ApiOperation(value = "Delete Appraisal  by Appraisal Id")
    @PostMapping("/deleteAppraisal")
    public ResponseEntity<Response> deleteAppraisal(@RequestParam Long apprRef) throws AppraisalException {
        log.info("DELETING APPRAISAL");
        return new ResponseEntity<>(service.deleteAppraisalVehicle(apprRef), HttpStatus.OK);
    }


    /**
     * This method checks the given vin number and userId is available or not
     * @param userId
     * @param vin
     * @return
     */

    @ApiOperation(value = "Check Vehicle Available", response = Response.class)
    @PostMapping("/checkVehicleAvailable")
    public ResponseEntity<Response> checkVinAvailable(@RequestHeader("userId") UUID userId,@RequestParam String vin) throws AppraisalException {


            if ((null != vin) && (null != userId)) {

                Response response  = service.checkVinAvaliable(vin,userId);
                log.info(response.getMessage());
                return new ResponseEntity<>(response, HttpStatus.ACCEPTED);

            } else throw new AppraisalException("Invalid user id");



    }

    /**
     * This method sends details of vehicle using vinNumber
     * @param vin This is vin number of vehicle
     * @return MarketCheckData
     */
    @PostMapping("/getvehicleinfo")
    public ResponseEntity<MarketCheckData> vehicleInformation(@RequestParam("vin") @Valid @Min(17) String vin) throws AppraisalException, WebClientResponseException {
        log.info("THIS METHOD SHOWS VEHICLE INFO BY PROVIDING VIN NUMBER");
        MarketCheckData marketCheckData = marketCheckService.getMarketCheckData(vin.toUpperCase());
        return new ResponseEntity<>(marketCheckData, HttpStatus.OK);
    }


    /**
     * This method moves the appraised vehicles to inventory
     * @param apprRef This is appraisal reference id
     * @return Response
     */
    @ApiOperation(value = "Add to Inventory by Appraisal Id")
    @PostMapping("/moveToInventory")
    public ResponseEntity<Response> moveToInvntry(@RequestParam Long apprRef) throws AppraisalException {
        Response response = service.moveToInventory(apprRef);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * This method moves the appraised vehicles to inventory
     * @param apprRef This is appraisal reference id
     * @param userId  this is session userId
     * @return Response
     */
    @ApiOperation(value = "Adding vehicle as favorite")
    @PostMapping("/moveToWishList")
    public ResponseEntity<Response> movToWishList(@RequestParam Long apprRef, @RequestHeader("userId") Long userId) throws AppraisalException {
        Response response = service.moveToWishList(apprRef, userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    /**
     * This method will get all the favorite vehicles
     * @param userId session userId
     * @return CardsPage
     */
    @ApiOperation(value = "It shows All the cards who is wish listed as favorite")
    @PostMapping("/getFavoriteCards")
    public ResponseEntity<CardsPage> findAllFavoriteVehicle(@RequestHeader("userId") Long userId, @RequestParam @Min(1) Integer pageNumber, @RequestParam @Min(1) Integer pageSize) throws AppraisalException {
        CardsPage wishListed = service.findFavoriteVehicle(userId, pageNumber, pageSize);
        return new ResponseEntity<>(wishListed, HttpStatus.OK);
    }

    /**
     * This method moves the appraised vehicles to inventory
     * @param apprId This is appraisal reference id
     * @param userId session userId
     * @return Response
     */
    @ApiOperation(value = "It will remove the favorite vehicle")
    @PostMapping("/removeFavorite")
    public ResponseEntity<Response> removeFromFavoritesPage(@RequestParam Long apprId, @RequestHeader("userId") Long userId) throws AppraisalException {
        Response response = service.removeVehicleFromFavoritePage(apprId, userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * This method is used to send email to user after Appraisal creation
     * @param userId getting from ui
     * @return message
     */
    @PostMapping("/sendingEmail")
    public ResponseEntity<Response> sendingCreateEmail(@RequestHeader("userId") UUID userId) throws AppraisalException, TemplateException, MessagingException, IOException {
        Response response = emailService.sendCreationEmail(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * This method will show appraisal vehicles based on filer parameters
     * @param filter
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return
     */

    @PostMapping("/appraisalfilter")
    public ResponseEntity<CardsPage> appraisalFilter(@RequestBody FilterParameters filter, @RequestHeader("userId") UUID userId,@RequestParam Integer pageNo,@RequestParam Integer pageSize) throws AppraisalException {
        return new ResponseEntity<>(filterSpec.filterAppraisalVehicle(filter,userId,pageNo,pageSize),HttpStatus.OK);
    }


    /**
     * This method will show Inventory vehicles based on filer parameters
     * @param filter
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @PostMapping("/inventoryfilter")
    public ResponseEntity<CardsPage> inventryFilter(@RequestBody FilterParameters filter, @RequestHeader("userId") UUID userId,@RequestParam Integer pageNo,@RequestParam Integer pageSize) throws AppraisalException {
        return new ResponseEntity<>(filterSpec.filterInventoryVehicle(filter,userId,pageNo,pageSize),HttpStatus.OK);
    }

    /**
     * This method will show search factory vehicles based on filer parameters
     * @param filter
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @PostMapping("/searchfactoryfilter")
    public ResponseEntity<CardsPage> searchFacFilter(@RequestBody FilterParameters filter, @RequestHeader("userId") UUID userId,@RequestParam Integer pageNo,@RequestParam Integer pageSize) throws AppraisalException {
        return new ResponseEntity<>(filterSpec.filterSearchFactoryVehicle(filter,userId,pageNo,pageSize),HttpStatus.OK);
    }

    @GetMapping("/apprFormPdf")
    public ResponseEntity<Resource> vehRepPdf(@RequestParam("apprId") Long apprId) throws IOException, TemplateException, JRException, JDOMException {
        String  filePath = apprForm.apprFormPdf(apprForm.setDataToPdf(apprId));

        Resource resource = new PathResource(filePath);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename= AppraisalForm.pdf" );
        headers.setContentType(MediaType.APPLICATION_PDF);

        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }

    @GetMapping("/keyAssureDownload")
    public ResponseEntity<Resource> keyAssureVehReport(@RequestParam ("apprId") Long apprId) throws JRException, IOException, JDOMException {


        EAppraiseVehicle appraisalById = repo.getAppraisalById(apprId);


        String filePath = pdfpath+appraisalById.getTdStatus().getKeyAssureFiles();

        Resource resource = new PathResource(filePath);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename= keyAssureVehicleReport.pdf" );
        headers.setContentType(MediaType.APPLICATION_PDF);

        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }

    @GetMapping("/keyAssurePreview")
    public ResponseEntity<byte[]> servePdf(@RequestParam ("apprId") Long apprId) throws IOException {
        EAppraiseVehicle appraisalById = repo.getAppraisalById(apprId);

        File file = new File(pdfpath, appraisalById.getTdStatus().getKeyAssureFiles());

        if (file.exists()) {
            InputStream inputStream = new FileInputStream(file);
            byte[] pdfBytes = IOUtils.toByteArray(inputStream);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.parse("inline; filename=" + appraisalById.getTdStatus().getKeyAssureFiles()));
            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        }else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @ApiOperation(value = "Add MarketCheck", response = Response.class)
    @PostMapping("/oneAppraiseVehicle")
    public ResponseEntity<Response> oneAppraiseVehicle(@RequestBody EInventoryVehicles apprCreaPage, @RequestHeader("userId") UUID userId) throws AppraisalException {

        if ((null != apprCreaPage) && (null != userId)) {
            log.debug("Creating Appraisal {}", apprCreaPage);
            Response response  = service.oneAppraiseVehicle(apprCreaPage, userId);
            log.info(response.getMessage());
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);

        } else throw new AppraisalException(AppraisalConstants.INALID_USER_ID);

    }
    @PostMapping("/transferringFile")
    public String transferringFileToAmazonS3(@RequestHeader("filePath") String filePath) throws AppraisalException, IOException {

        if ((null != filePath)) {
            log.debug("FileName {}", filePath);
            String strings = service.transferFile(filePath);
            //    log.info(response.getMessage());
            return strings;

        } else throw new AppraisalException("File Not Found");

    }



}
