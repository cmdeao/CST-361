package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import beans.Album;
import beans.Track;
import business.MusicManager;
import business.MusicManagerInterface;
import util.AlbumNotFoundException;
import util.TracksNotFoundException;

@ManagedBean
@ViewScoped
public class AlbumController 
{
	@EJB
	MusicManagerInterface mgr;
	
	public String onSubmit(Album album)
	{
		try 
		{
			album = mgr.addAlbum(album);
			
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
			album = mgr.getAlbum(album);
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