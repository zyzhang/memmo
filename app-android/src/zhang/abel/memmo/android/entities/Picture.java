package zhang.abel.memmo.android.entities;

import android.net.Uri;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Picture {
    private static final String PICTURE_FILE_EXT = ".jpg";

    private Album album;
    private File file;
    private String pictureName;

    public Picture(Album album, String pictureName) {
        this.album = album;
        this.pictureName = pictureName;
        this.file = new File(album.getDirectory(), this.pictureName + PICTURE_FILE_EXT);
    }

    public Picture(Album album) {
        this(album, new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()));
    }

    public File getFile() {
        return file;
    }

    public Uri getUri() {
        return Uri.fromFile(file);
    }
}
