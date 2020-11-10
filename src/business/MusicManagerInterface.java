package business;

import beans.Album;

public interface MusicManagerInterface 
{
	Album addAlbum(Album model);

	Album getAlbum(Album album);
}
