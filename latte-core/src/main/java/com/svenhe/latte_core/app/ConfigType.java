package com.svenhe.latte_core.app;

/**
 * @项目名: FestEC
 * @包名: com.svenhe.latte_core
 * @创建者: svenhe
 * @创建时间: 2017/10/31 17:19
 * @描述:
 * @更新人: $Author: svenhe $
 * @更新时间: $Date:2017/10/31 17:19 $
 * @更新描述: TODO
 */
public enum ConfigType {

    /**
     *  API 服务地址
     */
    API_HOST,

    /**
     * 全局上下文
     */
    APPLICATION_CONTEXT,

    /**
     * 初始化是否已完成
     */
    CONFIG_READY,

    /**
     * 图标资源
     */
    ICON,

    /**
     * 拦截器
     */
    INTERCEPT

}
