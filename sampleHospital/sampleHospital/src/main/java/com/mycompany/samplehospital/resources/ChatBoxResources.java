/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.samplehospital.resources;

import com.mycompany.samplehospital.Services.ChatBoxServices;
import com.mycompany.samplehospital.exception.objectNotFound;
import com.mycompany.samplehospital.model.ChatBox;
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
 * @author sandeshpoudel
 */
//maps to all the chat room available
@Path("/chatbox")
public class ChatBoxResources {

    ChatBoxServices chatService;

    public ChatBoxResources() throws Exception {
        this.chatService = new ChatBoxServices();
    }
//gets all the chat room

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<ChatBox> getAllChatBOx() {
        return ChatBoxServices.getAllChatBOx();

    }
//gets the chat room based on the given id

    @Path("/{ChatBoxId}")

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public ChatBox getAlert(@PathParam("ChatBoxId") int ID) {

        ChatBox newChatBOx = chatService.getChatBox(ID);
        if (newChatBOx == null) {
            //if the given id does not contain any chat room returns back no object found error
            throw new objectNotFound("chat room not Found");

        }
        //else returns back the chat room
        return newChatBOx;

    }
//to add a new  chat room

    @POST
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)

    public ChatBox addBox(ChatBox chat) {

        return chatService.addChatBox(chat);

    }
//update a new chat room

    @PUT
    @Path("/{BoxId}")

    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)

    public ChatBox updtaeUser(ChatBox chat) {
        ChatBox newBox = chatService.getChatBox(chat.getChatBoxId());
        if (newBox == null) { //if given id does not contain any chat room
            //maps to not found error
            throw new objectNotFound(" chat box not Found");

        }
//else returns back updated chat room
        return chatService.getChatBox(newBox.getChatBoxId());

    }
//delete the chat room

    @DELETE
    @Path("/{BOxId}")
    @Produces(MediaType.APPLICATION_XML)

    public List<ChatBox> delBOx(@PathParam("BOxId") int ID) {
        chatService.removeChatBox(ID);
        return ChatBoxServices.getAllChatBOx();

    }

}
