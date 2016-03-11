package com.mycompany.samplehospital.exception;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.mycompany.samplehospital.model.Error;
//this class is for handling all the general exception which are not explicitly defined
//it maps to exception mapper
//

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

    @Override
    @Produces(MediaType.APPLICATION_XML)
    public Response toResponse(Throwable ex) {
        Error newGeneralError = new Error(ex.getMessage(), 500, "you typed wrong resource URL");

        return Response.status(Status.INTERNAL_SERVER_ERROR).entity(newGeneralError).build();
    }

}
