package com.spreadwin.common.common.view.backactivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


import com.spreadwin.common.R;
import com.spreadwin.common.common.utils.ScreenUtils;
import com.spreadwin.common.ui.activity.BaseActivity;


public abstract class BackBaseActivity extends BaseActivity {

    public FrameLayout main;
    private View content;
    public Toolbar bar;
    private int statusBarHeight = 0;
    private TextView centerText;
    public View barDivider;
    public TextView leftText;
    public TextView rightText;
    public ImageView rightImg;
    private onTextClick listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        main = (FrameLayout) findViewById(R.id.main);
        centerText = (TextView) findViewById(R.id.centerText);

        leftText = (TextView) findViewById(R.id.leftText);
        rightText = (TextView) findViewById(R.id.rightText);
        rightImg = (ImageView) findViewById(R.id.rightImg);

        bar = (Toolbar) findViewById(R.id.toolbar);
        barDivider = findViewById(R.id.toolbar_divider);
        if (setContentView() != 0) {
            content = getLayoutInflater()
                    .inflate(setContentView(), null, false);
        }
        View contentView = getContentView();
        if (contentView != null) {
            content = contentView;
        }
        if (content != null) {
            main.addView(content, 0, new LayoutParams(
                    LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        }

        bar.setTitle("");
        bar.setTitleTextAppearance(this, R.style.ToolBarTitle);
        bar.setBackgroundResource(R.color.colorPrimary);
        resetToolBar();
        setToolBar(bar);
        setSupportActionBar(bar);
        registerButtonListener();
        init();

    }

    public void registerButtonListener() {
        bar.setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onNavigationClick();
            }
        });
        centerText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onCenterTextClick();
            }
        });
        leftText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onLeftClick();
            }
        });
        rightText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRightClick();
            }
        });
        rightImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRightClick();
            }
        });

    }

    public void addView(View child, int index) {
        main.addView(child, index);
    }

    // public void setStatusBarColor(int color) {
    // if (manager != null) {
    // manager.setStatusBarTintColor(color);
    // }
    // }
    //
    // public void setStatusBarAlpha(float alpha) {
    // if (manager != null) {
    // manager.setStatusBarAlpha(alpha);
    // }
    // }

    public void setBackgroundAlpha(float alpha) {
        Drawable drawable = bar.getBackground();
        Drawable drawable2 = barDivider.getBackground();
        drawable.setAlpha((int) alpha);
        drawable2.setAlpha((int) alpha);
    }

    public void setLayoutMargin(int barMargin, int contentMargin) {
        MarginLayoutParams params = (MarginLayoutParams) bar.getLayoutParams();
        params.topMargin = barMargin;
        bar.setLayoutParams(params);
        if (content != null) {
            MarginLayoutParams cparams = (MarginLayoutParams) content
                    .getLayoutParams();
            cparams.topMargin = contentMargin;
            content.setLayoutParams(cparams);
        }
    }

    public void setOnLeftClickListener(onTextClick listener) {
        this.listener = listener;
    }

    public void setOnRightClickListener(onTextClick listener) {
        this.listener = listener;
    }

    public void setLeftTextView(int resId) {
        setLeftTextView(getResources().getString(resId));
    }

    public void hideToolBar() {
        this.setFullscreen();
        bar.setVisibility(View.GONE);
    }

    public void showToolBar() {
        this.resetToolBar();
        bar.setVisibility(View.VISIBLE);
    }

    public void setLeftTextView(String text) {
        leftText.setVisibility(View.VISIBLE);
        leftText.setText(text);
    }

    public void setRightTextView(String text) {
        rightText.setVisibility(View.VISIBLE);
        rightImg.setVisibility(View.GONE);
        rightText.setText(text);
    }

    public void setRegihtImaegView(int path) {
        rightImg.setVisibility(View.VISIBLE);
        rightText.setVisibility(View.GONE);
        rightImg.setImageResource(path);
    }


    public void setFullscreen() {
        setLayoutMargin(0, 0);
        // setStatusBarColor(getResources().getColor(android.R.color.transparent));
        bar.setVisibility(View.GONE);
        barDivider.setVisibility(View.GONE);
    }

    public void setCenterTextView(int resId) {
        setCenterTextView(getResources().getString(resId));
    }

    public void setCenterTextView(String text) {
        centerText.setVisibility(View.VISIBLE);
        centerText.setText(text);
    }

    public void setCenterTextRightDrawble(int resId) {
        Drawable drawable = getResources().getDrawable(resId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                drawable.getMinimumHeight());
        centerText.setCompoundDrawables(null, null, drawable, null);
    }

    public void addCenterView(View view) {
        bar.removeAllViews();
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        bar.addView(view);
    }

    public void resetToolBar() {
        bar.setVisibility(View.VISIBLE);
        barDivider.setVisibility(View.VISIBLE);
        // setStatusBarColor(R.color.white_title);
        bar.setBackgroundResource(R.color.colorPrimary);
        setBackgroundAlpha(255);
        // setStatusBarAlpha(1f);
        setLayoutMargin(statusBarHeight,
                statusBarHeight + ScreenUtils.dip2px(this, 68));

        centerText.setVisibility(View.VISIBLE);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public TextView getCenterText() {
        return centerText;
    }

    public int getStatusBarHeight() {
        return statusBarHeight;
    }

    public Toolbar getToolBar() {
        return bar;
    }

    public void onNavigationClick() {
    }

    public void onRightButtonClick() {
    }

    public void onCenterTextClick() {
    }

    ;

    public void onSearchViewClick() {

    }

    public abstract void setToolBar(Toolbar bar);

    public abstract int setContentView();

    public abstract void init();

    public View getContentView() {
        return null;
    }

    public interface onTextClick {
        void onLeftClick();

        void onRightClick();
    }
}
