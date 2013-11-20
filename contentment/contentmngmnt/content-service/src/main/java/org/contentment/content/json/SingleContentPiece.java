package org.contentment.content.json;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "content")
public class SingleContentPiece {

	@XmlElement(name = "contentId")
	private String contentId;
	@XmlElement(name = "contentPath")
	private String contentPath;
	@XmlElement(name = "content")
	private String content;

	public SingleContentPiece(String contentId, String contentPath,
			String encodedContent) {
		
		this.contentId = contentId;
		this.contentPath = contentPath;
		this.content = encodedContent;
		
	}
	public String getContentId() {
		return contentId;
	}
	public void setContentId(String contentId) {
		this.contentId = contentId;
	}
	public String getContentPath() {
		return contentPath;
	}
	public void setContentPath(String contentPath) {
		this.contentPath = contentPath;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}


}
