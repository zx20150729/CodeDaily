package com.app.toast_test;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * 需要一个悬浮窗权限
 * Created by Administrator on 2016/11/11.
 */
public class Toast {
    private Context mContext;
    private WindowManager wm;
    private int mDuration;
    private View mNextView;
    public static final int LENGTH_SHORT = 1500;
    public static final int LENGTH_LONG = 3000;

    public Toast(Context mContext) {
        this.mContext = mContext;
        wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
    }

    public static Toast makeText(Context context, CharSequence text, int duration) {
        Toast toast = new Toast(context);
        View view = android.widget.Toast.makeText(context, text, duration).getView();
        if (view != null) {
            TextView tv = (TextView) view.findViewById(android.R.id.message);
            tv.setText(text);
        }
        toast.mNextView = view;
        toast.mDuration = duration;
        return toast;
    }

    public static Toast makeText(Context context, int resId, int duration)
            throws Resources.NotFoundException {
        return makeText(context, context.getResources().getString(resId), duration);
    }

    public void show() {
        if (mNextView != null) {
            WindowManager.LayoutParams params = new WindowManager.LayoutParams();
            params.width = WindowManager.LayoutParams.WRAP_CONTENT;
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            params.gravity = Gravity.CENTER | Gravity.CENTER_HORIZONTAL;
            params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                    | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                    | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
            params.format = PixelFormat.TRANSPARENT;
            params.windowAnimations = android.R.style.Animation_Toast;
            params.y = dip2px(mContext, 64);
            params.type = WindowManager.LayoutParams.TYPE_TOAST;
            wm.addView(mNextView, params);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (mNextView != null) {
                        wm.removeView(mNextView);
                        mNextView = null;
                        wm = null;
                    }
                }
            }, mDuration);
        }
    }

    private int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
