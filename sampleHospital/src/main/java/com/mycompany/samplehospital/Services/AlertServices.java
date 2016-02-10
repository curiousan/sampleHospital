/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.samplehospital.Services;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import com.mycompany.samplehospital.model.User;
import com.mycompany.samplehospital.Serialization.ReadObject;
import com.mycompany.samplehospital.Serialization.WriteObject;
import com.mycompany.samplehospital.model.Alert;
import com.mycompany.samplehospital.model.Message;

/**
 *
 * @author sandeshpoudel
 */
public class AlertServices implements Serializable	 {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	UserServices UserList = new UserServices();
	
   private  static Map<Integer,Alert> alert = AllServices.getAlert();

	
	public AlertServices() {
		alert.put(1, new Alert(UserList.getUser(1),"hello all!! emergency"));
		alert.put(2, new Alert(UserList.getUser(2),"Blood required"));


	}
	
	

    public static List<Alert> getAlerts(){
         return new ArrayList<Alert>(alert.values()); 

    }
	public Alert getAlert(int id) {
		return alert.get(id);
	}

    
    public Alert AddAlert(Alert alrt){
    	alrt.setId(alert.size()+1);
    	
    	alert.put(alrt.getId(), alrt);
    	return alrt;
    	
    }
    public Alert updateAlert (Alert alrt){
    	if (alrt.getId()<1){
    		return null;
    	}
    	alert.put(alrt.getId(), alrt);
    	return alrt;
    }
    
    public Alert removeAlert(Integer id){
    	return alert.remove(id);
    }
    public  List<Alert> getAllAlertByUser(int userId){
    	ArrayList<Alert> myAlert = new ArrayList<Alert>(alert.values());
    	ArrayList<Alert>finalAlertList = new ArrayList<Alert>();
    	for(int i =0; i< myAlert.size(); i++){
    		Alert alertAdder = myAlert.get(i);
    		if(alertAdder.getSenderID() ==userId){
    			finalAlertList.add(alertAdder);
    			
    			
    		}
    	}
    	return finalAlertList;
    	
    }
    
    
    
    
}

    

