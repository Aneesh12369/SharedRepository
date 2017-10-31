package com.pyro.hlr.pojo;

public class Response {

	private String msisdn;
	private String response;

	public Response(String msisdn2, String resp) {
		this.msisdn = msisdn2;
		this.response=resp;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

}
