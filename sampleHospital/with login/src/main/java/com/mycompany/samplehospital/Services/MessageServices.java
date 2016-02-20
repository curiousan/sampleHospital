/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.samplehospital.Services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


import com.mycompany.samplehospital.model.User;
import com.mycompany.samplehospital.model.Alert;
import com.mycompany.samplehospital.model.Message;

/**
 *
 * @author sandeshpoudel
 */
public class MessageServices implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	UserServices userList ;
	
private static Map<Integer,Message> message = AllServices.getMessages();
private static Map<Integer,User> user = AllServices.getUsers();
private static Map<Integer,Alert> alert = AllServices.getAlert();


	
	public MessageServices() throws Exception{
          userList  = new UserServices();
		message.put(1, new Message(userList.getUser(1).getId(),userList.getUser(2).getId(),"hello there"));
		message.put(2, new Message(userList.getUser(2).getId(),userList.getUser(1).getId(),"hello again"));

	}
	
	// all the method  down are for all the message not based on user   
    public static  List<Message> getMessages (){
         return new ArrayList<Message>(message.values()); 

    }
	public Message getMessage(int id) {
		return message.get(id);
	}

    
    public Message AddMessage(Message msg){
    msg.setdate(new Date().toString());
    msg.setId(message.size()+1);
      message.put(msg.getId(), msg);
      System.out.println(msg);
       return msg;
    	
    }
    public Message updateMessage (Message msg){
    	if (msg.getId()<1){
    		return null;
    	}
    	msg.setdate(new Date().toString());
    	message.put(msg.getId(), msg);
    	return msg;
    }
    
    public Message removeMessage(Integer id){
    	return message.remove(id);
    }
 // all the messages are on based of user
    
    public  List<Message> getAllMessageByUser(int userId){
    	ArrayList<Message> myMsg = new ArrayList<Message>(message.values());
    	ArrayList<Message>finalMessageList = new ArrayList<Message>();
    	for(int i =0; i< myMsg.size(); i++){
    		Message msgAdder = myMsg.get(i);
    		if (msgAdder.getRecieverID() ==(userId)|| msgAdder.getSenderID() ==(userId)){
    			finalMessageList.add(msgAdder);
    			
    		}
    	}
    	return finalMessageList;
    	
    }
    /*
    
   public Message getMessageByUser(int userId, int messageId){
	   Map<Integer,Message> messageList = user.get(key);
	   
        return messageList.get(messageId);
    
    
   }*/
   /*
   public Message  addMessageByUser(int userId,Message msg){
   	Map<Integer,Message> messageList = user.get(userId).getAllMessage();

	   int size = user.size()+1;
	   msg.setId(size);

	   message.put(size,msg);
	   return msg;
	   
   }
    
   public Message updateMessageByUser(int userId,Message msg){
	   if (msg.getId()<1){
		   return null;
	   }
	   msg.setSender(user.get(userId));
	 
	 message.put(msg.getId(), msg);
	 return msg;
   }
    
   public List<Message> deleteMessageByUser(int userId,int messageId){
	message.remove(messageId);
    return getAllMessageByUser(userId);
	   

	   
   }
   */
    
}

    

