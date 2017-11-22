package com.factory.locator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.factory.locator.servicefactory.RequestProcessorFactory;
import com.factory.locator.utils.RequestType;

@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired
	private RequestProcessorFactory processor;

	@RequestMapping(value = "/findClass/{type}", method = RequestMethod.GET)
	public String getServiceType(@PathVariable("type") String type) {
		RequestType t = Enum.valueOf(RequestType.class, type.toUpperCase());
		return processor.getProcessor(t).process();

	}

}
