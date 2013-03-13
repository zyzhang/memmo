package zhang.abel.memmo.android;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Random;

public class NotificationSettingActivity extends Activity {
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
                        calendar.setTimeInMillis(System.currentTimeMillis());
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);
                        calendar.set(Calendar.SECOND, 0);
                        calendar.set(Calendar.MILLISECOND, 0);
                        Intent intent = new Intent(NotificationSettingActivity.this, NotificationReceiver.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("info", "该拍照啦！");
                        bundle.putInt("id", new Random().nextInt(100));
                        intent.putExtras(bundle);
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(NotificationSettingActivity.this, 0,intent, 0);
                        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (10 * 1000), (24 * 60 * 60 * 1000), pendingIntent);
                    }
                }, hourOfDay, minute, true).show();
    }
}