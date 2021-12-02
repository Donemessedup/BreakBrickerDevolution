package com.example.breakbrickerdevolution;
import android.graphics.RectF;

public class Brick {

    private RectF rect;

    public Brick(int row, int column, int width, int height){

        int padding = 2;

        rect = new RectF(column * width + padding,
                row * height + padding,
                column * width + width - padding,
                row * height + height - padding);
    }

    public RectF getRect(){
        return this.rect;
    }
}
