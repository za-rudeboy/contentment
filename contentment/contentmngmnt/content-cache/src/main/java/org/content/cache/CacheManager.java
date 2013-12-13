/**
 * 
 */
package org.content.cache;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

/**
 * Instantiates and manages content cache.
 * 
 * @author Rudy Adams
 * 
 */
public class CacheManager {

	private net.sf.ehcache.CacheManager ehCacheManager;
	private Map<String, Cache> caches = new HashMap<>();
	
	private boolean cacheConfigured;
	
	public CacheManager(String configFile) throws IOException {
		configureCache(new ClassPathResource(configFile).getURL());
	}

	public boolean putInCache(String cacheName, Object key, Object value) {

		Element cacheElement = new Element(key, value);
		
		try {

			if (caches.get(cacheName) == null) {

				Cache cache = ehCacheManager.getCache(cacheName);
				
				if (cache == null){
					ehCacheManager.addCache(cacheName);
					cache = ehCacheManager.getCache(cacheName);
				}
				
				
				cache.put(cacheElement);
				caches.put(cacheName, cache);
				return true;

			} else {
				Cache cache = caches.get(cacheName);
				cache.put(cacheElement);
				return true;
			}

		} catch (Exception e) {
			logger.error("Failure to put element into cache", e);
			return false;
		}
	}
		
	public Object getFromCache (String cacheName, Object cacheKey){
		
		Cache cache = caches.get(cacheName);
		
		if (cache != null && cache.get(cacheKey) != null)
			return cache.get(cacheKey).getObjectValue();
		else
			return null;
		
		
		
	}
	
	public void configureCache(URL configFile){
		ehCacheManager = net.sf.ehcache.CacheManager.newInstance(configFile);
		cacheConfigured = true;
		
		logger.info("EhCache has been configured and initialised");
		
	}
	
	public boolean isCacheConfigured() {
		return cacheConfigured;
	}

	private Logger logger = LoggerFactory.getLogger(getClass().getName());
	
	

}
