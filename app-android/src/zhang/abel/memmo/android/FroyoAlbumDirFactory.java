package zhang.abel.memmo.android;

import android.os.Environment;

import java.io.File;

public final class FroyoAlbumDirFactory extends AlbumStorageDirFactory {

	@Override
	public File getAlbumStorageDir(String albumName) {
		return new File(
		  Environment.getExternalStoragePublicDirectory(
		    Environment.DIRECTORY_PICTURES
		  ), 
		  albumName
		);
	}

    //TODO Enyu: replace the duplicate code use getAlbumStorageDirParent method
    @Override
    public File getAlbumStorageDirParent() {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
    }
}
