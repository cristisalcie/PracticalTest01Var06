package ro.pub.cs.systems.eim.practicaltest01var06;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.Random;

public class PracticalTest01Var06Service extends Service {
    final private Random random = new Random();
    private Thread processingThread;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(Constants.TAG, "[service]onCreate() method was invoked");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(Constants.TAG, "[service]onBind() method was invoked");
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(Constants.TAG, "[service]onUnbind() method was invoked");
        return true;
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d(Constants.TAG, "[service]onRebind() method was invoked");
    }

    @Override
    public void onDestroy() {
        Log.d(Constants.TAG, "[service]onDestroy() method was invoked");
        processingThread.interrupt();  // Stop thread from working.
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(Constants.TAG, "[service]onStartCommand() method was invoked");
        processingThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(Constants.TAG, "[service]Thread.run() was invoked, PID: " + android.os.Process.myPid() + " TID: " + android.os.Process.myTid());
                try {
                    Thread.sleep(Constants.SLEEP_TIME);
                } catch (InterruptedException e) {
                    Log.d(Constants.TAG, "[service]Thread got interrupted (stopped)!");
                }
                int victory_code = intent.getIntExtra(Constants.SERVICE_VICOTRY_DATA_KEY, 0);
                    Log.d(Constants.TAG, "[service] victorycode = " + victory_code);
                if (victory_code == 1) {
                    int totalScore = intent.getIntExtra(Constants.TOTAL_SCORE_KEY, -1);
                    String data = String.format("[%s] %d",
                        new java.util.Date(),
                        totalScore);

                    Intent intent1 = new Intent();
                    intent1.setAction(Constants.ACTION_1);
                    intent1.putExtra(Constants.BROADCAST_DATA_KEY, data);
                    sendBroadcast(intent1);
                }
            }
        });
        processingThread.start();

        return START_REDELIVER_INTENT;
    }
}