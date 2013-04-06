package zhang.abel.memmo.android.repositories;

import android.net.Uri;
import android.os.Build;
import zhang.abel.memmo.android.entities.Album;
import zhang.abel.memmo.android.entities.Picture;
import zhang.abel.memmo.android.factories.AlbumStorageDirFactory;
import zhang.abel.memmo.android.factories.BaseAlbumDirFactory;
import zhang.abel.memmo.android.factories.FroyoAlbumDirFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PictureRepository {
    private final AlbumStorageDirFactory albumStorageFactory;

    public PictureRepository() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
            albumStorageFactory = new FroyoAlbumDirFactory();
        } else {
            albumStorageFactory = new BaseAlbumDirFactory();
        }
    }

    public void save(Picture picture) throws IOException {
        picture.getFile().createNewFile();
    }

    public Uri getUri(Picture currentPicture) {
        return Uri.fromFile(currentPicture.getFile());
    }

    public List<Picture> list(Album album) {
        ArrayList<Picture> pictures = new ArrayList<Picture>();

        File[] pictureFiles = album.getDirectory().listFiles();
        for (File file : pictureFiles) {
            pictures.add(new Picture(album, file.getName(), file));
        }

        return pictures;
    }
}
