package com.svenhe.latte_core.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.svenhe.latte_core.R;
import com.svenhe.latte_core.delegates.LatteDelegate;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * @项目名: FestEC
 * @包名: com.svenhe.latte_core.activities
 * @创建者: svenhe
 * @创建时间: 2017/11/1 19:05
 * @描述:
 * @更新人: $Author: svenhe $
 * @更新时间: $Date:2017/11/1 19:05 $
 * @更新描述: TODO
 */
public abstract class ProxyActivity extends SupportActivity {

    public abstract LatteDelegate setRootDelegate();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initContainer(savedInstanceState);
    }

    public void initContainer(Bundle savedInstanceState){
        final ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.container_delegate);
        setContentView(container);

        // 第一次加载
        if (savedInstanceState == null){
            loadRootFragment(R.id.container_delegate, setRootDelegate());
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        System.gc();
        System.runFinalization();
    }
}
