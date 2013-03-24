package zhang.abel.memmo.android.adapters;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImageListAdapter extends BaseAdapter {

    private Context context;
    private String albumPath;
    private List<Bitmap> imageBitmapList;

    public ImageListAdapter(Context context, String albumPath) {
        this.context = context;
        this.albumPath = albumPath;
        initialImageBitmapList();
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

    private List<Bitmap> initialImageBitmapList() {
        imageBitmapList = new ArrayList<Bitmap>();

        File fileDir = new File(albumPath);
        File[] files = fileDir.listFiles();

        if(files != null){
            for(File file : files){
                String fileName = file.getName();
                if(fileName.lastIndexOf(".") > 0
                        && fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).equals("jpg")){
                    Bitmap image =decodeBitmap(albumPath + File.separator + file.getName());
                    imageBitmapList.add(image);
                }
            }
        }
        return imageBitmapList;
    }
    public Bitmap decodeBitmap(String path)
    {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap imageBitmap = BitmapFactory.decodeFile(path, options);
        float realWidth = options.outWidth;
        float realHeight = options.outHeight;
        int scale = (int) ((realHeight > realWidth ? realHeight : realWidth) / 100);
        if (scale <= 0)
        {
            scale = 1;
        }
        options.inSampleSize = scale;
        options.inJustDecodeBounds = false;
        imageBitmap = BitmapFactory.decodeFile(path, options);

        return imageBitmap;
    }
}
