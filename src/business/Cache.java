package business;

import java.util.HashMap;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import beans.Album;

@Startup
@Singleton
public class Cache 
{
	HashMap<String, Album> cache;
	
	@PostConstruct
	public void init()
	{
		cache = new HashMap<String, Album>();
	}
	
	
	public Album getObject(Album album)
	{
		String cacheKey = album.getArtist() + " - " + album.getTitle() + " - " + album.getYear();
		
		if(cache.containsKey(cacheKey))
		{
			System.out.println(String.format("Cache contained album %s", album.getTitle()));
			return cache.get(cacheKey);
		}
		else
		{
			System.out.println(String.format("Cache did not contain album %s", album.getTitle()));
			return null;
		}
	}
	
	public void putObject(Album album)
	{
		String cacheKey = album.getArtist() + " - " + album.getTitle() + " - " + album.getYear();
		cache.put(cacheKey, album);
		System.out.println(String.format("Inserted album %s into the cache.", album.getTitle()));
	}
}
