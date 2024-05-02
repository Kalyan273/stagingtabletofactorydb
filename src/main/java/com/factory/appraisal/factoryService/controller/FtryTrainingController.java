package com.factory.appraisal.factoryService.controller;

import com.factory.appraisal.factoryService.ExceptionHandle.AppraisalException;
import com.factory.appraisal.factoryService.ExceptionHandle.Response;
import com.factory.appraisal.factoryService.dto.FtryTraining;
import com.factory.appraisal.factoryService.persistence.model.EFtryTraining;
import com.factory.appraisal.factoryService.responseHandler.ApiResponseHandler;
import com.factory.appraisal.factoryService.services.FtryTrainingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/trainingportal")
@Api(tags = "Factory Training", value = "Training Module")
public class FtryTrainingController {

    @Autowired
    private FtryTrainingService ftryTrainingService;


    Logger log = LoggerFactory.getLogger(FtryTrainingController.class);

    /**
     * This method saves the video in local folder and returns the file name
     * @return Response class
     */
    @ApiOperation(value = "Upload Video and Returns Video name", response = ApiResponseHandler.class)
    @PostMapping("/trainingUpload")
    public ResponseEntity<Response> trainingUpload(@RequestBody FtryTraining ftryTraining,  @RequestHeader("userId") UUID userId) throws AppraisalException, IOException {

            Response response = ftryTrainingService.trainingUpload(ftryTraining, userId);
            return new ResponseEntity<>(response,HttpStatus.OK);
    }



    @ApiOperation(value = "training download" , response = ApiResponseHandler.class)
    @PostMapping(value = "/trainingDownload")
    public ResponseEntity<List<FtryTraining>> trainingDownload() throws IOException {

        log.info("GETTING LIST OF TRAINING SECTIONS");
        List<FtryTraining> eFtryTrainings = ftryTrainingService.trainingDownload();
        return new ResponseEntity<>(eFtryTrainings, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete Video in Factory Training Portal")
    @PostMapping("/deleteTrainingSection")
    public ResponseEntity<Response> deleteTrainVideo(@RequestHeader("userId") UUID userId,@RequestParam Long factTrainId) throws AppraisalException {
        log.info("DELETING APPRAISAL");
        return new ResponseEntity<>(ftryTrainingService.deleteTrainVideo(userId,factTrainId), HttpStatus.OK);
    }

}
