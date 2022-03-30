package com.guusto.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.guusto.entity.Balance;
import com.guusto.repository.BalanceRepository;

@Service
public class CommonService {
	@Autowired 
	BalanceRepository balanceRepository;
	
	public Balance getBalance(Long id) throws Exception{
		return balanceRepository.findById(id)
			      .orElseThrow(() -> new Exception());
	}
	public Balance getBalanceByClientId(Long clientId) throws Exception{
		return balanceRepository.findByClientId(clientId);
	}
	
	public String processBalance (Long clientId, Long balance) throws Exception {
		Balance availableBalance = getBalanceByClientId(clientId);
		if (availableBalance.getBalance() >= balance) {
			availableBalance.setBalance(availableBalance.getBalance() - balance);
			updateBalance(clientId, availableBalance);
			return "Successfull";
		}
		else {
    		return "Insufficient Balance";
		}
		
	}
	
	public void updateBalance(Long clientId, Balance balance) throws Exception{
		Balance newBalance = balanceRepository.findByClientId(clientId);
		if (newBalance == null) {
			newBalance = balance;
			newBalance.setClientId(clientId);

		}else {
			newBalance.setBalance(balance.getBalance());
		}
		newBalance.setCreatedAt(LocalDateTime.now());
		balanceRepository.save(newBalance);
		
	}
	
}
