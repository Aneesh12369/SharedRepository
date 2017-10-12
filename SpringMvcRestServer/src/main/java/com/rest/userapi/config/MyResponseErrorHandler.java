package com.rest.userapi.config;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import com.rest.userapi.exceptions.RequestedResourceNotFoundException;

public class MyResponseErrorHandler implements ResponseErrorHandler {

	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		if (response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR
				|| response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) {
			return true;
		}
		return false;
	}

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
			throw new RequestedResourceNotFoundException("Resource not found");
		}

		throw new RequestedResourceNotFoundException("Resource not found22222");

	}

}
