package zhang.abel.memmo.android.entities;

import java.io.File;
import java.io.Serializable;

public class Album implements Serializable {
    private String name;
    private String remark;

    private File directory;

    public Album(String name) {
        this.name = name;
        // TODO to be the real remarks
        this.remark = name;
    }

    public Album(String name, File directory) {
        this(name);
        this.directory = directory;
    }

    public String getName() {
        return name;
    }

    public String getRemark() {
        return remark;
    }

    public File getDirectory() {
        return directory;
    }
}
