package com.example.jola.tutorialbroadcast;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView tvLevel;
    private BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvLevel = (TextView) findViewById(R.id.level);

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED)) {

                    String state = "Stan wlasnie sprawdzony";
                    if (isInitialStickyBroadcast()) {
                        state = "Brak aktualizacji";
                    }
                    tvLevel.setText(String.valueOf(intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1))
                            + System.getProperty("line.separator") + state);
                }
            }
        };

        registerReceiver(broadcastReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }
}
