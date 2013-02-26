package zhang.abel.memmo.android;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import zhang.abel.memmo.android.entities.Album;
import zhang.abel.memmo.android.entities.Picture;
import zhang.abel.memmo.android.repositories.AlbumRepository;
import zhang.abel.memmo.android.repositories.PictureRepository;
import zhang.abel.memmo.android.utils.BitmapUtils;
import zhang.abel.memmo.android.utils.IntentUtils;

import java.io.IOException;

public class AlbumActivity extends Activity {

    // temp name, it should be already created before.
    private final String albumName = "memmo";

    private static final int ACTION_TAKE_PHOTO_B = 1;

    private AlbumRepository albumRepository;
    private PictureRepository pictureRepository;

    private static final String BITMAP_STORAGE_KEY = "viewbitmap";
    private static final String IMAGEVIEW_VISIBILITY_STORAGE_KEY = "imageviewvisibility";

    private Picture currentPicture;

    private ImageView imageView;
    private Bitmap mImageBitmap;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.album);

        imageView = (ImageView) findViewById(R.id.imageView);

        Button btnTakePicView = (Button) findViewById(R.id.btn_take_pic);
        setBtnListenerOrDisable(
                btnTakePicView,
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dispatchTakePictureIntent(ACTION_TAKE_PHOTO_B);
                    }
                },
                MediaStore.ACTION_IMAGE_CAPTURE
        );

        albumRepository = new AlbumRepository();
        pictureRepository = new PictureRepository();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ACTION_TAKE_PHOTO_B) {
            if (resultCode == RESULT_OK) {
                displayPicture(imageView, currentPicture);
                addPictureToGallery(currentPicture);
            }
        }
    }

    // Some lifecycle callbacks so that the image can survive orientation change
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(BITMAP_STORAGE_KEY, mImageBitmap);
        outState.putBoolean(IMAGEVIEW_VISIBILITY_STORAGE_KEY, (mImageBitmap != null));
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mImageBitmap = savedInstanceState.getParcelable(BITMAP_STORAGE_KEY);
        imageView.setImageBitmap(mImageBitmap);
        imageView.setVisibility(
                savedInstanceState.getBoolean(IMAGEVIEW_VISIBILITY_STORAGE_KEY) ?
                        ImageView.VISIBLE : ImageView.INVISIBLE
        );
    }

    private void addPictureToGallery(Picture picture) {
        Intent mediaScanIntent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        mediaScanIntent.setData(picture.getUri());
        this.sendBroadcast(mediaScanIntent);
    }

    private void displayPicture(ImageView imageView, Picture picture) {

		/* There isn't enough memory to open up more than a couple camera photos */
        /* So pre-scale the target bitmap into which the file is decoded */

        Bitmap bitmap = BitmapUtils.loadAsBitmap(picture, imageView.getWidth(), imageView.getHeight());

		/* Associate the Bitmap to the ImageView */
        imageView.setImageBitmap(bitmap);
        imageView.setVisibility(View.VISIBLE);
    }

    private void dispatchTakePictureIntent(int actionCode) {
        if (actionCode == ACTION_TAKE_PHOTO_B) {
            try {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Album album = getAlbum();
                currentPicture = album.addNewPicture();
                pictureRepository.save(currentPicture);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, currentPicture.getUri());

                startActivityForResult(takePictureIntent, actionCode);
            } catch (IOException e) {
                e.printStackTrace();
                currentPicture = null;
            }
        }

    }

    private Album getAlbum() {
        Album album = albumRepository.get(albumName);

        // TODO the album should be already created
        if (album == null) {
            album = albumRepository.create(albumName);
        }
        return album;
    }

    private void setBtnListenerOrDisable(
            Button btn,
            Button.OnClickListener onClickListener,
            String intentName
    ) {
        if (IntentUtils.isIntentAvailable(this, intentName)) {
            btn.setOnClickListener(onClickListener);
        } else {
            btn.setText(getText(R.string.cannot).toString() + " " + btn.getText());
            btn.setClickable(false);
        }
    }
}