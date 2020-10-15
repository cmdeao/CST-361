package business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import beans.Album;
import beans.Track;
import data.DataAccessInterface;
import data.MusicDataService;
import util.AlbumNotFoundException;
import util.TracksNotFoundException;

@Stateless
public class MusicManager implements MusicManagerInterface
{
	private HashMap<String, List<Track>> info;
	
	
	@Override
	public Album addAlbum(Album model) throws TracksNotFoundException
	{
		model.setTracks(getTracks(model));
		
		if(model.getNumberTracks() == 0)
		{
			throw new TracksNotFoundException();
		}
		
		MusicDataService dao = new MusicDataService();
		return dao.create(model);
	}
	
	public Album getAlbum(Album album) throws AlbumNotFoundException
	{
		MusicDataService service = new MusicDataService();
		Album foundAlbum = new Album();
		foundAlbum = service.findBy(album);
		
		//Example call to showcase finding album based on passed ID number.
		Album idAlbum = new Album();
		idAlbum = service.findById(4);
		
		System.out.println(String.format("Album Title: %s - Album Artist: %s - Album Year: %d", idAlbum.getTitle(), idAlbum.getArtist(), idAlbum.getYear()));
		
		for(int i = 0; i < idAlbum.getNumberTracks(); i++)
		{
			System.out.println(String.format("Song Title: %s - Song Number: %d", idAlbum.getTracks().get(i).getTitle(), idAlbum.getTracks().get(i).getNumber()));
		}
		
		if(foundAlbum.getNumberTracks() == 0)
		{
			throw new AlbumNotFoundException();
		}
		else
		{
			return foundAlbum;
		}
	}
	
	public List<Track> getTracks(Album album)
	{
		String trackKey = album.getArtist() + " - " + album.getTitle() + " - " + album.getYear();
		
		if(info.containsKey(trackKey))
		{
			return info.get(trackKey);
		}
		else
		{
			return new ArrayList<Track>();
		}
	}
	
	public MusicManager()
	{
		info = new HashMap<String, List<Track>>();
		List<Track> albumTracks = new ArrayList<Track>();
		albumTracks.add(new Track("Fight Fire With Fire", 1));
		albumTracks.add(new Track("Ride The Lightning", 2));
		albumTracks.add(new Track("For Whom The Bell Tolls", 3));
		albumTracks.add(new Track("Fade To Black", 4));
		albumTracks.add(new Track("Trapped Under Ice", 5));
		albumTracks.add(new Track("Escape", 6));
		albumTracks.add(new Track("Creeping Death", 7));
		albumTracks.add(new Track("The Call Of Ktulu", 8));
		info.put("Metallica - Ride The Lightning - 1984", albumTracks);
	}
}
