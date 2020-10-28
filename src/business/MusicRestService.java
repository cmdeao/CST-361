package business;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import beans.Album;
import beans.ResponseDataModel;
import util.AlbumNotFoundException;

@RequestScoped
@Path("/music")
@Produces("application/xml, applicaiton/json")
@Consumes("application/xml, application/json")
public class MusicRestService 
{
	@EJB
	MusicManagerInterface service;
	
	@GET
	@Path("/getalbumj/{title}/{artist}/{year}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseDataModel getAlbumj(@PathParam("title")String title, @PathParam("artist")String artist, @PathParam("year")int year)
	{
		Album album = new Album(title, artist, year);		
		try
		{
			album = service.getAlbum(album);
			
			ResponseDataModel dataModel = new ResponseDataModel(0, "", album);
			return dataModel;
		}
		catch(AlbumNotFoundException e)
		{
			ResponseDataModel dataModel = new ResponseDataModel(-1, "Album not found exception occurred.", album);
			return dataModel;
		}
		catch(Exception ex)
		{
			ResponseDataModel dataModel = new ResponseDataModel(-2, "Exception has occurred.", album);
			return dataModel;
		}
	}
	
	@GET
	@Path("/getalbumx/{title}/{artist}/{year}")
	@Produces(MediaType.APPLICATION_XML)
	public ResponseDataModel getAlbumx(@PathParam("title")String title, @PathParam("artist")String artist, @PathParam("year")int year)
	{
		Album album = new Album(title, artist, year);
		ResponseDataModel dataModel = null;
				
		try
		{
			album = service.getAlbum(album);
			
			dataModel = new ResponseDataModel(0, "", album);
			return dataModel;
		}
		catch(AlbumNotFoundException e)
		{
			dataModel = new ResponseDataModel(-1, "Album not found exception occurred.", album);
			return dataModel;
		}
		catch(Exception ex)
		{
			dataModel = new ResponseDataModel(-2, "Exception has occurred.", album);
			return dataModel;
		}
	}
}
