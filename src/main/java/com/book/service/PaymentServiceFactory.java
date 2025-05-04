package com.book.service;

import java.util.*;

import org.springframework.stereotype.Component;
@Component
public class PaymentServiceFactory {

	private final Map<String,PaymentServices> bankServiceMap=new HashMap<>();
	/*
	 * Map<String, PaymentServices> is used to store bank name ↔ service instance pairs.
bankServiceMap = {
    "HDFC" → new HDFCBankService(),
    "ICICI" → new ICICIBankService()
}
final means you cannot reassign the map to another map (immutability of reference).

private means only this class can access it.
	 */

	// ✅ This is the constructor
	
	public PaymentServiceFactory(List<PaymentServices> bankServices) {
		for(PaymentServices service: bankServices)
		 bankServiceMap.put(service.getBankName().toUpperCase(), service);
	}
	/*
	 * @Autowired
public PaymentServiceFactory(List<PaymentServices> bankServices) {

✅ Constructor with Spring’s Dependency Injection:
Spring will automatically inject a list of all beans that implement the BankService interface.
So if you have:

@Service class HDFCBankService implements BankService { ... }
@Service class ICICIBankService implements BankService { ... 
@Service class SBIBankService implements BankService { ... }

Spring will pass all three in bankServices.
	 */
//==============
	/*
	 *   for(PaymentServices service: bankServices){
		 bankServiceMap.put(service.getBankName().toUpperCase(), service);
    }
✅ Populates the map:
Loops over each injected BankService implementation.
Calls getBankName() on each service (e.g., "HDFC", "SBI").
Converts it to UPPERCASE to ensure case-insensitive lookup later.
Adds it to bankServiceMap with:
Key = bank name (like "HDFC")
Value = actual BankService implementation (like HDFCBankService)
	 */
	
	public PaymentServices getBankService(String BankName) {
		
		return bankServiceMap.get(BankName.toUpperCase());
	}
	
	/*
	 public BankService getBankService(String bankName) {
	 
✅ Method to get the appropriate bank service:
You pass in a bankName (e.g., "hdfc" or "HDFC")
This method returns the corresponding service object from the map
	 */
	/*
	   return bankServiceMap.get(bankName.toUpperCase());
		}
✅ Returns the correct service:
Looks up the bankServiceMap using the uppercased version of bankName.
So "hdfc" and "HDFC" will both return the same service object.
	 */
	
	//========= real world uses
	/*
	 BankService bank = bankFactoryService.getBankService("sbi");
	double balance = bank.getBalance();
	 * 
	 */
}
