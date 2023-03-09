package com.asm1.tut5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.asm1.tut5.adapters.FriendAdapter;
import com.asm1.tut5.models.Friend;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static int REQUEST_NEW_FRIEND = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // dataset

        List<Friend> friends = new ArrayList<>();
        friends.add(new Friend("John", "abs@gmail.com", "123456", "ABC"));
        friends.add(new Friend("Katarina", "abd@gmail.com", "0234123", "FPT"));
        friends.add(new Friend("Peter", "abcs@gmail.com", "0772394123", "CMC"));


        // recycler view
        RecyclerView rvFriends = findViewById(R.id.recyclerView);


        //set layout items inside recycler view
        rvFriends.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        //adapter: dataset -> recycler view
        FriendAdapter friendAdapter = new FriendAdapter(friends);
        rvFriends.setAdapter(friendAdapter);

//       ImageButton addbtn = findViewById(R.id.addBtn);
//        addbtn.setOnClickListener(v -> {
//            //start activity
//            Intent intent = new Intent(MainActivity.this, AddFriendActivity.this);
//            startActivity(intent);
//        });
    }
}