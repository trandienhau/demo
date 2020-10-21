package com.example.demo.util;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class ServiceException extends Exception {
	private static final long serialVersionUID = 1L;
	private String module = "Employee";
	private HttpStatus status;
	private String message;
        public ServiceException(String message) {
		super();
		this.message = message;
	}

	public ServiceException(String message, HttpStatus status) {
		super();
		this.message = message;
		this.status = status;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}