package org.contentment.content.provider;

import org.contentment.content.inspector.meta.ContentMetaDataHolder;

public class ContentHolder {
	
	private boolean binary;
	private byte[] content;
	private ContentMetaDataHolder metaData;
	
	public ContentMetaDataHolder getMetaData() {
		return metaData;
	}
	public void setMetaData(ContentMetaDataHolder metaData) {
		this.metaData = metaData;
	}
	
	public boolean isBinary() {
		return binary;
	}
	public void setBinary(boolean binary) {
		this.binary = binary;
	}
	public byte[] getContent() {
		return content;
	}
	public void setContent(byte[] content) {
		this.content = content;
	}
	
	

}
