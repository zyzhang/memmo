package zhang.abel.memmo.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class NewAlbumActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newalbum);

        Intent intent = getIntent();
        String path = intent.getStringExtra("path");

        TextView viewById = (TextView) findViewById(R.id.newalbumname);
        viewById.setText(path);
    }
}