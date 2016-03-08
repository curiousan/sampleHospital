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
@Path("/chatbox")
public class ChatBoxResources {

    ChatBoxServices chatService;

    public ChatBoxResources() throws Exception {
        this.chatService = new ChatBoxServices();
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<ChatBox> getAllChatBOx() {
        return ChatBoxServices.getAllChatBOx();

    }

    @Path("/{ChatBoxId}")

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public ChatBox getAlert(@PathParam("ChatBoxId") int ID) {

        ChatBox newChatBOx = chatService.getChatBox(ID);
        if (newChatBOx == null) {
            throw new objectNotFound("chat room not Found");

        }
        return newChatBOx;

    }

    @POST
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)

    public ChatBox addBox(ChatBox chat) {

        return chatService.addChatBox(chat);

    }

    @PUT
    @Path("/{BoxId}")

    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)

    public ChatBox updtaeUser(ChatBox chat) {
        ChatBox newBox = chatService.getChatBox(chat.getChatBoxId());
        if (newBox == null) {
            throw new objectNotFound(" chat box not Found");

        }

        return chatService.getChatBox(newBox.getChatBoxId());

    }

    @DELETE
    @Path("/{BOxId}")
    @Produces(MediaType.APPLICATION_XML)

    public List<ChatBox> delBOx(@PathParam("BOxId") int ID) {
        chatService.removeChatBox(ID);
        return ChatBoxServices.getAllChatBOx();

    }

}
