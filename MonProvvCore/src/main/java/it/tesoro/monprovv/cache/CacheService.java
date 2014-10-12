package it.tesoro.monprovv.cache;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CacheService {
	
	@Value("#{config['cache.intervallo']}")
	private Integer cacheInterval;
	
	private Map<String, Object> cache;
	
	private Map<String, Date> timestamp;
	
	
	public CacheService() {
		cache = new HashMap<String, Object>();
		timestamp = new HashMap<String, Date>();
	}

	public void addToCache(String key, Object value) {
		cache.put(key, value);
		timestamp.put(key, new Date());
	}
	
	public Object getFromCache(String key) {
		
		Date cacheDate = timestamp.get(key);
		if (cacheDate == null)
			// cache miss
			return null;
		
		long diff = (new Date()).getTime() - cacheDate.getTime();
		
		long diffMinutes = diff / (60 * 1000) % 60; 
		
		if (diffMinutes > cacheInterval) 
			// cache miss
			return null;
		
		// cache hit
		return cache.get(key);
	}
	
	
	
	public void invalidateCache() {
		cache.clear();
		timestamp.clear();
	}
	

}
