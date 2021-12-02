package com.example.breakbrickerdevolution;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.SurfaceView;

public class MainActivity extends AppCompatActivity {

    BreakBrickerView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new BreakBrickerView(this);
        setContentView(view);
    }

    class BreakBrickerView extends SurfaceView {

        public BreakBrickerView(Context context) {
            super(context);
            Brick[] bricks = new Brick[200];
            int numBricks = 0;
            int brickWidth = 3;
            int brickHeight = 3;

            for (int column = 0; column < 3; column++) {
                for (int row = 0; row < 3; row++) {
                    bricks[numBricks] = new Brick(row, column, brickWidth, brickHeight);
                    numBricks++;
                }
            }
        }
    }

}