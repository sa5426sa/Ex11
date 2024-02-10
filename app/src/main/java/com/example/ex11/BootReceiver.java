package com.example.ex11;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class BootReceiver extends BroadcastReceiver {

    @SuppressLint({"UnsafeProtectedBroadcastReceiver", "ApplySharedPref"})
    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences bootCounter = context.getSharedPreferences("TIMES_BOOTED",Context.MODE_PRIVATE);
        int temp = bootCounter.getInt("counter", 0);
        int bootNum = temp + 1;
        SharedPreferences.Editor editor = bootCounter.edit();
        editor.putInt("counter", bootNum).commit();
    }
}