package org.zhuhailong.castlongutil;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * @author hailong
 * @date 2020年08月29日 4:15 PM
 * <p>
 * You never know what you can do until you try !
 * ----------------------------------------------------------------
 */
public class CastLongDisplayUtils {


    public static int dp2px(Context context, float dipValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5F);
    }

    public static int px2dp(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5F);
    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

    public static int getRealWidth(Context context, float dipValue) {
        return getScreenWidth(context) - dp2px(context, dipValue) * 2;
    }

}
