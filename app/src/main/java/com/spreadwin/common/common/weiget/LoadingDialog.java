package com.spreadwin.common.common.weiget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.spreadwin.common.R;
import com.spreadwin.common.common.utils.DeviceUtils;


public class LoadingDialog implements DialogInterface.OnCancelListener {
    private Context context;
    private AlertDialog dialog;
    private View loadView;
    private TextView loadText;
    private String showText = "加载中...";

    private OnCancelListener cancelListener;

    public LoadingDialog(Context context) {
        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        loadView = inflater.inflate(R.layout.dialog_loading, null);
        loadText = (TextView) loadView.findViewById(R.id.lab_message);

        dialog = new AlertDialog.Builder(context).create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnCancelListener(this);
    }

    public void show() {
        if (DeviceUtils.isValidContext(context)) {
            try {
            dialog.show();
            }catch (Exception e){
                e.printStackTrace();
            }
            dialog.getWindow().setContentView(loadView);

        }
    }

    public void show(String text) {
        loadText.setText(text);
        show();
    }

    public void show(int resId) {
        show(context.getString(resId));
    }

    public void setText(String showText) {
        loadText.setText(showText);
    }

    public void setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        dialog.setCanceledOnTouchOutside(cancel);
    }

    public void dismiss() {
        if (DeviceUtils.isValidContext(context) && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public void setOnCancelListener(OnCancelListener cancelListener) {
        this.cancelListener = cancelListener;
    }

    public interface OnCancelListener {
        void onCancel(DialogInterface dialog);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        if (cancelListener != null) {
            cancelListener.onCancel(dialog);
        }
    }
}
