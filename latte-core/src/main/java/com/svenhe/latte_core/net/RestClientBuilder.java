package com.svenhe.latte_core.net;

import android.content.Context;

import com.svenhe.latte_core.net.callback.IError;
import com.svenhe.latte_core.net.callback.IFailure;
import com.svenhe.latte_core.net.callback.IRequest;
import com.svenhe.latte_core.net.callback.ISuccess;
import com.svenhe.latte_core.ui.LoaderStyle;

import java.io.File;
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

    private IRequest mIRequest;

    private ISuccess mISuccess;

    private IError mIError;

    private IFailure mIFailure;

    private RequestBody mRequestBody;

    private Context mContext;

    private LoaderStyle mLoaderStyle;

    private File mFile;

    private String mDownloadFileDir;
    private String mDownloadFileExtension;
    private String mDownloadFileName;

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

    public RestClientBuilder body(String raw) {
        mRequestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), raw);
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

    public RestClientBuilder checkParams() {
        if (mParams == null) {
            mParams = new WeakHashMap<>();
        }

        return this;
    }

    /**
     * 上下文，用于 AlertDialog (不能传入全局 Context)
     *
     * @param context
     * @return
     */
    public RestClientBuilder context(Context context) {
        mContext = context;
        return this;
    }

    public RestClientBuilder loader(LoaderStyle loaderStyle) {
        mLoaderStyle = loaderStyle;
        return this;
    }

    public RestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public RestClientBuilder file(String file) {
        this.mFile = new File(file);
        return this;
    }

    /**
     * 设置文件存放目录 (下载文件)
     *
     * @param dir 文件存放目录
     * @return
     */
    public RestClientBuilder dir(String dir) {
        this.mDownloadFileDir = dir;
        return this;
    }

    /**
     * 设置文件后缀 (下载文件)
     *
     * @param extension 后缀
     * @return
     */
    public RestClientBuilder extension(String extension) {
        this.mDownloadFileExtension = extension;
        return this;
    }


    /**
     * 设置文件名 (下载文件)
     *
     * @param filename 文件名
     * @return
     */
    public RestClientBuilder filename(String filename) {
        this.mDownloadFileName = filename;
        return this;
    }


    public RestClient build() {
        return new RestClient(mURL, // 地址
                mParams, mRequestBody, // 数据
                mIRequest, mISuccess, mIError, mIFailure, // 回调
                mContext, mLoaderStyle, // loader 资源
                mFile, // 上传文件
                mDownloadFileDir, // 下载文件 - 文件存放目录
                mDownloadFileExtension, // 下载文件 - 文件后缀
                mDownloadFileName // 下载文件 - 文件名
        );
    }

}
