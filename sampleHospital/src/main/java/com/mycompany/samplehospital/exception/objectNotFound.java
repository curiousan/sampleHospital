package com.mycompany.samplehospital.exception;

public class objectNotFound extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public objectNotFound(String user){
		super(user);
		
	}
}
