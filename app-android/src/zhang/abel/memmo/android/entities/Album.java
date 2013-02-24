package zhang.abel.memmo.android.entities;

import java.io.File;

public class Album {
    File directory;

    public Album(File directory) {
        this.directory = directory;
    }

    public File getDirectory() {
        return directory;
    }
}
