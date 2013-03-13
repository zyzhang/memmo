package zhang.abel.memmo.android;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import zhang.abel.memmo.android.adapters.AlbumListAdapter;
import zhang.abel.memmo.android.entities.Album;
import zhang.abel.memmo.android.repositories.AlbumRepository;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewMainActivity extends ListActivity {
    public final static String SER_KEY = "";
    AlbumRepository albumRepository = new AlbumRepository();
    private List<Map<String, Object>> albumList;
    private Album albumParent;
    private AlbumListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        renderAlbumListPage();
    }

    @Override
    public void onResume(){
        super.onResume();
        renderAlbumListPage();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuItem create = menu.add(0, 1, 0, "创建");
        MenuItem edit = menu.add(0, 2, 1, "编辑");
        create.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        edit.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                //CreateAlbum();TODO : need replace it with createablum page.
                Intent intent = new Intent(this, NewCreateAlbumActivity.class);
                startActivity(intent);
                return true;
            case 2:
                //The Edit logic.
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        File path = new File((String) albumList.get(position).get("path"));
        Album album = new Album(path);

        Intent mIntent = new Intent(this, NewAlbumActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putSerializable(SER_KEY, album);
        mIntent.putExtras(mBundle);
        startActivity(mIntent);
    }

    private void renderAlbumListPage() {
        albumParent = albumRepository.getAlbumStorageDirParent();
        albumList = getAlbumListItems(albumParent);
        initAlbumList(albumList, false);
        showActionBar();
    }

    private void initAlbumList(List<Map<String, Object>> albumList, boolean isRefresh) {
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

    private void showActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.show();
    }

    private List<Map<String, Object>> getAlbumListItems(Album albumParent) {
        File[] files = albumParent.getDirectory().listFiles();
        List<Map<String, Object>> albumList = new ArrayList<Map<String, Object>>();
        for (File aFile : files)
            if (aFile.isDirectory()) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("title", aFile.getName());
                map.put("info", aFile.getName());
                map.put("img", R.drawable.ic_launcher);
                map.put("path", albumParent.getDirectory().getPath() + File.separator + aFile.getName());
                albumList.add(map);
            }
        return albumList;
    }
}
