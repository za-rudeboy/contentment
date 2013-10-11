package org.contentment.content.provider;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.contentment.content.inspector.meta.ContentMetaDataHolder;
import org.contentment.content.inspector.meta.MetaDataReader;
import org.contentment.content.inspector.meta.MetaSearch;

public class DiskContentProvider implements ContentProvider {

	private MetaDataReader metaDataReader;

	@Override
	public ContentHolder getContent(ContentMetaDataHolder contentMetaDataHolder)
			throws IOException {

		File file = new File(contentMetaDataHolder.getAbsoluteFilePath());
		ContentHolder contentHolder = new ContentHolder();

		contentHolder.setContent(FileUtils.readFileToByteArray(file));

		contentHolder.setBinary(contentMetaDataHolder.getContentType()
				.contains("text") ? false : true);
		
		contentHolder.setMetaData(contentMetaDataHolder);
		
		return contentHolder;
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

}