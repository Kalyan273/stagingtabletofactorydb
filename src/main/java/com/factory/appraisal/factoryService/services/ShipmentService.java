package com.factory.appraisal.factoryService.services;

import com.factory.appraisal.factoryService.ExceptionHandle.AppraisalException;
import com.factory.appraisal.factoryService.ExceptionHandle.GlobalException;
import com.factory.appraisal.factoryService.ExceptionHandle.Response;
import com.factory.appraisal.factoryService.dto.CardsPage;
import com.factory.appraisal.factoryService.dto.Shipment;

import java.io.IOException;
import java.util.UUID;

public interface ShipmentService {
    /**
     * This method returns all cards which are of my sell section
     * @param userId This is seller userId or seller dealerId
     * @param pageNumber
     * @param pageSize
     * @return CardsPage
     */
    CardsPage mySellsCards(UUID userId, Integer pageNumber, Integer pageSize) throws AppraisalException;

    /**
     * This method returns all cards which are of my buyer section
     * @param userId This is buyer userId or seller dealerId
     * @param pageNumber
     * @param pageSize
     * @return CardsPage
     */

    CardsPage myBuyerCards(UUID userId, Integer pageNumber, Integer pageSize) throws AppraisalException;


    Response buyerAgreedService(Shipment shipment,Long ship_id) throws GlobalException, IOException;


    Response sellerAgreedService(Shipment shipment,Long ship_id) throws GlobalException, AppraisalException, IOException;

}
