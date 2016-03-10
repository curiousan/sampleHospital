/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.samplehospital.Services;

import com.mycompany.samplehospital.model.ChatBox;
import com.mycompany.samplehospital.model.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sandeshpoudel
 */
//service for group chat

public class ChatBoxServices {

    private static final long serialVersionUID = 1L;
    private static Map<Integer, User> users = AllServices.getUsers();
    private static Map<Integer, ChatBox> chatBoxList = AllServices.getChatBox();
    List<Integer> userList =  new ArrayList<Integer>();
 //adds two chat room for testing
    //however admin can add chat room as needed
    public ChatBoxServices() throws Exception {
        userList.add(1);
        userList.add(2);
        userList.add(3);
        userList.add(4);
               
        userList.add(5);
        chatBoxList.put(1, new ChatBox(1,"case1", "case1password", userList));
        
    }

    //gets all the chat room lists
    public static List<ChatBox> getAllChatBOx() {
        return new ArrayList<>(chatBoxList.values());

    }
//gets the specific chat room based on id
    public ChatBox getChatBox(int id) {
        return chatBoxList.get(id);
    }
//service to add the chat room and next available id is set here
    public  ChatBox addChatBox(ChatBox chatBox) {
        Integer size =chatBoxList.size();
        chatBox.setChatBoxId(size+1);

       chatBoxList.put(chatBox.getChatBoxId(), chatBox);
        return chatBox;

    }
//update the pre-existing chat room
    public ChatBox updateChatBox(ChatBox chatBox) {
        if (chatBox.getChatBoxId() < 1) {
            return null;
        }
        chatBoxList.put(chatBox.getChatBoxId(), chatBox);
        return chatBox;
    }
//deletes the existing chat room having id
    public ChatBox removeChatBox(Integer id) {
        return chatBoxList.remove(id);
    }

}
