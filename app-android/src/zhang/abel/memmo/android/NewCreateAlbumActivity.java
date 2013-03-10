package zhang.abel.memmo.android;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import zhang.abel.memmo.android.repositories.AlbumRepository;

public class NewCreateAlbumActivity extends Activity {
    AlbumRepository albumRepository = new AlbumRepository();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createalbum);

        ActionBar actionBar = getActionBar();
        actionBar.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuItem cancel = menu.add(0, 1, 0, "返回");
        cancel.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

        cancel.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(NewCreateAlbumActivity.this, NewMainActivity.class);
                startActivity(intent);
                return true;
            }
        });
        return true;
    }

    public void createAlbum(View view) {
        EditText txtName = (EditText)findViewById(R.id.txt_name);
        EditText txtRemarks = (EditText)findViewById(R.id.txt_remarks);//TODO save it into db

        String albumName = txtName.getText().toString();
        if (!albumName.isEmpty()) {
            albumRepository.create(albumName);
        }

        Intent intent = new Intent(NewCreateAlbumActivity.this, NewMainActivity.class);
        startActivity(intent);
    }
}