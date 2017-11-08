package com.svenhe.latte_core.ui.banner;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * @项目名: FestEC
 * @包名: com.svenhe.latte_core.ui.banner
 * @创建者: svenhe
 * @创建时间: 2017/11/6 14:23
 * @描述:
 * @更新人: $Author: svenhe $
 * @更新时间: $Date:2017/11/6 14:23 $
 * @更新描述: TODO
 */
public class LauncherHolderCreator implements CBViewHolderCreator<LauncherHolder> {

    @Override
    public LauncherHolder createHolder() {
        return new LauncherHolder();
    }

}
