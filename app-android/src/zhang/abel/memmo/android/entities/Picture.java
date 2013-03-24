package zhang.abel.memmo.android.entities;

import android.net.Uri;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Picture {

    private Album album;
    private String pictureName;

    private Picture(Album album, String pictureName) {
        this.album = album;
        this.pictureName = pictureName;
    }

    public Picture(Album album) {
        this(album, new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()));
    }

    @Deprecated
    public File getFile() {
        return null;
    }

    public String getPictureName() {
        return pictureName;
    }

    public Album getAlbum() {
        return album;
    }
}
