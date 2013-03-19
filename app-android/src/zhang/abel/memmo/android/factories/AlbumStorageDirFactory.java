package zhang.abel.memmo.android.factories;

import java.io.File;

public abstract class AlbumStorageDirFactory {
	public abstract File getAlbumStorageDir(String albumName);
	public abstract File getAlbumStorageDirParent();
}
