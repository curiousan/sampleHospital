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
//services for private messages
public class MessageServices implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    UserServices userList;

    private static Map<Integer, Message> message = AllServices.getMessages();
    private static Map<Integer, User> user = AllServices.getUsers();
    private static Map<Integer, Alert> alert = AllServices.getAlert();

    public MessageServices() throws Exception {

    }

    // all the method  down are for all the message not based on user   
    public static List<Message> getMessages() {
        return new ArrayList<>(message.values());

    }
//gets the specific message with the given id

    public Message getMessage(int id) {
        return message.get(id);
    }
//add a new message

    public Message AddMessage(Message msg) {
        msg.setdate(new Date().toString());
        msg.setId(message.size() + 1);
        message.put(msg.getId(), msg);
        System.out.println(msg);
        return msg;

    }
//update the pre-existing message

    public Message updateMessage(Message msg) {
        if (msg.getId() < 1) {
            return null;
        }
        msg.setdate(new Date().toString());
        message.put(msg.getId(), msg);
        return msg;
    }
//removes the message which has the following id

    public Message removeMessage(Integer id) {
        return message.remove(id);
    }
    // all the messages are on based of user

    public List<Message> getAllMessageByUser(int userId) {
        ArrayList<Message> myMsg = new ArrayList<Message>(message.values());
        ArrayList<Message> finalMessageList = new ArrayList<Message>();
        for (int i = 0; i < myMsg.size(); i++) {
            Message msgAdder = myMsg.get(i);
            if (msgAdder.getRecieverID() == (userId) || msgAdder.getSenderID() == (userId)) {
                finalMessageList.add(msgAdder);

            }
        }
        return finalMessageList;

    }
//list of all the private message between two users

    public List<Message> getPrivateMessage(int senderId, int recieverId) {
        ArrayList<Message> myMsg = new ArrayList<Message>(message.values());
        ArrayList<Message> filterMsg = new ArrayList<Message>();

        for (Message msgAdder : myMsg) {
            if ((msgAdder.getRecieverID() == recieverId && msgAdder.getSenderID() == senderId)
                    || (msgAdder.getRecieverID() == senderId && msgAdder.getSenderID() == recieverId)) {
                filterMsg.add(msgAdder);
            }

        }
        System.out.println(filterMsg);
        return filterMsg;

    }
}
