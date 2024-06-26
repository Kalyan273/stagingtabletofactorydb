package com.factory.appraisal.factoryService.services.impl;


import com.factory.appraisal.factoryService.ExceptionHandle.AppraisalException;
import com.factory.appraisal.factoryService.ExceptionHandle.GlobalException;
import com.factory.appraisal.factoryService.ExceptionHandle.Response;
import com.factory.appraisal.factoryService.constants.AppraisalConstants;
import com.factory.appraisal.factoryService.dto.UserRegistration;
import com.factory.appraisal.factoryService.persistence.model.*;
import com.factory.appraisal.factoryService.repository.*;
import com.factory.appraisal.factoryService.services.EmailService;
import com.factory.appraisal.factoryService.services.FactoryPdfGenerator;
import com.factory.appraisal.factoryService.util.CompareUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import net.sf.jasperreports.engine.JRException;
import org.jdom2.JDOMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@Service
public class EmailServiceImpl implements EmailService {



    Logger log = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private UserRegistrationRepo userRegistrationRepo;

    @Autowired
    private AppraiseVehicleRepo appraiseVehicleRepo;

    @Autowired
    private OffersRepo offersRepo;

    @Autowired
    private FileStatusRepo fileRepo;
    @Autowired
    private JavaMailSender sender;
    @Autowired
    private FactoryPdfGenerator pdfGenerator;

    @Autowired
    private RoleMappingRepo roleMappingRepo;

    @Autowired
    private Configuration config;
    @Autowired
    private CompareUtils comUtl;
    @Value("${saved_pdf_Path}")
    private String pdfpath;
    @Value("${image_folder_path}")
    private String imageFolderPath;


    @Value("${spring.mail.username}")
    private String fromMail;



    @Override
    public Response sendCreationEmail(UUID userId) throws AppraisalException, TemplateException, IOException, MessagingException {

        Logger log = LoggerFactory.getLogger(EmailServiceImpl.class);
        Response response = new Response();
        MimeMessage message = sender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, StandardCharsets.UTF_8.name());
            EUserRegistration userById = userRegistrationRepo.findUserById(userId);

            if(null!=userById) {
                EAppraiseVehicle apprVehByUserId = appraiseVehicleRepo.getAppraisalDetails(userId);
                EApprTestDrSts apprTestDrSts = apprVehByUserId.getTdStatus();

                String email = userById.getEmail();


                String name = comUtl.checkDbVariable(apprVehByUserId.getClientFirstName()) + " " + comUtl.checkDbVariable(apprVehByUserId.getClientLastName());
                String vin = comUtl.checkDbVariable(apprVehByUserId.getVinNumber());
                String vehYr = comUtl.checkDbVariable(apprVehByUserId.getVehicleYear().toString());
                String vehSer = comUtl.checkDbVariable(apprVehByUserId.getVehicleSeries());
                String vehMod = comUtl.checkDbVariable(apprVehByUserId.getVehicleModel());
                Long vehMil = comUtl.checkDbVariable(apprVehByUserId.getVehicleMileage());
                String vehMake = comUtl.checkDbVariable(apprVehByUserId.getVehicleMake());
                Integer appVal = comUtl.checkDbVariable(apprVehByUserId.getAppraisedValue()).intValue();
                String frDrSdDgSts = comUtl.checkDbVariable(apprTestDrSts.getFrDrSideDmgSts());
                String rrDrSdDgSts = comUtl.checkDbVariable(apprTestDrSts.getRearDrSideDmgSts());
                String rrPsSdDgSts = comUtl.checkDbVariable(apprTestDrSts.getRearPassenSideDmgSts());
                String frPsSdDgSts = comUtl.checkDbVariable(apprTestDrSts.getFrPassenSideDmgSts());
                String frDrSdPaWkSts = comUtl.checkDbVariable(apprTestDrSts.getFrDrSidePntWrkSts());
                String rrDrSdPaWkSts = comUtl.checkDbVariable(apprTestDrSts.getRearDrSidePntWrk());
                String frPsSdPaWkSts = comUtl.checkDbVariable(apprTestDrSts.getFrPassenSidePntWrk());
                String rrPsSdPaWkSts = comUtl.checkDbVariable(apprTestDrSts.getRearPassenSidePntWrk());


                Template t = config.getTemplate("email-template.ftl");

                Map<String, Object> model1 = new HashMap<>();
                model1.put(AppraisalConstants.NAME, name);
                model1.put(AppraisalConstants.VIN, vin);
                model1.put(AppraisalConstants.VEHICLE_YEAR, vehYr);
                model1.put(AppraisalConstants.VEHICLE_SERIES, vehSer);
                model1.put(AppraisalConstants.VEHICLE_MODEL, vehMod);
                model1.put(AppraisalConstants.VEHICLE_MILEAGE, vehMil);
                model1.put(AppraisalConstants.VEHICLE_MAKE, vehMake);
                model1.put(AppraisalConstants.APPRAISAL_VALUE, appVal);
                model1.put(AppraisalConstants.FRONT_DRIVER_SIDE_DAMAGE_STATUS, frDrSdDgSts);
                model1.put(AppraisalConstants.REAR_DRIVER_SIDE_DAMAGE_STATUS, rrDrSdDgSts);
                model1.put(AppraisalConstants.REAR_PASSENGER_SIDE_DAMAGE_STATUS, rrPsSdDgSts);
                model1.put(AppraisalConstants.FRONT_PASSENGER_SIDE_DAMAGE_STATUS, frPsSdDgSts);
                model1.put(AppraisalConstants.FRONT_DRIVER_SIDE_PAINTWORK_STATUS, frDrSdPaWkSts);
                model1.put(AppraisalConstants.REAR_DRIVER_SIDE_PAINTWORK_STATUS, rrDrSdPaWkSts);
                model1.put(AppraisalConstants.FRONT_PASSENGER_SIDE_PAINTWORK_STATUS, frPsSdPaWkSts);
                model1.put(AppraisalConstants.REAR_PASSENGER_SIDE_PAINTWORK_STATUS, rrPsSdPaWkSts);

                String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model1);

                helper.setTo(email);
                helper.setText(html, true);
                helper.setSubject(AppraisalConstants.SUBJECT);
                sender.send(message);
                response.setCode((HttpStatus.OK.value()));
                response.setMessage("mail send to : " + email);
                response.setStatus(Boolean.TRUE);
                log.info("mail send to : {}" , email);

            }else throw new AppraisalException("user not found with userId: "+userId);



        return response;
    }
    @Override
    public Response offerUpdateEmail(Long offerId) throws AppraisalException, MessagingException, TemplateException, IOException {

        Logger log = LoggerFactory.getLogger(EmailServiceImpl.class);
        Response response = new Response();
        MimeMessage message = sender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, StandardCharsets.UTF_8.name());


            EOffers offerById = offersRepo.findById(offerId).orElse(null);
            if(null!=offerById) {


                String statusCode = offerById.getStatus().getStatusCode();

                EUserRegistration userById = userRegistrationRepo.findUserById(offerById.getBuyerUserId().getId());
                String email = userById.getEmail();

                EAppraiseVehicle apprVeh = appraiseVehicleRepo.getAppraisalDetails(offerById.getSellerUserId().getId());



                String message1 = "";
                String price = "";
                if (statusCode.equals(AppraisalConstants.BUYERACCEPTED)) {
                    message1 = "Your offer has been accepted successfully. ";
                    price = offerById.getPrice().toString();
                } else if (statusCode.equals(AppraisalConstants.SELLERREJECTED)) {
                    message1 = "Your offer has been rejected by the seller. ";
                    price = offerById.getPrice().toString();
                } else if (statusCode.equals(AppraisalConstants.BUYERREJECTED)) {
                    message1 = "Your offer has been rejected . ";
                    price = offerById.getPrice().toString();
                } else if (statusCode.equals(AppraisalConstants.SELLERACCEPTED)) {
                    message1 = "Your offer has been accepted successfully by the seller. ";
                    price = offerById.getPrice().toString();
                }


                String name = comUtl.checkDbVariable(apprVeh.getClientFirstName()) + " " + comUtl.checkDbVariable(apprVeh.getClientLastName());
                String vin = comUtl.checkDbVariable(apprVeh.getVinNumber());

                Template t = config.getTemplate("offerEmail.ftl");

                Map<String, Object> model1 = new HashMap<>();
                model1.put(AppraisalConstants.SELLERNAME, name);
                model1.put(AppraisalConstants.VIN, vin);
                model1.put(AppraisalConstants.MESSAGE, message1);
                model1.put(AppraisalConstants.VALUE, price);

                String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model1);

                helper.setTo(email);
                helper.setText(html, true);
                helper.setSubject(AppraisalConstants.SUBJECT);

                sender.send(message);
                response.setCode((HttpStatus.OK.value()));
                response.setMessage("mail send to : " + email);
                response.setStatus(Boolean.TRUE);
                log.info("mail send to : {}" ,email);
            } else throw new AppraisalException("invalid offer id");



        return response;
    }

    @Override
    public void sendMailWithAttachment(Long offerId) throws MessagingException, IOException, AppraisalException, JRException, JDOMException, GlobalException {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);

        EOffers offerById = offersRepo.findById(offerId).orElse(null);
        List<EFileStatus> fileData = fileRepo.findByAppId(offerId);
        if( fileData.size()==0) {
            Response response = pdfGenerator.pdfTable(offerById.getId());

        }

        List<EFileStatus> fileDataAfterCreate = fileRepo.findByAppId(offerId);
        if(null != fileDataAfterCreate) {

            EUserRegistration userById = userRegistrationRepo.findUserById(offerById.getSellerUserId().getId());

            String email = userById.getEmail();
            helper.setTo(email);
            helper.setSubject("PDF attachments of Factory key Assure");
            helper.setText("the purchased vehicle details");

            for(int i=0; i<fileDataAfterCreate.size();i++){

                String pdf = comUtl.checkDbVariable(fileDataAfterCreate.get(i).getFileName());
                String attachmentPath=pdfpath+pdf;

                Path path = Paths.get(attachmentPath);
                byte[] attachmentBytes = Files.readAllBytes(path);
                ByteArrayResource attachment = new ByteArrayResource(attachmentBytes);

                helper.addAttachment(path.getFileName().toString(), attachment);
            }
            sender.send(message);

        }
    }

    @Override
    public void sendToUser(UserRegistration userReg) throws MessagingException, IOException, TemplateException {
        log.info("This method is used to send mail to factory user after user creation");
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);

        List<ERoleMapping> factoryAdmin = roleMappingRepo.findByRole();


        Template t = config.getTemplate("usercreation.ftl");





        Map<String, Object> model1 = new HashMap<>();
        model1.put(AppraisalConstants.NAME,userReg.getFirstName()+" "+userReg.getLastName());
        model1.put(AppraisalConstants.USERNAME,userReg.getUserName());
        model1.put(AppraisalConstants.STREETADR,userReg.getStreetAddress());

        String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model1);


        helper.setFrom(fromMail);
        helper.setTo( userReg.getEmail());
        helper.setText(html, true);

        helper.setSubject(AppraisalConstants.USERCREATON);
        sender.send(message);
        log.info("mail send to : {}" , userReg.getEmail());


    }


}

