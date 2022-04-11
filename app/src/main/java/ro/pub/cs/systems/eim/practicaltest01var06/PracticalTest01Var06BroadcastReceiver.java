package ro.pub.cs.systems.eim.practicaltest01var06;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class PracticalTest01Var06BroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String data = data = intent.getStringExtra(Constants.BROADCAST_DATA_KEY);
        Log.d(Constants.TAG, data);
        Toast.makeText(context.getApplicationContext(), data, Toast.LENGTH_LONG).show();
    }
}