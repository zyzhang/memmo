package zhang.abel.memmo.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void clickMe(View view){
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText("成功了");
        setContentView(textView);
    }

    public void CheckMe(View view){
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText("我们一直在成功！");
        setContentView(textView);
    }
}
