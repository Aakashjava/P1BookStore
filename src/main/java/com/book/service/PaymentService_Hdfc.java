package com.book.service;

import org.springframework.stereotype.Service;

@Service()
public class PaymentService_Hdfc implements PaymentServices{

	@Override
	public String getBankName() {
		// TODO Auto-generated method stub
		return "HDFC";
	}

	@Override
	public double getBalance() {
		// TODO Auto-generated method stub
		return 9000;
	}

	@Override
	public double getCreditAmount() {
		// TODO Auto-generated method stub
		return 2000;
	}

	@Override
	public double getDeductedAmount() {
		// TODO Auto-generated method stub
		return 3000;
	}

}
