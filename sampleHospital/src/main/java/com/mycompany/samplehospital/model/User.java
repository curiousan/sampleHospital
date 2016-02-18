/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.samplehospital.model;

import java.io.Serializable;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import Authentication.HashPassword;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sandeshpoudel
 */
@XmlRootElement

public  class User implements Serializable {
    /**
	 * 
	 */
   
	private static final long serialVersionUID = 1L;
	private String title;
    private int age;
    private String Sex;
    private String Address;
    private int phoneNo;
    private String fullName;
    private int id;
    private Map<Integer, Message> allMessage;
    private Map<Integer,Alert> allAlerts;
    private String userName;
    private String passWord;
    private HashPassword hs ;
    	

    

    
	public User() {
    }
   

    public User(int id,String fullName, String Sex, Integer age, Integer  phoneNumber, String Address, String title,String userName,String password) throws Exception {
        hs = new HashPassword();
        this.id= id;
        this.fullName = fullName;
    	this.title = title;
        this.age = age;
        this.Sex = Sex;
        this.Address = Address;
        this.phoneNo = phoneNumber;
         setPassWord(password);
       // setPassWord(passWord) uncomment this and remove next line to execute on encryption mode;
       
    
        this.userName= userName;
        
    }
    public void setId(int id){
    	this.id= id;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSex(String Sex) {
        this.Sex = Sex;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public void setPhoneNo(int phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    @XmlElement
    public String getPassWord() {
        return passWord;
    }

    public  void setPassWord(String passWord) throws Exception {
          
                hs = new HashPassword();
                this.passWord = hs.encrypt(passWord);
          
        
      //  this.passWord = passWord;
    }

   
    
    
@XmlElement
    public String getFullName() {
        return fullName;
    }
    /*
    
    */
@XmlElement
  public int getId(){
	return id;
}
@XmlElement

    public String getTitle() {
        return title;
    }
@XmlElement

    public int getAge() {
        return age;
    }
@XmlElement

    public String getSex() {
        return Sex;
    }
@XmlElement

    public String getAddress() {
        return Address;
    }
@XmlElement

    public int getPhoneNo() {
        return phoneNo;
    }
   @XmlTransient
    public Map<Integer, Message> getAllMessage() {
	return allMessage;
}
    public void setAllMessage(Map<Integer, Message> allMessage) {
	this.allMessage = allMessage;
}   @XmlTransient

    public Map<Integer, Alert> getAllAlerts() {
	return allAlerts;
}
    public void setAllAlerts(Map<Integer, Alert> allAlerts) {
	this.allAlerts = allAlerts;
    }
   
    
  @Override
    public String toString(){
        return (getSex() +" "+ getAddress()+" "+ getPhoneNo() +" "+ getFullName());
    }
}
