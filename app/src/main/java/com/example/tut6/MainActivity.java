package com.example.tut6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences
                = this.getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);

        String username = sharedPreferences.getString("language", "Vietnamese");
        sharedPreferences.edit().putString("language", "English").apply();

        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setMessage("Confirm?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    Toast.makeText(this, "Yes", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("No", null)
                .show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.vie:
                Toast.makeText(this, "VIETNAMESE", Toast.LENGTH_SHORT);
                return true;
            case R.id.eng:
                Toast.makeText(this, "ENGLISH", Toast.LENGTH_SHORT);
                return true;
        }

        return false;
    }
}