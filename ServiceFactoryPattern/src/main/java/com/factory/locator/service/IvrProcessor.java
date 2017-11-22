package com.factory.locator.service;

import org.springframework.stereotype.Service;

@Service("ivr")
public class IvrProcessor implements RequestProcessor{

	@Override
	public String process() {
		
		return this.getClass().getName();
	}


}
