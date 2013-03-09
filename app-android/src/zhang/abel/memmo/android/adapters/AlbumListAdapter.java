package zhang.abel.memmo.android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import zhang.abel.memmo.android.R;
import zhang.abel.memmo.android.entities.AlbumListItem;

import java.util.List;
import java.util.Map;


public class AlbumListAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<Map<String, Object>> albumList;

    public AlbumListAdapter(Context context){
        this.mInflater = LayoutInflater.from(context);
    }

    public AlbumListAdapter(Context context,List<Map<String, Object>> albumList){
        this.mInflater = LayoutInflater.from(context);
        this.albumList = albumList;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return albumList.size();
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

        AlbumListItem holder = null;
        if (convertView == null) {

            holder=new AlbumListItem();
            convertView = mInflater.inflate(R.layout.albumlistview, null);
            holder.img = (ImageView)convertView.findViewById(R.id.img);
            holder.title = (TextView)convertView.findViewById(R.id.title);
            holder.info = (TextView)convertView.findViewById(R.id.info);
            holder.viewBtn = (Button)convertView.findViewById(R.id.view_btn);
            convertView.setTag(holder);
        }else {
            holder = (AlbumListItem)convertView.getTag();
        }

        holder.img.setBackgroundResource((Integer) albumList.get(position).get("img"));
        holder.title.setText((String) albumList.get(position).get("title"));
        holder.info.setText((String) albumList.get(position).get("info"));

        return convertView;
    }
}
