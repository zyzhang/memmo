package zhang.abel.memmo.android;

import android.app.Activity;
import android.app.AlertDialog;
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
        new AlertDialog.Builder(this)
                .setTitle("loding.")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setNegativeButton("cacel", null)
                .show();
    }
}