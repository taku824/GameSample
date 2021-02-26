package com.webserva.wings.android.gamesample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView scoreLabel;
    private TextView startLabel;
    private ImageView droid;
    private ImageView orange;
    private ImageView black;
    private ImageView pink;

    // 位置
    private float droidY;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreLabel = findViewById(R.id.scoreLabel);
        startLabel = findViewById(R.id.startLabel);
        droid = findViewById(R.id.droid);
        black = findViewById(R.id.black);
        pink = findViewById(R.id.pink);
        orange = findViewById(R.id.orange);

        orange.setX(-80.0f);
        orange.setY(-80.0f);
        pink.setX(-80.0f);
        pink.setY(-80.0f);
        black.setX(-80.0f);
        black.setY(-80.0f);

        startLabel.setVisibility(View.INVISIBLE);
        droidY = 1000.0f;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(event.getAction() == MotionEvent.ACTION_DOWN){
            droidY -= 20;
        }
        droid.setY(droidY);

        return true;
    }
}








