package zhang.abel.memmo.android;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

public class NotificationActivity extends Activity {
    private static int NOTIFICATION_ID = 1;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        notifyUser();
    }

    private void notifyUser() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification= buildNotification();
        notificationManager.notify(NOTIFICATION_ID, notification);
    }

    private Notification buildNotification() {
        return new Notification.Builder(this)
                .setContentTitle("提醒")
                .setContentText("拍照啦！")
                .setSmallIcon(R.drawable.ic_launcher)
                .setSound(Uri.withAppendedPath(MediaStore.Audio.Media.INTERNAL_CONTENT_URI, "20"))
                .setContentIntent(showNotificationDetail())
                .build();
    }

    private PendingIntent showNotificationDetail() {
        Intent intent = new Intent(this, NotificationDetail.class);
        intent.putExtra("notificationId", NOTIFICATION_ID);
        return PendingIntent.getActivity(this, 0, intent, 0);
    }
}