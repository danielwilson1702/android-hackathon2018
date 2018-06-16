package com.sp.loylap.activities

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AppCompatDelegate
import android.view.View
import android.widget.TextView
import com.amplify.R
import com.amplify.connection.UIForLongRunningTask
import net.frakbot.jumpingbeans.JumpingBeans

abstract class BaseActivity : AppCompatActivity(), UIForLongRunningTask {
    internal var mContext: Context = this
    private var mJumpingBeans: JumpingBeans? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun hideUIForLongRunningTask() {
        showUIForLongRunningTask(false, 0)
    }

    override fun hideUIForLongRunningTask(layoutToShow: Int) {
        showUIForLongRunningTask(false, layoutToShow, 0)
    }

    override fun showUIForLongRunningTask(show: Boolean, layoutToHide: Int, text: Int) {
        val containerView = findViewById<View>(layoutToHide)
        val progressView = findViewById<View>(R.id.progress_include)
        val textView = findViewById<TextView>(R.id.progress_text)

        if (containerView != null && progressView != null) {
            val shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime)
            containerView.visibility = if (show) View.GONE else View.VISIBLE
            containerView.animate().setDuration(shortAnimTime.toLong()).alpha(
                    (if (show) 0 else 1).toFloat()).setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    containerView.visibility = if (show) View.GONE else View.VISIBLE
                }
            })

            progressView.visibility = if (show) View.VISIBLE else View.GONE
            progressView.animate().setDuration(shortAnimTime.toLong()).alpha(
                    (if (show) 1 else 0).toFloat()).setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    progressView.visibility = if (show) View.VISIBLE else View.GONE
                }
            })

            if (textView != null) {
                if (show) {
                    textView.setText(text)
                    mJumpingBeans = JumpingBeans.with(textView)
                            .appendJumpingDots()
                            .build()
                } else {
                    if (mJumpingBeans != null)
                        mJumpingBeans!!.stopJumping()
                }
            }
        }
    }

    override fun showUIForLongRunningTask(show: Boolean, text: Int) {
        showUIForLongRunningTask(show, R.id.container, text)
    }

    override fun updateUIForLongRunningTask(text: Int) {
        val textView = findViewById<TextView>(R.id.progress_text)
        if (textView != null) {

            val fadeOut = ObjectAnimator.ofFloat<View>(textView, View.ALPHA, 0F)
            fadeOut.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
                    if (mJumpingBeans != null)
                        mJumpingBeans!!.stopJumping()

                    textView.setText(text)
                    mJumpingBeans = JumpingBeans.with(textView)
                            .appendJumpingDots()
                            .build()
                }
            })
            val fadeIn = ObjectAnimator.ofFloat<View>(textView, View.ALPHA, 1F)

            val set = AnimatorSet()
            set.playSequentially(fadeOut, fadeIn)
            set.start()
        }
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        hideUIForLongRunningTask()
        super.onActivityResult(requestCode, resultCode, data)
    }

    companion object {

        private val TAG = "LoyLapActivity"

        init {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        }
    }
}
