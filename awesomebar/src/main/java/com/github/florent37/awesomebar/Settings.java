package com.github.florent37.awesomebar;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;

/**
 * Created by florentchampigny on 30/01/2017.
 */

public class Settings {

    private int colorDark;
    private int colorMain;
    private int actionsColor;
    private boolean animateMenu;

    public void init(Context context, AttributeSet attrs){
        colorDark = fetchPrimaryDarkColor(context);
        colorMain = fetchPrimaryColor(context);

        if(attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AwesomeBar);

            colorMain = a.getColor(R.styleable.AwesomeBar_bar_primaryColor, colorMain);
            colorDark = a.getColor(R.styleable.AwesomeBar_bar_primaryDarkColor, colorDark);
            animateMenu = a.getBoolean(R.styleable.AwesomeBar_bar_animatedIcons, true);
            actionsColor = a.getColor(R.styleable.AwesomeBar_bar_actionsColor, colorMain);

            a.recycle();
        }
    }

    private int fetchPrimaryColor(Context context) {
        final TypedValue typedValue = new TypedValue();

        final TypedArray a = context.obtainStyledAttributes(typedValue.data, new int[] { R.attr.colorPrimary });
        int color = a.getColor(0, 0);

        a.recycle();

        return color;
    }

    private int fetchPrimaryDarkColor(Context context) {
        final TypedValue typedValue = new TypedValue();

        final TypedArray a = context.obtainStyledAttributes(typedValue.data, new int[] { R.attr.colorPrimaryDark });
        int color = a.getColor(0, 0);

        a.recycle();

        return color;
    }

    public void setColorDark(int colorDark) {
        this.colorDark = colorDark;
    }

    public void setColorMain(int colorMain) {
        this.colorMain = colorMain;
    }

    public int getColorDark() {
        return colorDark;
    }

    public int getColorMain() {
        return colorMain;
    }

    public boolean isAnimateMenu() {
        return animateMenu;
    }

    public void setAnimateMenu(boolean animateMenu) {
        this.animateMenu = animateMenu;
    }

    public int getActionsColor() {
        return actionsColor;
    }

    public void setActionsColor(int actionsColor) {
        this.actionsColor = actionsColor;
    }

    public float dpToPx(Context context, float dp){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }
}
