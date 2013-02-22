package zhang.abel.memmo.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void createAlbum(View view){
        new AlertDialog.Builder(this)
                .setTitle("新建记忆相册")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setView(new EditText(this))
                .setPositiveButton("好了", null)
                .setNegativeButton("取消", null)
                .show();
    }
}
