package com.twowayssl.test;
/*
 * @author arun
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

public class TestHandler {
	public static void main(String[] args) {
		try {
			javax.net.ssl.HttpsURLConnection
					.setDefaultHostnameVerifier(new javax.net.ssl.HostnameVerifier() {

						public boolean verify(String hostname,
								javax.net.ssl.SSLSession sslSession) {
							return hostname.equals("172.22.42.51");
						}
					});

			System.out
					.println("=============Allowed Host 172.22.42.51 . Configure here to allow hosts=============");

			System.out.println("=============Testing 2-Way SSL HandShaking=============");

			System.setProperty("javax.net.ssl.trustStore",
					"./certs/Server.jks");
			System.setProperty("javax.net.ssl.trustStorePassword", "password");

			System.setProperty("javax.net.ssl.keyStore",
					"./certs/clientNew.pfx");
			System.setProperty("javax.net.ssl.keyStorePassword", "password");
			System.setProperty("javax.net.ssl.keyStoreType", "pkcs12");
			// javax.net.debug=ssl

			System.out.println("Calling Server ============");
			System.setProperty("javax.net.debug","ssl");
			System.out.println("DEBUGGING ===========");
			SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory
					.getDefault();
			URL url = new URL(
					"https://172.22.42.51:40040/2WaySSLServer/TestHandler");
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(sslsocketfactory);
			InputStream inputstream = conn.getInputStream();
			InputStreamReader inputstreamreader = new InputStreamReader(
					inputstream);
			BufferedReader bufferedreader = new BufferedReader(
					inputstreamreader);

			String string = null;
			while ((string = bufferedreader.readLine()) != null) {
				System.out.println("\n \n ============= Response From Server ============= \n" + string);
			}
			System.out.println("============= Done ============\n");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
