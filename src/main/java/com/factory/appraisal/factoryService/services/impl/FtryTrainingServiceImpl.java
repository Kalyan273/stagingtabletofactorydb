package com.factory.appraisal.factoryService.services.impl;

import com.factory.appraisal.factoryService.ExceptionHandle.AppraisalException;
import com.factory.appraisal.factoryService.ExceptionHandle.GlobalException;
import com.factory.appraisal.factoryService.ExceptionHandle.Response;
import com.factory.appraisal.factoryService.dto.FtryTraining;
import com.factory.appraisal.factoryService.persistence.mapper.AppraisalVehicleMapper;
import com.factory.appraisal.factoryService.persistence.model.EAppraiseVehicle;
import com.factory.appraisal.factoryService.persistence.model.EFtryTraining;
import com.factory.appraisal.factoryService.persistence.model.ERoleMapping;
import com.factory.appraisal.factoryService.persistence.model.EUserRegistration;
import com.factory.appraisal.factoryService.repository.FtryTrainingRepo;
import com.factory.appraisal.factoryService.repository.RoleMappingRepo;
import com.factory.appraisal.factoryService.repository.UserRegistrationRepo;
import com.factory.appraisal.factoryService.services.AppraiseVehicleService;
import com.factory.appraisal.factoryService.services.FtryTrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FtryTrainingServiceImpl implements FtryTrainingService {

    @Value("${video_folder_path}")
    private String videoFolderPath;

    @Autowired
    private AppraiseVehicleService appraiseVehicleService;
    @Autowired
    private RoleMappingRepo roleRepo;

    @Autowired
    private FtryTrainingRepo ftryTrainingRepo;
    @Autowired
    private AppraisalVehicleMapper appraisalVehicleMapper;
    @Value("${file_size}")
    private Long fileSize;

    @Override
    public Response trainingUpload(FtryTraining ftryTraining,  UUID userId) throws AppraisalException, IOException {

        Response response=new Response();
        ERoleMapping userById = roleRepo.findByUserId(userId);
        if (null!=userById && userById.getRole().getRoleGroup().equals("FA")) {


            EFtryTraining eFtryTraining = appraisalVehicleMapper.ftryTrainToEFtryTrain(ftryTraining);

            eFtryTraining.setTitle(ftryTraining.getTitle());
            eFtryTraining.setDescription(ftryTraining.getDescription());
            eFtryTraining.setVideo(ftryTraining.getVideo());
            eFtryTraining.setUser(userById.getUser());

            ftryTrainingRepo.save(eFtryTraining);

            response.setStatus(Boolean.TRUE);
            response.setMessage("Training video uploaded successfully ");
            response.setCode(HttpStatus.OK.value());


        }else throw new AppraisalException("Role not found");
        return response;
    }

    @Override
    public List<FtryTraining> trainingDownload() throws IOException {
        List<EFtryTraining> all = ftryTrainingRepo.findAllByValid();
        List<FtryTraining> ftryTrainings = appraisalVehicleMapper.eFtryTrainToFtryTrain(all);

        return ftryTrainings;

    }

    @Override
    public Response deleteTrainVideo(UUID userId, Long factTrainId) throws AppraisalException {

        Response response=new Response();
        ERoleMapping userById = roleRepo.findByUserId(userId);
        EFtryTraining trainingId = ftryTrainingRepo.findById(factTrainId).orElse(null);
        if (null!=userById && userById.getRole().getRole().equals("A1")) {
            if(null !=trainingId && trainingId.getValid() ){
                trainingId.setValid(false);
                ftryTrainingRepo.save(trainingId);
                response.setMessage("Deleted Successfully");
                response.setCode(HttpStatus.OK.value());
                response.setStatus(true);
            }else
                throw new AppraisalException("Did not find factoryTrainingId of  - " + factTrainId);
        }else
            throw new AppraisalException("Only Admin can delete videos  - " + userId);

        return response;
    }


}
