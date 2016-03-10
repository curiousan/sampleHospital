package com.mycompany.samplehospital.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import com.mycompany.samplehospital.model.Error;
//handles exception if the resource is not found
//with the error code 404 and some information

@Provider
public class ObjectNotFoundExceptionMapper implements ExceptionMapper<objectNotFound> {

	

	@Override
	public Response toResponse(objectNotFound ex) {
		Error errorMsg = new Error(ex.getMessage(),404," you have no content buddy hahah");
		
		return Response.status(Status.NOT_FOUND).entity(errorMsg).build();
	}

	
}
