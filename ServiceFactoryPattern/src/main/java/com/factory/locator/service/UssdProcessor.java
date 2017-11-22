package com.factory.locator.service;

import org.springframework.stereotype.Service;

@Service("ussd")
public class UssdProcessor implements RequestProcessor {

	@Override
	public String process() {

		return this.getClass().getName();

	}
}