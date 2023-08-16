package com.ibm.app.dto;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
	
	@JsonProperty("error")
	private String error;
	
	@JsonProperty("error_message")
	private String errorMessage;
	
	@JsonProperty("http_status")
	private HttpStatus httpStatus;

}
