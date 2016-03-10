
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
            recieverList.add(3);
            recieverList.add(4);
            recieverList.add(5);
           

		alert.put(1, new Alert(1,1,"hello all!! emergency" ,"Surendra Pandey","News",recieverList));
		alert.put(2, new Alert(2,2,"Our expansion progress continues to make great strides in all areas! "
                         + "Parking Construction on parking is currently underway, as concrete for the main hospital drive has been poured."
                         + " A newly designed parking lot will e open in late June, "
                         + "located in front of the main ER entrance—to help make your parking experience "
                         + "at Jorvi a convenient one! Good Day To all !! ", "Sandesh Poudel","Announcements", recieverList));
                  alert.put(3, new Alert(3,3, "We have vacancies in the following sectors","Sushil Bastola","Vacancy", recieverList));
                   alert.put(4, new Alert(4,4, "Jorvi Hospital today announced CLARITY Undiagnosed, its second international competition"
                           + " to advance standards for genomic analysis in medicine. In this $25,000 crowd-sourcing "
                           + "challenge, up to 30 research teams will seek to solve the medical mysteries "
                           + "of five patients with undiagnosed disease and provide clinically useful, actionable information.","Sagar Aryal","Events", recieverList));

alert.put(5, new Alert(5,5,"Need Report" ,"Surakshya Kharel","Vacancy",recieverList));
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

    

