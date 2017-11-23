package com.pyro.hlr;

import java.io.File;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.ResourceUtils;

import com.pyro.hlr.service.HlrProcessHub;

@SpringBootApplication
@EnableAsync
public class HlrOperationsApplication implements CommandLineRunner {
	static Logger logger = LoggerFactory.getLogger(HlrOperationsApplication.class);

	public static void main(String[] args) {
		// SpringApplication.run(HlrOperationsApplication.class, args);
		ApplicationContext context = null;
		HlrProcessHub hub = null;
		ThreadPoolTaskExecutor exec = null;

		SpringApplicationBuilder springAppBuilder = new SpringApplicationBuilder();
		context = springAppBuilder.parent(HlrOperationsApplication.class).build().run(args);
		logger.info("Application started.............");
		hub = context.getBean("hlrService", HlrProcessHub.class);
		exec = context.getBean("taskExecutor", ThreadPoolTaskExecutor.class);
		logger.info("Processing.............");
		hub.process(args[0], args[1]);
		logger.info("#####################################DONE###################################################");
		logger.info("Processing Completed.............");
		exec.shutdown();

	}

	@Bean(name = "taskExecutor")
	public ThreadPoolTaskExecutor taskExecutors() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(30);
		executor.setMaxPoolSize(100);
		executor.setQueueCapacity(100);
		executor.setThreadNamePrefix("pyrogroup-");
		executor.setKeepAliveSeconds(60);
		executor.setWaitForTasksToCompleteOnShutdown(true);
		//executor.setAwaitTerminationSeconds(3 * 60);
		executor.initialize();

		return executor;

	}

	@Override
	public void run(String... arg) throws Exception {
		logger.info("Arguments Passed {}", Arrays.toString(arg));
		if (arg.length < 2) {
			logger.info("Invalid Arguments....", arg.length);
			logger.info("Try again [Usage] java xxx.jar  [filepath] {enablehlr|disablehlr}");
			System.exit(0);
		} else {
			if (arg[1].equalsIgnoreCase("enablehlr") || arg[1].equalsIgnoreCase("disablehlr") ||
					arg[1].equalsIgnoreCase("savesubscription") || arg[1].equalsIgnoreCase("saveContentSubscription")) {
				logger.info("File Path {}", arg[0]);
				File file = ResourceUtils.getFile(arg[0]);
				if (!file.exists()) {
					logger.info("File Not Found............");
					System.exit(0);
				}
				if(arg[1].equalsIgnoreCase("savesubscription")){
					boolean validName = file.getName().startsWith("SUB_");
					if(!validName){
						logger.info("File name shuold be prefixed with  SUB_");
						System.exit(0);
					}
				}
				
				if(arg[1].equalsIgnoreCase("saveContentSubscription")){
					boolean validName = file.getName().startsWith("CON_");
					if(!validName){
						logger.info("File name shuold be prefixed with  CON_");
						System.exit(0);
					}
				}
				
				logger.info("Waiting to Process..............");
			} else {
				logger.info("Invalid Options choose between [enablehlr|disablehlr]");
				System.exit(0);
			}
		}

	}
}
