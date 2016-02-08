/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.samplehospital.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import com.mycompany.samplehospital.model.User;
import com.mycompany.samplehospital.model.Alert;
import com.mycompany.samplehospital.model.Message;

/**
 *
 * @author sandeshpoudel
 */
public class AlertServices {
	UserServices UserList = new UserServices();
	
private  Map<Integer,Alert> alert = AllServices.getAlert();

	
	public AlertServices(){
		alert.put(1, new Alert(UserList.getUser(1).getId(),"hello all!! emergency"));
		alert.put(2, new Alert(UserList.getUser(2).getId(),"Blood required"));

	}
	
	

    public  List<Alert> getAlerts(){
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

    
    
    
    
}

    

