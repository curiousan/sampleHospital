package com.mycompany.samplehospital.resources;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.mycompany.samplehospital.model.User;

import com.mycompany.samplehospital.Services.UserServices;
import com.mycompany.samplehospital.exception.objectNotFound;
import com.mycompany.samplehospital.Services.AllServices;
import com.mycompany.samplehospital.Services.MessageServices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.mycompany.samplehospital.model.Message;

/**
 *
 * @author sandesh poudel
 */
@Path("/messages")
public class MessageResources {
    
    
 MessageServices service = new MessageServices();

 
 

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<Message> getAllMessage(){
    	
    return service.getMessages();

    
    }
    @Path("/{MsgId}")

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Message getMessage(@PathParam("MsgId") int ID ){
        
     Message newMessage = service.getMessage(ID);
     if(newMessage == null){
     	throw new objectNotFound("Message not Found");	

     } return newMessage;
         
    }
    
     
    @POST
    @Produces(MediaType.APPLICATION_XML)
      @Consumes(MediaType.APPLICATION_XML)
     
     
    public Message addUser(Message msg ){
    	
        return service.AddMessage(msg);
        
    
         
    }
       
     
    @PUT
        @Path("/{MdgId}")

    @Produces(MediaType.APPLICATION_XML)
      @Consumes(MediaType.APPLICATION_XML)
     
     
    public Message updtaeUser(Message msg){
    	Message newMessage = service.getMessage(msg.getId());
        if(newMessage == null){
        	throw new objectNotFound("Message not Found");	

        }
    return service.updateMessage(msg);
         
    }
    @DELETE 
      @Path("/{MessageId}")
       @Produces(MediaType.APPLICATION_XML)

    public Message delUser(@PathParam("MessageId") int ID){
    	return service.removeMessage(ID);
    	
        
    }
    
    
    
    
}
