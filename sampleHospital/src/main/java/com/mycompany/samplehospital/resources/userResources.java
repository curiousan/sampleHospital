package com.mycompany.samplehospital.resources;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.mycompany.samplehospital.model.User;

import com.mycompany.samplehospital.Services.UserServices;
import com.mycompany.samplehospital.Services.AllServices;
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

/**
 *
 * @author sandesh poudel
 */
@Path("/users")
public class userResources {
    
    
 UserServices service = new UserServices();

 
 

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<User> getAllUser(){
    return service.getUsers();

    
    }
    @Path("/{userId}")

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public User getUser(@PathParam("userId") int ID ){
        
     return service.getUser(ID);
         
    }
    
     
    @POST
    @Produces(MediaType.APPLICATION_XML)
      @Consumes(MediaType.APPLICATION_XML)
     
     
    public User addUser(User user ){
    	
        return service.AddUser(user);
        
    
         
    }
       
     
    @PUT
        @Path("/{userId}")

    @Produces(MediaType.APPLICATION_XML)
      @Consumes(MediaType.APPLICATION_XML)
     
     
    public User updtaeUser(User user){
        
    return service.updateUser(user);
         
    }
    @DELETE 
      @Path("/{userId}")
       @Produces(MediaType.APPLICATION_XML)

    public User delUser(@PathParam("userId") int ID){
    	return service.removeUser(ID);
    	
        
    }
    
    
    
    
}
