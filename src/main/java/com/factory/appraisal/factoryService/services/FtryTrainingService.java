package com.factory.appraisal.factoryService.services;

import com.factory.appraisal.factoryService.ExceptionHandle.AppraisalException;
import com.factory.appraisal.factoryService.ExceptionHandle.GlobalException;
import com.factory.appraisal.factoryService.ExceptionHandle.Response;
import com.factory.appraisal.factoryService.dto.FtryTraining;
import com.factory.appraisal.factoryService.dto.VideoAndImageResponse;
import com.factory.appraisal.factoryService.persistence.model.EFtryTraining;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface FtryTrainingService {

    /**
     * This method uploads the training videos and description
     * @return
     */
    Response trainingUpload(FtryTraining ftryTraining,  UUID userId) throws AppraisalException, IOException;


    /**
     * This method downloads the training videos and description
     * @return
     */
    List<FtryTraining> trainingDownload() throws IOException;

    Response deleteTrainVideo(UUID userId,Long factTrainId) throws AppraisalException;


}
