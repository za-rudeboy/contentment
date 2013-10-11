package org.contentment.content.provider;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.contentment.content.inspector.meta.ContentMetaDataHolder;
import org.contentment.content.inspector.meta.MetaDataReader;
import org.contentment.content.inspector.meta.MetaSearch;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.when;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/content-provider-context.xml")
public class DiskContentProviderTest {

	
	@Mock
	MetaDataReader metaDataReader;
	
	@InjectMocks
	@Autowired
	DiskContentProvider contentProvider;
	
	ContentMetaDataHolder value = new ContentMetaDataHolder();
	ContentMetaDataHolder value2 = new ContentMetaDataHolder();
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		
		value.setContentId("100");
		value.setContentPath("/StaticContent.html");
		value.setContentType("text/html");
		value.setRelativeFilePath("/StaticContent.html");
		
		value2.setContentId("101");
		value2.setContentPath("/fluidpage/rudyFluid.html");
		value2.setContentType("text/html");
		value2.setRelativeFilePath("/fluidpage/rudyFluid.html");
		
		ClassPathResource classPathResource = new ClassPathResource("StaticHTML/StaticContent.html");
		value.setAbsoluteFilePath(classPathResource.getFile().getAbsolutePath());
		
		ClassPathResource classPathResourceq2 = new ClassPathResource("StaticHTML/fluidpage/rudyFluid.html");
		value2.setAbsoluteFilePath(classPathResourceq2.getFile().getAbsolutePath());
		
		when(metaDataReader.getMetadata("100", MetaSearch.BY_ID)).thenReturn(value);
		when(metaDataReader.getMetadata("101", MetaSearch.BY_ID)).thenReturn(value2);
	}

	@Test
	public void testGetContentContentMetaDataHolder() throws IOException {
		ContentHolder contentHolder = contentProvider.getContent(value);
		
		ClassPathResource classPathResource = new ClassPathResource(
				"StaticHTML/StaticContent.html");

		assertArrayEquals(
				FileUtils.readFileToByteArray(classPathResource.getFile()),
				contentHolder.getContent());
	}

	@Test
	public void testGetContentStringMetaSearch() throws Exception {
		ContentHolder contentHolder = contentProvider.getContent("100",
				MetaSearch.BY_ID);

		ClassPathResource classPathResource = new ClassPathResource(
				"StaticHTML/StaticContent.html");

		assertArrayEquals(
				FileUtils.readFileToByteArray(classPathResource.getFile()),
				contentHolder.getContent());
		
		
	}

	@Test
	public void testGetMultipleContent() throws Exception {
		
		String[] string = {"100","101"};
		Map<String, ContentHolder>  map = contentProvider.getMultipleContent(string, MetaSearch.BY_ID);
		
		for (ContentHolder contentHolder  :map.values()){
			assertNotNull(contentHolder.getContent());
		}
	}

}
