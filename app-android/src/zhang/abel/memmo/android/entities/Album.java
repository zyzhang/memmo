package zhang.abel.memmo.android.entities;

import java.io.File;
import java.io.Serializable;

public class Album implements Serializable {
    File directory;

    public Album(File directory) {
        this.directory = directory;
    }

    public Picture addNewPicture() {
        return new Picture(this);
    }

    public File getDirectory() {
        return directory;
    }
}
