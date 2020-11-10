package business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

import beans.Album;
import beans.Track;

@Stateless
@Alternative
@Local(TrackFinderInterface.class)
@LocalBean
public class TrackFinder1 implements TrackFinderInterface
{
	private HashMap<String, List<Track>> info;
	
	public TrackFinder1()
	{
		info = new HashMap<String, List<Track>>();
		List<Track> albumTracks = new ArrayList<Track>();
		
		albumTracks.add(new Track("Feel Invincible", 1));
		albumTracks.add(new Track("Back from the Dead", 2));
		albumTracks.add(new Track("Stars", 3));
		albumTracks.add(new Track("I Want to Live", 4));
		albumTracks.add(new Track("Undefeated", 5));
		albumTracks.add(new Track("Famous", 6));
		albumTracks.add(new Track("Lions", 7));
		albumTracks.add(new Track("Out of Hell", 8));
		albumTracks.add(new Track("Burn It Down", 9));
		albumTracks.add(new Track("Watching for Comets", 10));
		albumTracks.add(new Track("Saviors of the World", 11));
		albumTracks.add(new Track("The Resistance", 12));
		info.put("Skillet - Unleashed - 2016", albumTracks);
	}
	
	@Override
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
}
