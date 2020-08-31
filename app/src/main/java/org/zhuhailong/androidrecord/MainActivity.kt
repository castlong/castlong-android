package org.zhuhailong.androidrecord

import android.os.Bundle
import org.zhuhailong.androidrecord.databinding.ActivityMainBinding
import org.zhuhailong.castlongcore.BaseViewBindingActivity
import org.zhuhailong.castlongdialog.dialog.CastLongAlertDialogFragment
import org.zhuhailong.castlongdialog.dialog.CastLongConfirmDialogFragment
import org.zhuhailong.castlongdialog.dialog.CastLongLoadingDF
import org.zhuhailong.castlongdialog.dialog.CastLongPickTakeDialogFragment

class MainActivity : BaseViewBindingActivity<ActivityMainBinding>() {


    override fun onSetContentView(savedInstanceState: Bundle?) {
        setContentView(ActivityMainBinding.inflate(layoutInflater).also { mBinding = it }.root)
    }

    override fun onInitContentView() {
        mBinding.alert.setOnClickListener {
            CastLongAlertDialogFragment(false)
                .apply {
                    mTitle = "我是标题"
                    mMessage = "我是消息体"
                    mActionName = "确定"
                    mAction = Runnable {
                        showToastMessage("asdfasdfasdf")
                    }
                }.show(supportFragmentManager,
                    CastLongAlertDialogFragment::class.java.name
                )
        }

        mBinding.confirm.setOnClickListener {
            CastLongConfirmDialogFragment(false)
                .apply {
                    mTitle = "我是标题"
                    mMessage = "我是消息体"
                    mLeftActionName = "取消"
                    mRightActionName = "确定"
                }
                .show(supportFragmentManager,
                    CastLongConfirmDialogFragment::class.java.name
                )
        }

        mBinding.pickTake.setOnClickListener {
            CastLongPickTakeDialogFragment(false).show(supportFragmentManager,
                CastLongPickTakeDialogFragment::class.java.name
            )
        }

        mBinding.loading.setOnClickListener {
            CastLongLoadingDF().show(supportFragmentManager,
                CastLongLoadingDF::class.java.name)
        }
    }


}