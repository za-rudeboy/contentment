package org.contentment.content.provider;

import static org.junit.Assert.*;

import java.io.IOException;

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
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		
		value.setContentId("100");
		value.setContentPath("/StaticContent.html");
		value.setContentType("html");
		value.setRelativeFilePath("/StaticContent.html");
		
		ClassPathResource classPathResource = new ClassPathResource("StaticHTML/StaticContent.html");
		
		value.setAbsoluteFilePath(classPathResource.getFile().getAbsolutePath());
		
		when(metaDataReader.getMetadata("100", MetaSearch.BY_ID)).thenReturn(value);
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

	@Ignore
	public void testGetMultipleContent() {
		fail("Not yet implemented"); // TODO
	}

}
