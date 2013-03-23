package zhang.abel.memmo.android.repositories;

import zhang.abel.memmo.android.entities.Picture;

import java.io.IOException;

public class PictureRepository {
    public void save(Picture picture) throws IOException {

        picture.getFile().createNewFile();
//        File.createTempFile()
    }
}
