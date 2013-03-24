package zhang.abel.memmo.android.entities;

import java.io.Serializable;

public class Album implements Serializable {
    private String name;
    private String remark;

    public Album(String name) {
        this.name = name;
        // TODO to be the real remarks
        this.remark = name;
    }

    public String getName() {
        return name;
    }

    public String getRemark() {
        return remark;
    }
}
