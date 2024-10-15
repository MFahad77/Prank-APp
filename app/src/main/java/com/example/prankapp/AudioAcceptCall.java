package com.example.prankapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class AudioAcceptCall extends AppCompatActivity {

    private Handler timerHandler;
    private Runnable timerRunnable;
    private int timerSeconds;
    private TextView timerTextView;
    private ImageView cutCallButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audiocallaccept);

        // Initialize views
        timerTextView = findViewById(R.id.timer);
        cutCallButton = findViewById(R.id.cutcall);

        // Initialize timer
        timerHandler = new Handler();
        timerSeconds = 0;

        // Start the timer
        startTimer();

        // Set up the cut call button
        cutCallButton.setOnClickListener(v -> {
            stopTimer();
            Intent resultIntent = new Intent();
            setResult(RESULT_OK, resultIntent); // Set result code
            finish(); // Close the activity
        });
    }

    private void startTimer() {
        timerRunnable = new Runnable() {
            @Override
            public void run() {
                timerSeconds++;
                int minutes = timerSeconds / 60;
                int seconds = timerSeconds % 60;
                timerTextView.setText(String.format("%02d:%02d", minutes, seconds));

                if (timerSeconds < 13) {
                    timerHandler.postDelayed(this, 1000);
                }
            }
        };
        timerHandler.post(timerRunnable);
    }

    private void stopTimer() {
        if (timerRunnable != null) {
            timerHandler.removeCallbacks(timerRunnable);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopTimer();
    }
}
