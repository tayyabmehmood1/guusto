package com.guusto.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;

import com.guusto.dto.ClientRequestPayload;
import com.guusto.entity.GiftCard;
import com.guusto.service.CommonService;

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class GiftCardController {
    private static Logger logger = LoggerFactory.getLogger(GiftCardController.class);

	@Autowired 
	CommonService commonService;
	
    @RequestMapping(value = "/v1/guusto-service/buy-gift", method = RequestMethod.POST)
    public ResponseEntity<?> buyGift(@RequestBody ClientRequestPayload payload) throws Exception{  
    	log.info("Inside GiftCardController.buyGift");
    	GiftCard balance = null;
    	String returnMessage = null;
    	try {
    		returnMessage = commonService.buyGiftCard(payload);
    	}catch (HttpClientErrorException e) {
    		Map<Object, Object> returnValue = new HashMap<Object, Object>();
			if (e.getStatusCode() == HttpStatus.FORBIDDEN) {
	    		returnValue.put("status", "Invalid Authorization Token.");
				return new ResponseEntity<>(returnValue, HttpStatus.FORBIDDEN);	
			}
    		returnValue.put("status", "Not Found. Make sure Client exists in system.");
    		e.printStackTrace();
    		return new ResponseEntity<>(returnValue, HttpStatus.NOT_FOUND);			
		}
    	catch(Exception e) {
    		Map<Object, Object> returnValue = new HashMap<Object, Object>();
    		returnValue.put("status", "Not Found. Make sure Client exists in system.");
    		e.printStackTrace();
    		return new ResponseEntity<>(returnValue, HttpStatus.NOT_FOUND);
    	}
		Map<Object, Object> returnValue = new HashMap<Object, Object>();
		returnValue.put("status", returnMessage);
		return new ResponseEntity<>(returnValue, HttpStatus.OK);	  
	}
}
