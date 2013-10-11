/**
 * 
 */
package org.contentment.content.inspector.meta;

import java.io.Serializable;

/**
 * Holds the metadata for a particular piece of content.
 * 
 * @author Rudy Adams
 *
 */
public class ContentMetaDataHolder implements Serializable {
	

	private static final long serialVersionUID = -1625730030028932089L;
	private String relativeFilePath;
	private String absoluteFilePath;
	private String contentPath;
	private String contentId;
	private String contentType;
	

	public String getRelativeFilePath() {
		return relativeFilePath;
	}
	public void setRelativeFilePath(String relativeFilePath) {
		this.relativeFilePath = relativeFilePath;
	}
	public String getContentPath() {
		return contentPath;
	}
	public void setContentPath(String contentPath) {
		this.contentPath = contentPath;
	}
	public String getContentId() {
		return contentId;
	}
	public void setContentId(String contentId) {
		this.contentId = contentId;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getAbsoluteFilePath() {
		return absoluteFilePath;
	}
	public void setAbsoluteFilePath(String absoluteFilePath) {
		this.absoluteFilePath = absoluteFilePath;
	}
	
	

}
