package zhang.abel.memmo.android.entities;

import android.net.Uri;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Picture {

    private static final String PICTURE_FILE_EXT = ".jpg";
    private static final SimpleDateFormat FILE_NAME_FORMAT = new SimpleDateFormat("yyyyMMdd_HHmmss");

    private Album album;
    private String pictureName;
    private File file;

    public Picture(Album album) {
        String fileName = FILE_NAME_FORMAT.format(new Date());
        this.album = album;
        this.pictureName = fileName;
        this.file = new File(album.getDirectory(), fileName + PICTURE_FILE_EXT);
    }

    public Picture(Album album, String pictureName, File pictureFile) {
        this.album = album;
        this.pictureName = pictureName;
        this.file = pictureFile;
    }

    public File getFile() {
        return file;
    }

    public Uri fileUri() {
        return Uri.fromFile(file);
    }
}
