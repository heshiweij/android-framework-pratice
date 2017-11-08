package com.svenhe.latte_core.ui.banner;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;

/**
 * @项目名: FestEC
 * @包名: com.svenhe.latte_core.ui.banner
 * @创建者: svenhe
 * @创建时间: 2017/11/6 14:24
 * @描述:
 * @更新人: $Author: svenhe $
 * @更新时间: $Date:2017/11/6 14:24 $
 * @更新描述: TODO
 */
public class LauncherHolder implements Holder<Integer> {

    private ImageView imageView;

    @Override
    public View createView(Context context) {
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, Integer data) {
        imageView.setImageResource(data);
    }
}
