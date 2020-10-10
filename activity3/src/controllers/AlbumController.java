package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import beans.Album;
import beans.Track;
import business.MusicManager;
import util.TracksNotFoundException;

@ManagedBean
@ViewScoped
public class AlbumController 
{
	public String onSubmit(Album album)
	{
		try 
		{
			MusicManager manager = new MusicManager();
			album = manager.addAlbum(album);
			
			System.out.println(String.format("Album Title: %s - Artist: %s - Year: %d", album.getTitle(), album.getArtist(), album.getYear()));
			
			List<Track> tracks = new ArrayList<Track>();
			tracks = album.getTracks();
			
			for(int i = 0; i < tracks.size(); i++)
			{
				System.out.println(String.format("Song Title: %s - Song Number: %d", tracks.get(i).getTitle(), tracks.get(i).getNumber()));
			}
		}
		catch(TracksNotFoundException e)
		{
			System.out.println("Exception: " + e);
		}
		
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("album", album);
		return "AddAlbumResponse.xhtml";
	}
}