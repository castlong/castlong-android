package org.zhuhailong.castlongcore;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Size;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


/**
 * @author hailong
 * @date 2020年06月15日 7:38 PM
 * <p>
 * You never know what you can do until you try !
 * ----------------------------------------------------------------
 */
public abstract class BaseActivity extends AppCompatActivity {

    public String TAG;

    protected Toast mToast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.TAG = this.getClass().getName();
        Log.e("hailong", "onCreate->" + TAG + "  " + (savedInstanceState == null));

        /**
         *  supper 之前的预处理配置操作 如果savedInstanceState
         */
        final Bundle savedInstanceStateOrExtras = savedInstanceState == null ? getIntent().getExtras() : savedInstanceState;
        beforeSuperOnCreate(savedInstanceStateOrExtras);
        super.onCreate(savedInstanceState);
        onSetContentView(savedInstanceState);
        onInitContentView();
        onAfterInitContentView(savedInstanceState);
        BaseApplication.sInstance.addCurrentActivity(this);
    }


    /**
     * 在当前{@link #onCreate(Bundle)}调用{@link super#onCreate(Bundle)}之前调用执行一些其他操作
     *
     * @param savedInstanceStateOrExtras savedInstanceStateOrExtras
     */
    protected void beforeSuperOnCreate(@Nullable Bundle savedInstanceStateOrExtras) {
        initToast();
    }

    protected abstract void onSetContentView(@Nullable Bundle savedInstanceState);

    /**
     * initContentView
     */
    protected abstract void onInitContentView();

    protected void onAfterInitContentView(@Nullable Bundle savedInstanceState) {

    }


    /**
     * Check if the calling context has a set of permissions.
     *
     * @param perms one ore more permissions, such as {@link Manifest.permission#CAMERA}.
     * @return true if all permissions are already granted, false if at least one permission is not
     * yet granted.
     * @see Manifest.permission
     */
    public boolean hasPermissions(@Size(min = 1) @NonNull String... perms) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            Log.e("hailong", "hasPermissions: API version < M, returning true by default");
            // DANGER ZONE!!! Changing this will break the library.
            return true;
        }

        for (String perm : perms) {
            if (ContextCompat.checkSelfPermission(this, perm)
                    != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }

        return true;
    }


    @SuppressLint("ShowToast")
    protected void initToast() {
        mToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        mToast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 100);
    }


    /**
     * 吐丝提示信息
     *
     * @param msg
     */
    protected void showToastMessage(CharSequence msg) {
        mToast.setText(msg);
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.show();
    }

    /**
     * 吐丝提示信息
     *
     * @param msgId
     */
    protected void showToastMessage(@StringRes int msgId) {
        mToast.setText(getString(msgId));
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.show();
    }


    @Override
    protected void onStart() {
        Log.e("hailong", "onStart->" + TAG);
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.e("hailong", "onResume->" + TAG);
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.e("hailong", "onPause->" + TAG);
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.e("hailong", "onStop->" + TAG);
        super.onStop();
    }


    @Override
    protected void onDestroy() {
        Log.e("hailong", "onDestroy->" + TAG);
        super.onDestroy();
        BaseApplication.sInstance.removeCurrentActivity(this);
    }


}
