package com.lude.sisempresa.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{
	private static final long serialVersionUID = 1L;
	
	List<FieldMessage> errors = new ArrayList<>();
	
	public ValidationError(Integer statusHTTPdoErro, String msg, Long timeStamp) {
		super(statusHTTPdoErro, msg, timeStamp);
		
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addError(String fielName, String message) {
		errors.add(new FieldMessage(fielName, message));
	}
	
	
	
	

}
