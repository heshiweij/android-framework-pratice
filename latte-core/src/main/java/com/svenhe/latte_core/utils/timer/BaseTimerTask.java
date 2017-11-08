package com.svenhe.latte_core.utils.timer;

import java.util.TimerTask;

/**
 * @项目名: FestEC
 * @包名: com.svenhe.latte_core.utils.timer
 * @创建者: svenhe
 * @创建时间: 2017/11/5 16:07
 * @描述:
 * @更新人: $Author: svenhe $
 * @更新时间: $Date:2017/11/5 16:07 $
 * @更新描述: TODO
 */
public class BaseTimerTask extends TimerTask{

    private ITimerListener mITimerListener;

    public BaseTimerTask(ITimerListener listener) {
        this.mITimerListener = listener;
    }

    @Override
    public void run() {
        if (mITimerListener != null){
            mITimerListener.onTimer();
        }
    }
}
