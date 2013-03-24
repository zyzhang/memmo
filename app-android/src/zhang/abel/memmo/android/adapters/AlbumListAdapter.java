package zhang.abel.memmo.android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import zhang.abel.memmo.android.R;
import zhang.abel.memmo.android.entities.Album;
import zhang.abel.memmo.android.entities.AlbumListItem;

import java.util.List;
import java.util.Map;


public class AlbumListAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<Map<String, Object>> albumList;
    private List<Album> albums;

    public AlbumListAdapter(Context context,List<Album> albums) {
        this.mInflater = LayoutInflater.from(context);
        this.albums = albums;
    }

//    public AlbumListAdapter(Context context,List<Map<String, Object>> albumList){
//        this.mInflater = LayoutInflater.from(context);
//        this.albumList = albumList;
//    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return albums.size();
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

        AlbumListItem item = null;

        if (convertView == null) {
            item = new AlbumListItem();
            convertView = mInflater.inflate(R.layout.albumlist, null);
            item.img = (ImageView)convertView.findViewById(R.id.img);
            item.title = (TextView)convertView.findViewById(R.id.title);
            item.info = (TextView)convertView.findViewById(R.id.info);

            convertView.setTag(item);
        }else {
            item = (AlbumListItem)convertView.getTag();
        }

        item.img.setBackgroundResource(R.drawable.ic_launcher);
        item.title.setText(albums.get(position).getName());
        item.info.setText(albums.get(position).getRemark());

        return convertView;
    }
}
