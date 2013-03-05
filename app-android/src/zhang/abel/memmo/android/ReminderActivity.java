package zhang.abel.memmo.android;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;

import java.util.Calendar;

public class ReminderActivity extends Activity {
    private static int interval = 24 * 60 * 60 * 1000;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setReminder(true);
    }

    private void setReminder(boolean enable) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(this, ReminderReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        if (enable) {
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, getDefaultReminderTime().getTimeInMillis(), interval, pendingIntent);
        } else {
            alarmManager.cancel(pendingIntent);
        }
    }

    private Calendar getDefaultReminderTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 00);
        return calendar;
    }
}
