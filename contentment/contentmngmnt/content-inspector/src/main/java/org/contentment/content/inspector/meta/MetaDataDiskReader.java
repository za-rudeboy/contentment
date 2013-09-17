/**
 * 
 */
package org.contentment.content.inspector.meta;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import org.sideproject.configuration.ConfigurationProvider;

/**
 * Reads content metadata from disk.
 * 
 * @author Rudy Adams
 *
 */

public class MetaDataDiskReader implements MetaDataReader {

	ConfigurationProvider configurationProvider;
	MetaDataVisitor metaDataVisitor;
	
	Map<String, String> pathToIdMap;
	Map<String, ContentMetaDataHolder> metaDataMap;
	

	private void loadMetaDataIntoMemory() throws IOException {
		String baseDirectory = getBaseDirectory();
		loadAllMetaDataFromBaseDir(baseDirectory);
	}


	private void loadAllMetaDataFromBaseDir(String baseDirectory) throws IOException {
		
		Files.walkFileTree(Paths.get(new File(baseDirectory).toURI()), metaDataVisitor);
		metaDataMap = metaDataVisitor.getMetaDataMap();
		pathToIdMap = metaDataVisitor.getPathToIdMap();
		
	}


	/**
	 * returns all metadata that was read into memory as a Map
	 * in which the key is the content id;
	 * 
	 */
	
	public Map<String, ContentMetaDataHolder> getAllContentMetadata() throws Exception {
		
		if (metaDataMap == null){
			loadMetaDataIntoMemory();
		} 
		
		return metaDataMap;
	}
	
	public ContentMetaDataHolder getMetadata(String value, MetaSearch metaSearch)
			throws Exception {
		
		if (metaDataMap == null){
			loadMetaDataIntoMemory();
		} 
		
		if (metaSearch == MetaSearch.BY_PATH)
			return metaDataMap.get(pathToIdMap.get(value));
		else if(metaSearch == MetaSearch.BY_ID)
			return metaDataMap.get(value);
			
		
		return null;
	}
	

	private String getBaseDirectory() {
		return configurationProvider.getConfigurations().getString("static.html.baseDirectory");
	}


	public void configureReader(Map<String, String> configs) throws Exception {
		// TODO Auto-generated method stub

	}
	

	public ConfigurationProvider getConfigurationProvider() {
		return configurationProvider;
	}

	public void setConfigurationProvider(ConfigurationProvider configurationProvider) {
		this.configurationProvider = configurationProvider;
	}


	public MetaDataVisitor getMetaDataVisitor() {
		return metaDataVisitor;
	}


	public void setMetaDataVisitor(MetaDataVisitor metaDataVisitor) {
		this.metaDataVisitor = metaDataVisitor;
	}


	@Override
	public boolean preLoadMetaData() throws IOException {
		loadMetaDataIntoMemory();
		return true;
	}

}
