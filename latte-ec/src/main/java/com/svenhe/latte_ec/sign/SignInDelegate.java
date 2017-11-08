package com.svenhe.latte_ec.sign;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.joanzapata.iconify.widget.IconTextView;
import com.svenhe.latte_core.app.Latte;
import com.svenhe.latte_core.delegates.LatteDelegate;
import com.svenhe.latte_ec.R;
import com.svenhe.latte_ec.R2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @项目名: FestEC
 * @包名: com.svenhe.latte_ec.sign
 * @创建者: svenhe
 * @创建时间: 2017/11/6 15:38
 * @描述:
 * @更新人: $Author: svenhe $
 * @更新时间: $Date:2017/11/6 15:38 $
 * @更新描述: TODO
 */
public class SignInDelegate extends LatteDelegate {


    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText editSignInEmail;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText editSignInPassword;
    @BindView(R2.id.btn_sign_in)
    AppCompatButton btnSignIn;
    @BindView(R2.id.tv_link_sign_up)
    AppCompatTextView tvLinkSignUp;
    @BindView(R2.id.icon_sign_in_wechat)
    IconTextView iconSignInWechat;
    Unbinder unbinder;

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {

    }

    @OnClick(R2.id.icon_sign_in_wechat)
    void onClickWeChat() {
        Toast.makeText(Latte.getApplicationContext(), "微信登陆", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R2.id.tv_link_sign_up)
    void onClickLink() {
        start(new SignUpDelegate(), SINGLETASK);
        
    }

    private boolean checkForm() {
        final String email = editSignInEmail.getText().toString();
        final String password = editSignInPassword.getText().toString();

        boolean isPass = true;

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editSignInEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            editSignInEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            editSignInPassword.setError("请填写至少6位数密码");
            isPass = false;
        } else {
            editSignInPassword.setError(null);
        }

        return isPass;
    }


}
