/**
 * 
 */
package org.contentment.content.provider;

import java.io.IOException;
import java.util.Map;

import org.contentment.content.inspector.meta.ContentMetaDataHolder;
import org.contentment.content.inspector.meta.MetaSearch;

/**
 * Provides the actual content;
 * 
 * @author Rudy Adams
 *
 */
public interface ContentProvider {
	
	public ContentHolder getContent(ContentMetaDataHolder contentMetaDataHolder) throws IOException;
	
	public ContentHolder getContent(String value, MetaSearch metaSearch) throws Exception;
	
	public Map<String, ContentHolder> getMultipleContent(String[] multipleValues, MetaSearch metaSearch) throws Exception;

}
