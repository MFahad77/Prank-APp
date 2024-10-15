package com.example.prankapp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prankapp.Adapters.ItemAdapter;
import com.example.prankapp.Model.Item;

import java.util.ArrayList;
import java.util.List;

public class VideoCallInterface extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ItemAdapter adapter;
    private List<Item> itemList;
    private Toolbar toolbar;
    private ImageView backButton, optionsButton;
    private TextView toolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videocallinterface);

        toolbar = findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);

        backButton = findViewById(R.id.back_button);
        optionsButton = findViewById(R.id.options_button);
        toolbarTitle = findViewById(R.id.toolbar_title);

        toolbarTitle.setText("Fake Video Call");

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        optionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOptionsMenu(v);
            }
        });

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));


        itemList = new ArrayList<>();
        itemList.add(new Item("Santa", R.drawable.santa));
        itemList.add(new Item("Hacker", R.drawable.hacker));
        itemList.add(new Item("Princess Anna", R.drawable.princess));
        itemList.add(new Item("Scientist", R.drawable.scientist));
        itemList.add(new Item("BTS", R.drawable.bts));

        adapter = new ItemAdapter(this, itemList);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new ItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Item selectedItem = itemList.get(position);
                Intent intent = new Intent(VideoCallInterface.this, VideoCallIncoming.class);
                intent.putExtra("callerName", selectedItem.getName());
                intent.putExtra("callerImage", selectedItem.getImageResource());
                startActivity(intent);
            }
        });
    }

    private void showOptionsMenu(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.share) {
                    String shareText = "Check out this amazing app: https://play.google.com/store/apps/details?id=" + getPackageName();

                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, shareText);
                    sendIntent.setType("text/plain");

                    Intent shareIntent = Intent.createChooser(sendIntent, null);
                    startActivity(shareIntent);
                    return true;
                } else if (item.getItemId() == R.id.feedback) {
                    // Handle feedback option
                    return true;
                } else if (item.getItemId() == R.id.rate) {
                    String appPackageName = getPackageName();

                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                    } catch (ActivityNotFoundException e) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                    }
                    return true;
                } else {
                    return false;
                }
            }
        });
        popup.show();
    }
}
