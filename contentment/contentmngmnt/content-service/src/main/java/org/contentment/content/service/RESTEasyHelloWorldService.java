/**
 * 
 */
package org.contentment.content.service;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.contentment.content.spring.SpringApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * @author Rudy Adams
 *
 */

@Path("/RESTEasyHelloWorld")
public class RESTEasyHelloWorldService {
	
	Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	
	@GET
	@Path("/{pathParameter}")
	public Response responseMsg(@PathParam("pathParameter") String pathParameter, @DefaultValue ("Nothing to say") @QueryParam("queryParameter") String queryParameter){
		String response = "Hello from: " +pathParameter+ ":" + queryParameter;
		
		logger.info("Saying hello to {}", pathParameter);
		
		SpringApplicationContext.getBean("contentProvider");
		
		
		return Response.status(Response.Status.OK).entity(response).build();
		
	}
}
