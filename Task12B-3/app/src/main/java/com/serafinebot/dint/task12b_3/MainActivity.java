package com.serafinebot.dint.task12b_3;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = "MainActivity";
    private static final String sharedPref = "task12-pref";
    private static final String counterKey = "counter-key";
    private int counter;
    private TextView counterView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counterView = (TextView)findViewById(R.id.counter);

        SharedPreferences sp = getSharedPreferences(sharedPref, Activity.MODE_PRIVATE);
        counter = sp.getInt(counterKey, 0);
        setCounter(counter);
    }

    public void counterButtonPressed(View view) {
        if (counterView != null) {
            setCounter(++counter);
            Log.d(LOG_TAG, String.format("counter: %d", counter));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences sp = getSharedPreferences(sharedPref, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(counterKey, counter);
        editor.apply();
    }

    @SuppressLint("SetTextI18n")
    public void setCounter(int val) {
        counter = val;
        counterView.setText(Integer.toString(counter));
    }
}