package org.contentment.content.inspector.meta;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/content-inspector-context.xml")
public class MetaDataVisitorTest {
	
	@Autowired
	MetaDataVisitor metaDataVisitor;

	@Before
	public void setUp() throws Exception {
		
		ClassPathResource classPathResource = new ClassPathResource("StaticHTML");
		
		Files.walkFileTree(Paths.get(classPathResource.getFile().toURI()), metaDataVisitor);
	
	}

	@Test
	public void testVisitFile() {
		metaDataVisitor.getMetaDataMap().get("100");
	}

}
