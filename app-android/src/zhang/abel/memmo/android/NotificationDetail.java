package zhang.abel.memmo.android;

import android.app.Activity;
import android.app.NotificationManager;
import android.os.Bundle;
import android.widget.TextView;

public class NotificationDetail extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notificationdetail);
        showDetail();
    }

    private void showDetail() {
        TextView textView = (TextView) findViewById(R.id.notification_detail);
        textView.setText("该是拍照的时候啦！");
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancel(getIntent().getExtras().getInt("notificationId"));
    }
}