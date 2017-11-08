package com.svenhe.latte_core.ui.loader;

import android.content.Context;
import android.net.LinkAddress;
import android.text.TextUtils;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;
import com.wang.avi.indicators.BallBeatIndicator;

import java.util.WeakHashMap;

/**
 * @项目名: FestEC
 * @包名: com.svenhe.latte_core.ui
 * @创建者: svenhe
 * @创建时间: 2017/11/4 14:04
 * @描述:
 * @更新人: $Author: svenhe $
 * @更新时间: $Date:2017/11/4 14:04 $
 * @更新描述: TODO
 */
public class LoaderCreator {

    private static WeakHashMap<String, Indicator> LOADING_MAP = new WeakHashMap<>();

    public static AVLoadingIndicatorView create(String type, Context context) {

        AVLoadingIndicatorView view = new AVLoadingIndicatorView(context);

        if (LOADING_MAP.get(type) == null) {
            Indicator indicator = getIndicator(type);
            LOADING_MAP.put(type, indicator);
        }

        view.setIndicator(LOADING_MAP.get(type));

        return  view;
    }

    private static Indicator getIndicator(String name) {
        if (TextUtils.isEmpty(name)) {
            return null;
        }

        final StringBuilder sb = new StringBuilder();

        // 不是全类名
        if (!name.contains(".")) {
            String defaultPackage = AVLoadingIndicatorView.class.getPackage().getName();

            sb.append(defaultPackage)
                    .append(".indicators")
                    .append(".");
        }

        sb.append(name);

        try {
            Class<?> clazz = Class.forName(sb.toString());
            return (Indicator) clazz.newInstance();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }


}
