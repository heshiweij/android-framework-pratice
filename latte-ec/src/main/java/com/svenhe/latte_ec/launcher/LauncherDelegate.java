package com.svenhe.latte_ec.launcher;

import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Toast;

import com.svenhe.latte_core.app.Latte;
import com.svenhe.latte_core.delegates.LatteDelegate;
import com.svenhe.latte_core.utils.storage.LattePreference;
import com.svenhe.latte_core.utils.timer.BaseTimerTask;
import com.svenhe.latte_core.utils.timer.ITimerListener;
import com.svenhe.latte_ec.R;
import com.svenhe.latte_ec.R2;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @项目名: FestEC
 * @包名: com.svenhe.latte_ec.launcher
 * @创建者: svenhe
 * @创建时间: 2017/11/5 16:16
 * @描述:
 * @更新人: $Author: svenhe $
 * @更新时间: $Date:2017/11/5 16:16 $
 * @更新描述: TODO
 */
public class LauncherDelegate extends LatteDelegate implements ITimerListener {

    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvLauncherTimer;

    private Timer mTimer = null;
    private static final int PERIOD_TIME_MILLISECONDS = 1000;

    private int mTimerCount = 3;

    @OnClick(R2.id.tv_launcher_timer)
    void clickTimer(){
        cancelAndEnter();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {
        mTvLauncherTimer.setText("1");

        initializeTimer();
    }

    private void initializeTimer() {
        mTimer = new Timer();

        final BaseTimerTask task = new BaseTimerTask(this);

        mTimer.schedule(task, 0, PERIOD_TIME_MILLISECONDS);
    }

    /**
     * 当 Timer 执行时调用
     */
    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // 更新倒计时
                updateTimerCount();
            }
        });
    }

    /**
     * 更新倒计时
     */
    private void updateTimerCount(){
        if (mTimerCount >= 0){
            if (mTvLauncherTimer != null){
                mTvLauncherTimer.setText(MessageFormat.format("跳过\n{0}s", mTimerCount + ""));
            }
        } else {
            cancelAndEnter();
        }

        mTimerCount --;
    }

    private void cancelAndEnter(){
        mTimerCount = 3;
        // 进入下一个界面

        if (mTimer != null){
            mTimer.cancel();
            mTimer = null;

        }

        boolean isFirst = checkIsScroller();
        if (isFirst){
            start(new LauncherScrollerDelegate(), SINGLETASK);
        } else {
            // 是否已经登陆

        }
    }

    private boolean checkIsScroller(){
        return !LattePreference.getAppFlag(ScrollerLauncherTag.NOT_FIRST_LAUNCHER_APP.name());
    }

}
