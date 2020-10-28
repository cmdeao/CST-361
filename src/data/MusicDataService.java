package data;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import beans.Album;
import beans.Track;
import util.DatabaseException;

import java.sql.*;

@Stateless
@Local(DataAccessInterface.class)
@LocalBean
public class MusicDataService implements DataAccessInterface<Album>
{
	Connection myConn = null;
	String connURL = "jdbc:mysql://localhost:3306/music";
	String username = "root";
	String password = "connection";
	
	@Override
	public List<Album> findAll()
	{
		List<Album> dbAlbums = new ArrayList<Album>();
		
		try
		{
			myConn = DriverManager.getConnection(connURL, username, password);
			String sqlStatement = "SELECT * FROM album";
			Statement state = myConn.createStatement();
			ResultSet rs = state.executeQuery(sqlStatement);
			
			while(rs.next())
			{
				Album album = new Album();
				album.setTitle(rs.getString("TITLE"));
				album.setArtist(rs.getString("ARTIST"));
				album.setYear(rs.getInt("YEAR"));
				
				List<Track> tracks = new ArrayList<Track>();
				String trackStatement = "SELECT * FROM TRACK WHERE ALBUM_ID = " + rs.getInt("ID");
				Statement trackState = myConn.createStatement();
				ResultSet trackRS = trackState.executeQuery(trackStatement);
				
				while(trackRS.next())
				{
					tracks.add(new Track(trackRS.getString("TITLE"), trackRS.getInt("NUMBER")));
				}
				
				rs.close();
				trackRS.close();
				state.close();
				trackState.close();
				
				album.setTracks(tracks);
				dbAlbums.add(album);
				myConn.close();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new DatabaseException();
		}
		finally
		{
			
		}
		return dbAlbums;
	}

	@Override
	public Album findById(int id) 
	{
		Album foundAlbum = new Album();
		try 
		{
			myConn = DriverManager.getConnection(connURL, username, password);
			String query = "SELECT * FROM album WHERE ID = ?";
			PreparedStatement statement = myConn.prepareStatement(query);
			
			statement.setInt(1, id);
			
			ResultSet rs = statement.executeQuery();
			while(rs.next())
			{
				foundAlbum.setTitle(rs.getString("TITLE"));
				foundAlbum.setArtist(rs.getString("ARTIST"));
				foundAlbum.setYear(rs.getInt("YEAR"));
			}
			
			List<Track> tracks = new ArrayList<Track>();
			String trackQuery = "SELECT * FROM track WHERE ALBUM_ID = " + id;
			Statement trackStatement = myConn.createStatement();
			ResultSet trackRS = trackStatement.executeQuery(trackQuery);
			
			while(trackRS.next())
			{
				tracks.add(new Track(trackRS.getString("TITLE"), trackRS.getInt("NUMBER")));
			}
			
			foundAlbum.setTracks(tracks);
			
			rs.close();
			trackRS.close();
			statement.close();
			trackStatement.close();
			myConn.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			throw new DatabaseException();
		}
		
		return foundAlbum;
	}

	@Override
	public Album findBy(Album t) 
	{
		int albumID = 0;
		try 
		{
			myConn = DriverManager.getConnection(connURL, username, password);
			String query = "SELECT * FROM album WHERE TITLE = ? AND ARTIST = ? AND YEAR = ?";
			PreparedStatement statement = myConn.prepareStatement(query);
			
			statement.setString(1, t.getTitle());
			statement.setString(2, t.getArtist());
			statement.setInt(3, t.getYear());
			ResultSet rs = statement.executeQuery();
			
			while(rs.next())
			{
				albumID = rs.getInt("ID");
			}
			
			List<Track> tracks = new ArrayList<Track>();
			String trackQuery = "SELECT * FROM track WHERE ALBUM_ID = " + albumID;
			Statement trackStatement = myConn.createStatement();
			ResultSet trackRS = trackStatement.executeQuery(trackQuery);
			
			while(trackRS.next())
			{
				tracks.add(new Track(trackRS.getString("TITLE"), trackRS.getInt("NUMBER")));
			}
			
			for(int i = 0; i < tracks.size(); i++)
			{
				System.out.println(tracks.get(i).getTitle() + " " + tracks.get(i).getNumber());
			}
			
			t.setTracks(tracks);
			
			rs.close();
			trackRS.close();
			statement.close();
			trackStatement.close();
			myConn.close();
			System.out.println(String.format("Successfully found album in database. Title: %s - Artist: %s - Year: %d", t.getTitle(), t.getArtist(), t.getYear()));
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			throw new DatabaseException();
		}
		
		return t;
	}

	@Override
	public Album create(Album t) 
	{
		System.out.println("Title: " + t.getTitle() + " Artist: " + t.getArtist() + " Year: " + t.getYear());
		try
		{
			myConn = DriverManager.getConnection(connURL, username, password);
			String insertQuery = "INSERT INTO album (TITLE, ARTIST, YEAR) VALUES (?, ?, ?)";
			
			PreparedStatement prep = myConn.prepareStatement(insertQuery);
			prep.setString(1, t.getTitle());
			prep.setString(2, t.getArtist());
			prep.setInt(3, t.getYear());
			
			prep.executeUpdate();
			
			String idQuery = "SELECT LAST_INSERT_ID() AS LAST_ID FROM album";
			Statement statement = myConn.createStatement();
			ResultSet rs = statement.executeQuery(idQuery);
			rs.next();
			int id = rs.getInt("LAST_ID");
			rs.close();
			statement.close();
		
			for(Track track: t.getTracks())
			{
				String insert = "INSERT INTO track (ALBUM_ID, TITLE, NUMBER) VALUES (?, ?, ?)";
				PreparedStatement trackPrep = myConn.prepareStatement(insert);
				trackPrep.setInt(1, id);
				trackPrep.setString(2, track.getTitle());
				trackPrep.setInt(3, track.getNumber());
				trackPrep.executeUpdate();
			}
			
			System.out.println(String.format("Successfully created album in database. Title: %s - Artist: %s - Year: %d", t.getTitle(), t.getArtist(), t.getYear()));
			myConn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new DatabaseException();
		}
		return findBy(t);
	}

	@Override
	public boolean update(Album t) 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Album t) 
	{
		// TODO Auto-generated method stub
		return false;
	}

	
}
