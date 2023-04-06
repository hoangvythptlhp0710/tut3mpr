package com.asm1.tut9;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init bookmark fragment
        FragmentManager manager = getSupportFragmentManager();

        BookmarkFragment bookmarkFragment = new BookmarkFragment();
        manager.beginTransaction().replace(R.id.fragment_container, bookmarkFragment).commit();


        // handle events


    }
}