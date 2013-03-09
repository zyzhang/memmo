package zhang.abel.memmo.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import zhang.abel.memmo.android.adapters.AlbumListAdapter;
import zhang.abel.memmo.android.entities.Album;
import zhang.abel.memmo.android.repositories.AlbumRepository;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewMainActivity extends ListActivity {
    AlbumRepository albumRepository = new AlbumRepository();
    private List<Map<String, Object>> albumList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Album dirPath = albumRepository.getAlbumStorageDirParent();
        albumList = getAlbumListItems(dirPath);
        AlbumListAdapter adapter = new AlbumListAdapter(this,albumList);
        setListAdapter(adapter);
    }

    private List<Map<String, Object>> getAlbumListItems(Album dirPath) {
        File[] files = dirPath.getDirectory().listFiles();
        List<Map<String, Object>> albumList = new ArrayList<Map<String, Object>>();
        for (File aFile : files)
            if (aFile.isDirectory()) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("title", aFile.getName());
                map.put("info", aFile.getName());
                map.put("img", R.drawable.ic_launcher);
                albumList.add(map);
            }
        return albumList;
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
                           //TODO: List all albums in the listview.
                        }
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }

//    public void onClickToAlbum(View view) {
//        Intent intent = new Intent(NewMainActivity.this, AlbumActivity.class);
//        startActivity(intent);
//    }
//
//
//    public void setReminder(View view) {
//        Intent intent = new Intent(this, ReminderActivity.class);
//        startActivity(intent);
//    }
//
//    public void listAlbums(View view) {
//        Intent intent = new Intent(NewMainActivity.this, AlbumListView.class);
//        startActivity(intent);
//    }
}
