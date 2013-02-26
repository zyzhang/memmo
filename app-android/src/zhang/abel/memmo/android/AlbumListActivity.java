package zhang.abel.memmo.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.EditText;

public class AlbumListActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.albumlist);
        loadAlbums();
    }
    private void loadAlbums()
    {
        final ProgressDialog dialog = ProgressDialog.show(this, "读取相册", "请等待...", true);
        new Thread(new Runnable() {
            public void run() {
                try{
                    Thread.sleep(5000);
                    dialog.dismiss();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start();
        dialog.show();
    }
}