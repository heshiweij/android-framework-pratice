package com.svenhe.latte_core.utils.dimen;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.svenhe.latte_core.app.Latte;

/**
 * @项目名: FestEC
 * @包名: com.svenhe.latte_core.utils.dimen
 * @创建者: svenhe
 * @创建时间: 2017/11/4 14:34
 * @描述:
 * @更新人: $Author: svenhe $
 * @更新时间: $Date:2017/11/4 14:34 $
 * @更新描述: TODO
 */
public class DimenUtil {

    public static int getScreenWidth() {

        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;

    }

    public static int getScreenHeight() {

        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;

    }
}
