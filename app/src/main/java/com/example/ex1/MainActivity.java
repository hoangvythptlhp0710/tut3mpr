package com.example.ex1;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private ImageButton starBtn;
    private ImageButton ballBtn;
    private int mRotationAngle = 0;
    private int moveByY = 0;
    private int rotateByY = 0;

    private ImageView starImg;
    private ImageView ballImg;

    private ImageView currentImg;

    Button spinButton;
    Button jumpBtn;
    Button flipBtn;
    Button clapHand;
    MediaPlayer clap;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        starBtn = findViewById(R.id.btnStar);
        ballBtn = findViewById(R.id.btnBall);
        
        starImg = findViewById(R.id.star);
        ballImg = findViewById(R.id.ball);
        
        currentImg = starImg;

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

        spinButton = findViewById(R.id.spin);
        spinButton.setOnClickListener(v -> {
            mRotationAngle += 360;
            rotateImage(mRotationAngle);
        });

        jumpBtn = findViewById(R.id.jump);
        jumpBtn.setOnClickListener(v -> {
            moveByY = -300;
            jumpImg(moveByY);
        });

        flipBtn = findViewById(R.id.flipByY);
        flipBtn.setOnClickListener(v -> {
            rotateByY += 360;
            flipImg(rotateByY);
        });

        clapHand = findViewById(R.id.clap);
        clap = MediaPlayer.create(MainActivity.this, R.raw.clapping);
        clapHand.setOnClickListener(v -> {
            clap.setOnCompletionListener(mp -> {
                clap.release();
            });
            clap.start();
            clap.setLooping(true);
        });

        starBtn.setOnClickListener(v -> {
            starImg.animate().alpha(1).setDuration(2000);
            ballImg.animate().alpha(0).setDuration(2000);
            currentImg = starImg;
        });

        ballBtn.setOnClickListener(v -> {
            starImg.animate().alpha(0).setDuration(2000);
            ballImg.animate().alpha(1).setDuration(2000);
            currentImg = ballImg;
        });


    }

    private void rotateImage(int mRotationAngle) {
        currentImg.animate().rotation(mRotationAngle).setDuration(1000);
    }

    private void jumpImg(int moveByY) {
        currentImg.animate().translationYBy(moveByY).setDuration(1000).withEndAction(() -> {
            currentImg.animate().translationY(0).setDuration(1000);
        });
    }

    private void flipImg(int rotateByY) {
        currentImg.animate().rotationY(rotateByY).setDuration(1000);
    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("rotation_angle", mRotationAngle);
        outState.putInt("jump_height", moveByY);
        outState.putInt("flip_img", rotateByY);
    }


}