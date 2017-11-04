package com.svenhe.latte_core.net;

import com.svenhe.latte_core.net.callback.IError;
import com.svenhe.latte_core.net.callback.IFailure;
import com.svenhe.latte_core.net.callback.IRequest;
import com.svenhe.latte_core.net.callback.ISuccess;
import com.svenhe.latte_core.net.callback.RequestCallbacks;

import java.util.WeakHashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HTTP;

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

    private final RequestBody ROW;

    private final ISuccess SUCCESS;

    private final IError ERROR;

    private final IFailure FAILURE;

    private final IRequest REQUEST;

    private final RequestBody REQUEST_BODY;


    public RestClient(String mURL,
                      WeakHashMap<String, Object> mParams,
                      RequestBody mRaw,
                      IRequest mIRequest,
                      ISuccess mISuccess,
                      IError mIError,
                      IFailure mIFailure,
                      RequestBody mRequestBody) {

        this.URL = mURL;
        this.PARAMS = mParams;
        this.ROW = mRaw;
        this.REQUEST = mIRequest;
        this.SUCCESS = mISuccess;
        this.ERROR = mIError;
        this.FAILURE = mIFailure;
        this.REQUEST_BODY = mRequestBody;

    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    public void request(HttpMethod method) {
        RestService service = RestCreator.getRestService();

        Call<String>  call = null;

        // 请求开始
        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case PATCH:
                call = service.patch(URL, PARAMS);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            default:
                call = service.get(URL, PARAMS);
                break;
        }

        if (call != null){
            call.enqueue(getRequestCallbacks());
        }

    }

    public RequestCallbacks getRequestCallbacks(){
        return new RequestCallbacks(SUCCESS, ERROR, FAILURE, REQUEST);
    }

    public void get() {
        request(HttpMethod.GET);
    }

    public void post() {
        request(HttpMethod.POST);
    }

    public void put() {
        request(HttpMethod.PUT);
    }

    public void patch() {
        request(HttpMethod.PATCH);
    }

    public void delete() {
        request(HttpMethod.DELETE);
    }

}
