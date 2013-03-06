package zhang.abel.memmo.android;

import java.io.File;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class AlbumListView extends ListActivity {
    private List<Map<String, Object>> albumData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent1 = getIntent();
        if (intent1.getStringExtra("dirPath")!=null){

            String dirPath = intent1.getStringExtra("dirPath");
            File[] files = new File(dirPath).listFiles();
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            for (File aFile : files)
                if (aFile.isDirectory()) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("title", aFile.getName());
                    map.put("info", aFile.getName());
                    map.put("img", R.drawable.ic_launcher);
                    list.add(map);
                }
            albumData = list;
        }else{
            albumData = getData();
        }


        AlbumListAdapter adapter = new AlbumListAdapter(this);
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

        Log.v("AlbumListView-click", (String) albumData.get(position).get("title"));
    }

    public void showInfo(){
        new AlertDialog.Builder(this)
                .setTitle("listview")
                .setMessage("...")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }

    public final class ViewHolder{
        public ImageView img;
        public TextView title;
        public TextView info;
        public Button viewBtn;
    }

    public class AlbumListAdapter extends BaseAdapter{

        private LayoutInflater mInflater;


        public AlbumListAdapter(Context context){
            this.mInflater = LayoutInflater.from(context);
        }
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return albumData.size();
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            if (convertView == null) {

                holder=new ViewHolder();
                convertView = mInflater.inflate(R.layout.albumlistview, null);
                holder.img = (ImageView)convertView.findViewById(R.id.img);
                holder.title = (TextView)convertView.findViewById(R.id.title);
                holder.info = (TextView)convertView.findViewById(R.id.info);
                holder.viewBtn = (Button)convertView.findViewById(R.id.view_btn);
                convertView.setTag(holder);
            }else {
                holder = (ViewHolder)convertView.getTag();
            }

            holder.img.setBackgroundResource((Integer) albumData.get(position).get("img"));
            holder.title.setText((String) albumData.get(position).get("title"));
            holder.info.setText((String) albumData.get(position).get("info"));

            holder.viewBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    showInfo();
                }
            });

            return convertView;
        }
    }
}
