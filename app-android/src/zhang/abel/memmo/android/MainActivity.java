package zhang.abel.memmo.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;

import java.io.File;

import static android.os.Environment.getExternalStoragePublicDirectory;

public class MainActivity extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void createAlbum(View view){
        final EditText albumName = new EditText(this);
        new AlertDialog.Builder(this)
                .setTitle("新建记忆相册")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setView(albumName)
                .setPositiveButton("好了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (hasSDCard()) {
                            String path = getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
                            String folderName = String.valueOf(albumName.getText());
                            File folderPath = new File(path + File.separator + folderName);

                            if (!folderPath.exists()) {
                                folderPath.mkdir();
                            }
                        }else{
                            //write the internal storage directory.
                        }

                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }

    private static boolean hasSDCard() {
        String status = Environment.getExternalStorageState();
        return status.equals(Environment.MEDIA_MOUNTED);
    }
}
