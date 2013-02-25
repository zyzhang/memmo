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

        System.out.println("blablabla........");
        photoList = readFileList();
    }

    private List<String> readFileList() {
        List<String> fileList = new ArrayList<String>();
        System.out.println("blablaPath:=====" + imagePath);
        File fileDir = new File(imagePath);
        File[] files = fileDir.listFiles();

        System.out.println("blablafiles: " + files.length);
        if(files != null){
            for(File file : files){
                System.out.println("blablaFileName: " + file.getName());
                String fileName = file.getName();
                if(fileName.lastIndexOf(".") > 0
                        && fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).equals("jpg")){
                    fileList.add(file.getPath());
                    System.out.println("=======" + file.getPath());
                }
            }
        }
        return fileList;
    }


    public View makeView() {
        return null;
    }

}
