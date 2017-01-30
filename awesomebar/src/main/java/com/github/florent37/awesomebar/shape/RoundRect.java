package com.github.florent37.awesomebar.shape;

/**
 * Created by florentchampigny on 29/01/2017.
 */


import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public class RoundRect extends Shape {

    public static final int BORDER_PADDING = 30;
    private int x;
    private int y;
    private int width;
    private int height;

    public RoundRect(int x, int y, int width, int height) {
        super();
        this.width = width;
        this.height = height;
    }

    private static void drawRoundedRect(Canvas canvas, float left, float top, float right, float bottom, Paint paint) {
        float radius = (bottom - top) / 2;

        RectF rectF = new RectF(left, top, right, bottom);
        canvas.drawRoundRect(rectF, radius, radius, paint);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public void drawOn(Canvas canvas) {
        if (isDisplayBorder()) {
            drawRoundedRect(canvas, getX() - BORDER_PADDING, getY() - BORDER_PADDING, getX() + getWidth() + BORDER_PADDING, getY() + getHeight() + BORDER_PADDING, getBorderPaint());
        }
        drawRoundedRect(canvas, getX(), getY(), getX() + getWidth(), getY() + getHeight(), paint);
    }
}