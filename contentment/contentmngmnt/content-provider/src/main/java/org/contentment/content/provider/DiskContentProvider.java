package org.contentment.content.provider;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.content.cache.CacheManager;
import org.contentment.content.inspector.meta.ContentMetaDataHolder;
import org.contentment.content.inspector.meta.MetaDataReader;
import org.contentment.content.inspector.meta.MetaSearch;

public class DiskContentProvider implements ContentProvider {

	private MetaDataReader metaDataReader;
	private CacheManager cacheManager;
	private final static String CACHE_NAME = ContentHolder.class.getName();

	@Override
	public ContentHolder getContent(ContentMetaDataHolder contentMetaDataHolder)
			throws IOException {

		Object cachedContent = cacheManager.getFromCache(CACHE_NAME, contentMetaDataHolder.getContentId());
		
		if (cachedContent != null){
			
			ContentHolder contentHolder = (ContentHolder) cachedContent;
			return contentHolder;
			
		} else {
			
			File file = new File(contentMetaDataHolder.getAbsoluteFilePath());
			ContentHolder contentHolder = new ContentHolder();
			contentHolder.setContent(FileUtils.readFileToByteArray(file));
			contentHolder.setBinary(contentMetaDataHolder.getContentType()
					.contains("text") ? false : true);
			contentHolder.setMetaData(contentMetaDataHolder);
			
			
			cacheManager.putInCache(CACHE_NAME, contentMetaDataHolder.getContentId(), contentHolder);
			
			return contentHolder;
			
		}
		
		
		
	}

	@Override
	public ContentHolder getContent(String value, MetaSearch metaSearch)
			throws Exception {
		ContentMetaDataHolder contentMetaDataHolder = metaDataReader
				.getMetadata(value, metaSearch);
		return getContent(contentMetaDataHolder);
	}

	@Override
	public Map<String, ContentHolder> getMultipleContent(String[] multipleIds, MetaSearch metaSearch) throws Exception {
		
		Map<String, ContentHolder> contentMap = new HashMap<String, ContentHolder>();
		
		for (int i = 0; i < multipleIds.length; i++){
			contentMap.put(multipleIds[i], getContent(multipleIds[i], metaSearch));
		}
		
		return contentMap;
	}

	public MetaDataReader getMetaDataReader() {
		return metaDataReader;
	}

	public void setMetaDataReader(MetaDataReader metaDataReader) {
		this.metaDataReader = metaDataReader;
	}

	public CacheManager getCacheManager() {
		return cacheManager;
	}

	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

}
