package business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import beans.Album;
import beans.Track;
import data.DataAccessInterface;
import data.MusicDataService;
import util.AlbumNotFoundException;
import util.TracksNotFoundException;

@Stateless
@Local(MusicManagerInterface.class)
@LocalBean
@Interceptors(LoggingInterceptor.class)
public class MusicManager implements MusicManagerInterface
{
	@EJB
	DataAccessInterface<Album> dao;
	
	@EJB 
	Cache cache;
	
	@Inject
	TrackFinderInterface tf;
	
	@Override
	public Album addAlbum(Album model) throws TracksNotFoundException
	{	
		if(model.getYear() == 0)
		{
			return model;
		}
		
		model.setTracks(tf.getTracks(model));
		
		if(model.getNumberTracks() == 0)
		{
			throw new TracksNotFoundException();
		}
		
		return dao.create(model);
	}
	
	@Override
	public Album getAlbum(Album album) throws AlbumNotFoundException
	{
		Album foundAlbum = new Album();
		
		foundAlbum = cache.getObject(album);
		
		if(foundAlbum != null)
		{
			return foundAlbum;
		}
		
		foundAlbum = dao.findBy(album);
		
		//Example call to showcase finding album based on passed ID number.
//		Album idAlbum = new Album();
//		idAlbum = dao.findById(6);
//		
//		System.out.println(String.format("Album Title: %s - Album Artist: %s - Album Year: %d", idAlbum.getTitle(), idAlbum.getArtist(), idAlbum.getYear()));
//		
//		for(int i = 0; i < idAlbum.getNumberTracks(); i++)
//		{
//			System.out.println(String.format("Song Title: %s - Song Number: %d", idAlbum.getTracks().get(i).getTitle(), idAlbum.getTracks().get(i).getNumber()));
//		}
		
		if(foundAlbum.getNumberTracks() == 0)
		{
			throw new AlbumNotFoundException();
		}
		else
		{
			cache.putObject(foundAlbum);
			return foundAlbum;
		}
	}
}
