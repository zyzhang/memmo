package zhang.abel.memmo.android;

import android.app.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import zhang.abel.memmo.android.entities.Album;
import zhang.abel.memmo.android.repositories.AlbumRepository;

public class MainActivity extends Activity {
    AlbumRepository albumRepository = new AlbumRepository();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void createAlbum(View view) {
        final EditText albumName = new EditText(this);
        new AlertDialog.Builder(this)
                .setTitle("新建记忆相册")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setView(albumName)
                .setPositiveButton("好了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String folderName = String.valueOf(albumName.getText());
                        if (!folderName.isEmpty()) {
                            Album newAlbum = albumRepository.create(folderName);
                            String dirPath = newAlbum.getDirectory().getParent().toString();

                            Intent intent = new Intent(MainActivity.this, AlbumListActivity.class);
                            intent.putExtra("dirPath",dirPath);
                            startActivity(intent);
                        }
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }

    public void onClickToAlbum(View view) {
        Intent intent = new Intent(MainActivity.this, AlbumActivity.class);
        startActivity(intent);
    }

    public void listAlbums(View view) {
        Intent intent = new Intent(MainActivity.this, AlbumListView.class);
        startActivity(intent);
    }
}
