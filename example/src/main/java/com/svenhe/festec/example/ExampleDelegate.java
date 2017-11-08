package com.svenhe.festec.example;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.svenhe.latte_core.app.Latte;
import com.svenhe.latte_core.delegates.LatteDelegate;
import com.svenhe.latte_core.net.RestClient;
import com.svenhe.latte_core.net.callback.IError;
import com.svenhe.latte_core.net.callback.IFailure;
import com.svenhe.latte_core.net.callback.ISuccess;
import com.svenhe.latte_core.ui.loader.LoaderStyle;

import butterknife.BindView;

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

    @BindView(R.id.btn_erq)
    Button btnReq;

    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {

        btnReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                test();

                Toast.makeText(Latte.getApplicationContext(), "c54693515c85d957f7cb6947d7c0cbf2", Toast.LENGTH_SHORT).show();

            }
        });


    }


    private void test() {

        RestClient.builder()
//                .url("https://www.baidu.com")
                .url("http://static.699pic.com/images/sup_up/c54693515c85d957f7cb6947d7c0cbf2.jpg")
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
                .context(getActivity())
                .loader(LoaderStyle.BallSpinFadeLoaderIndicator)
                .dir("hehe")
                .extension(".jpg")
                .filename("hahaha")
                .build()
                .download();
        ;
    }

}
