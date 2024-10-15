package com.example.prankapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    ImageView call, message, audio, setting;

    private View overlay;
    private FrameLayout settingsLayout;
    private ImageView settingClose;

    private Switch vibrationSwitch;
    private Switch soundSwitch;
    private Switch flashSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        call = findViewById(R.id.callbutton);
        message = findViewById(R.id.messagebutton);
        audio = findViewById(R.id.audiobutton);
        setting = findViewById(R.id.settingbutton);
        overlay = findViewById(R.id.overlay);
        settingsLayout = findViewById(R.id.settings_layout);
        settingClose = findViewById(R.id.settingclose);

        vibrationSwitch = settingsLayout.findViewById(R.id.vibration);
        soundSwitch = settingsLayout.findViewById(R.id.sound);
        flashSwitch = settingsLayout.findViewById(R.id.flash);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent calls = new Intent(MainActivity.this, VideoCallInterface.class);
                MainActivity.this.startActivity(calls);
            }
        });

        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent messages = new Intent(MainActivity.this, MessageInterface.class);
                MainActivity.this.startActivity(messages);
            }
        });

        audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent messages = new Intent(MainActivity.this, AudioActivityInterface.class);
                MainActivity.this.startActivity(messages);
            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                overlay.setVisibility(View.VISIBLE);
                settingsLayout.setVisibility(View.VISIBLE);
            }
        });

        settingClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                overlay.setVisibility(View.GONE);
                settingsLayout.setVisibility(View.GONE);
            }
        });

        vibrationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Enable vibration
                    Toast.makeText(MainActivity.this, "Vibration Enabled", Toast.LENGTH_SHORT).show();
                } else {
                    // Disable vibration
                    Toast.makeText(MainActivity.this, "Vibration Disabled", Toast.LENGTH_SHORT).show();
                }
            }
        });

        soundSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Enable sound
                    Toast.makeText(MainActivity.this, "Sound Enabled", Toast.LENGTH_SHORT).show();
                } else {
                    // Disable sound
                    Toast.makeText(MainActivity.this, "Sound Disabled", Toast.LENGTH_SHORT).show();
                }
            }
        });

        flashSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(MainActivity.this, "Flash Enabled", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Flash Disabled", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
