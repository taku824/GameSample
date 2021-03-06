package com.webserva.wings.android.gamesample;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Delayed;

public class MainActivity extends AppCompatActivity {

    private TextView scoreLabel;
    private TextView startLabel;
    private ImageView droid;
    private ImageView orange;
    private ImageView black;
    private ImageView pink;

    // size
    private int frameHeight;
    private int droidSize;
    private int screenWidth;
    private int screenHeight;

    // 位置
    private float droidY;
    private float orangeX;
    private float orangeY;
    private float pinkX;
    private float pinkY;
    private float blackX;
    private float blackY;

    
    // Handler & Timer
    private Handler handler = new Handler();
    private Timer timer = new Timer();

    // Status
    private boolean action_flg = false;
    private boolean start_flg = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Viewの生成
        scoreLabel = findViewById(R.id.scoreLabel);
        startLabel = findViewById(R.id.startLabel);
        droid = findViewById(R.id.droid);
        black = findViewById(R.id.black);
        pink = findViewById(R.id.pink);
        orange = findViewById(R.id.orange);

        WindowManager wm = getWindowManager();
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        screenWidth = size.x;
        screenHeight = size.y;

        // 初期位置・状態
        orange.setX(-800.0f);
        orange.setY(-800.0f);
        pink.setX(-800.0f);
        pink.setY(-800.0f);
        black.setX(-800.0f);
        black.setY(-800.0f);

    }


    // 移動
    public void changePos(){

        // orange
        orangeX -= 45;
        if(orangeX < 0){
            orangeX = screenWidth + 20;
            orangeY = (float)Math.floor(Math.random() * (frameHeight - orange.getHeight()));
        }
        orange.setX(orangeX);
        orange.setY(orangeY);

        // pink
        pinkX -= 12;
        if(pinkX < 0){
            pinkX = screenWidth + 20;
            pinkY = (float)Math.floor(Math.random() * (frameHeight - pink.getHeight()));
        }
        pink.setX(pinkX);
        pink.setY(pinkY);

        // black
        blackX -= 24;
        if(blackX < 0){
            blackX = screenWidth + 20;
            blackY = (float)Math.floor(Math.random() * (frameHeight - black.getHeight()));
        }
        black.setX(blackX);
        black.setY(blackY);




        // droid_kun
        if(action_flg){
            // Touching
            droidY -= 20;
        }else{
            // Releasing
            droidY += 20;
        }

        // 上下の壁を作る
        if(droidY < 0) droidY = 0;
        if(droidY > frameHeight - droidSize) droidY = frameHeight - droidSize;

        droid.setY(droidY);
    }


    // タッチしてるか否かで、移動方向を変更
    @Override
    public boolean onTouchEvent(MotionEvent event) {


        if(start_flg == false){
            start_flg = true;

            // 画面の大き取得
            FrameLayout frame = findViewById(R.id.frame);
            frameHeight = frame.getHeight();

            droidY = droid.getY();
            droidSize = droid.getHeight();

            // どこかへすっ飛ばす
            startLabel.setVisibility(View.GONE);

            // 20ms毎にchangePos()を実行 LOOPiNG...
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            changePos();
                        }
                    });
                }
            }, 0, 20);


        }else{
            if(event.getAction() == MotionEvent.ACTION_DOWN){
                action_flg = true;
            }else if (event.getAction() == MotionEvent.ACTION_UP){
                action_flg = false;

            }
        }
        return true;
    }






}

