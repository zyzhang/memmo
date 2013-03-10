package zhang.abel.memmo.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import zhang.abel.memmo.android.entities.Album;

public class NewAlbumActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newalbum);

        //TODO Should load pictures under this album.

        Album album = (Album)getIntent().getSerializableExtra(NewMainActivity.SER_KEY);
        TextView albumName = (TextView) findViewById(R.id.newalbumname);
        albumName.setText(album.getDirectory().getPath());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuItem create = menu.add(0, 1, 0, "拍照");
        MenuItem edit = menu.add(0, 2, 1, "提醒");
        create.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        edit.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                //TODO Take photo logic should be wrote here
                return true;
            case 2:
                //TODO Set notification logic should be wrote here
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}