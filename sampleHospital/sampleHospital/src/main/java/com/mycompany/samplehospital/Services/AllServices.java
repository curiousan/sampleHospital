package com.mycompany.samplehospital.Services;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.mycompany.samplehospital.model.Message;
import com.mycompany.samplehospital.model.Alert;
import com.mycompany.samplehospital.model.ChatBox;
import com.mycompany.samplehospital.model.User;

public class AllServices implements Serializable {

    /**
     *
     */
    //adds all the model objects list to a map and returns back the map
    private static final long serialVersionUID = 1L;
    private static Map<Integer, User> user = new HashMap<>();
    private static Map<Integer, Alert> Alert = new HashMap<>();
    private static Map<Integer, Message> message = new HashMap<>();
    private static Map<Integer, ChatBox> chat = new HashMap<>();

    public static Map<Integer, Message> getMessages() {
        return message;
    }

    public static Map<Integer, Alert> getAlert() {
        return Alert;
    }

    public static Map<Integer, User> getUsers() {
        return user;
    }

    public static Map<Integer, ChatBox> getChatBox() {
        return chat;
    }

}
