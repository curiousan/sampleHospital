/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.samplehospital.model;

import java.util.Date;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.mycompany.samplehospital.Services.AllServices;

/**
 *
 * @author sandeshpoudel
 */
@XmlRootElement
public class Message {
    private int SenderID;
    private int recieverID;
    private   String date;
    private String content; 
    private int MessageId;
    private  Map<Integer,Message> message = AllServices.getMessages();

    public  Message() {
        
    }
    
    public Message( Integer senderID,Integer recieverID,String txt){
    	this.SenderID = senderID;
    	this.recieverID = recieverID;
    	this.date = new Date().toString();
    	this.content = txt;
    	this.MessageId = message.size()+1;
    	
    }
    public void setdate(String Date){
    	this.date = Date;
    }
    public void setId(int id){
    	this.MessageId = id;
    	
    }
    @XmlElement
    public int getId(){
    	return MessageId;
    }
    
    public void setSender (User sender) {
        this.SenderID = sender.getId();
    }

    public void setReciever (User reciever) {
        this.recieverID = reciever.getId();
    }
    @XmlElement
    public Integer getSenderID(){
    	return SenderID;
    }
    @XmlElement
    public Integer getRecieverID(){
    	return recieverID;
    }
    @XmlElement
    public String getContent() {
        return content;
    }
    @XmlElement
 public String getDate(){
	 return date;
 }
    public void setContent(String content) {
        this.content = content;
    }
    
    
}
