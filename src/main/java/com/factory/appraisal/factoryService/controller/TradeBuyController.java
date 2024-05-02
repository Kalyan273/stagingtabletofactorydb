package com.factory.appraisal.factoryService.controller;

import com.factory.appraisal.factoryService.ExceptionHandle.AppraisalException;
import com.factory.appraisal.factoryService.ExceptionHandle.OfferException;
import com.factory.appraisal.factoryService.ExceptionHandle.Response;
import com.factory.appraisal.factoryService.dto.CardsPage;
import com.factory.appraisal.factoryService.dto.OfferInfo;
import com.factory.appraisal.factoryService.services.TradeBuyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/tradeBuy")
@Api(tags = "Trade Buy Vehicles", value = "TradeBuy Module")
public class TradeBuyController {
    Logger log = LoggerFactory.getLogger(TradeBuyController.class);

    @Autowired
    private TradeBuyService tradeBuyService;


    /**
     * This method used to send the Available Trade cards of which appraisal status is inventory & push for buy figure is enable of same id
     * @param id this is the userId or DealerId
     * @param pageNumber This is the page number
     * @param pageSize This is the number of cards per page
     * @return Available Trade card list and page information ie total records and  total pages
     */
    @ApiOperation(value = "get created cards of someone userid which are not moved to inventory and pushForBuyFig is on", response = Response.class)
    @PostMapping("/getAvailableTradeCards")
    public ResponseEntity<CardsPage> availableTradeCards(@RequestHeader("id") UUID id, @RequestParam Integer pageNumber, @RequestParam Integer pageSize) throws AppraisalException {
        log.info("GETTING TRADEBUY CARDS");
        CardsPage apv = tradeBuyService.availableTradesCards(id,pageNumber,pageSize);
        return new ResponseEntity<CardsPage>(apv, HttpStatus.OK);
    }

    /**
     * This method used to send the FactoryOffer cards of which appraisal status is inventory & push for buy figure is enable of different id
     * @param id this is the userId or DealerId you can give any id
     * @param pageNumber This is the page number
     * @param pageSize This is the number of cards per page
     * @return FactoryOffer card list and page information ie total records and  total pages
     */
    @ApiOperation(value = "get created cards of same appraisal vehicles which are not moved to inventory and makeOffer is done ", response = Response.class)
    @PostMapping("/getFactoryOffersCards")
    public ResponseEntity<CardsPage> factoryOffersCards(@RequestHeader("id") UUID id,@RequestParam Integer pageNumber,@RequestParam Integer pageSize) throws AppraisalException {
        log.info("GETTING FACTORY OFFER CARDS");
        CardsPage apv = tradeBuyService.factoryOffersCards(id,pageNumber,pageSize);
        return new ResponseEntity<CardsPage>(apv, HttpStatus.OK);
    }

    /**
     * This method show factory offer cards
     * @param offerId receive from UI
     * @return list of  factory offer cards
     */

    @ApiOperation(value = "update Offer by offer id ", response = Response.class)
    @PostMapping("/showFactoryOffersCardInfo")
    public ResponseEntity<OfferInfo> factoryOffersCardInfo(@RequestHeader("offerId") Long offerId) throws OfferException {
        log.info("Show Card in Liquidation");

            if (null != offerId) {
                OfferInfo offerInfo = tradeBuyService.factoryOffersInfo(offerId);
                return new ResponseEntity<>(offerInfo, HttpStatus.OK);
            }

            OfferInfo offerInfo= new OfferInfo();
            offerInfo.setCode(HttpStatus.BAD_REQUEST.value());
            offerInfo.setMessage(("unable to find Offers with :" + offerId));
            return new ResponseEntity<>(offerInfo, HttpStatus.BAD_REQUEST);

    }

    /**
     * This method return available trade cards
     * @param offerId receive from UI
     * @return list of available trade cards
     */
    @ApiOperation(value = "update Offer by offer id ", response = Response.class)
    @PostMapping("/showAvailableTradeCardInfo")
    public ResponseEntity<OfferInfo> availableTradeCardInfo(@RequestHeader("offerId") Long offerId) throws OfferException {
        log.info("Show Card in Liquidation");


            if (null != offerId) {
                OfferInfo offerInfo = tradeBuyService.availableTradeInfo(offerId);
                return new ResponseEntity<>(offerInfo, HttpStatus.OK);
            }
            OfferInfo offerInfo= new OfferInfo();
            offerInfo.setCode(HttpStatus.BAD_REQUEST.value());
            offerInfo.setMessage(("unable to find offer with :" + offerId));
            return new ResponseEntity<>(offerInfo, HttpStatus.BAD_REQUEST);

    }

}