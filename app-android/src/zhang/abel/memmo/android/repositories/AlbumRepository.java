package zhang.abel.memmo.android.repositories;

import android.os.Build;
import android.os.Environment;
import zhang.abel.memmo.android.entities.Album;
import zhang.abel.memmo.android.exceptions.MemmoException;
import zhang.abel.memmo.android.factories.AlbumStorageDirFactory;
import zhang.abel.memmo.android.factories.BaseAlbumDirFactory;
import zhang.abel.memmo.android.factories.FroyoAlbumDirFactory;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

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
            return new Album(albumName);
        }
        return null;
    }

    public List<Album> list() {
        verifyExternalStorageAvailable();

        File baseDir = albumStorageFactory.getAppPhotoBaseDir();
        if (!baseDir.exists() || !baseDir.isDirectory()) {
            return new ArrayList<Album>();
        }

        File[] dirs = baseDir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isDirectory();
            }
        });
        ArrayList<Album> results = new ArrayList<Album>();
        for(File dir : dirs) {
            results.add(new Album(dir.getName()));
        }
        return results;
    }

    private static void verifyExternalStorageAvailable() {
        if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            throw new MemmoException("External storage is not mounted READ/WRITE");
        }
    }

    public File getAlbumDirectory(Album album) {
        return albumStorageFactory.getAlbumStorageDir(album.getName());
    }
}
