package business;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

import beans.Album;
import beans.Track;
import sun.applet.Main;

@Stateless
@Local(TrackFinderInterface.class)
@LocalBean
@Alternative
public class TrackFinder2 implements TrackFinderInterface 
{

	@Override
	public List<Track> getTracks(Album album) 
	{
		List<Track> tracks = new ArrayList<Track>();
		
		String file = "/files/" + album.getTitle() + "-" + album.getArtist() + "-" + album.getYear() + ".txt";
		String filePath = Thread.currentThread().getContextClassLoader().getResource(file).getPath().replaceAll("%20", " ");
		
		BufferedReader br;
		
		try 
		{
			br = new BufferedReader(new FileReader(filePath));
			String incText;
			int trackNumber = 1;
			while((incText = br.readLine()) != null)
			{
				tracks.add(new Track(incText, trackNumber));
				trackNumber++;
			}
			br.close();
			return tracks;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return new ArrayList<Track>();
		}
	}
}
