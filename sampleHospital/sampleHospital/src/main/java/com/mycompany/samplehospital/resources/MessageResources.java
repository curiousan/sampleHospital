package com.mycompany.samplehospital.resources;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.mycompany.samplehospital.exception.objectNotFound;
import com.mycompany.samplehospital.Services.MessageServices;

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
import javax.ws.rs.QueryParam;

/**
 *
 * @author sandesh poudel
 */
//resource for handling all the private messsage
@Path("/messages")
public class MessageResources {

    MessageServices service;

    public MessageResources() throws Exception {

        service = new MessageServices();
    }

//gets all the messages
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<Message> getAllMessage(@QueryParam("firstUserId") int firstId,
            @QueryParam("secondUserId") int secondId) {
        //if the query param contains two user id it will returns all the private messages between them
        if ((firstId < 0) || (secondId < 0)) {
            return service.getPrivateMessage(firstId, secondId);

        }

        return service.getMessages();

    }

    //returns the message having the id given
    @Path("/{MsgId}")

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Message getMessage(@PathParam("MsgId") int ID) {

        Message newMessage = service.getMessage(ID);
        if (newMessage == null) {
            throw new objectNotFound("Message not Found");

        }
        return newMessage;

    }

    //add a new message 
    @POST
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)

    public Message addUser(Message msg) {

        return service.AddMessage(msg);

    }

    //update the message 
    @PUT
    @Path("/{MsgId}")

    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)

    public Message updtaeUser(Message msg) {
        Message newMessage = service.getMessage(msg.getId());
        if (newMessage == null) {
            throw new objectNotFound("Message not Found");

        }
        return service.updateMessage(msg);

    }

    @DELETE
    @Path("/{MessageId}")
    @Produces(MediaType.APPLICATION_XML)

    public Message delUser(@PathParam("MessageId") int ID) {
        return service.removeMessage(ID);

    }
    //resource for getting the private message between two users

    @GET
    @Path("/{senderId}/{recieverId}")
    @Produces(MediaType.APPLICATION_XML)
    public List<Message> getPrivateMessage(@PathParam("senderId") int senderId, @PathParam("recieverId") int recieverId) {
        return service.getPrivateMessage(senderId, recieverId);

    }

}
