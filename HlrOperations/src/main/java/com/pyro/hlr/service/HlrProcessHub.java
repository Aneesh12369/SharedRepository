package com.pyro.hlr.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

@Service("hlrService")
public class HlrProcessHub {
	@Autowired
	HlrOperations operations;

	Logger logger = LoggerFactory.getLogger(HlrProcessHub.class);
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	public void process(String file, String type) {
		try {
			File filePath = ResourceUtils.getFile(file);

			switch (type) {
			case "enablehlr":
				operations.setFw(getFileWriter("enablehlr"));
				operations.updateStatus(filePath);
				break;
			case "disablehlr":
				operations.setFw(getFileWriter("disablehlr"));
				operations.disableStatus(filePath);
				break;
			case "statushlr":
				operations.setFw(getFileWriter("statushlr"));
				//operations.readAndCheckStatus(filePath);
				break;
			case "savesubscription":
				operations.setFw(getFileWriter("savesubscription"));
				operations.insertSubscription(filePath);
				break;
			case "saveContentSubscription":
				operations.setFw(getFileWriter("saveContentSubscription"));
				operations.insertContentSubscription(filePath);
				break;
			default:
				logger.error("Some Error...........Try Again");

			}

		} catch (IOException e) {
			logger.error("Exception in creating file", e);
		}

	}
	
	
	
	public FileWriter getFileWriter(String type) throws IOException{
		FileWriter fw = null;
		if(type.equalsIgnoreCase("enablehlr")){
			fw =  new FileWriter(new File("HLR_ENABLE" + format.format(new Date()) + ".csv"));
		}else if(type.equalsIgnoreCase("disablehlr")){
			fw = new FileWriter(new File("HLR_DISABLE" + format.format(new Date()) + ".csv"));
		}else if(type.equalsIgnoreCase("statushlr")){
			fw = new FileWriter(new File("HLR_STATUS" + format.format(new Date()) + ".csv"));
		}else if(type.equalsIgnoreCase("savesubscription")){
			fw = new FileWriter(new File("SUBSCRIPTION_" + format.format(new Date()) + ".csv"));
		}else if(type.equalsIgnoreCase("saveContentSubscription")){
			fw = new FileWriter(new File("CONTENT_" + format.format(new Date()) + ".csv"));
		}
		
		
		return fw;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
