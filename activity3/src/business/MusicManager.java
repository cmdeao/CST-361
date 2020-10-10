package business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ejb.Stateless;

import beans.Album;
import beans.Track;
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
		
		System.out.println("Total tracks: " + model.getNumberTracks());
		
		return model;
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
		albumTracks.add(new Track("Where Have All the Good Times Gone!", 1));
		albumTracks.add(new Track("Hang 'Em High", 2));
		albumTracks.add(new Track("Cathedral", 3));
		albumTracks.add(new Track("Secrets", 4));
		albumTracks.add(new Track("Intruder", 5));
		albumTracks.add(new Track("(Oh) Pretty Woman", 6));
		albumTracks.add(new Track("Dancing in the Street", 7));
		albumTracks.add(new Track("Little Guitars (Intro)", 8));
		albumTracks.add(new Track("Little Guitars", 9));
		albumTracks.add(new Track("Big Bad Bill (Is Sweet William Now)", 10));
		albumTracks.add(new Track("The Full Bug", 11));
		albumTracks.add(new Track("Happy Trails", 12));
		info.put("Van Halen - Diver Down - 1982", albumTracks);
	}
}
