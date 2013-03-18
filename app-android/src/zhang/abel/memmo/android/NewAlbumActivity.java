package zhang.abel.memmo.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import zhang.abel.memmo.android.entities.Album;
import zhang.abel.memmo.android.entities.Picture;
import zhang.abel.memmo.android.repositories.AlbumRepository;
import zhang.abel.memmo.android.repositories.PictureRepository;
import zhang.abel.memmo.android.utils.IntentUtils;

import java.io.IOException;

public class NewAlbumActivity extends Activity {

    public static final int TAKE_PIC_MENU_ITEM_ID = 1;
    public static final int REMINDER_MENU_ITEM_ID = 2;

    private static final int ACTION_TAKE_PIC = 11;

    private AlbumRepository albumRepository;
    private PictureRepository pictureRepository;
    private Picture currentPicture;
    private Album currentAlbum;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newalbum);

        pictureRepository = new PictureRepository();
        albumRepository = new AlbumRepository();

        currentAlbum = (Album) getIntent().getSerializableExtra(NewMainActivity.SER_KEY);
        TextView albumName = (TextView) findViewById(R.id.newalbumname);
        albumName.setText(currentAlbum.getDirectory().getPath());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuItem takePictureMenuItem = menu.add(0, TAKE_PIC_MENU_ITEM_ID, 0, "拍照");
        MenuItem reminderMenuItem = menu.add(0, REMINDER_MENU_ITEM_ID, 1, "提醒");
        takePictureMenuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        reminderMenuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case TAKE_PIC_MENU_ITEM_ID:
                String intentName = MediaStore.ACTION_IMAGE_CAPTURE;
                if (IntentUtils.isIntentAvailable(this, intentName)) {
                    dispatchTakePictureIntent();
                } else {
                    showMessageBox("Can not call the Camera...");
                }
                return true;
            case REMINDER_MENU_ITEM_ID:
                Intent intent = new Intent(this, NotificationSettingActivity.class);
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showMessageBox(String message) {
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
        dlgAlert.setMessage(message);
        dlgAlert.setTitle("Message");
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ACTION_TAKE_PIC) {
            if (resultCode == RESULT_OK) {
                addPictureToGallery(currentPicture);
            }
        }
    }

    private void addPictureToGallery(Picture picture) {
        Intent mediaScanIntent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        mediaScanIntent.setData(picture.getUri());
        this.sendBroadcast(mediaScanIntent);
    }

    private void dispatchTakePictureIntent() {
        try {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            Album album = currentAlbum;
            if (album == null) {
                showMessageBox("No Album.........");
            }
            currentPicture = album.addNewPicture();
            pictureRepository.save(currentPicture);
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, currentPicture.getUri());

            startActivityForResult(takePictureIntent, ACTION_TAKE_PIC);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}