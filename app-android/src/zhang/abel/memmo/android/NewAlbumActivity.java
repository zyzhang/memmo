package zhang.abel.memmo.android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import zhang.abel.memmo.android.entities.Album;

public class NewAlbumActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newalbum);

        Album album = (Album)getIntent().getSerializableExtra(NewMainActivity.SER_KEY);

        TextView albumName = (TextView) findViewById(R.id.newalbumname);
        albumName.setText(album.getDirectory().getPath());
    }
}