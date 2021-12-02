package com.example.breakbrickerdevolution;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class MainActivity extends Activity {

    BrickBreakerView brickBreakerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        brickBreakerView = new BrickBreakerView(this);
        setContentView(brickBreakerView);

    }

    class BrickBreakerView extends SurfaceView implements Runnable {

        Thread gameThread = null;

        SurfaceHolder ourHolder;

        volatile boolean playing;

        Canvas canvas;
        Paint paint;


        int screenX;
        int screenY;

        Brick[] bricks = new Brick[30];
        int numBricks = 0;


        public BrickBreakerView(Context context) {

            super(context);

            ourHolder = getHolder();
            paint = new Paint();

            Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);

            screenX = size.x;
            screenY = size.y;
            createBricks();

        }

        public void createBricks() {
            int brickWidth = screenX / 8;
            int brickHeight = screenY / 10;

            numBricks = 0;
            for (int column = 0; column < 8; column++) {
                for (int row = 0; row < 3; row++) {
                    bricks[numBricks] = new Brick(row, column, brickWidth, brickHeight);
                    numBricks++;
                }
            }
        }

        @Override
        public void run() {
            while (playing) {
                drawBricks();
            }

        }


        public void drawBricks() {
            if (ourHolder.getSurface().isValid()) {
                canvas = ourHolder.lockCanvas();

                canvas.drawColor(Color.argb(255, 200, 25, 76));

                paint.setColor(Color.argb(255, 255, 255, 255));


                paint.setColor(Color.argb(255, 31, 129, 78));

                for (int i = 0; i < numBricks; i++) {
                    canvas.drawRect(bricks[i].getRect(), paint);
                }

                ourHolder.unlockCanvasAndPost(canvas);
            }
        }

        public void resume() {
            playing = true;
            gameThread = new Thread(this);
            gameThread.start();
        }


    }

    @Override
    protected void onResume() {
        super.onResume();

        brickBreakerView.resume();
    }



}
