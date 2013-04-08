package zhang.abel.memmo.android;

import android.app.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TimePicker;
import zhang.abel.memmo.android.entities.Album;
import zhang.abel.memmo.android.repositories.AlbumRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class NotificationSettingActivity extends Activity {

    private static final String NOTIFICATION_CONTENT = "该拍照啦！";
    private static final int NOTIFICATION_INTERVAL = 24 * 60 * 60 * 1000;
    private int NOTIFICATION_ID = GenerateNotificationId();
    private AlarmManager alarmManager;
    private Calendar calendar;
    private Album currentAlbum;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.albumlist);

        currentAlbum = (Album) getIntent().getSerializableExtra(AlbumListActivity.SER_KEY);

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        setTime(hourOfDay, minute);
                    }
                }, hourOfDay, minute, true).show();
    }

    private void setTime(int hour, int min) {
        final int hourOfDay = hour;
        final int minute = min;
        final String[] days = new String[] { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
        final boolean [] selectDays = new boolean[] { false, false, false, false, false, false, false };

        Dialog dialog = new AlertDialog.Builder(this)
                .setTitle("Select Day")
                .setMultiChoiceItems(days, selectDays, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        selectDays[which] = isChecked;
                    }
                })
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    ArrayList<Integer> intDays = new ArrayList<Integer>();
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int index = 0; index < selectDays.length; index++) {
                            if (selectDays[index]) {
                                intDays.add(index + 1);
                            }
                        }
                        doSetting(hourOfDay, minute, intDays);
                    }
                })
                .create();
        dialog.show();
    }

    private void doSetting(int hourOfDay, int minute, ArrayList<Integer> intDays) {
        setCalendar(hourOfDay, minute);
        alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(),
                NOTIFICATION_INTERVAL,
                getPendingIntent(intDays)
        );
        SaveNotificationInfo(calendar);
        Intent intent = new Intent(this, AlbumActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(AlbumListActivity.SER_KEY, currentAlbum);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void SaveNotificationInfo(Calendar calendar) {
        String prefName = "notification";
        SharedPreferences pref = getSharedPreferences(prefName, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        String key = currentAlbum.getName();
        editor.putLong(key, calendar.getTimeInMillis());
        editor.commit();
    }

    private PendingIntent getPendingIntent(ArrayList<Integer> intDays) {
        Intent intent = new Intent(this, NotificationReceiver.class);
        intent.putExtras(setNotificationBundle(intDays));
        return PendingIntent.getBroadcast(NotificationSettingActivity.this, NOTIFICATION_ID, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private void setCalendar(int hourOfDay, int minute) {
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    private Bundle setNotificationBundle(ArrayList<Integer> intDays) {
        Bundle bundle = new Bundle();
        bundle.putString("info", NOTIFICATION_CONTENT);
        bundle.putInt("id", NOTIFICATION_ID);
        bundle.putSerializable(AlbumListActivity.SER_KEY, currentAlbum);
        bundle.putIntegerArrayList("selectDays", intDays);
        return bundle;
    }

    private int GenerateNotificationId(){
        Random random = new Random();
        int randomInt = random.nextInt(100);
        return randomInt + 1;
    }
}