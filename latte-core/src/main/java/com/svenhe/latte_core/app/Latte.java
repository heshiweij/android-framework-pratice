package com.svenhe.latte_core.app;

import android.content.Context;

import java.util.HashMap;

/**
 * @项目名: FestEC
 * @包名: com.svenhe.latte_core
 * @创建者: svenhe
 * @创建时间: 2017/10/31 17:12
 * @描述:
 * @更新人: $Author: svenhe $
 * @更新时间: $Date:2017/10/31 17:12 $
 * @更新描述: TODO
 */
public final class Latte {


    /**
     *
     * 初始化全局配置
     * @param context 全局 context
     * @return
     */
    public static Configurator init(Context context){

        // 设置全局 context
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(), context);

        return Configurator.getInstance();
    }

    private static HashMap<String, Object> getConfigurations(){

        return Configurator.getInstance().getLatteConfigs();

    }


}
