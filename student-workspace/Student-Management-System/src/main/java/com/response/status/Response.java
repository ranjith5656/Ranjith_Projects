package com.response.status;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Response<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected StatusType statusType;
	protected String code;
	protected String message;
	@JsonInclude(Include.NON_NULL)
	private String errorType;
	@JsonInclude(Include.NON_NULL)
	private String error;
	@JsonInclude(Include.NON_NULL)
	private T data;
	
	public Response() {
		super();
	}

	public StatusType getStatusType() {
		return statusType;
	}
	public void setStatusType(StatusType statusType) {
		this.statusType = statusType;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getErrorType() {
		return errorType;
	}
	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public T getData() {
		return data;
	}
	public void setData(T response) {
		this.data = response;
	}

	public static <T> Response<T> builResponse(T t,StatusType statusType,Integer statuscode,String message){
		Response<T> response=new Response<>();
		response.setStatusType(statusType);
		response.setData(t);
		response.setMessage(message);
		response.setCode(statuscode.toString());
		return response;
	}
	
}


