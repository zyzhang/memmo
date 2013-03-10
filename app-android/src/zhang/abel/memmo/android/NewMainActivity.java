package zhang.abel.memmo.android;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
    private Album albumParent;
    private AlbumListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        albumParent = albumRepository.getAlbumStorageDirParent();
        albumList = getAlbumListItems(albumParent);
        initAdapter(albumList, false);

        ActionBar actionBar = getActionBar();
        actionBar.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu(menu);
        MenuItem create = menu.add(0, 1, 0, "创建");
        MenuItem edit = menu.add(0, 2, 1, "编辑");
        create.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        edit.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case 1:
                //CreateAlbum();TODO : need replace it with createablum page.
                Intent intent = new Intent(this, NewCreateAlbumActivity.class);
                startActivity(intent);
                return true;
            case 2:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initAdapter(List<Map<String, Object>> albumList, boolean isRefresh) {
        if (albumList.size() == 0) {
            return;
        }
        adapter = new AlbumListAdapter(this, albumList);
        if (!isRefresh) {
            setListAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
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

    private void CreateAlbum() {
        final EditText albumName = new EditText(this);
        new AlertDialog.Builder(this)
                .setTitle("新建记忆相册")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setView(albumName)
                .setPositiveButton("好了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String folderName = albumName.getText().toString();
                        if (!folderName.isEmpty()) {
                            albumRepository.create(folderName);
                            albumList = getAlbumListItems(albumParent);
                            initAdapter(albumList, true);
                        }
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }
}
