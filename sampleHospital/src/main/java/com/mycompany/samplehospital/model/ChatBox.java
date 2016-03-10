/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.samplehospital.model;

import Authentication.HashPassword;
import com.mycompany.samplehospital.Services.AllServices;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sandeshpoudel
 */
@XmlRootElement
//this is the model class for chat room
//however is not yet connected to websocket
//contains name, password ,unique id  and the list of user which have access of it
//only admin is allowed to add the chat room
public class ChatBox {
    private String chatName;
    private String Password;
    private Integer chatBoxId;
    private AllServices service;
    private List<Integer>AllowedUser;
    private HashPassword hs ;

    public ChatBox(Integer id,String chatName, String Password, List<Integer> Recievers) throws Exception {
     
        this.chatName = chatName;
        
        setChatBoxId(id);
        this.AllowedUser = Recievers;
        
         setPassword(Password);
     
    }

    public ChatBox() {
    }
  
    
@XmlElement
    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }
@XmlElement
    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) throws Exception {
           hs= new HashPassword(); 
        this.Password = hs.encrypt(Password);
    }
@XmlElement
    public Integer getChatBoxId() {
        return chatBoxId;
    }

    public void setChatBoxId(Integer id) {
    this.chatBoxId=id;
      
        
    }
@XmlElement
    public List<Integer> getUsers() {
        return AllowedUser;
    }

    public void setUsers(List<Integer> Users) {
        this.AllowedUser = Users;
    }
    
}
