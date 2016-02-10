/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.samplehospital.model;

import java.io.Serializable;
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
public class Alert  implements Serializable	{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int SenderID;
    
    private   String date;
    private String content; 
    private int AlertId;
    private  Map<Integer,Alert> alert = AllServices.getAlert();

    public  Alert() {
        
    }
    
    public Alert( User sender,String txt){
    	setSenderID(sender);
    	setDate(new Date().toString());
    	setContent(txt);
    	setId(alert.size()+1);
    	
    	
    }
    public void setDate(String Date){
    	this.date = Date;
    	
    }
    public void setId(int id){
    	this.AlertId= id;
    	
    }
    
    
    @XmlElement
    public int getId(){
    	return AlertId;
    }
    
    public void setSenderID (User sender) {
        this.SenderID = sender.getId();
    }
    @XmlElement

    public int getSenderID(){
    	return SenderID;
    }
    @XmlElement
   public String getDate(){
	   return date;
   }
    @XmlElement
   
    public String getContent() {
        return content;
    }
 
    public void setContent(String content) {
        this.content = content;
    }
    
}
