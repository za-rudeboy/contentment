package org.contentment.content.inspector.meta;

import java.io.IOException;
import java.util.Map;

/**
 * @author Rudy Adams
 *
 */
public interface MetaDataReader {
	
	public Map<String, ContentMetaDataHolder> getAllContentMetadata() throws Exception;
	public void configureReader(Map<String, String> configs) throws Exception;
	public ContentMetaDataHolder getMetadata (String value, MetaSearch metaSearch) throws Exception;
	
	/**
	 * Loads metadata into memory, so that it's in memory when needed. 
	 * Otherwise metadata will be loaded on first use.
	 * @return true if successfull
	 * @throws IOException 
	 */
	public boolean preLoadMetaData() throws IOException;

}
