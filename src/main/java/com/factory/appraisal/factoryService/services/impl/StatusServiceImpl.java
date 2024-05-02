package com.factory.appraisal.factoryService.services.impl;

import com.factory.appraisal.factoryService.ExceptionHandle.AppraisalException;
import com.factory.appraisal.factoryService.ExceptionHandle.Response;
import com.factory.appraisal.factoryService.dto.Status;
import com.factory.appraisal.factoryService.persistence.mapper.AppraisalVehicleMapper;
import com.factory.appraisal.factoryService.persistence.model.EStatus;
import com.factory.appraisal.factoryService.repository.StatusRepo;
import com.factory.appraisal.factoryService.services.StatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class StatusServiceImpl implements StatusService {
    Logger log= LoggerFactory.getLogger(ConfigCodesServiceImpl.class);

    @Autowired
    private StatusRepo statusRepo;
    @Autowired
    private AppraisalVehicleMapper mapper;

    @Override
    public String addStatus(List<Status> statuses) {

        List<EStatus> sts = mapper.lStatusToEStatus(statuses);


        statusRepo.saveAll(sts);
        return "saved successfully";
    }

    @Override
    public Response updateStatus(Status status, Long stsId) throws AppraisalException {
        log.info("Status Upadte method is Triggered **Service IMPL**");
        EStatus sts = statusRepo.findStsById(stsId);

            if (null!=sts) {
                sts = mapper.updateOfferStatus(status, sts);
                log.debug("object coming after update",sts);
                sts.setModifiedOn(new Date());
                statusRepo.save(sts);
            }else throw new AppraisalException("invalid statusId");

        Response response =new Response();
        response.setMessage("Updated Successfully");
        response.setCode(HttpStatus.OK.value());
        response.setStatus(true);
        return response;
    }




    @Override
    public Response deleteStatus(Long stsId) throws AppraisalException {
        EStatus sts = statusRepo.findStsById(stsId);
        Response response=new Response();

            if (null!= sts && sts.getValid()) {

                sts.setValid(false);
                statusRepo.save(sts);

                response.setMessage("Deleted Status Successfully");
                response.setCode(HttpStatus.OK.value());
                response.setStatus(true);

            } else
                throw new AppraisalException("Invalid Status Id");

        return response;
    }




}
