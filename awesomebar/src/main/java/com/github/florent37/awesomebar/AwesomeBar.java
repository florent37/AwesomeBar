package com.github.florent37.awesomebar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.ActionMenuView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by florentchampigny on 29/01/2017.
 */

public class AwesomeBar extends FrameLayout {

    public static final int DELAYT_BEFORE_FIRST_MENU_ANIMATION = 1000;
    public static final int DELAY_BETWEEN_MENU_ANIMATION_AND_CLICK = 800;
    public static final int MESSAGE_ANIMATION_START = 1;
    public static final float RATIO_RADIUS_MIN_MAX = (3f / 5f);
    private final int radius;
    private OnClickListener onMenuClickListener;
    private ActionItemClickListener actionItemClickListener;
    private OverflowActionItemClickListener overflowActionItemClickListener;
    private Paint paintDark;
    private Paint paintMain;
    private Settings settings;

    private ImageView iconMenu;
    private ImageView iconBack;
    private ImageView iconApp;
    private ImageView iconAppBackground;
    private ViewGroup actionsLayout;

    private ActionMenuView actionMenuView;

    private Handler menuAnimationHandler = new MenuAnimationHandler();

    public AwesomeBar(Context context) {
        this(context, null);
    }

    public AwesomeBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AwesomeBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        radius = getResources().getDimensionPixelOffset(R.dimen.bar_radius);

        setMinimumHeight(getResources().getDimensionPixelOffset(R.dimen.bar_min_height));

        settings = new Settings();
        settings.init(context, attrs);


        setWillNotDraw(false);
        paintDark = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintDark.setColor(settings.getColorDark());
        paintDark.setStyle(Paint.Style.FILL_AND_STROKE);

        paintMain = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintMain.setColor(settings.getColorMain());
        paintMain.setStyle(Paint.Style.FILL_AND_STROKE);

        addView(LayoutInflater.from(context).inflate(R.layout.bar_layout, this, false));

        iconMenu = (ImageView) findViewById(R.id.bar_menu_icon);
        iconBack = (ImageView) findViewById(R.id.bar_back_icon);
        iconApp = (ImageView) findViewById(R.id.bar_app_icon);
        iconAppBackground = (ImageView) findViewById(R.id.bar_app_icon_background);
        actionsLayout = (ViewGroup) findViewById(R.id.bar_actions_layout);
        actionMenuView = (ActionMenuView) findViewById(R.id.bar_actions_menu_view);

        iconMenu.setImageDrawable(AnimatedVectorDrawableCompat.create(getContext(), R.drawable.awsb_ic_menu_animated));

        iconApp.setImageDrawable(getAppIcon(context));
        iconAppBackground.setImageDrawable(getAppIcon(context));
        iconAppBackground.setColorFilter(Color.WHITE);

        if (settings.isAnimateMenu()) {
            menuAnimationHandler.sendEmptyMessageDelayed(MESSAGE_ANIMATION_START, DELAYT_BEFORE_FIRST_MENU_ANIMATION);
        }
        iconMenu.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (settings.isAnimateMenu()) {
                    animateMenuImage();
                    animateDarkView();
                    iconMenu.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (onMenuClickListener != null) {
                                onMenuClickListener.onClick(iconMenu);
                            }
                        }
                    }, DELAY_BETWEEN_MENU_ANIMATION_AND_CLICK);
                } else {
                    if (onMenuClickListener != null) {
                        onMenuClickListener.onClick(iconMenu);
                    }
                }
            }
        });

        DrawableCompat.setTint(actionMenuView.getOverflowIcon(), settings.getColorMain());
        actionMenuView.setVisibility(GONE);

        actionMenuView.setOnMenuItemClickListener(new ActionMenuView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(overflowActionItemClickListener != null){
                    overflowActionItemClickListener.onOverflowActionItemClicked(item.getOrder(), item.getTitle().toString());
                }
                return true;
            }
        });

        iconBack.setVisibility(GONE);
        iconBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onMenuClickListener != null) {
                    onMenuClickListener.onClick(iconBack);
                }
            }
        });
    }

    private void animateDarkView(){
        final int minAlpha = 100;
        final int duration = 200;
        final int delay = 350;

        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, radius  * RATIO_RADIUS_MIN_MAX);
        valueAnimator.setInterpolator(new AccelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                animRadius = (float) animation.getAnimatedValue();
                paintDark.setAlpha(255 - (int) (minAlpha*animation.getAnimatedFraction()));
                postInvalidate();
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        final ValueAnimator valueAnimator2 = ValueAnimator.ofFloat(radius  * RATIO_RADIUS_MIN_MAX, 0);
                        valueAnimator2.setInterpolator(new AccelerateInterpolator());
                        valueAnimator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                animRadius = (float) animation.getAnimatedValue();
                                paintDark.setAlpha((255 - minAlpha) + (int) (minAlpha*animation.getAnimatedFraction()));
                                postInvalidate();
                            }
                        });
                        valueAnimator2.setDuration(duration);
                        valueAnimator2.start();
                    }
                }, delay);
            }
        });
        valueAnimator.setDuration(duration);
        valueAnimator.start();
    }

    private void animateMenuImage() {
        final Drawable drawable = iconMenu.getDrawable();
        if (drawable instanceof AnimatedVectorDrawableCompat) {
            ((AnimatedVectorDrawableCompat) iconMenu.getDrawable()).start();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        menuAnimationHandler.removeMessages(MESSAGE_ANIMATION_START);
    }

    private float animRadius = 0;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        final int cY = getHeight() / 2;

        canvas.drawCircle(0, cY, radius, paintMain);
        canvas.drawCircle(-radius * RATIO_RADIUS_MIN_MAX + animRadius, cY, radius, paintDark);
    }

    public Drawable getAppIcon(Context context) {
        return context.getPackageManager().getApplicationIcon(context.getApplicationInfo());
    }

    public void addAction(@Nullable @DrawableRes Integer drawable, String actionName) {
        final ActionItem actionItem = new ActionItem(getContext());
        actionItem.setText(actionName);
        actionItem.setDrawable(drawable);
        actionItem.setAnimateBeforeClick(settings.isAnimateMenu());
        actionItem.setBackgroundColor(settings.getActionsColor());
        this.actionsLayout.addView(actionItem);

        actionItem.setClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (actionItemClickListener != null) {
                    actionItemClickListener.onActionItemClicked(actionsLayout.indexOfChild(actionItem), actionItem);
                }
            }
        });

        final LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) actionItem.getLayoutParams();
        layoutParams.leftMargin = getResources().getDimensionPixelOffset(R.dimen.bar_actions_margin_left);
        layoutParams.rightMargin = getResources().getDimensionPixelOffset(R.dimen.bar_actions_margin_right);
        actionItem.setLayoutParams(layoutParams);
    }

    public void clearActions() {
        this.actionsLayout.removeAllViews();
    }

    public void setOnMenuClickedListener(OnClickListener onMenuClickListener) {
        this.onMenuClickListener = onMenuClickListener;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setActionItemClickListener(ActionItemClickListener actionItemClickListener) {
        this.actionItemClickListener = actionItemClickListener;
    }

    public void addOverflowItem(String item){
        actionMenuView.getMenu().add(item);

        actionMenuView.setVisibility(VISIBLE);
    }

    public void setOverflowActionItemClickListener(OverflowActionItemClickListener overflowActionItemClickListener) {
        this.overflowActionItemClickListener = overflowActionItemClickListener;
    }

    public void displayHomeAsUpEnabled(boolean enabled) {
        if(enabled) {
            this.iconBack.setVisibility(VISIBLE);
            this.iconMenu.setVisibility(GONE);
        } else {
            this.iconBack.setVisibility(GONE);
            this.iconMenu.setVisibility(VISIBLE);
        }
    }

    public interface ActionItemClickListener {
        void onActionItemClicked(int position, ActionItem actionItem);
    }

    public interface OverflowActionItemClickListener {
        void onOverflowActionItemClicked(int position, String item);
    }

    class MenuAnimationHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MESSAGE_ANIMATION_START:
                    final Drawable drawable = iconMenu.getDrawable();
                    if (drawable instanceof AnimatedVectorDrawableCompat) {
                        ((AnimatedVectorDrawableCompat) iconMenu.getDrawable()).start();
                        sendEmptyMessageDelayed(MESSAGE_ANIMATION_START, 5000);
                    }
                    break;
            }
        }
    }
}
