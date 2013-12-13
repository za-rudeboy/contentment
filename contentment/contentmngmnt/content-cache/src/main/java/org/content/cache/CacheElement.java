/**
 * 
 */
package org.content.cache;

import java.io.Serializable;


public class CacheElement implements Serializable {


	private static final long serialVersionUID = -8782086514252743309L;
	private String key;
	private String base64EncodedContent;
	private long lastmodified;
	
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getBase64EncodedContent() {
		return base64EncodedContent;
	}
	public void setBase64EncodedContent(String base64EncodedContent) {
		this.base64EncodedContent = base64EncodedContent;
	}
	public long getLastmodified() {
		return lastmodified;
	}
	public void setLastmodified(long lastmodified) {
		this.lastmodified = lastmodified;
	}
	
	
	

}
