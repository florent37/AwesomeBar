package com.github.florent37.awesomebar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.florent37.awesomebar.shape.RoundRect;

/**
 * Created by florentchampigny on 30/01/2017.
 */

public class ActionItem extends LinearLayout {

    private OnClickListener onClickListener;

    private RoundRect roundRect;
    private int backgroundColor = Color.RED;
    private boolean animateBeforeClick = true;

    private final ImageView icon;
    private final TextView text;

    public ActionItem(Context context) {
        this(context, null);
    }

    public ActionItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ActionItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        inflate(context, R.layout.bar_action_item, this);
        setWillNotDraw(false);

        icon = (ImageView) findViewById(R.id.action_icon);
        text = (TextView) findViewById(R.id.action_text);

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                if(animateBeforeClick) {
                    boolean animated = tryToAnimate();
                    if(animated) {
                        postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (onClickListener != null) {
                                    onClickListener.onClick(v);
                                }
                            }
                        }, 300);
                    } else {
                        if (onClickListener != null) {
                            onClickListener.onClick(v);
                        }
                    }
                } else {
                    if (onClickListener != null) {
                        onClickListener.onClick(v);
                    }
                }
            }
        });
    }

    private boolean tryToAnimate(){
            final Drawable drawable = icon.getDrawable();
            if (drawable instanceof AnimatedVectorDrawable) {
                final AnimatedVectorDrawable d = (AnimatedVectorDrawable) drawable;
                d.start();
                return true;
            } else if (drawable instanceof AnimatedVectorDrawableCompat) {
                final AnimatedVectorDrawableCompat d = (AnimatedVectorDrawableCompat) drawable;
                d.start();
                return true;
            }
            return false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(roundRect == null) {
            roundRect = new RoundRect(0, 0, getWidth(), getHeight());
            roundRect.setColor(backgroundColor);
        }

        roundRect.drawOn(canvas);

        super.onDraw(canvas);
    }

    @Override
    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
        if(roundRect != null){
            roundRect.setColor(backgroundColor);
        }
    }

    public void setText(String actionName) {
        text.setText(actionName);
        postInvalidate();
    }

    public String getText() {
        return text.getText().toString();
    }

    public void setDrawable(@Nullable @DrawableRes Integer drawable) {
        if(drawable == null){
            icon.setVisibility(GONE);
        } else {
            icon.setImageResource(drawable);
            icon.setVisibility(VISIBLE);
        }
    }

    public void setAnimateBeforeClick(boolean animateBeforeClick) {
        this.animateBeforeClick = animateBeforeClick;
    }

    public void setClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
