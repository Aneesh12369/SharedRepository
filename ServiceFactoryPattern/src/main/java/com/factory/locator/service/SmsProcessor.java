package com.factory.locator.service;

import org.springframework.stereotype.Service;

@Service("sms")
public class SmsProcessor implements RequestProcessor{

	@Override
	public String process() {
		
		return this.getClass().getName();
	}

}
