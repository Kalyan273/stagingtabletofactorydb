package com.factory.appraisal.factoryService.controller;


import com.factory.appraisal.factoryService.ExceptionHandle.AppraisalException;
import com.factory.appraisal.factoryService.ExceptionHandle.Response;
import com.factory.appraisal.factoryService.dto.CardsPage;
import com.factory.appraisal.factoryService.services.InventoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.UUID;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/inventory")
@Api(tags = "Inventory vehicle", value = "Inventory Module")
public class InventoryVehicleController {


    @Autowired
    private InventoryService inventoryService;

    /**
     * This method get all inventory cards
     * @param userId receive from UI
     * @param pageNumber receive from UI
     * @param pageSize receive from UI
     * @return list of inventory cards
     */

    @ApiOperation(value = "get Inventory cards by user id ", response = Response.class)
    @PostMapping("/getInventoryCards")
    public ResponseEntity<CardsPage> getInventoryCards(@RequestHeader("userId") UUID userId, @RequestParam @Min(1) Integer pageNumber, @RequestParam @Min(1) Integer pageSize) throws AppraisalException {

        CardsPage apv = inventoryService.inventoryCards(userId,pageNumber,pageSize);

        return new ResponseEntity<CardsPage>(apv, HttpStatus.OK);

    }

    /**
     * This method return all the search factory cards
     * @param id receive from UI
     * @param pageNumber receive from UI
     * @param pageSize receive from UI
     * @return list of search factory vehicle
     */

    @ApiOperation(value = "get SearchFactory cards not by user id ", response = Response.class)
    @PostMapping("/getSearchFactory")
    public ResponseEntity<CardsPage> getSearchFactory(@RequestHeader("id") UUID id, @RequestParam @Min(1) Integer pageNumber,@RequestParam @Min(1) Integer pageSize) throws AppraisalException {

        CardsPage sf = inventoryService.searchFactory(id,pageNumber,pageSize);

        return new ResponseEntity<CardsPage>(sf, HttpStatus.OK);

    }
    /**
     * This method will make vehicle as hold
     * @param apprRef receive fromui
     * @return response
     */
    @ApiOperation(value = "making vehicle as on hold",response=Response.class)
    @PostMapping("/holdvehicle")
    public ResponseEntity<Response> makeStatusAsHold(@RequestHeader("apprRef") Long apprRef) throws AppraisalException {
        Response response = inventoryService.holdAppraisal(apprRef);
        return new ResponseEntity<Response>(response,HttpStatus.OK);
    }
    /**
     * This method will make vehicle as unhold
     * @param apprRef receive fromui
     * @return response
     */

    @ApiOperation(value="unholding the vehicle",response = Response.class)
    @PostMapping("/unholdvehicle")
    public ResponseEntity<Response> unholdAppraisal(@RequestHeader("apprRef") Long apprRef) throws AppraisalException {
        Response response = inventoryService.UnHoldAppraisal(apprRef);
        return new ResponseEntity<Response>(response,HttpStatus.OK);
    }

    /**
     * This method will make vehicle sold retail on
     * @param apprRef receive fromui
     * @return response
     */
    @ApiOperation(value = "making vehicle sold retail on",response=Response.class)
    @PostMapping("/soldRetailOn")
    public ResponseEntity<Response> makeStatusSoldRetailOn(@RequestHeader("apprRef") Long apprRef) throws AppraisalException {
        Response response = inventoryService.makeSoldRetailOn(apprRef);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    /**
     * This method will make vehicle sold retail off
     * @param apprRef receive fromui
     * @return response
     */

    @ApiOperation(value="making vehicle sold retail off",response = Response.class)
    @PostMapping("/soldRetailOff")
    public ResponseEntity<Response> makeStatusSoldRetailOff(@RequestHeader("apprRef") Long apprRef) throws AppraisalException {
        Response response = inventoryService.makeSoldRetailOff(apprRef);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }


    /**
     * This method will make vehicle sold wholesale on
     * @param apprRef receive fromui
     * @return response
     */
    @ApiOperation(value = "making vehicle sold wholesale on",response=Response.class)
    @PostMapping("/soldWholesaleOn")
    public ResponseEntity<Response> makeStatusSoldWholesaleOn(@RequestHeader("apprRef") Long apprRef) throws AppraisalException {
        Response response = inventoryService.makeSoldWholesaleOn(apprRef);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    /**
     * This method will make vehicle sold wholesale off
     * @param apprRef receive fromui
     * @return response
     */

    @ApiOperation(value="making vehicle sold wholesale off",response = Response.class)
    @PostMapping("/soldWholesaleOff")
    public ResponseEntity<Response> makeStatusSoldWholesaleOff(@RequestHeader("apprRef") Long apprRef) throws AppraisalException {
        Response response = inventoryService.makeSoldWholesaleOff(apprRef);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }



}
