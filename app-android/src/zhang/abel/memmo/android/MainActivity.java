package zhang.abel.memmo.android;

import android.app.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import zhang.abel.memmo.android.entities.Album;
import zhang.abel.memmo.android.repositories.AlbumRepository;

import java.io.File;
import java.util.Calendar;

import static android.os.Environment.getExternalStoragePublicDirectory;

public class MainActivity extends Activity {

    private static int NOTIFICATION_ID = 1;
    AlbumRepository albumRepository = new AlbumRepository();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void createAlbum(View view) {
        final EditText albumName = new EditText(this);
        new AlertDialog.Builder(this)
                .setTitle("新建记忆相册")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setView(albumName)
                .setPositiveButton("好了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String folderName = String.valueOf(albumName.getText());
                        if (!folderName.isEmpty()) {
                            Album newAlbum = albumRepository.create(folderName);
                            String dirPath = newAlbum.getDirectory().getParent().toString();

                            Intent intent = new Intent(MainActivity.this, AlbumListActivity.class);
                            intent.putExtra("dirPath",dirPath);
                            startActivity(intent);
                        }
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }

    public void onClickToAlbum(View view) {
        Intent intent = new Intent(MainActivity.this, AlbumActivity.class);
        startActivity(intent);
    }

    public void setNotification(View view) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification = buildNotification();
        notificationManager.notify(NOTIFICATION_ID, notification);
//TODO:
// 1. will using alarm to set repeating notification
// 2. will allow user to set custom alarm time

//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.HOUR_OF_DAY, 11);
//        calendar.set(Calendar.MINUTE, 34);
//        calendar.set(Calendar.SECOND, 00);
//        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 5*1000, pendingIntent);
    }

    private Notification buildNotification() {
        return new Notification.Builder(this)
                    .setContentTitle("提醒")
                    .setContentText("拍照啦！")
                    .setSmallIcon(R.drawable.ic_launcher)
                    .setSound(Uri.withAppendedPath(MediaStore.Audio.Media.INTERNAL_CONTENT_URI, "20"))
                    .setContentIntent(onNotificationClicked())
                    .build();
    }

    private PendingIntent onNotificationClicked() {
        Intent intent = new Intent(this, NotificationActivity.class);
        intent.putExtra("notificationId", NOTIFICATION_ID);
        return PendingIntent.getActivity(this, 0, intent, 0);
    }
}
