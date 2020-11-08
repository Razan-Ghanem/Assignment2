package com.example.assignment2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {
    private int seconds = 59 ;
    private int min = 5;
    private boolean running = false;
    private TextView txt2;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        txt2 = findViewById(R.id.txt2);
        runTimer();
        checkSaveInstance(savedInstanceState);
    }

    private void checkSaveInstance(Bundle savedInstanceState){
        if (savedInstanceState!= null){
            seconds = savedInstanceState.getInt("seconds");
            running= savedInstanceState.getBoolean("running");

        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("seconds",seconds);
        outState.putBoolean("running",running);
    }


    public void runTimer() {

        final TextView txt2 = findViewById(R.id.txt2);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (running) {
                    seconds--;
                    if (seconds ==0)
                        min =min-1;
                }
                    int secs = seconds;
                   int m = min;
                String time =  + m + " : " + secs;
                txt2.setText(time);
                handler.postDelayed(this, 1000);

            }
        });
    }

    public void btnStartOnClick(View view) {
        running=true;
    }

    public void btnPauseOnClick(View view) {
        running=false;

    }

    public void btnStopOnClick(View view) {
        running=false;
    }
}
