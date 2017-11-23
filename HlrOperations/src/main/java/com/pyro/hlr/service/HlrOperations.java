package com.pyro.hlr.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pyro.hlr.pojo.Response;
import com.pyro.hlr.util.HlrUtils;

@Service
public class HlrOperations {

	private static Logger logger = LoggerFactory.getLogger(HlrOperations.class);
	/*@Autowired
	private HlrDao dao;*/

	@Autowired
	private HlrUtils utils;

	private FileWriter fw = null;

	public void setFw(FileWriter fw) {
		this.fw = fw;
	}

	/*public void readAndCheckStatus(File f) {

		logger.info("Reading File............");
		boolean checkStatus = false;
		String line = "";

		try {
			logger.info("Checking the status............");
			List<String> lines = Files.readAllLines(Paths.get(f.toURI()));
			for (String str : lines) {
				if (str.length() == 0) {
					continue;
				}
				logger.info("Checking for msisdn {}", str);
				checkStatus = dao.checkStatus(str);
				if (true == checkStatus) {
					line = str + "," + "Active";
				} else {
					line = str + "," + "InActive";
				}
				logger.info(" for msisdn {}", line);
				writeToFile(line);
			}
			fw.flush();

		} catch (Exception e) {
			logger.error("Exception.............", e);
		}

	}
*/
	public void updateStatus(File f) {

		logger.info("Reading File............");
		Future<Response> responseStatus = null;
		String line = "";
		String resp = "";
		String msisdn = "";
		List<Future<Response>> list = new ArrayList<>();
		try {
			logger.info("updating the status............");
			Charset charset = Charset.forName("ISO-8859-1");
			List<String> lines = Files.readAllLines(Paths.get(f.toURI()),charset);
			for (String str : lines) {
				if (str.length() == 0) {
					continue;
				}
				logger.info("updating for msisdn {}", str);

				responseStatus = utils.hitURL_HLR(str, "A");
				logger.info("future for msisdn {}", str);
				list.add(responseStatus);
			}

			for (Future<Response> fututre : list) {
				
				// resp = responseStatus.get(1,TimeUnit.MINUTES);
				try {
					Response respObj = fututre.get(1, TimeUnit.MINUTES);
					logger.info("Response Object future return {}",respObj);
					msisdn = respObj.getMsisdn();
					resp = respObj.getResponse();
					if (resp.equalsIgnoreCase("SUCCESS")) {
						line = msisdn + "," + "Activated";
					} else {
						line = msisdn + "," + "not activated";
					}
					logger.info(" for msisdn {}", line);

				} catch (InterruptedException | TimeoutException | ExecutionException e) {
					line = msisdn + "," + "not activated";
					logger.error("Timeout Exception.......... ",e);
					logger.error("Reeeeeeeeeeeeeeeee  {} ",line);
					e.printStackTrace();
				}
				writeToFile(line);
				
			}
			fw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	
	public void insertSubscription(File f){
		
	}
	
	
	
	public void insertContentSubscription(File f){
		
	}
	
	
	
	
	public void disableStatus(File f) {
		

		logger.info("Reading File............");
		Future<Response> responseStatus = null;
		String line = "";
		String resp = "";
		String msisdn = "";
		List<Future<Response>> list = new ArrayList<>();
		try {
			logger.info("disabling the status............");
			Charset charset = Charset.forName("ISO-8859-1");
			List<String> lines = Files.readAllLines(Paths.get(f.toURI()),charset);
			for (String str : lines) {
				if (str.length() == 0) {
					continue;
				}
				logger.info("Disable for msisdn {}", str);

				responseStatus = utils.hitURL_HLR(str, "D");
				list.add(responseStatus);
			}

			for (Future<Response> fututre : list) {
				
				try {
					Response respObj = fututre.get(1, TimeUnit.MINUTES);
					msisdn = respObj.getMsisdn();
					resp = respObj.getResponse();
					if (resp.equalsIgnoreCase("SUCCESS")) {
						line = msisdn + "," + "Deactivated";
					} else {
						line = msisdn + "," + "Not Deactivated";
					}
					logger.info(" for msisdn {}", line);

				} catch (InterruptedException | TimeoutException | ExecutionException e) {
					line = msisdn + "," + "not activated";
				}
				writeToFile(line);
				
			}
			fw.flush();
			
		} catch (IOException e) {
			logger.error("Exception.............", e);
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	
	

	/*public void disableStatus(File f) {

		logger.info("Reading File............");
		Future<String> checkStatus = null;
		String line = "";
		String result = "";

		try {
			logger.info("Disable the status............");
			List<String> lines = Files.readAllLines(Paths.get(f.toURI()));
			for (String str : lines) {
				if (str.length() == 0) {
					continue;
				}
				logger.info("Disable for msisdn {}", str);
				checkStatus = utils.hitURL_HLR(str, "D");
				result = checkStatus.get();
				if (result.equalsIgnoreCase("SUCCESS")) {
					line = str + "," + "Deactivated";
				} else {
					line = str + "," + "Not Deactivated";
				}

				logger.info(" for msisdn {}", line);
				writeToFile(line);
			}
			fw.flush();

		} catch (Exception e) {
			logger.error("Exception.............", e);
		}

	}
*/
	public void writeToFile(String line) throws IOException {
		fw.write(line + "\n");

	}

}
