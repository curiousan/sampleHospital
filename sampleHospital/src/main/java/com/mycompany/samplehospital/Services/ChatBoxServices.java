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
public class ChatBoxServices {

    private static final long serialVersionUID = 1L;
    private static Map<Integer, User> users = AllServices.getUsers();
    private static Map<Integer, ChatBox> chatBoxList = AllServices.getChatBox();
    List<Integer> userList =  new ArrayList<Integer>();

    public ChatBoxServices() throws Exception {
        userList.add(1);
        userList.add(2);
        chatBoxList.put(1, new ChatBox(1,"case1", "case1password", userList));
        chatBoxList.put(2, new ChatBox(2,"case2", "case2password", userList));
    }

    public static List<ChatBox> getAllChatBOx() {
        return new ArrayList<>(chatBoxList.values());

    }

    public ChatBox getChatBox(int id) {
        return chatBoxList.get(id);
    }

    public  ChatBox addChatBox(ChatBox chatBox) {
        Integer size =chatBoxList.size();
        chatBox.setChatBoxId(size+1);

       chatBoxList.put(chatBox.getChatBoxId(), chatBox);
        return chatBox;

    }

    public ChatBox updateChatBox(ChatBox chatBox) {
        if (chatBox.getChatBoxId() < 1) {
            return null;
        }
        chatBoxList.put(chatBox.getChatBoxId(), chatBox);
        return chatBox;
    }

    public ChatBox removeChatBox(Integer id) {
        return chatBoxList.remove(id);
    }

}
