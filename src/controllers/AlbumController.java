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
import util.AlbumNotFoundException;
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
			
			List<Track> tracks = new ArrayList<Track>();
			tracks = album.getTracks();
		}
		catch(TracksNotFoundException e)
		{
			System.out.println("Exception: occurred");
			e.printStackTrace();
		}
		
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("album", album);
		return "AddAlbumResponse.xhtml";
	}
	
	public String onFind(Album album)
	{
		try
		{
			MusicManager manager = new MusicManager();
			album = manager.getAlbum(album);
		}
		catch(AlbumNotFoundException e)
		{
			System.out.println("Exception occurred");
			e.printStackTrace();
		}
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("album", album);
		return "AddAlbumResponse.xhtml";
	}
}