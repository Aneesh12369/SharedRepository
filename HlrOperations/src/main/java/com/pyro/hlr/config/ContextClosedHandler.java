package com.pyro.hlr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

//@Component
public class ContextClosedHandler implements ApplicationListener<ContextClosedEvent> {
	@Autowired
	ThreadPoolTaskExecutor executor;
	

	public ContextClosedHandler() {
		System.out.println("Context CLosed Handler Constructor.......");
	}


	@Override
	public void onApplicationEvent(ContextClosedEvent event) {
		System.out.println("Closing event........");
		////executor.shutdown();
	}
}
