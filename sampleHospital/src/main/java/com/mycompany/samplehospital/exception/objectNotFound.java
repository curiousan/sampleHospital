package com.mycompany.samplehospital.exception;

public class objectNotFound extends RuntimeException {

	/**
	 * 
	 */
    //class maps the object not found error
	private static final long serialVersionUID = 1L;

	
	public objectNotFound(String user){
		super(user);
		
	}
}
