package zhang.abel.memmo.android.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import zhang.abel.memmo.android.R;
import zhang.abel.memmo.android.entities.Album;
import zhang.abel.memmo.android.entities.AlbumListItem;

import java.io.File;
import java.util.List;
import java.util.Map;


public class AlbumListAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<Album> albums;

    public AlbumListAdapter(Context context,List<Album> albums) {
        this.mInflater = LayoutInflater.from(context);
        this.albums = albums;
    }

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

        File file = new File(albums.get(position).getDirectory().getPath());
        if (file.listFiles().length == 0){
            item.img.setBackgroundResource(R.drawable.ic_launcher);
        }else{
            item.img.setImageBitmap(decodeBitmap(file.listFiles()[0]));
        }
        item.title.setText(albums.get(position).getName());
        item.info.setText(albums.get(position).getRemark());

        return convertView;
    }

    private Bitmap decodeBitmap(File file) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        float realWidth = options.outWidth;
        float realHeight = options.outHeight;
        int scale = (int) ((realHeight > realWidth ? realHeight : realWidth) / 500);
        if (scale <= 0) {
            scale = 5;
        }
        options.inSampleSize = scale;
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(file.getAbsolutePath(), options);
    }
}
