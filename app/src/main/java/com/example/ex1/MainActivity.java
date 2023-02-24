package com.example.ex1;

import android.annotation.SuppressLint;
import android.media.MediaParser;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private boolean showingCenterImg = true;
    private int mRotationAngle = 0;
    private int moveByY = 0;
    private int rotateByY = 0;

    private ImageView mImageView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView = findViewById(R.id.star);

        if (savedInstanceState != null) {
            mRotationAngle = savedInstanceState.getInt("rotation_angle");
            rotateImage(mRotationAngle);
        }

        if (savedInstanceState != null) {
            moveByY = savedInstanceState.getInt("jump_height");
            jumpImg(moveByY);
        }

        if (savedInstanceState != null) {
            rotateByY = savedInstanceState.getInt("flip_img");
            flipImg(rotateByY);
        }

        Button spinButton = findViewById(R.id.spin);
        spinButton.setOnClickListener(v -> {
            mRotationAngle += 360;
            rotateImage(mRotationAngle);
        });
        
        Button jumpBtn = findViewById(R.id.jump);
        jumpBtn.setOnClickListener(v -> {
            moveByY = -300;
            jumpImg(moveByY);
        });

        Button flipBtn = findViewById(R.id.flipByY);
        flipBtn.setOnClickListener(v -> {
            rotateByY += 360;
            flipImg(rotateByY);
        });
    }

    private void rotateImage(int mRotationAngle) {
        mImageView.animate().rotation(mRotationAngle).setDuration(1000);
    }

    private void jumpImg(int moveByY) {
        mImageView.animate().translationYBy(moveByY).setDuration(1000).withEndAction(() -> {
                 mImageView.animate().translationY(0).setDuration(1000);
        });
    }

    private void flipImg(int rotateByY) {
        mImageView.animate().rotationY(rotateByY).setDuration(1000);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("rotation_angle", mRotationAngle);
        outState.putInt("jump_height", moveByY);
        outState.putInt("flip_img", rotateByY);
    }


}