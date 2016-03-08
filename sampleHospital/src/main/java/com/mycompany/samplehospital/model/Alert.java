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
import java.util.List;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
 *
 * @author sandeshpoudel
 */
@XmlRootElement
public class Alert implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private int SenderID;

  

    private String date;
    private String content;
    private int AlertId;
    private Map<Integer, Alert> alert; 
    private String Type;
    private List<Integer>recieversList;

    // private ArrayList<Integer> userList;
    public Alert() {

    }

    public Alert(Integer id,Integer sender, String txt,String type,List<Integer>recievers) {
        alert= AllServices.getAlert();
        setSenderID(sender);
        setDate(new Date().toString());
        setContent(txt);
        setId(id);
        setType(type);
         this.recieversList=recievers;

    }

    public void setDate(String Date) {
        this.date = Date;

    }

    public void setId(int id) {
        this.AlertId = id;

    }

    @XmlElement
    public int getId() {
        return AlertId;
    }

    public void setSenderID(Integer sender) {
        this.SenderID = sender;
    }

    @XmlElement

    public int getSenderID() {
        return SenderID;
    }

    @XmlElement
    public String getDate() {
        return date;
    }

    @XmlElement

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
 @XmlElementWrapper
    public List<Integer> getUserList() {
        return recieversList;
    }

    public void setUserList(List<Integer> recieversList) {
        this.recieversList= recieversList;
    }
   
      public String getType() {
        return Type;
    }
       @XmlElement

    public void setType(String Type) {
        this.Type = Type;
    }
    @Override
    public String toString() {
        return " " + getDate() + " " + getContent() + " " + getSenderID();
    }

}
