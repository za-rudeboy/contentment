/**
 * 
 */
package org.contentment.content.inspector.meta;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;

/**
 * @author Rudy Adams
 *
 */
public class MetaDataVisitor extends SimpleFileVisitor<Path> {

	
	Map<String, String> pathToIdMap = new HashMap<>();
	Map<String, ContentMetaDataHolder> metaDataMap = new HashMap<>();
	
	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
			throws IOException {
		
		if (file.toString().contains(".cm")){
			File metaDataFile = file.toFile();
			Properties properties = new Properties();
			properties.load(new ByteArrayInputStream(FileUtils
					.readFileToByteArray(metaDataFile)));
			
			ContentMetaDataHolder contentMetaDataHolder = new ContentMetaDataHolder();
			contentMetaDataHolder.setContentId(properties.getProperty("content.id"));
			contentMetaDataHolder.setContentPath(properties.getProperty("content.path"));
			contentMetaDataHolder.setContentType(properties.getProperty("content.type"));
			contentMetaDataHolder.setFilePath(properties.getProperty("file.path"));
			
			metaDataMap.put(contentMetaDataHolder.getContentId(), contentMetaDataHolder);
			pathToIdMap.put(contentMetaDataHolder.getContentPath(), contentMetaDataHolder.getContentId());
			
		}
		
		return FileVisitResult.CONTINUE;
	}

	public Map<String, String> getPathToIdMap() {
		return pathToIdMap;
	}

	public Map<String, ContentMetaDataHolder> getMetaDataMap() {
		return metaDataMap;
	}

	
	

}
