package com.mycompany.samplehospital.resources;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.mycompany.samplehospital.exception.objectNotFound;
import com.mycompany.samplehospital.Services.AlertServices;
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

/**
 *
 * @author sandesh poudel
 */
//resources to map all the notifications to the path
@Path("/alerts")
public class AlertResources {

    AlertServices service = new AlertServices();

    //gets all the notifications 
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<Alert> getAllAlert() {
        return AlertServices.getAlerts();

    }

    //gets the specfic notification based on its id
    @Path("/{AlertId}")

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Alert getAlert(@PathParam("AlertId") int ID) {

        Alert newAlert = service.getAlert(ID);
        if (newAlert == null) {
            //maps to the error resource not found 404
            throw new objectNotFound("Alert not Found");
//if  notification is available ,returns back the notification
        }
        return newAlert;

    }

    //post for adding the new notification  
    @POST
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)

    public Alert addAlert(Alert alrt) {

        return service.AddAlert(alrt);

    }
    //update existing notification     

    @PUT
    @Path("/{AlrtId}")

    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)

    public Alert updtaeAlert(Alert alrt) {
        Alert newAlert = service.getAlert(alrt.getId());
        //if given id does not contain any notification returns back 404 error
        if (newAlert == null) {
            throw new objectNotFound("Alert not Found");

        }
        //else updates the notification     
        return service.updateAlert(alrt);

    }

    //deletes the notification with the given id
    //even admin is not allowed to remove all the notifications at once
    @DELETE
    @Path("/{AlrtId}")
    @Produces(MediaType.APPLICATION_XML)

    public Alert delAlert(@PathParam("AlrtId") int ID) {
        return service.removeAlert(ID);

    }

}
