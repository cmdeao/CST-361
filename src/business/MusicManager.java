package business;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import beans.Album;
import data.DataAccessInterface;
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
