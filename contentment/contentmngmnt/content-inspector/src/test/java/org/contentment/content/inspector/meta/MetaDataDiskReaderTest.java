package org.contentment.content.inspector.meta;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.sideproject.configuration.ConfigurationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/content-inspector-context.xml")
public class MetaDataDiskReaderTest {
	
	@Mock
	ConfigurationProvider configurationProvider;
	
	
	@InjectMocks
	@Autowired
	MetaDataDiskReader metaDataDiskReader;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		PropertiesConfiguration propConf = new PropertiesConfiguration();
		ClassPathResource classPathResource = new ClassPathResource("StaticHTML");
		
		propConf.addProperty("static.html.baseDirectory", classPathResource.getFile().getAbsolutePath());
		
		when(configurationProvider.getConfigurations())
					.thenReturn(propConf);
		
	}

	@Test
	public void testGetAllContentMetadata() throws Exception {
		
		Map<String, ContentMetaDataHolder> contentMetaDataHolders = metaDataDiskReader.getAllContentMetadata();
		assertTrue(contentMetaDataHolders.size() > 1);
	}

	@Test
	public void testGetMetadata() throws Exception {
		ContentMetaDataHolder contentMetaDataHolder = metaDataDiskReader.getMetadata("100", MetaSearch.BY_ID);
		assertEquals("/StaticContent.html", contentMetaDataHolder.getContentPath());
		
		ContentMetaDataHolder contentMetaDataHolder2 = metaDataDiskReader.getMetadata("/StaticContent.html", MetaSearch.BY_PATH);
		assertEquals("100", contentMetaDataHolder2.getContentId());
	}

}
