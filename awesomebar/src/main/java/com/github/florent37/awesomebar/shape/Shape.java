package com.github.florent37.awesomebar.shape;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by florentchampigny on 29/01/2017.
 */

public abstract class Shape {
    private int color = Color.argb(0, 0, 0, 0);
    protected Paint paint;

    private int borderColor = Color.parseColor("#AA999999");
    private Paint borderPaint;

    private boolean displayBorder = false;

    public Shape() {
        this.paint = new Paint();
        this.paint.setColor(getColor());
        this.paint.setAntiAlias(true);
        this.paint.setStyle(Paint.Style.FILL_AND_STROKE);
        //this.paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP));

        this.borderPaint = new Paint();
        this.borderPaint.setAntiAlias(true);
        this.borderPaint.setColor(borderColor);
    }

    public void setColor(int color) {
        this.color = color;
        this.paint.setColor(this.color);
    }

    public int getColor() {
        return color;
    }

    public Paint getPaint() {
        return paint;
    }

    public int getBorderColor() {
        return borderColor;
    }

    public boolean isDisplayBorder() {
        return displayBorder;
    }

    public void setDisplayBorder(boolean displayBorder) {
        this.displayBorder = displayBorder;
    }

    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;
        this.paint.setColor(borderColor);
    }

    public Paint getBorderPaint() {
        return borderPaint;
    }

    public abstract void drawOn(Canvas canvas);
}