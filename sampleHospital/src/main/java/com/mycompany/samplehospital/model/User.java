/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.samplehospital.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sandeshpoudel
 */
@XmlRootElement

public class User {
    private String title;
    private int age;
    private String Sex;
    private String Address;
    private int phoneNo;
    private String fullName;
    private int id;

    

    public User() {
    }

    public User(int id,String fullName, String Sex, Integer age, Integer  phoneNumber, String Address, String title) {
        this.id= id;
        this.fullName = fullName;
    	this.title = title;
        this.age = age;
        this.Sex = Sex;
        this.Address = Address;
        this.phoneNo = phoneNumber;
        
        
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
   
    
  @Override
    public String toString(){
        return (getSex() +" "+ getAddress()+" "+ getPhoneNo() +" "+ getFullName());
    }
}
