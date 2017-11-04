package com.svenhe.latte_core.net.download;

import android.os.AsyncTask;

import com.svenhe.latte_core.net.RestCreator;
import com.svenhe.latte_core.net.callback.IError;
import com.svenhe.latte_core.net.callback.IFailure;
import com.svenhe.latte_core.net.callback.IRequest;
import com.svenhe.latte_core.net.callback.ISuccess;

import java.util.WeakHashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @项目名: FestEC
 * @包名: com.svenhe.latte_core.net.download
 * @创建者: svenhe
 * @创建时间: 2017/11/4 16:59
 * @描述:
 * @更新人: $Author: svenhe $
 * @更新时间: $Date:2017/11/4 16:59 $
 * @更新描述: TODO
 */
public class DownloadHandler {

    private final String URL;
    private final WeakHashMap<String, Object> PARAMS;
    private final IRequest REQUEST;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;
    private final ISuccess SUCCESS;
    private final IError ERROR;
    private final IFailure FAILURE;

    public DownloadHandler(String url,
                           WeakHashMap<String, Object> params,
                           IRequest request,
                           String download_dir,
                           String extension,
                           String name,
                           ISuccess success,
                           IError error,
                           IFailure failure) {
        this.URL = url;
        this.PARAMS = params;
        this.REQUEST = request;
        this.DOWNLOAD_DIR = download_dir;
        this.EXTENSION = extension;
        this.NAME = name;
        this.SUCCESS = success;
        this.ERROR = error;
        this.FAILURE = failure;
    }

    public final void handleDownload() {
        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        RestCreator.getRestService().download(URL, PARAMS).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    final ResponseBody responseBody = response.body();
                    final SaveFileAsyncTask task = new SaveFileAsyncTask(REQUEST, SUCCESS);
                    task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, DOWNLOAD_DIR, EXTENSION, responseBody, NAME);
                    //这里一定要注意判断，否则文件下载不全
                    // TODO 此方法在 SaveFileAsyncTask 也被调用了一遍，有争议，待测试
                    if (task.isCancelled()) {
                        REQUEST.onRequestEnd();
                    }
                } else {
                    if (ERROR != null) {
                        ERROR.onError(response.code(), response.message());
                    }
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (FAILURE != null) {
                    FAILURE.onFailure(t);
                }

            }
        });

    }

}
