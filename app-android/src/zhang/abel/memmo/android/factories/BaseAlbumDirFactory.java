package zhang.abel.memmo.android.factories;

import android.os.Environment;
import zhang.abel.memmo.android.R;

import java.io.File;

public final class BaseAlbumDirFactory extends AlbumStorageDirFactory {

	// Standard storage location for digital camera files
	private static final String CAMERA_DIR = "/dcim/";

    @Override
    public File getAppPhotoBaseDir() {
        return new File(Environment.getExternalStorageDirectory() + CAMERA_DIR + APP_PHOTO_BASE_DIR);
    }
}
