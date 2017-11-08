package com.svenhe.latte_core.delegates;

import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.svenhe.latte_core.activities.ProxyActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * @项目名: FestEC
 * @包名: com.svenhe.latte_core.delegates
 * @创建者: svenhe
 * @创建时间: 2017/11/1 19:09
 * @描述:
 * @更新人: $Author: svenhe $
 * @更新时间: $Date:2017/11/1 19:09 $
 * @更新描述: TODO
 */
public abstract class BaseDelegate extends SwipeBackFragment {

    /**
     * 从子类获得一个 view
     * @return id 或 view
     */
    public abstract Object setLayout();

    @SuppressWarnings("SpellCheckingInspection")
    private Unbinder mUnbinder = null;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = null;
        if (setLayout() instanceof Integer){
            rootView = inflater.inflate((Integer) setLayout(), container, false);
        } else if (setLayout() instanceof View){
            rootView = (View) setLayout();
        }

        if (rootView != null){
            mUnbinder = ButterKnife.bind(this, rootView);
            onBindView(savedInstanceState, rootView);
        }

        return rootView;
    }

    public ProxyActivity getProxyActivity(){
        return (ProxyActivity) _mActivity;
    }


    protected abstract void onBindView(Bundle savedInstanceState, View rootView);

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
        mUnbinder = null;
    }
}
