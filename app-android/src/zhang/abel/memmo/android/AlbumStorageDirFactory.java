package zhang.abel.memmo.android;

import java.io.File;

public abstract class AlbumStorageDirFactory {
	public abstract File getAlbumStorageDir(String albumName);
	public abstract File getAlbumStorageDirParent();
}
