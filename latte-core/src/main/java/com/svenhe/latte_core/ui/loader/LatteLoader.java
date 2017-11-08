package com.svenhe.latte_core.ui.loader;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.svenhe.latte_core.R;
import com.svenhe.latte_core.utils.dimen.DimenUtil;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

/**
 * @项目名: FestEC
 * @包名: com.svenhe.latte_core.ui
 * @创建者: svenhe
 * @创建时间: 2017/11/4 14:22
 * @描述:
 * @更新人: $Author: svenhe $
 * @更新时间: $Date:2017/11/4 14:22 $
 * @更新描述: TODO
 */
public  class LatteLoader {

    private static int LOADER_SIZE_SCALE = 8;
    private static int LOADER_OFFSET_SCALE = 10;

    private static final List<AppCompatDialog> LOADERS = new ArrayList<>();
    private static final String DEFAULT_LOADER = LoaderStyle.BallClipRotatePulseIndicator.name();


    public static void showLoading(Context context, Enum<LoaderStyle> type){
        showLoading(context, type.name());
    }

    public static void showLoading(Context context,String type){
        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.dialog);
        final AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreator.create(type, context);

        dialog.setContentView(avLoadingIndicatorView);

        // 获得屏幕宽高
        int deviceWidth = DimenUtil.getScreenWidth();
        int deviceHeight = DimenUtil.getScreenHeight();

        final Window dialogWindow = dialog.getWindow();
        if (dialog != null) {
            final WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.width = deviceWidth / LOADER_SIZE_SCALE;
            lp.height = deviceHeight / LOADER_SIZE_SCALE;
            lp.height = lp.height + deviceHeight / LOADER_OFFSET_SCALE;
            lp.gravity = Gravity.CENTER;
        }

        LOADERS.add(dialog);
        dialog.show();

    }

    /**
     * 显示默认风格 Dialog
     * @param context
     */
    public static void showLoading(Context context) {
        showLoading(context, DEFAULT_LOADER);
    }

    public static void stopLoading(){
        for (AppCompatDialog loader : LOADERS) {
            if (loader != null){
                if (loader.isShowing()){
                    loader.cancel();
                }
            }
        }
    }

}
