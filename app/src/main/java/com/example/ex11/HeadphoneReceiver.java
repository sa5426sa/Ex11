package com.example.ex11;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class HeadphoneReceiver extends BroadcastReceiver {
    boolean isAlreadyConnected = false;

    @SuppressLint("ApplySharedPref")
    @Override
    public void onReceive(Context context, Intent intent) {
        int state = intent.getIntExtra("state",-1);
        SharedPreferences headphoneCounter = context.getSharedPreferences("HEADSET_PLUGGED", Context.MODE_PRIVATE);
        int temp = headphoneCounter.getInt("counter", 0);
        int headphoneNum = temp + 1;
        int counterCounter = headphoneCounter.getInt("counterCounter", 0) + 1;
        int countCountCounter = headphoneCounter.getInt("counter2", 0);
        SharedPreferences.Editor editor = headphoneCounter.edit();
        if (state == 1) {
            if (!isAlreadyConnected) {
                if (countCountCounter == 4) {
                    editor.putInt("counterCounter", counterCounter).commit();
                    editor.putInt("counter2", 0).commit();
                }
                else editor.putInt("counter2", countCountCounter + 1).commit();
                editor.putInt("counter", headphoneNum).commit();
                isAlreadyConnected = true;
            }
            else {
                editor.putInt("counter", temp).commit();
            }
        }
        else {
            editor.putInt("counter", temp).commit();
            isAlreadyConnected = false;
        }
    }
}