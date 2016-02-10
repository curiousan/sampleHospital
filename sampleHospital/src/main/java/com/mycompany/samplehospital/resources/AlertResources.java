package com.mycompany.samplehospital.resources;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.mycompany.samplehospital.model.User;

import com.mycompany.samplehospital.Services.UserServices;
import com.mycompany.samplehospital.exception.objectNotFound;
import com.mycompany.samplehospital.Services.AlertServices;
import com.mycompany.samplehospital.Services.AllServices;
import com.mycompany.samplehospital.Services.MessageServices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.mycompany.samplehospital.model.Alert;
import com.mycompany.samplehospital.model.Message;

/**
 *
 * @author sandesh poudel
 */
@Path("/alerts")
public class AlertResources {
    
    
 AlertServices service = new AlertServices();

 
 

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<Alert> getAllAlert(){
    return AlertServices.getAlerts();

    
    }
    @Path("/{AlertId}")

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Alert getAlert(@PathParam("AlertId") int ID ){
        
     Alert newAlert = service.getAlert(ID);
     if (newAlert== null){
     	throw new objectNotFound("Alert not Found");	

     } return newAlert;
         
    }
    
     
    @POST
    @Produces(MediaType.APPLICATION_XML)
      @Consumes(MediaType.APPLICATION_XML)
     
     
    public Alert addAlert(Alert alrt ){
    	
        return service.AddAlert(alrt);
        
    
         
    }
       
      @PUT
        @Path("/{AlrtId}")

    @Produces(MediaType.APPLICATION_XML)
      @Consumes(MediaType.APPLICATION_XML)
     
     
    public Alert updtaeUser(Alert alrt){
    	  Alert newAlert = service.getAlert(alrt.getId());
    	     if (newAlert== null){
    	     	throw new objectNotFound("Alert not Found");	

    	     }
        
    return service.updateAlert(alrt);
         
    }
    @DELETE 
      @Path("/{AlrtId}")
       @Produces(MediaType.APPLICATION_XML)

    public Alert delAlert(@PathParam("AlrtId") int ID){
    	return service.removeAlert(ID);
    	
        
    }
    
    
    
    
}
