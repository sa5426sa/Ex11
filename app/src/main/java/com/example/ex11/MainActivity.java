package com.example.ex11;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView bootCounter, headphoneCounter, counterCounter;
    private HeadphoneReceiver headphoneReceiver;

    @SuppressLint({"SetTextI18n", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bootCounter = findViewById(R.id.bootCounter);
        headphoneCounter = findViewById(R.id.headphoneCounter);
        counterCounter = findViewById(R.id.counterCounter);
        headphoneReceiver = new HeadphoneReceiver();
        SharedPreferences bootPrefs = getSharedPreferences("TIMES_BOOTED",MODE_PRIVATE);
        int bootNum = bootPrefs.getInt("counter", 0);
        bootCounter.setText(Integer.toString(bootNum));
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter headphoneFilter = new IntentFilter(AudioManager.ACTION_HEADSET_PLUG);
        registerReceiver(headphoneReceiver, headphoneFilter);
        SharedPreferences headsetPrefs = getSharedPreferences("HEADSET_PLUGGED", MODE_PRIVATE);
        int headsetNum = headsetPrefs.getInt("counter", 0);
        int counterNum = headsetPrefs.getInt("counterCounter", 0);
        headphoneCounter.setText(Integer.toString(headsetNum));
        counterCounter.setText(Integer.toString(counterNum));
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(headphoneReceiver);
    }
}