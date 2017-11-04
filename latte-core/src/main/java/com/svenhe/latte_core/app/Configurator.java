package com.svenhe.latte_core.app;

import java.util.HashMap;

/**
 * @项目名: FestEC
 * @包名: com.svenhe.latte_core
 * @创建者: svenhe
 * @创建时间: 2017/10/31 17:19
 * @描述: 配置类，非常安全的线程单利模式
 * @更新人: $Author: svenhe $
 * @更新时间: $Date:2017/10/31 17:19 $
 * @更新描述:
 */
public class Configurator {

    /**
     * 全局配置
     */
    private static final HashMap<String, Object> LATTE_CONFIGS = new HashMap<>();

    public Configurator() {

        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), false);

    }

    private static class Holder {

        public static final Configurator INSTANCE = new Configurator();

    }

    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    public HashMap<String, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    /**
     * 已经配置完成
     */
    public void configure() {
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
    }


    public final void checkConfiguration() {
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY.name());

        if (!isReady) {
            throw new RuntimeException("Configuration is not ready, please call configure first!");
        }

    }

    public final <T> T getConfiguration(Enum<ConfigType> key) {
        return (T) LATTE_CONFIGS.get(key.name());
    }

    /**
     * 设置 Api Host
     *
     * @param host
     * @return
     */
    public final Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(), host);
        return this;
    }


}
