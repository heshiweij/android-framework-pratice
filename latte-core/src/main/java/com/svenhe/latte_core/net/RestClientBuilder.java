package com.svenhe.latte_core.net;

import com.svenhe.latte_core.net.callback.IError;
import com.svenhe.latte_core.net.callback.IFailure;
import com.svenhe.latte_core.net.callback.IRequest;
import com.svenhe.latte_core.net.callback.ISuccess;

import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @项目名: FestEC
 * @包名: com.svenhe.latte_core.net
 * @创建者: svenhe
 * @创建时间: 2017/11/1 20:44
 * @描述:
 * @更新人: $Author: svenhe $
 * @更新时间: $Date:2017/11/1 20:44 $
 * @更新描述: TODO
 */
public class RestClientBuilder {

    private String mURL;

    private WeakHashMap<String, Object> mParams;

    private RequestBody mRaw;

    private IRequest mIRequest;

    private ISuccess mISuccess;

    private IError mIError;

    private IFailure mIFailure;

    private RequestBody mRequestBody;

    /**
     * 只能被 RestClient 使用
     */
    RestClientBuilder() {

    }

    public RestClientBuilder url(String url) {
        this.mURL = url;
        return this;
    }

    public RestClientBuilder params(WeakHashMap<String, Object> params) {
        mParams = params;
        return this;
    }

    public RestClientBuilder params(String key, Object value) {
        if (mParams == null) {
            mParams = new WeakHashMap<String, Object>();
        }
        mParams.put(key, value);
        return this;
    }

    public RestClientBuilder raw(String raw) {
        mRaw = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), raw);
        return this;
    }


    public RestClientBuilder request(IRequest request) {
        this.mIRequest = request;
        return this;
    }

    public RestClientBuilder success(ISuccess success) {
        this.mISuccess = success;
        return this;
    }

    public RestClientBuilder error(IError error) {
        this.mIError = error;
        return this;
    }

    public RestClientBuilder failure(IFailure failure) {
        this.mIFailure = failure;
        return this;
    }


    public RestClientBuilder body(RequestBody body) {
        this.mRequestBody = body;
        return this;
    }

    public RestClientBuilder checkParams(){
        if (mParams == null){
            mParams = new WeakHashMap<>();
        }

        return this;
    }


    public RestClient build() {
        return new RestClient(mURL, mParams, mRaw, mIRequest, mISuccess, mIError, mIFailure, mRequestBody);
    }

}
