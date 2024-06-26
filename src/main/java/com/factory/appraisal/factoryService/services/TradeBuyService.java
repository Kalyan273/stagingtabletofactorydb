
package com.factory.appraisal.factoryService.services;

import com.factory.appraisal.factoryService.ExceptionHandle.AppraisalException;
import com.factory.appraisal.factoryService.ExceptionHandle.OfferException;
import com.factory.appraisal.factoryService.ExceptionHandle.Response;
import com.factory.appraisal.factoryService.dto.CardsPage;
import com.factory.appraisal.factoryService.dto.OfferInfo;
import com.factory.appraisal.factoryService.dto.Offers;

import java.util.UUID;

public interface TradeBuyService {

    /**
     * This method used to send the Available Trade cards of which appraisal status is inventory & push for buy figure is enable of same id
     * @param id this is the userId or DealerId
     * @param pageNumber This is the page number
     * @param pageSize This is the number of cards per page
     * @return Available Trade card list and page information ie total records and  total pages
     * @author yudhister
     */
     CardsPage availableTradesCards(UUID id, Integer pageNumber, Integer pageSize) throws AppraisalException;

    /**
     *This method used to send the FactoryOffer cards of which appraisal status is inventory & push for buy figure is enable of different id
     * @param id this is the userId or DealerId
     * @param pageNumber This is the page number
     * @param pageSize This is the number of cards per page
     * @return FactoryOffer card list and page information ie total records and  total pages
     * @author yudhister
     */

     CardsPage factoryOffersCards(UUID id, Integer pageNumber, Integer pageSize) throws AppraisalException;

    /**
     * This method show the info after pressing quote button
     * @param offerId
     * @return OfferInfo
     */


    OfferInfo factoryOffersInfo(Long offerId) throws OfferException;

    /**
     * This method show the info after pressing quote button
     * @param offerId
     * @return
     */

    OfferInfo availableTradeInfo(Long offerId) throws OfferException;






}