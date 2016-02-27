package com.mycompany.samplehospital.resources;

import com.mycompany.samplehospital.model.Alert;
import com.mycompany.samplehospital.model.Message;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.mycompany.samplehospital.model.User;

import com.mycompany.samplehospital.Services.UserServices;
import com.mycompany.samplehospital.exception.objectNotFound;
import com.mycompany.samplehospital.Services.AlertServices;
import com.mycompany.samplehospital.Services.MessageServices;

import java.util.List;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
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
@Produces(MediaType.APPLICATION_XML)
@Path("/users")
public class userResources {

    UserServices service;

    public userResources() throws Exception {
        service = new UserServices();
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    @PermitAll
    public List<User> getAllUser() {
        return UserServices.getUsers();

    }

    @Path("/{userId}")

    @GET
    @Produces(MediaType.APPLICATION_XML)
    @PermitAll
    public User getUser(@PathParam("userId") int ID) throws Exception {
        User myUserList = service.getUser(ID);
        if (myUserList == null) {
            throw new objectNotFound("User not Found");
        } else {
            return myUserList;
        }

    }

    @POST
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    @RolesAllowed("admin")

    public User addUser(User user) throws Exception {

        return service.AddUser(user);

    }

    @PUT
    @Path("/{userId}")

    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    @RolesAllowed({"admin", "user"})

    public User updtaeUser(User user) throws Exception {

        return service.updateUser(user);

    }

    @DELETE
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_XML)
    @RolesAllowed("admin")

    public User delUser(@PathParam("userId") int ID) throws Exception {

        return service.removeUser(ID);

    }

    @Path("/{userId}/messages")

    @GET
    @Produces(MediaType.APPLICATION_XML)
    @RolesAllowed({"admin", "user"})

    public List<Message> getAllMessageByUser(@PathParam("userId") int ID) throws Exception {
        MessageServices mservice = new MessageServices();

        List<Message> messageUserList = mservice.getAllMessageByUser(ID);
        if (messageUserList == null) {
            throw new objectNotFound("messages not Found");

        }
        return messageUserList;

    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("/{userId}/alerts")
    @RolesAllowed({"admin", "user"})

    public List<Alert> AlertsResources(@PathParam("userId") int userId) {
        AlertServices myAlert = new AlertServices();

        List<Alert> newAlertUserList = myAlert.getAllAlertByUser(userId);
        if (newAlertUserList == null) {
            throw new objectNotFound("messages not Found");

        }
        return newAlertUserList;

    }
    /*
  @Path("/{userId}/messages/{messageId}")
    
    @GET
    @Produces(MediaType.APPLICATION_XML)
    
    public  Message  getMessageByUser(@PathParam("userId") int userId,@PathParam("messageId") int messageId){
        MessageServices mservice = new MessageServices();
        
        return mservice.getMessageByUser(userId,messageId);
             
        }
  @Path("/{userId}/messages/{messageId}")
  
  @PUT
  @Produces(MediaType.APPLICATION_XML)
  @Consumes(MediaType.APPLICATION_XML)

  
  
  public  Message  updateMessageByUser(@PathParam("userId") int userId,Message message){
      MessageServices mservice = new MessageServices();
      return mservice.updateMessageByUser(userId, message);
           
      }
  
  @Path("/{userId}/messages/{messageId}")
  
  @PUT
  @Produces(MediaType.APPLICATION_XML)
 

  
  
  public  List<Message>  deleteMessageByUser(@PathParam("userId") int userId,@PathParam("messageId") int messageId){
      MessageServices mservice = new MessageServices();
      return mservice.deleteMessageByUser(userId,messageId);
           
      }

     */

}
