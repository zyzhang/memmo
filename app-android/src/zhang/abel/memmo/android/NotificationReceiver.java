package zhang.abel.memmo.android;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import zhang.abel.memmo.android.entities.Album;

import java.util.ArrayList;
import java.util.Calendar;

public class NotificationReceiver extends BroadcastReceiver {
    private int id;
    private String info;
    private ArrayList<Integer> selectDays;
    private Album currentAlbum;

    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        id = bundle.getInt("id");
        info = bundle.getString("info");
        selectDays = bundle.getIntegerArrayList("selectDays");
        currentAlbum = (Album) bundle.getSerializable(AlbumListActivity.SER_KEY);

        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        if(selectDays.contains(dayOfWeek)){
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(id, buildNotification(context));
        }
    }

    private Notification buildNotification(Context context) {
        Notification notification = new Notification(R.drawable.ic_launcher, info, System.currentTimeMillis());
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        Intent intent = new Intent(context, AlbumActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(AlbumListActivity.SER_KEY, currentAlbum);
        intent.putExtras(bundle);
        notification.setLatestEventInfo(context, "提醒", info, PendingIntent.getActivity(context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT));
        return notification;
    }
}
