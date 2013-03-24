package zhang.abel.memmo.android.repositories;

import android.os.Build;
import android.os.Environment;
import zhang.abel.memmo.android.factories.AlbumStorageDirFactory;
import zhang.abel.memmo.android.factories.BaseAlbumDirFactory;
import zhang.abel.memmo.android.factories.FroyoAlbumDirFactory;
import zhang.abel.memmo.android.entities.Album;
import zhang.abel.memmo.android.exceptions.MemmoException;

import java.io.File;

public class AlbumRepository {

    private final AlbumStorageDirFactory albumStorageFactory;

    public AlbumRepository() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
            albumStorageFactory = new FroyoAlbumDirFactory();
        } else {
            albumStorageFactory = new BaseAlbumDirFactory();
        }
    }

    public void create(Album album) {
        verifyExternalStorageAvailable();

        File storageDir = albumStorageFactory.getAlbumStorageDir(album.getName());

        if (storageDir.exists()) {
            throw new MemmoException(String.format("%s already exists", storageDir.getAbsolutePath()));
        }
        storageDir.mkdirs();
    }

    public Album get(String albumName) {
        File storageDir = albumStorageFactory.getAlbumStorageDir(albumName);
        if (storageDir.exists() && storageDir.isDirectory()) {
            return new Album(storageDir);
        }
        return null;
    }

    public Album getAlbumStorageDirParent() {
        verifyExternalStorageAvailable();

        File storageDir = albumStorageFactory.getAppPhotoBaseDir();
        if (!storageDir.exists() || !storageDir.isDirectory()) {
            storageDir.mkdirs();
        }
        return new Album(storageDir);
    }

    private static void verifyExternalStorageAvailable() {
        if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            throw new MemmoException("External storage is not mounted READ/WRITE");
        }
    }
}
