package com.svenhe.latte_core.net;

import android.content.Context;
import android.content.pm.ProviderInfo;
import android.text.TextUtils;

import com.svenhe.latte_core.net.callback.IError;
import com.svenhe.latte_core.net.callback.IFailure;
import com.svenhe.latte_core.net.callback.IRequest;
import com.svenhe.latte_core.net.callback.ISuccess;
import com.svenhe.latte_core.net.callback.RequestCallbacks;
import com.svenhe.latte_core.ui.LatteLoader;
import com.svenhe.latte_core.ui.LoaderStyle;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

/**
 * @项目名: FestEC
 * @包名: com.svenhe.latte_core.net
 * @创建者: svenhe
 * @创建时间: 2017/11/1 20:00
 * @描述:
 * @更新人: $Author: svenhe $
 * @更新时间: $Date:2017/11/1 20:00 $
 * @更新描述: TODO
 */
public class RestClient {

    private final String URL;

    private final WeakHashMap<String, Object> PARAMS;

    private final RequestBody BODY;

    private final ISuccess SUCCESS;

    private final IError ERROR;

    private final IFailure FAILURE;

    private final IRequest REQUEST;

    private final Context CONTEXT;

    private final LoaderStyle LOADER_STYLE;

    private final File FILE;


    public RestClient(String url,
                      WeakHashMap<String, Object> params,
                      RequestBody raw,
                      IRequest request,
                      ISuccess success,
                      IError error,
                      IFailure failure,
                      Context context,
                      LoaderStyle loaderStyle,
                      File file
    ) {

        this.URL = url;
        this.PARAMS = params;
        this.BODY = raw;
        this.REQUEST = request;
        this.SUCCESS = success;
        this.ERROR = error;
        this.FAILURE = failure;

        this.CONTEXT = context;
        this.LOADER_STYLE = loaderStyle;

        this.FILE = file;
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    public void request(HttpMethod method) {
        RestService service = RestCreator.getRestService();

        Call<String> call = null;

        // 请求开始
        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        // 开启 loading
        if (LOADER_STYLE != null) {
            LatteLoader.showLoading(CONTEXT, LOADER_STYLE);
        }

        checkUrl();

        switch (method) {
            case GET:
                checkParams();

                call = service.get(URL, PARAMS);
                break;
            case POST:
                checkParams();

                call = service.post(URL, PARAMS);
                break;
            case POST_RAW:
                checkBody();

                call = service.postRow(URL, BODY);
                break;
            case PUT:
                checkParams();

                call = service.put(URL, PARAMS);
                break;
            case PUT_RAW:
                checkBody();

                call = service.putRaw(URL, BODY);
                break;
            case PATCH:
                checkParams();

                call = service.patch(URL, PARAMS);
                break;
            case DELETE:
                checkParams();

                call = service.delete(URL, PARAMS);
                break;
            case UPLOAD:
                checkFile();

                RequestBody requestBody = RequestBody.create(MultipartBody.FORM, FILE);
                MultipartBody.Part body = MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                call = service.upload(URL, body);
                break;
            default:
                break;
        }

        if (call != null) {
            call.enqueue(getRequestCallbacks());
        }

    }

    private void checkUrl(){
        if (TextUtils.isEmpty(URL)){
            throw new RuntimeException("Sorry! The URL must not be empty");
        }
    }

    private void checkParams(){
        if (PARAMS == null){
            throw new RuntimeException("Sorry! The PARAMS must not be empty");
        }
    }

    private void checkBody(){
        if (BODY == null){
            throw new RuntimeException("Sorry! The BODY must not be empty");
        }
    }

    private void checkFile(){
        if (FILE == null){
            throw new RuntimeException("Sorry! The FILE must not be empty");
        }
    }

    public RequestCallbacks getRequestCallbacks() {
        return new RequestCallbacks(SUCCESS, ERROR, FAILURE, REQUEST, LOADER_STYLE);
    }

    public void get() {
        request(HttpMethod.GET);
    }

    public void post() {
        if (BODY != null) {
            if (PARAMS.isEmpty()) {
                request(HttpMethod.POST_RAW);
            } else {
                throw new RuntimeException("Sorry! The Params must be empty when you request with raw");
            }
        } else {
            request(HttpMethod.POST);
        }
    }


    public void put() {
        if (BODY != null) {
            if (PARAMS.isEmpty()) {
                request(HttpMethod.PUT_RAW);
            } else {
                throw new RuntimeException("Sorry! The Params must be empty when you request with raw");
            }
        } else {
            request(HttpMethod.PUT);
        }
    }

    public void patch() {
        request(HttpMethod.PATCH);
    }

    public void delete() {
        request(HttpMethod.DELETE);
    }

    public void upload(){
        request(HttpMethod.UPLOAD);
    }

}
