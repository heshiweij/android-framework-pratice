package com.svenhe.festec.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.svenhe.latte_core.app.Configurator;
import com.svenhe.latte_core.app.Latte;
import com.svenhe.latte_core.delegates.LatteDelegate;
import com.svenhe.latte_core.net.RestClient;
import com.svenhe.latte_core.net.callback.IError;
import com.svenhe.latte_core.net.callback.IFailure;
import com.svenhe.latte_core.net.callback.IRequest;
import com.svenhe.latte_core.net.callback.ISuccess;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @项目名: FestEC
 * @包名: com.svenhe.festec.example
 * @创建者: svenhe
 * @创建时间: 2017/11/1 19:39
 * @描述:
 * @更新人: $Author: svenhe $
 * @更新时间: $Date:2017/11/1 19:39 $
 * @更新描述: TODO
 */
public class ExampleDelegate extends LatteDelegate {

    //    @BindView(R.id.btn_erq)
    Button btnReq;

//    AVLoadingIndicatorView mAvi;

    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {

        btnReq = (Button) rootView.findViewById(R.id.btn_erq);

//        mAvi = (AVLoadingIndicatorView) rootView.findViewById(R.id.avi);


        btnReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                test();

//                mAvi.hide();
            }
        });


    }

    private void test() {

        RestClient.builder()
                .url("https://www.baidu.com")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Toast.makeText(getActivity(), "返回码: " + code + ", 消息: " + msg, Toast.LENGTH_SHORT).show();
                    }
                })
                .failure(new IFailure() {

                    @Override
                    public void onFailure(Throwable throwable) {
                        Toast.makeText(getActivity(), "请求失败: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })
                .checkParams()
                .build()
                .get();
        ;
    }

}
