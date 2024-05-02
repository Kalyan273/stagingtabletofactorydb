package com.factory.appraisal.factoryService.controller;

import com.factory.appraisal.factoryService.ExceptionHandle.AppraisalException;
import com.factory.appraisal.factoryService.ExceptionHandle.OfferException;
import com.factory.appraisal.factoryService.ExceptionHandle.Response;
import com.factory.appraisal.factoryService.dto.CardsPage;
import com.factory.appraisal.factoryService.dto.ListedOffer;
import com.factory.appraisal.factoryService.dto.OfferInfo;
import com.factory.appraisal.factoryService.dto.Offers;
import com.factory.appraisal.factoryService.services.EmailService;
import com.factory.appraisal.factoryService.services.OffersService;
import freemarker.template.TemplateException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/offers")
@Api(tags = "Offers", value = "Offers Module")
public class OffersController {

    Logger log = LoggerFactory.getLogger(AppraisalVehicleController.class);
    @Autowired
    private OffersService service;

    @Autowired
    private EmailService emailService;

    /**
     * This method sends list of offers procurement cards and pagination information  to ui
     * @param id  This   user id or dealer id coming in header from ui
     * @param pageNo   This is page number given by ui
     * @param pageSize  This is Number of records per page given by ui
     * @return   CardsPage
     */
    @ApiOperation(value = "get procurement cards by user id and dealer id", response = CardsPage.class)
    @PostMapping("/getProcurementCards")
    public ResponseEntity<CardsPage> getProcurementCards(@RequestHeader("id") UUID id, @RequestParam Integer pageNo, @RequestParam Integer pageSize) throws AppraisalException {
        log.info("GETTING PROCUREMENT CARDS");
        CardsPage apv = service.procurementCards(id, pageNo, pageSize);
        return new ResponseEntity<CardsPage>(apv, HttpStatus.OK);

    }

    /**
     * This method sends list of offers liquidation  cards and pagination information  to ui
     * @param id  This   user id or dealer id coming in header from ui
     * @param pageNo   This is page number given by ui
     * @param pageSize  This is Number of records per page given by ui
     * @return   CardsPage
     */
    @ApiOperation(value = "get liquidation cards by user id or dealer id ", response = CardsPage.class)
    @PostMapping("/getLiqCards")
    public ResponseEntity<CardsPage> getLiquidationCards(@RequestHeader("id") UUID id, @RequestParam Integer pageNo, @RequestParam Integer pageSize) throws AppraisalException {
        log.info("GETTING LIQUIDATION CARDS");
        CardsPage apv = service.liquidationCards(id, pageNo, pageSize);
        return new ResponseEntity<CardsPage>(apv, HttpStatus.OK);

    }

    /**
     *This method is use to receive first offer from buyer
     * @param appraisalId receive from ui
     * @param buyerUserId receive from ui
     * @param offers receive object from ui
     * @return message
     */
    @ApiOperation(value = "with AppraisalId buyer is making offer", response = Response.class)
    @PostMapping("/makeOffer")
    public ResponseEntity<Response> makeOfferFromBuyer(@RequestHeader("appraisalId") Long appraisalId, @RequestHeader("buyerUserId") UUID buyerUserId, @RequestBody Offers offers) throws OfferException, AppraisalException {
        log.info("Making Offer for First time");

            if (null != appraisalId && null != buyerUserId) {
                Response response = service.makeOfferByBuyer(appraisalId, offers, buyerUserId);
                log.debug("offer coming from UI for insertion of offers {}", offers);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else throw new AppraisalException("unable to find offers with :" + appraisalId);

    }

    /**
     * This method update the seller quote
     * @param offerId receive from UI
     * @param offers oject receive from UI
     * @return message
     */


    @ApiOperation(value = "update Offer by offer id ", response = Response.class)
    @PostMapping("/sellerQuotes")
    public ResponseEntity<Response> sellerQuotes(@RequestHeader("offerId") Long offerId,  @RequestBody Offers offers) throws OfferException, AppraisalException {
        log.info("seller counter");

            if (null != offerId) {
                Response response = service.sellerCounter(offerId, offers);
                log.debug("offerId coming from UI for update of Seller Quotes {}", offerId);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else throw new AppraisalException("unable to find offer with :" + offerId);

    }

    /**
     * This method update the buyer quotes
     * @param offerId receive from UI
     * @param offers object receive from UI
     * @return message
     */

    @ApiOperation(value = "update Offer by offer id ", response = Response.class)
    @PostMapping("/buyerQuotes")
    public ResponseEntity<Response> buyerQuotes(@RequestHeader("offerId") Long offerId, @RequestBody Offers offers) throws OfferException, AppraisalException {
        log.info("buyer counter offer");

            if (null != offerId) {
                Response response = service.buyerCounter(offerId, offers);
                log.debug("offerId coming from UI for update of Buyer Quotes {}", offerId);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else throw new AppraisalException("unable to find offer with :" + offerId);
    }

    /**
     * This method update the seller acceptance
     * @param offerId receive from ui
     * @return message
     */

    @ApiOperation(value = "update Offer by status id ", response = Response.class)
    @PostMapping("/sellerAccepted")
    public ResponseEntity<Response> sellerAccepted(@RequestHeader("offerId") Long offerId) throws OfferException, AppraisalException {

            if (null != offerId) {
                Response response1 = service.sellerAccept(offerId);
                log.debug("offerId coming from UI for update of Seller acceptance {}", offerId);
                return new ResponseEntity<>(response1, HttpStatus.OK);
            } else throw new AppraisalException("unable to find Offers with :" + offerId);


    }

    /**
     * This method update buyer acceptance
     * @param offerId receive from UI
     * @return message
     */

    @ApiOperation(value = "update Status by offer id ", response = Response.class)
    @PostMapping("/buyerAccepted")
    public ResponseEntity<Response> buyerAccepted(@RequestHeader("offerId") Long offerId) throws OfferException, AppraisalException {


            if (null != offerId) {
                Response response1 = service.buyerAccept(offerId);
                log.debug("offerId coming from UI for update of Buyer acceptance {}", offerId);
                return new ResponseEntity<>(response1, HttpStatus.OK);
            } else throw new AppraisalException("unable to find Offers with :" + offerId);


    }

    /**
     * This method will update seller status
     * @param offerId receive from UI
     * @return message
     */

    @ApiOperation(value = "update Offer by offer id ", response = Response.class)
    @PostMapping("/sellerRejected")
    public ResponseEntity<Response> sellerRejected(@RequestHeader("offerId") Long offerId) throws OfferException, AppraisalException {

            if (null != offerId) {
                Response response = service.sellerRejected(offerId);
                log.debug("offerId coming from UI for update of Seller rejection {}", offerId);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else throw new AppraisalException("unable to find Offers with :" + offerId);


    }

    /**
     * This method update offer receive by buyer
     * @param offerId receive from ui
     * @return message
     */

    @ApiOperation(value = "update Offer by offer id ", response = Response.class)
    @PostMapping("/buyerRejected")
    public ResponseEntity<Response> buyerRejected(@RequestHeader("offerId") Long offerId) throws OfferException, AppraisalException {
        log.info("Updating buyer reject status");


            if (null != offerId) {
                Response response1 = service.buyerRejected(offerId);
                log.debug("offerId coming from UI for Buyer rejection {}", offerId);
                return new ResponseEntity<>(response1, HttpStatus.OK);
            } else throw new AppraisalException("unable to find Offers with :" + offerId);


    }

    /**
     * This method returns the Offer Information and Appraisal vehicle information base on offerId
     * @param offerId This is offerId
     * @return OfferInfo
     */

    @ApiOperation(value = "liquidation card info ", response = Response.class)
    @PostMapping("/showLiqCardInfo")
    public ResponseEntity<OfferInfo> getLiquidationCardInfo(@RequestHeader("offerId") Long offerId) throws AppraisalException, OfferException {
        log.info("Show Card in Liquidation");


            if (null != offerId) {
                OfferInfo offerInfo = service.liquidationOfferInfo(offerId);
                return new ResponseEntity<>(offerInfo, HttpStatus.OK);
            } else throw new AppraisalException("unable to find Offers with :" + offerId);


    }

    /**
     * This method returns the Offer Information and Appraisal vehicle information base on offerId
     * @param offerId This is offerId
     * @return OfferInfo
     */
    @ApiOperation(value = "procurement card info", response = OfferInfo.class)
    @PostMapping("/getProcuCardInfo")
    public ResponseEntity<OfferInfo> getProcurementCardInfo(@RequestHeader("offerId") Long offerId) throws OfferException, AppraisalException {
        log.info("Sending offer procurement info");


            if (null!= offerId) {
                OfferInfo offerInfo = service.procurementOfferInfo(offerId);
                return new ResponseEntity<>(offerInfo, HttpStatus.OK);
            } else throw new AppraisalException("unable to find Offers with :" + offerId);


    }

    /**
     * This method is used to send email to user after making offer
     * @param offerId getting from ui
     * @return message
     */
    @PostMapping("/offerEmail")
    public ResponseEntity<Response> finalOfferEmail(@RequestHeader("offerId") Long offerId) throws AppraisalException, MessagingException, TemplateException, IOException {
        Response response=emailService.offerUpdateEmail(offerId);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    /**
     *This method is giving List of offers
     * @param appraisalId
     * @return
     */

    @PostMapping("/getOffers")
    public ResponseEntity<ListedOffer>getOffDetails(@RequestHeader("appraisalId")Long appraisalId) throws OfferException {
        log.info("Sending offer details");
        ListedOffer offerList = service.getOfferList(appraisalId);
        return new ResponseEntity<>(offerList, HttpStatus.OK);

    }


}
