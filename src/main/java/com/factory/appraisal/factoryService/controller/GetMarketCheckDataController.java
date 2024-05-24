package com.factory.appraisal.factoryService.controller;

import com.factory.appraisal.factoryService.ExceptionHandle.AppraisalException;
import com.factory.appraisal.factoryService.ExceptionHandle.Response;
import com.factory.appraisal.factoryService.constants.AppraisalConstants;
import com.factory.appraisal.factoryService.services.MarketCheckApiServiceDump;
import freemarker.template.TemplateException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.jasperreports.engine.JRException;
import org.jdom2.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/appraisal")
@Api(tags = "MarketCheckDataController", value = "MarketCheck Info.")
public class GetMarketCheckDataController {

    @Autowired
    private MarketCheckApiServiceDump service;

    @ApiOperation(value = "This method is used to save cities in DB from a file")
    @PostMapping("/saveCitiesInDB")
    public ResponseEntity<Response> saveCitiesInDB() throws IOException {

        service.saveCitiesInFl();
        Response response=new Response();
        response.setMessage("data added successfully");
        response.setCode(HttpStatus.OK.value());
        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }


    @ApiOperation(value="save marketCheck dealers into our mktchk DB",response = Response.class)
    @PostMapping("/saveDealersFromMktCheck")
    public ResponseEntity<Response> saveDealersFromMktCheck() throws AppraisalException, IOException {
        return new ResponseEntity<>(service.getMarketCheckDataToSaveDealers(),HttpStatus.OK);
    }


    @PostMapping("/addInvFrmMktForFacMem")
    public ResponseEntity<Response> addInvFrmMktForFacMem() throws AppraisalException, IOException{
        Response response = service.mkFacDlrInvDumpfrMem();
        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }

    @PostMapping("/addInvFrmMktForNonFacMem")
    public ResponseEntity<Response> addInvFrmMktForNonFacMem() throws AppraisalException, IOException{
        Response response = service.mkFacDlrInvDumpfrNonMem();

        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }

    @PostMapping("/mktInvToAppr")
    public ResponseEntity<Response> mktInvToAppr() throws AppraisalException, IOException, JRException, JDOMException {
      service.storeDataFromMkInventoryToAppr();

        return new ResponseEntity<>(null,HttpStatus.ACCEPTED);
    }


}
