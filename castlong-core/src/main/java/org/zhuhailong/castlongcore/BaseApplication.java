package org.zhuhailong.castlongcore;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;


/**
 * @author hailong
 * @date 2020年04月22日 11:27 AM
 * <p>
 * You never know what you can do until you try !
 * ----------------------------------------------------------------
 */
public class BaseApplication extends Application {

    public static BaseApplication sInstance;
    public static boolean isDebug = true;
    public static List<Activity> mActivityList = new ArrayList<>();


    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

    }

    /**
     * 获取进程名称
     *
     * @param context application
     * @return 进程名称
     */
    protected static String getProcessName(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo proInfo : runningApps) {
            if (proInfo.pid == android.os.Process.myPid()) {
                if (proInfo.processName != null) {
                    return proInfo.processName;
                }
            }
        }
        return null;
    }

    /**
     * 生成Activity存入列表
     *
     * @param activity
     */
    public static void addCurrentActivity(Activity activity) {
        if (activity != null && mActivityList != null)
            mActivityList.add(activity);
    }

    /**
     * 从列表移除activity
     *
     * @param activity
     */
    public static void removeCurrentActivity(Activity activity) {
        if (activity != null && mActivityList != null)
            mActivityList.remove(activity);
    }

    /**
     * @param activityName 包名+类名
     */
    public static void finishActivity(String activityName) {
        Activity activity = null;
        if (activityName != null && activityName.length() > 0 && mActivityList != null) {
            for (Activity item : mActivityList) {
                if (item.getClass().getName().equals(activityName)) {
                    activity = item;
                    break;
                }
            }
        }
        if (activity != null && !activity.isFinishing()) {
            mActivityList.remove(activity);
            activity.finish();
        }
    }

    /**
     * 清除所有的activity
     */
    public static void removeAllActivity() {
        for (int i = 0; i < mActivityList.size(); i++) {
            Activity activity = mActivityList.get(i);
            if (activity != null)
                activity.finish();
        }
        mActivityList.clear();
    }


    /**
     * 结束应用
     */
    public static void finish() {
        removeAllActivity();
    }
}
