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
		idAlbum = service.findById(6);
		
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
		
//		MusicDataService service = new MusicDataService();
//		return service.findBy(album);
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
//		albumTracks.add(new Track("Fight Fire With Fire", 1));
//		albumTracks.add(new Track("Ride The Lightning", 2));
//		albumTracks.add(new Track("For Whom The Bell Tolls", 3));
//		albumTracks.add(new Track("Fade To Black", 4));
//		albumTracks.add(new Track("Trapped Under Ice", 5));
//		albumTracks.add(new Track("Escape", 6));
//		albumTracks.add(new Track("Creeping Death", 7));
//		albumTracks.add(new Track("The Call Of Ktulu", 8));
//		info.put("Metallica - Ride The Lightning - 1984", albumTracks);
		
//		albumTracks.add(new Track("Shine on You Crazy Diamond (Pts. 1-5)", 1));
//		albumTracks.add(new Track("Welcome to the Machine", 2));
//		albumTracks.add(new Track("Have a Cigar", 3));
//		albumTracks.add(new Track("Wish You Were Here", 4));
//		albumTracks.add(new Track("Shine On You Crazy Diamond (Pts. 6-9)", 5));
//		info.put("Pink Floyd - Wish You Were Here - 1975", albumTracks);
		
//		albumTracks.add(new Track("TestingSong", 1));
//		info.put("Metallica - TestAlbum - 1994", albumTracks);
		
		albumTracks.add(new Track("Wanna be Startin' Something", 1));
		albumTracks.add(new Track("Baby Be Mine", 2));
		albumTracks.add(new Track("This Girl is Mine", 3));
		albumTracks.add(new Track("Thriller", 4));
		albumTracks.add(new Track("Beat It", 5));
		albumTracks.add(new Track("Billie Jean", 6));
		albumTracks.add(new Track("Human Nature", 7));
		albumTracks.add(new Track("P.Y.T.", 8));
		albumTracks.add(new Track("The Lady in My Life", 9));
		info.put("Michael Jackson - Thriller - 1982", albumTracks);
	}
}
