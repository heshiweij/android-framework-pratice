package com.svenhe.latte_ec.sign;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
 * @创建时间: 2017/11/6 15:22
 * @描述:
 * @更新人: $Author: svenhe $
 * @更新时间: $Date:2017/11/6 15:22 $
 * @更新描述: TODO
 */
public class SignUpDelegate extends LatteDelegate {


    @BindView(R2.id.edit_sign_up_name)
    TextInputEditText editSignUpName;
    @BindView(R2.id.edit_sign_up_email)
    TextInputEditText editSignUpEmail;
    @BindView(R2.id.edit_sign_up_phone)
    TextInputEditText editSignUpPhone;
    @BindView(R2.id.edit_sign_up_password)
    TextInputEditText editSignUpPassword;
    @BindView(R2.id.edit_sign_up_re_password)
    TextInputEditText editSignUpRePassword;
    @BindView(R2.id.btn_sign_up)
    AppCompatButton btnSignUp;
    @BindView(R2.id.tv_link_sign_in)
    AppCompatTextView tvLinkSignIn;
    Unbinder unbinder;

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {

    }

    @OnClick(R2.id.btn_sign_up)
    void onClickSignUp() {
        checkForm();
    }

    @OnClick(R2.id.tv_link_sign_in)
    void onClickLinkIn() {
        start(new SignInDelegate(), SINGLETASK);
    }

    private boolean checkForm() {
        final String name = editSignUpName.getText().toString();
        final String email = editSignUpEmail.getText().toString();
        final String phone = editSignUpPhone.getText().toString();
        final String password = editSignUpPassword.getText().toString();
        final String rePassword = editSignUpRePassword.getText().toString();

        boolean isPass = true;

        if (name.isEmpty()) {
            editSignUpName.setError("请输入姓名");
            isPass = false;
        } else {
            editSignUpName.setError(null);
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editSignUpEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            editSignUpEmail.setError(null);
        }

        if (phone.isEmpty() || phone.length() != 11) {
            editSignUpPhone.setError("手机号码错误");
            isPass = false;
        } else {
            editSignUpPhone.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            editSignUpPassword.setError("请填写至少6位数密码");
            isPass = false;
        } else {
            editSignUpPassword.setError(null);
        }

        if (rePassword.isEmpty() || rePassword.length() < 6 || !(rePassword.equals(password))) {
            editSignUpRePassword.setError("密码验证错误");
            isPass = false;
        } else {
            editSignUpRePassword.setError(null);
        }

        return isPass;
    }


}
