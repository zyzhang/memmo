package zhang.abel.memmo.android.factories;

import android.os.Environment;

import java.io.File;

public final class FroyoAlbumDirFactory extends AlbumStorageDirFactory {

    @Override
    public File getAppPhotoBaseDir() {
        return new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), APP_PHOTO_BASE_DIR);
    }
}
