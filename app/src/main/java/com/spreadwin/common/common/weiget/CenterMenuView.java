package com.spreadwin.common.common.weiget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.spreadwin.common.R;


/**
 * Created by eric on 16/9/13.
 */
public class CenterMenuView extends LinearLayout {
    private Context mContext;
    private ImageView menuIcon;
    private TextView menuTitle;

    public CenterMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.center_menu, this);
        initView();

        setAttrs(context, attrs, 0);
    }

    public CenterMenuView(Context context, AttributeSet attrs, int defstyle) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.center_menu, this);
        initView();

        setAttrs(context, attrs, defstyle);
    }

    protected void initView() {
        menuIcon = (ImageView) findViewById(R.id.menu_icon);
        menuTitle = (TextView) findViewById(R.id.menu_title);
    }

    private void setAttrs(Context context, AttributeSet attrs, int defStyle) {
        if (attrs != null) {
            /**
             * 获得我们所定义的自定义样式属性
             */
            TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CenterMenuView, defStyle, 0);
            for (int i = 0; i < attrs.getAttributeCount(); i++)
            {
                int attr = array.getIndex(i);
                switch (attr)
                {
                    case R.styleable.CenterMenuView_menu_icon:
                        menuIcon.setImageResource(array.getResourceId(attr, R.mipmap.user));
                        break;
                    case R.styleable.CenterMenuView_menu_title:
                        // 默认颜色设置为黑色
                        menuTitle.setText(array.getString(attr));
                        break;
                }

            }
        }
    }
}
