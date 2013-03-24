package zhang.abel.memmo.android.repositories;

import android.net.Uri;
import android.os.Build;
import zhang.abel.memmo.android.entities.Picture;
import zhang.abel.memmo.android.factories.AlbumStorageDirFactory;
import zhang.abel.memmo.android.factories.BaseAlbumDirFactory;
import zhang.abel.memmo.android.factories.FroyoAlbumDirFactory;

import java.io.File;
import java.io.IOException;

public class PictureRepository {
    private static final String PICTURE_FILE_EXT = ".jpg";
    private final AlbumStorageDirFactory albumStorageFactory;

    public PictureRepository() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
            albumStorageFactory = new FroyoAlbumDirFactory();
        } else {
            albumStorageFactory = new BaseAlbumDirFactory();
        }
    }

    public void save(Picture picture) throws IOException {
        getPictureFile(picture).createNewFile();
    }

    private File getPictureFile(Picture picture) {
        File albumDir = albumStorageFactory.getAlbumStorageDir(picture.getAlbum().getName());
        return new File(albumDir, picture.getPictureName() + PICTURE_FILE_EXT);
    }

    public Uri getUri(Picture currentPicture) {
        return Uri.fromFile(getPictureFile(currentPicture));
    }
}
