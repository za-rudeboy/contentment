package org.content.cache;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CacheManagerTest {

	
	 CacheManager cacheManager ;
	
	@Before
	public void setUp() throws Exception {
		
		cacheManager  = new CacheManager("ehcacheConfig.xml");
		
	}

	@Test
	public void testPutInCache() {
		assertTrue(cacheManager.putInCache("za.co.RandomCacheName", "testKey", new CacheElement()));
	}

	@Test
	public void testGetFromCache() {
		cacheManager.putInCache("za.co.RandomCacheName", "testGetFromCacheKey", new CacheElement());
		
		
		assertNotNull(cacheManager.getFromCache("za.co.RandomCacheName", "testGetFromCacheKey"));
		assertNull(cacheManager.getFromCache("za.co.RandomCacheName", "nonExistentKey"));
		
	}

}
