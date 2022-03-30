package com.guusto.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.guusto.entity.Balance;
import com.guusto.security.Security;
import com.guusto.service.CommonService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BalanceController {
    private static Logger logger= LoggerFactory.getLogger(BalanceController.class);

	@Autowired 
	CommonService commonService;
	
	@Autowired
	Security security;
	
    @RequestMapping(value = "/v1/balance/{clientId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateBalanceAfterBuyGift(@PathVariable Long clientId, @RequestBody Long balance, @RequestHeader HttpHeaders headers) throws Exception{  
    	logger.debug("Inside BalanceController.updateBalanceAfterBuyGift");
    	try {
    		if (!security.isTokenValidated(headers)) {
        		throw new SecurityException("Invalid or missing token");
        	}
    		String returnStatus = commonService.processBalance(clientId, balance);
    		if (returnStatus.equals("Successfull")) {
    			Map<Object, Object> returnValue = new HashMap<Object, Object>();
    			returnValue.put("status", returnStatus);
    			return new ResponseEntity<>(returnValue, HttpStatus.OK);
    		}
    		else {
    			Map<Object, Object> returnValue = new HashMap<Object, Object>();
        		returnValue.put("status", returnStatus);
        		return new ResponseEntity<>(returnValue, HttpStatus.NO_CONTENT);
    		}
    	}catch(SecurityException e) {
    		Map<Object, Object> returnValue = new HashMap<Object, Object>();
    		returnValue.put("status", "Forbidden");
    		return new ResponseEntity<>(returnValue, HttpStatus.FORBIDDEN);
    	}catch(Exception e) {
    		Map<Object, Object> returnValue = new HashMap<Object, Object>();
    		returnValue.put("status", "Not Found");
    		return new ResponseEntity<>(returnValue, HttpStatus.NOT_FOUND);
    	}	  
	}
    
//  @RequestMapping(value = "/v1/balance/{id}", method = RequestMethod.GET)
//  public ResponseEntity<?> getBalance(@PathVariable Long id) throws Exception{  
//  	Balance balance = null;
//  	try {
//  		balance = commonService.getBalance(id);
//  	}catch(Exception e) {
//  		Map<Object, Object> returnValue = new HashMap<Object, Object>();
//  		returnValue.put("status", "Not Found");
//  		return new ResponseEntity<>(returnValue, HttpStatus.NOT_FOUND);
//  	}
//		Map<Object, Object> returnValue = new HashMap<Object, Object>();
//		returnValue.put("balance", balance.getBalance());
//		returnValue.put("status", "Successfull");
//		return new ResponseEntity<>(returnValue, HttpStatus.OK);	  
//	}
}
