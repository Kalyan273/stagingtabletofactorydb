package com.factory.appraisal.factoryService.services;

import com.factory.appraisal.factoryService.dto.ApprFormDto;
import com.factory.appraisal.factoryService.dto.ApprFormSelectManyDto;
import com.factory.appraisal.factoryService.persistence.model.EAppraiseVehicle;
import freemarker.template.TemplateException;
import net.sf.jasperreports.engine.JRException;
import org.jdom2.JDOMException;

import java.io.IOException;

public interface ApprFormService {

    public String apprFormPdf(ApprFormDto apprFormDto) throws IOException, JRException, JDOMException;

    public ApprFormDto setDataToPdf(Long apprRefId) throws IOException, TemplateException;

    public ApprFormSelectManyDto setSelectManyData(EAppraiseVehicle eAppraiseVehicles);
}
