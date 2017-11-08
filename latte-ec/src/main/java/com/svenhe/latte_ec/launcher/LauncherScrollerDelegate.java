package com.svenhe.latte_ec.launcher;

import android.os.Bundle;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.svenhe.latte_core.delegates.LatteDelegate;
import com.svenhe.latte_core.ui.banner.LauncherHolderCreator;
import com.svenhe.latte_core.utils.storage.LattePreference;
import com.svenhe.latte_ec.R;

import java.util.ArrayList;

/**
 * @项目名: FestEC
 * @包名: com.svenhe.latte_ec.launcher
 * @创建者: svenhe
 * @创建时间: 2017/11/6 14:17
 * @描述:
 * @更新人: $Author: svenhe $
 * @更新时间: $Date:2017/11/6 14:17 $
 * @更新描述: TODO
 */
public class LauncherScrollerDelegate extends LatteDelegate implements OnItemClickListener {

    private ConvenientBanner<Integer> mConvenientBanner;

    private static final ArrayList<Integer> mBannerResIds = new ArrayList<>();

    private void initBanners() {
        mBannerResIds.add(R.mipmap.launcher_01);
        mBannerResIds.add(R.mipmap.launcher_02);
        mBannerResIds.add(R.mipmap.launcher_03);
        mBannerResIds.add(R.mipmap.launcher_04);
        mBannerResIds.add(R.mipmap.launcher_05);

        if (mConvenientBanner != null) {
            mConvenientBanner.setPages(new LauncherHolderCreator(), mBannerResIds)
                    .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                    .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                    .setOnItemClickListener(this)
                    .setCanLoop(false);

        }

    }

    @Override
    public Object setLayout() {
        mConvenientBanner = new ConvenientBanner<>(getProxyActivity());
        return mConvenientBanner;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {
        initBanners();
    }

    @Override
    public void onItemClick(int position) {
        // 点击最后一个的逻辑
        if (position == mBannerResIds.size() - 1) {
            // 设置已经不是第一次打开
            LattePreference.setAppFlag(ScrollerLauncherTag.NOT_FIRST_LAUNCHER_APP.name(), true);

            // 判断是否登陆

        }
    }
}
