package id.im.skybandtest1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class YourBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
       int result = intent.getIntExtra("ecrPort", 2222);
        Toast.makeText(context, "Data from Mada App"+result, Toast.LENGTH_SHORT).show();
    }
}
