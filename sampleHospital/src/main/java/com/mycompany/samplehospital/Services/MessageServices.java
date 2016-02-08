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
import com.mycompany.samplehospital.model.Message;

/**
 *
 * @author sandeshpoudel
 */
public class MessageServices {
	UserServices userList = new UserServices();
	
private  Map<Integer,Message> message = AllServices.getMessages();

	
	public MessageServices(){
		message.put(1, new Message(userList.getUser(1).getId(),userList.getUser(2).getId(),"hello there"));
		message.put(2, new Message(userList.getUser(2).getId(),userList.getUser(1).getId(),"hello again"));

	}
	
	

    public  List<Message> getMessages (){
         return new ArrayList<Message>(message.values()); 

    }
	public Message getMessage(int id) {
		return message.get(id);
	}

    
    public Message AddMessage(Message msg){
    	msg.setId(message.size()+1);
    	message.put(msg.getId(), msg);
    	return msg;
    	
    }
    public Message updateMessage (Message msg){
    	if (msg.getId()<1){
    		return null;
    	}
    	message.put(msg.getId(), msg);
    	return msg;
    }
    
    public Message removeMessage(Integer id){
    	return message.remove(id);
    }

    
    
    
    
}

    

