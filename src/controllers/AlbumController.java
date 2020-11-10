package controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import beans.Album;
import beans.Track;
import business.LoggingInterceptor;
import business.MusicManagerInterface;
import util.AlbumNotFoundException;
import util.TracksNotFoundException;

@Named
@ViewScoped
@Interceptors(LoggingInterceptor.class)
public class AlbumController implements Serializable
{
	private static final long serialVersionUID = 1L;
	
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