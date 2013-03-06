package zhang.abel.memmo.android;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

                        Intent intent = new Intent(AlbumListActivity.this, AlbumListView.class);
                        intent.putExtra("dirPath",dirPath);
                        startActivity(intent);
                    }

                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start();
        dialog.show();
    }
}