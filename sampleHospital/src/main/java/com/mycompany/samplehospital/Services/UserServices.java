/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.samplehospital.Services;


/**
 *
 * @author sandeshpoudel
 */
import com.mycompany.samplehospital.model.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserServices  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static  Map<Integer,User> users = AllServices.getUsers();

	
	public UserServices(){
		users.put(1,new User(1,"sandesh poudel","male",23,27939,"vantaa","boss"));
		users.put(2,new User(2,"surendra Pandey","male",23,27939,"helsinki","ASSISTANT"));

	}
	
	

    public static List<User> getUsers (){
         return new ArrayList<User>(users.values()); 

    }
	public User getUser(int id) {
		return users.get(id);
	}

    
    public User AddUser(User user){
    	user.setId(users.size()+1);
    	users.put(user.getId(), user);
    	return user;
    	
    }
    public User updateUser (User user){
    	if (user.getId()<1){
    		return null;
    	}
    	users.put(user.getId(), user);
    	return user;
    }
    
    public User removeUser(Integer id){
    	return users.remove(id);
    }

    
    
    
    
}
