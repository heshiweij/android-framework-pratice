package com.svenhe.latte_core.net;

import com.svenhe.latte_core.app.ConfigType;
import com.svenhe.latte_core.app.Configurator;
import com.svenhe.latte_core.app.Latte;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @项目名: FestEC
 * @包名: com.svenhe.latte_core.net
 * @创建者: svenhe
 * @创建时间: 2017/11/1 20:18
 * @描述:
 * @更新人: $Author: svenhe $
 * @更新时间: $Date:2017/11/1 20:18 $
 * @更新描述: TODO
 */
public class RestCreator {

    private static class RetrofitHolder {

        private static final String BASE_URL = Configurator.getInstance().getConfiguration(ConfigType.API_HOST);

        private static final Retrofit RETROFIT_CLIENT =
                new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .client(OkHttpHolder.HTTP_CLIENT)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .build();
    }

    private static class OkHttpHolder {
        private static final int TIME_OUT = 60;

        private static final List<Interceptor> INTERCEPTORS = Configurator.getInstance().getInterceptors();

        private static final OkHttpClient.Builder OK_HTTP_BUILDER = new OkHttpClient
                .Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS);

        private static OkHttpClient.Builder addIntercepts() {
            if (INTERCEPTORS != null && !INTERCEPTORS.isEmpty()) {
                for (Interceptor interceptor : INTERCEPTORS) {
                    OK_HTTP_BUILDER.addInterceptor(interceptor);
                }
            }

            return OK_HTTP_BUILDER;
        }

//        addIntercepts

        private static final OkHttpClient HTTP_CLIENT = addIntercepts().build();

//        private static final OkHttpClient HTTP_CLIENT =
//                new OkHttpClient.Builder()
////                        .addInterceptor() // 拦截器, 只有响应
////                        .addNetworkInterceptor() // 拦截器, 请求和响应
//                        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
//                        .build();
    }

    private static class RestServiceHolder {
        private static final RestService REST_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }


    public static final Retrofit getRetrofitHolder() {
        return RetrofitHolder.RETROFIT_CLIENT;
    }

    public static final OkHttpClient getOkHttpClient() {
        return OkHttpHolder.HTTP_CLIENT;
    }

    public static final RestService getRestService() {
        return RestServiceHolder.REST_SERVICE;
    }

}
