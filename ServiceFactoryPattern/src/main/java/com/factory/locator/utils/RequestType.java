package com.factory.locator.utils;

public enum RequestType {

	SMS("sms"), IVR("ivr"), USSD("ussd");

	private String str;
		
	private RequestType(String str) {
		this.str = str;
	}
	
	@Override
	public String toString(){
		return this.str;
	}
	
	
}
