package com.pyro.hlr.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import com.pyro.hlr.pojo.Response;

@Component
public class HlrUtils {

	private Logger log = LoggerFactory.getLogger(HlrUtils.class);

	@Async
	public Future<Response> hitURL_HLR(String msisdn, String status) {
		String resp = "1:1:ERROR";
		try {

			log.info("Received request for " + status + " for MSISDN ... " + msisdn);

			Random rand = new Random();
			String randomNum = String.valueOf(rand.nextInt(900000000) + 100000000);

			String url = "http://localhost:20060/hlr?request_type=hlr_update&msisdn={msisdn}&ref_id={ref_id}&service_name=PRBT&req_type={req_type}&status=0&service_type=DEFAULT";

			url = url.replace("{msisdn}", msisdn);
			url = url.replace("{ref_id}", randomNum);
			url = url.replace("{req_type}", status);

			log.info("Hitting the URL...." + url);

			log.info("URL:" + url);
			URL urlObj = new URL(url);
			URLConnection con = urlObj.openConnection();
			con.setConnectTimeout(60000);
			con.setReadTimeout(60000);

			// con.setDoOutput(true);

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

			StringBuffer response = new StringBuffer();
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}

			in.close();

			resp = response.toString();
			log.info("URL RESP:" + resp);
			log.info("Response received as...." + resp);

		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception in hitURL : " + e.getMessage());
			e.printStackTrace();
			resp = "1:1:ERROR";
		}
		Response response = new Response(msisdn, resp);
		log.info("Response Object ********** {}",response);
		return new AsyncResult<Response>(response);

	}
	
	

}
