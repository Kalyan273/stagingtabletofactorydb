package com.factory.appraisal.factoryService.controller;

import com.factory.appraisal.factoryService.ExceptionHandle.AppraisalException;
import com.factory.appraisal.factoryService.ExceptionHandle.Response;
import com.factory.appraisal.factoryService.dto.Company;
import com.factory.appraisal.factoryService.services.CompanyDetailsService;
import com.factory.appraisal.factoryService.services.FilterSpecificationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/company")
@Api(tags = "Company registration", value = "Company Module")
public class CompDetailsController {
    @Autowired
    private CompanyDetailsService companyDetailsService;
    @Autowired
    private FilterSpecificationService filterSpecificationService;

    /**
     * This method creates new company details
     * @param compDetails  This is model of AddCompany Details page coming from ui
     * @return Response class
     */

    @ApiOperation(value = "Add Company details in database")
    @PostMapping("/addCompDetails")
    public ResponseEntity<Response> addCompanyDetails(@RequestBody Company compDetails){
        String message=companyDetailsService.addCompany(compDetails);
        Response response= new Response();
        response.setCode(HttpStatus.OK.value());
        response.setMessage(message);
        response.setStatus(true);
        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }

    /**
     * This method updates the Company Details
     * @param compDetails This is model of compDetails page coming from ui
     * @param compId This is  company id coming in header from ui
     * @return
     */
    @ApiOperation(value = "update Company details by compId ", response = Response.class)
    @PostMapping("/updateCompDetails")
    public ResponseEntity<Response> updateCompanyDtls(@RequestBody Company compDetails, @RequestHeader("id") Long compId) throws AppraisalException {


                Response response = companyDetailsService.updateCompanyDetails(compDetails, compId);
                return new ResponseEntity<>(response, HttpStatus.OK);


    }

    @PostMapping("/searchCompany")
    public ResponseEntity<List<Company>> searchCompanies(@RequestParam ("companyName") String name){
        return new ResponseEntity<>(filterSpecificationService.searchCompany(name),HttpStatus.OK);
    }


}
