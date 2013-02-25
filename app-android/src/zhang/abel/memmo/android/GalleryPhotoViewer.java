package zhang.abel.memmo.android;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ViewSwitcher;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class GalleryPhotoViewer extends Activity implements ViewSwitcher.ViewFactory {

    private static String imagePath = Environment.getExternalStorageDirectory().getPath() + "/DCIM/Camera";

    private List<String> photoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photoscan);

        photoList = readFileList();
    }

    private List<String> readFileList() {
        List<String> fileList = new ArrayList<String>();

        File fileDir = new File(imagePath);
        File[] files = fileDir.listFiles();

        if(files != null){
            for(File file : files){
                String fileName = file.getName();
                if(fileName.lastIndexOf(".") > 0
                        && fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).equals("jpg")){
                    fileList.add(file.getPath());
                }
            }
        }
        return fileList;
    }


    public View makeView() {
        return null;
    }

}
