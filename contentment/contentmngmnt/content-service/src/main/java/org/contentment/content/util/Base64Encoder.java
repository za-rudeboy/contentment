package org.contentment.content.util;

import org.apache.commons.codec.binary.Base64;

public class Base64Encoder {
	
	static public String encodeContent(byte[] content){
		return Base64.encodeBase64String(content);
	}

}
