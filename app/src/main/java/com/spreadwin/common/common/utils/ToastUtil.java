package com.spreadwin.common.common.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.spreadwin.common.R;


public class ToastUtil {

    public static void show(Context context, int res) {
        show(context, context.getResources().getString(res));
    }

    public static void show(Context context, String text) {
        Toast toast = makeText(context.getApplicationContext(), text, Toast.LENGTH_SHORT);
        toast.show();
    }

    public static Toast makeText(Context context, CharSequence text, int duration) {
        Toast result = new Toast(context);
        LayoutInflater inflate = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflate.inflate(R.layout.toast, null);
        TextView tv = (TextView) v.findViewById(R.id.message);
        tv.setText(text);
        result.setView(v);
        result.setDuration(duration);
        result.setGravity(Gravity.CENTER, 0, 0);
        return result;
    }

    public static Toast makeText(Context context, int stringId, int duration) {
        return makeText(context, context.getString(stringId), duration);
    }

}
