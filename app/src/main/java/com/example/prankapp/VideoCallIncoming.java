package com.example.prankapp;


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class VideoCallIncoming extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private ImageView answerButton;
    private ImageView endCallButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videocallincoming);

        ImageView callerImage = findViewById(R.id.callerImage);
        TextView callerName = findViewById(R.id.callerName);
        answerButton = findViewById(R.id.answervideoButton);
        endCallButton = findViewById(R.id.endCallButton);

        String name = getIntent().getStringExtra("callerName");
        int imageResId = getIntent().getIntExtra("callerImage", R.drawable.santa);

        callerName.setText(name);
        callerImage.setImageResource(imageResId);

        mediaPlayer = MediaPlayer.create(VideoCallIncoming.this, R.raw.audio1);
        mediaPlayer.start();

        answerButton.setOnClickListener(v -> {
            Intent intent = new Intent(VideoCallIncoming.this, VideoCall.class);
            startActivityForResult(intent, 1);
        });

        endCallButton.setOnClickListener(v -> {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            }
            finish();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
