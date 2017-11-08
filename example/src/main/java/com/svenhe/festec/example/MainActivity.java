package com.svenhe.festec.example;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.svenhe.latte_core.app.Latte;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @项目名: FestEC
 * @包名: com.svenhe.festec.example
 * @创建者: svenhe
 * @创建时间: 2017/11/6 11:30
 * @描述:
 * @更新人: $Author: svenhe $
 * @更新时间: $Date:2017/11/6 11:30 $
 * @更新描述: TODO
 */
public class MainActivity extends AppCompatActivity {

    private Timer timer;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    doSome();
                    break;
            }

        }
    };

    private void doSome(){
        showToast("haha");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

    }

    @OnClick(R.id.tv_main)
    public void start(){
        timer = new Timer();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Message message = handler.obtainMessage();
                message.what = 1;
                message.sendToTarget();
            }
        };

        timer.schedule(timerTask, 3000);
    }

    @OnClick(R.id.bbt_cancel)
    public void cancel(){
        if (timer != null){
            timer.cancel();
        }
    }

    private void showToast(String msg){
        Toast.makeText(Latte.getApplicationContext(),new String(msg), Toast.LENGTH_SHORT).show();
    }
}
