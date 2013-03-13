package zhang.abel.memmo.android;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class NotificationReceiver extends BroadcastReceiver {
    private int id;

    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        String info = bundle.getString("info");
        id = bundle.getInt("id", 1);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new Notification(R.drawable.ic_launcher, info, System.currentTimeMillis());
        notification.flags = Notification.FLAG_AUTO_CANCEL;

        Intent intentTarget = new Intent(context, NewMainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent contentIntent = PendingIntent.getActivity(context, id, intentTarget, PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setLatestEventInfo(context, "拍照提醒", info, contentIntent);

        notificationManager.notify(id, notification);
    }
}
