package zhang.abel.memmo.android.factories;

import java.io.File;

public abstract class AlbumStorageDirFactory {

    protected final String APP_PHOTO_BASE_DIR = "memmo";

    public File getAlbumStorageDir(String albumName) {
        return new File (getAppPhotoBaseDir(), albumName);
    }

	public abstract File getAppPhotoBaseDir();
}
