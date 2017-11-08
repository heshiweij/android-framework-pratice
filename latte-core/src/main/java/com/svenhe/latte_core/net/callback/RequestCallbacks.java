package com.svenhe.latte_core.net.callback;

import com.svenhe.latte_core.ui.loader.LatteLoader;
import com.svenhe.latte_core.ui.loader.LoaderStyle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @项目名: FestEC
 * @包名: com.svenhe.latte_core.net.callback
 * @创建者: svenhe
 * @创建时间: 2017/11/4 11:09
 * @描述:
 * @更新人: $Author: svenhe $
 * @更新时间: $Date:2017/11/4 11:09 $
 * @更新描述: TODO
 */
public class RequestCallbacks implements Callback<String> {

    private final ISuccess SUCCESS;

    private final IError ERROR;

    private final IFailure FAILURE;

    private final IRequest REQUEST;

    private final LoaderStyle LOADER_STYLE;

    public RequestCallbacks(ISuccess SUCCESS,
                            IError ERROR,
                            IFailure FAILURE,
                            IRequest REQUEST,
                            LoaderStyle LOADER_STYLE) {

        this.SUCCESS = SUCCESS;
        this.ERROR = ERROR;
        this.FAILURE = FAILURE;
        this.REQUEST = REQUEST;

        this.LOADER_STYLE = LOADER_STYLE;

    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (SUCCESS != null) {
                    SUCCESS.onSuccess(response.body());
                }
            }
        } else {
            if (ERROR != null) {
                ERROR.onError(response.code(), response.message());
            }
        }

        // 请求结束
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }

        // 停止 loading
        stopLoading();

    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (FAILURE != null) {
            FAILURE.onFailure(t);
        }

        // 请求结束
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }

        // 停止 loading
        stopLoading();
    }

    /**
     * 停止 loading
     */
    private void stopLoading() {
        if (LOADER_STYLE != null) {
            LatteLoader.stopLoading();
        }
    }
}
