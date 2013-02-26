package zhang.abel.memmo.android.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import zhang.abel.memmo.android.entities.Picture;

public class BitmapUtils {
    public static Bitmap loadAsBitmap(Picture picture, int targetWidth, int targetHeight) {
         /* Get the size of the image */
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(picture.getFile().getAbsolutePath(), bmOptions);

        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

		/* Figure out which way needs to be reduced less */
        int scaleFactor = 1;
        if ((targetWidth > 0) || (targetHeight > 0)) {
            scaleFactor = Math.min(photoW / targetWidth, photoH / targetHeight);
        }

		/* Set bitmap options to scale the image decode target */
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

		/* Decode the JPEG file into a Bitmap */
        return BitmapFactory.decodeFile(picture.getFile().getAbsolutePath(), bmOptions);
    }
}
