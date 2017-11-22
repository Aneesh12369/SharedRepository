package com.factory.locator.servicefactory;

import com.factory.locator.service.RequestProcessor;
import com.factory.locator.utils.RequestType;

public interface RequestProcessorFactory{
	
	public RequestProcessor getProcessor(RequestType type);

}
