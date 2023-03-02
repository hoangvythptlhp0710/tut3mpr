package com.tut4;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    String link;
    @Override
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText input = findViewById(R.id.linkVideo);
        ImageButton play = findViewById(R.id.play);
        play.setOnClickListener(v -> {
                link = input.getText().toString();
            if (link.isEmpty()) {
                Toast.makeText(this, "Please enter a link", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(MainActivity.this, PlayerActivity.class);
                intent.putExtra("link", link);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //get current playback position


        //save the video position
        outState.putString("link", link);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        //get saved video playback position
        link = savedInstanceState.getString("link");

        //set the video playback position
        EditText input = findViewById(R.id.linkVideo);
        input.setText(link);

    }
}