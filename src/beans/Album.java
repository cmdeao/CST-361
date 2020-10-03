package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class Album 
{
	@NotNull()
	@Size(min=5, max=50)
	String title = "";
	
	@NotNull()
	@Size(min=5, max=25)
	String artist = "";
	
	@Min(1920)
	@Max(2020)
	int year = 0;
	List<Track> tracks = new ArrayList<Track>();
	
	public String getTitle()
	{
		return title;
	}
	
	public void setTitle(String inc)
	{
		this.title = inc;
	}
	
	public String getArtist()
	{
		return artist;
	}
	
	public void setArtist(String inc)
	{
		this.artist = inc;
	}
	
	public int getYear()
	{
		return year;
	}
	
	public void setYear(int inc)
	{
		this.year = inc;
	}
	
	public List<Track> getList()
	{
		return tracks;
	}
	
	public void setList(List<Track> inc)
	{
		this.tracks = inc;
	}
	
	public int getNumberTracks()
	{
		return tracks.size();
	}
}
