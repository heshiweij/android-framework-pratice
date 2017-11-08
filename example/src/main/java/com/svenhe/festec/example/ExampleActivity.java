package com.svenhe.festec.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.svenhe.latte_core.activities.ProxyActivity;
import com.svenhe.latte_core.delegates.LatteDelegate;
import com.svenhe.latte_ec.launcher.LauncherDelegate;
import com.svenhe.latte_ec.launcher.LauncherScrollerDelegate;
import com.svenhe.latte_ec.sign.SignUpDelegate;

import butterknife.BindView;

/**
 * @项目名: FestEC
 * @包名: com.svenhe.festec.example
 * @创建者: svenhe
 * @创建时间: 2017/11/1 19:38
 * @描述:
 * @更新人: $Author: svenhe $
 * @更新时间: $Date:2017/11/1 19:38 $
 * @更新描述: TODO
 */
public class ExampleActivity extends ProxyActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 去除 ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    @Override
    public LatteDelegate setRootDelegate() {
//        return new LauncherScrollerDelegate();
//        return new LauncherDelegate();
        return new SignUpDelegate();
    }

}
