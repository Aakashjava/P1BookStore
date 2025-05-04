package com.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.book.service.PaymentServiceFactory;
import com.book.service.PaymentServices;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/payment")
public class PaymentController {

	@Autowired
	PaymentServiceFactory paymentservice;
	
	@GetMapping("/{BankName}/balance")
	public ResponseEntity<Double> getBalance(@PathVariable String BankName) {
		
		PaymentServices service=paymentservice.getBankService(BankName);
		return ResponseEntity.ok(service.getBalance());
	}
	
	@GetMapping("/{BankName}/amountDeduct")
	public ResponseEntity<Double> getDeductedAmount(@PathVariable String BankName) {
		
		PaymentServices service=paymentservice.getBankService(BankName);
		return ResponseEntity.ok(service.getDeductedAmount());
	}
	@GetMapping("/{BankName}/creditAmount")
	public ResponseEntity<Double> getCreditAmount(@PathVariable String BankName) {
		
		PaymentServices service=paymentservice.getBankService(BankName);
		return ResponseEntity.ok(service.getCreditAmount());
	}
	
}
