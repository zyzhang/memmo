package zhang.abel.memmo.android.gif;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class JpgToGif {
    //synchronized
    public  static void jpgToGif(String pic[],
                                 String newPic) {
        try {
            Log.i("jpgToGif", "is connection =" + newPic);
            AnimatedGifEncoder e = new AnimatedGifEncoder();
            e.setRepeat(1);
            e.start(newPic);

            for (int i = 0; i < pic.length; i++) {
                e.setDelay(200); // 设置播放的延迟时间
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                options.inSampleSize = 5;
                options.inJustDecodeBounds = false;
                Bitmap src= BitmapFactory.decodeFile(pic[i], options);
                e.addFrame(src); // 添加到帧中
            }
            e.finish();//刷新任何未决的数据，并关闭输出文件
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}