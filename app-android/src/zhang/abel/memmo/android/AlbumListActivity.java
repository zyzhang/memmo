package zhang.abel.memmo.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import java.io.File;

public class AlbumListActivity extends Activity {
    String dirPath;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.albumlist);
        loadAlbums();
        Intent intent=getIntent();
        dirPath = intent.getStringExtra("dirPath");
    }
    private void loadAlbums()
    {
        final ProgressDialog dialog = ProgressDialog.show(this, "读取相册", "请等待...", true);
        new Thread(new Runnable() {
            public void run() {
                try{
                    Thread.sleep(5000);
                    dialog.dismiss();
                    if (!dirPath.isEmpty()) {
                        File[] files = new File(dirPath).listFiles();
                        StringBuilder sb = new StringBuilder();
                        for (File aFile : files)
                            if (aFile.isDirectory()) {
                                sb.append(aFile.getName());
                                sb.append("*");
                            }
                        sb.toString();
                    }

                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start();
        dialog.show();
    }
}