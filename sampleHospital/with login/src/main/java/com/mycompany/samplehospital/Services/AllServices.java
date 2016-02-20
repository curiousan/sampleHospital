package com.mycompany.samplehospital.Services;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.mycompany.samplehospital.model.Message;
import com.mycompany.samplehospital.Serialization.ReadObject;
import com.mycompany.samplehospital.Serialization.WriteObject;
import com.mycompany.samplehospital.model.Alert;
import com.mycompany.samplehospital.model.User;

public class AllServices implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Map<Integer, User> user = new HashMap<>();
	private static Map<Integer, Alert> Alert = new HashMap<>();
	private static Map<Integer, Message> message = new HashMap<>();

	
	public static Map<Integer, Message> getMessages() {
		return message;
	}
	public static Map<Integer, Alert> getAlert() {
		return Alert;
	}
	
	public static Map<Integer, User> getUsers() {
		return user;
	}
	

		
	}
	
	
	
		
	
	
	

	
	
	

