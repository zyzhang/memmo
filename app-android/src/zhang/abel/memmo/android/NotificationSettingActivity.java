package zhang.abel.memmo.android;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TimePicker;

import java.util.Calendar;

public class NotificationSettingActivity extends Activity {
    private static final int NOTIFICATION_ID = 1;
    private static final String NOTIFICATION_CONTENT = "该拍照啦！";
    private static final int NOTIFICATION_INTERVAL = 24 * 60 * 60 * 1000;
    private AlarmManager alarmManager;
    private Calendar calendar;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newmain);

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        setCalendar(hourOfDay, minute);
                        alarmManager.setRepeating(
                                AlarmManager.RTC_WAKEUP,
                                calendar.getTimeInMillis(),
                                NOTIFICATION_INTERVAL,
                                getPendingIntent()
                        );
                    }
                }, hourOfDay, minute, true).show();
    }

    private PendingIntent getPendingIntent() {
        Intent intent = new Intent(this, NotificationReceiver.class);
        intent.putExtras(setNotificationBundle());
        return PendingIntent.getBroadcast(NotificationSettingActivity.this, 0,intent, 0);
    }

    private void setCalendar(int hourOfDay, int minute) {
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    private Bundle setNotificationBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("info", NOTIFICATION_CONTENT);
        bundle.putInt("id", NOTIFICATION_ID);
        return bundle;
    }
}