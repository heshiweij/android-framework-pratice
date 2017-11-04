package com.svenhe.festec.example;

import android.app.Application;

import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.svenhe.latte_core.app.Latte;

/**
 * @项目名: FestEC
 * @包名: com.svenhe.festec.example
 * @创建者: svenhe
 * @创建时间: 2017/11/1 11:06
 * @描述:
 * @更新人: $Author: svenhe $
 * @更新时间: $Date:2017/11/1 11:06 $
 * @更新描述: TODO
 */
public class ExampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化配置

        Latte.init(this)
                .withApiHost("http://127.0.0.1")
                .configure();

        Iconify
                .with(new FontAwesomeModule());
//                .with(new EntypoModule())
//                .with(new TypiconsModule())
//                .with(new MaterialModule())
//                .with(new MaterialCommunityModule())
//                .with(new MeteoconsModule())
//                .with(new WeathericonsModule())
//                .with(new SimpleLineIconsModule())
//                .with(new IoniconsModule());

    }
}
