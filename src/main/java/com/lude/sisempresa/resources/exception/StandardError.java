package com.lude.sisempresa.resources.exception;

import java.io.Serializable;

public class StandardError implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer statusHTTPdoErro;
	private String msg;
	private Long timeStamp;
	public StandardError(Integer statusHTTPdoErro, String msg, Long timeStamp) {
		super();
		this.statusHTTPdoErro = statusHTTPdoErro;
		this.msg = msg;
		this.timeStamp = timeStamp;
	}
	public Integer getStatusHTTPdoErro() {
		return statusHTTPdoErro;
	}
	public void setStatusHTTPdoErro(Integer statusHTTPdoErro) {
		this.statusHTTPdoErro = statusHTTPdoErro;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	
	
}
