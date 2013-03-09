package zhang.abel.memmo.android;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import zhang.abel.memmo.android.adapters.AlbumListAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlbumListView extends ListActivity {
    private List<Map<String, Object>> albumList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent1 = getIntent();
        if (intent1.getStringExtra("dirPath")!=null){

            String dirPath = intent1.getStringExtra("dirPath");
            File[] files = new File(dirPath).listFiles();
            List<Map<String, Object>> albumList = new ArrayList<Map<String, Object>>();
            for (File aFile : files)
                if (aFile.isDirectory()) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("title", aFile.getName());
                    map.put("info", aFile.getName());
                    map.put("img", R.drawable.ic_launcher);
                    albumList.add(map);
                }
            this.albumList = albumList;
        }else{
            albumList = getData();
        }
        AlbumListAdapter adapter = new AlbumListAdapter(this,albumList);
        setListAdapter(adapter);
    }

    protected List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title", "Xamarin");
        map.put("info", "Xamarin 1");
        map.put("img", R.drawable.ic_launcher);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "Android");
        map.put("info", "Android 2");
        map.put("img", R.drawable.ic_launcher);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "WP8");
        map.put("info", "WP8 3");
        map.put("img", R.drawable.ic_launcher);
        list.add(map);

        return list;
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Log.v("AlbumListView-click", (String) albumList.get(position).get("title"));
    }
}
