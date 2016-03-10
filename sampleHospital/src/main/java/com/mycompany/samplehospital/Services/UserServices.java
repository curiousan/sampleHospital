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
    private static List<Integer>UserIdList;
	private static final long serialVersionUID = 1L;
	private static  Map<Integer,User>  users = AllServices.getUsers();
	
	public UserServices() throws Exception{
            

		users.put(1,new User(1,"Surendra Pandey","male",23,27939,"vantaa","boss","surendra","surendra","assistant"));
		users.put(2,new User(2,"Sandesh Poudel","male",23,27939,"helsinki","ASSISTANT","sandy","sandy","admin"));
                users.put(3,new User(3,"Sushil Bastola","male",23,27939,"vantaa","boss","sushil","sushil","user"));
		users.put(4,new User(4,"Sagar Aryal","male",23,27939,"helsinki","ASSISTANT","sagar","sagar","reporter"));
                users.put(4,new User(5,"Shurakshya kharel","female",21,27939,"Espoo","ASSISTANT","shurakshya","shurakshya","Designer"));

	}
	
	

    public static List<User> getUsers (){
         return new ArrayList<User>(users.values()); 

    }
	public User getUser(int id) {
		return users.get(id);
	}
     public static List<Integer> getAllUserID(){
             for(User user:users.values()){
                 UserIdList.add(user.getId());
             }
             return UserIdList;
             
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
