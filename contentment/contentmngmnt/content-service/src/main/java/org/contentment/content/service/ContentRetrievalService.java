package org.contentment.content.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.contentment.content.inspector.meta.MetaSearch;
import org.contentment.content.json.SingleContentPiece;
import org.contentment.content.provider.ContentHolder;
import org.contentment.content.provider.ContentProvider;
import org.contentment.content.spring.SpringApplicationContext;
import org.contentment.content.util.Base64Encoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/GetContent")
public class ContentRetrievalService {
	
	
	@GET
	@Produces("application/json")
	@Path("/ById")
	public Response getContentById(@QueryParam("contentId") String contentId) throws Exception{
		
		ContentProvider contentProvider = (ContentProvider)SpringApplicationContext.getBean("contentProvider");
		ContentHolder contentHolder = contentProvider.getContent(contentId, MetaSearch.BY_ID);
		
		SingleContentPiece singleContentPiece = new SingleContentPiece(contentHolder.getMetaData().getContentId(), contentHolder.getMetaData().getContentPath(),
				Base64Encoder.encodeContent(contentHolder.getContent()));
		
		logger.info("Request for content with id: {}", contentId);
		
		return Response.status(Response.Status.OK).entity(singleContentPiece).build();
		
	}
	
	@GET
	@Produces("application/json")
	@Path("/ByPath")
	public Response getContentByPath(@QueryParam("contentPath") String contentPath) throws Exception{
		
		ContentProvider contentProvider = (ContentProvider)SpringApplicationContext.getBean("contentProvider");
		ContentHolder contentHolder = contentProvider.getContent(contentPath, MetaSearch.BY_PATH);
		
		SingleContentPiece singleContentPiece = new SingleContentPiece(contentHolder.getMetaData().getContentId(), contentHolder.getMetaData().getContentPath(),
				Base64Encoder.encodeContent(contentHolder.getContent()));
		
		logger.info("Request for content with path: {}", contentPath);
		
		return Response.status(Response.Status.OK).entity(singleContentPiece).build();
	}
	
	
	Logger logger = LoggerFactory.getLogger(this.getClass().getName());

}
