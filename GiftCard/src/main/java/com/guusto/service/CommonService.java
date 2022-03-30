package com.guusto.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.guusto.dto.ClientRequestPayload;
import com.guusto.dto.RequestPayload;
import com.guusto.entity.GiftCard;
import com.guusto.repository.GiftCardRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommonService {
    private static Logger logger= LoggerFactory.getLogger(CommonService.class);

	@Autowired 
	GiftCardRepository giftCardRepository;

	
	@Transactional(rollbackFor = Exception.class)
	public String buyGiftCard(ClientRequestPayload payload) throws Exception{
		long totalAmount = 0l;
		String returnValue = "";
		if (payload != null) {
			RequestPayload[] payloadItems =  payload.getRequestPayLoad();
			if (payloadItems != null && payloadItems.length > 0) {
				for (int items=0; items < payloadItems.length; items++ )
					totalAmount = totalAmount + (payloadItems[items].getAmount() * payloadItems[items].getQuantity());
			}
			logger.info("Total Amount : " + totalAmount);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("Authorization", "uioopaknwbiuiu12n41onkndmnbayqebzbhsjlpfnwjkajsnnmsjb889ssyroiygbiv");
			HttpEntity<Long> httpEntity = new HttpEntity<Long>(new Long(totalAmount), headers);
			String uri = "http://balance:8082/v1/balance/"+payload.getClientId();//balance instead of localhost for docker.
			logger.info("Returned from ReST call");
			ResponseEntity<HashMap> response = new RestTemplate().exchange(uri, HttpMethod.PUT, httpEntity, HashMap.class);
            if (response.getStatusCode() == HttpStatus.OK) {
            	LocalDateTime localDateTime = LocalDateTime.now();
            	ArrayList<GiftCard> giftCardList = new ArrayList<GiftCard>();
            	for (int items=0; items < payloadItems.length; items++ ) {
					totalAmount = totalAmount + (payloadItems[items].getAmount() * payloadItems[items].getQuantity());
					GiftCard giftCard = new GiftCard();
					giftCard.setClientId(payload.getClientId());
					giftCard.setAmount(new Long(payloadItems[items].getAmount()));
					giftCard.setQuantity(new Long(payloadItems[items].getQuantity()));
					giftCard.setCreatedAt(localDateTime);
					giftCardList.add(giftCard);	
            	}
				giftCardRepository.saveAll(giftCardList);
            	returnValue = "Giftcard successfully purchased and balance updated";
            }else if (response.getStatusCode() == HttpStatus.NO_CONTENT) {
            	returnValue = "Insufficient balance. Unable to buy gift";
            }else {
            	returnValue = "Client doesnot exist";
            }
            log.info("Completed buygift ");
		}
        return returnValue;
	}
		
}
