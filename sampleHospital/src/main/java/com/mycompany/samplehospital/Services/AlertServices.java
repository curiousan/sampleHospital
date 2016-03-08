
package com.mycompany.samplehospital.Services;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import com.mycompany.samplehospital.model.User;
import com.mycompany.samplehospital.model.Alert;

/**
 *
 * @author sandeshpoudel
 */
public class AlertServices implements Serializable	 {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
        UserServices users;
 
    List<Integer> recieverList = new ArrayList<Integer>();
    
   
   
   
        ArrayList<User>UserList =  (ArrayList<User>) UserServices.getUsers();
       
        
        
	
   private static  Map<Integer,Alert> alert = AllServices.getAlert();
  

	
	public AlertServices() {
            
            recieverList.add(1);
            recieverList.add(2);

		alert.put(1, new Alert(1,1,"hello all!! emergency" ,"News",recieverList));
		alert.put(2, new Alert(2,2, "Blood required","emergency", recieverList));


	}
	
	

    public static List<Alert> getAlerts(){
         return new ArrayList<>(alert.values()); 

    }
	public Alert getAlert(int id) {
		return alert.get(id);
	}

    
    public Alert AddAlert(Alert alrt){
    	alrt.setId(alert.size()+1);
    	alrt.setDate(new Date().toString());
    	
    	alert.put(alrt.getId(), alrt);
    	return alrt;
    	
    }
    public Alert updateAlert (Alert alrt){
    	if (alrt.getId()<1){
    		return null;
    	}
         alrt.setDate(new Date().toString());
    	alert.put(alrt.getId(), alrt);
    	return alrt;
    }
    
    public Alert removeAlert(Integer id){
    	return alert.remove(id);
    }
    public  List<Alert> getAllAlertByUser(int userId){
    	ArrayList<Alert> myAlert = new ArrayList<>(alert.values());
    	ArrayList<Alert>finalAlertList = new ArrayList<>();
    	for(int i =0; i< myAlert.size(); i++){
    		Alert alertAdder = myAlert.get(i);
    		if(alertAdder.getSenderID() ==userId){
    			finalAlertList.add(alertAdder);
    			
    			
    		}
    	}
    	return finalAlertList;
    	
    }
    
    
    
    
}

    

