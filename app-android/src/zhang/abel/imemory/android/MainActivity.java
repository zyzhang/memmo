package zhang.abel.imemory.android;

import android.app.Activity;
import android.content.Intent;
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
}
