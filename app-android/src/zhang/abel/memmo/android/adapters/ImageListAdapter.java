package zhang.abel.memmo.android.adapters;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import zhang.abel.memmo.android.entities.Picture;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImageListAdapter extends BaseAdapter {

    private Context context;
    private List<Bitmap> imageBitmapList;

    public ImageListAdapter(Context context, List<Picture> pictures) {
        this.context = context;
        initialImageBitmapList(pictures);
    }

    public int getCount() {
        return imageBitmapList.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(140, 140));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageBitmap(imageBitmapList.get(position));
        return imageView;
    }

    private void initialImageBitmapList(List<Picture> pictures) {
        imageBitmapList = new ArrayList<Bitmap>();

        for (Picture pic : pictures) {
            imageBitmapList.add(decodeBitmap(pic.getFile()));
        }
    }

    public Bitmap decodeBitmap(File file) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        float realWidth = options.outWidth;
        float realHeight = options.outHeight;
        int scale = (int) ((realHeight > realWidth ? realHeight : realWidth) / 100);
        if (scale <= 0) {
            scale = 1;
        }
        options.inSampleSize = scale;
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(file.getAbsolutePath(), options);
    }
}
