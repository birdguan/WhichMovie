package com.birdguan.whichmovie.ui.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.birdguan.whichmovie.R;
import com.birdguan.whichmovie.base.BaseActivity;

/**
 * @Author: birdguan
 * @Date: 2020/6/18 17:50
 */
public class LoginActivity extends BaseActivity {
    private ImageView imageViewLogin;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private CheckBox checkBoxRemember;
    private Button buttonSignUp;
    private Button  buttonLogin;

    /**
     * 焦点定位在editTextPassword时切换为捂眼图片，负责为睁眼图片
     * 勾选记住密码复选框时用SharedPreferences保存用户名和密码
     * 点击注册按钮buttonSignUp时存储到SQLite
     * 点击登录按钮buttonLogin时到SQLite验证username对应的
     * @param savedInstanceState -
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        imageViewLogin = (ImageView) findViewById(R.id.iv_login);
        editTextUsername = (EditText) findViewById(R.id.et_username);
        editTextPassword = (EditText) findViewById(R.id.et_password);
        checkBoxRemember = (CheckBox) findViewById(R.id.cb_remember);
        buttonSignUp = (Button) findViewById(R.id.btn_sign_up);
        buttonLogin = (Button) findViewById(R.id.btn_login);

        // 监听注册按钮buttonSignUp，按下时将用户名和密码存到SQLite
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // 监听登录按钮buttonLogin，按下时从SQLite验证，验证成功进入MainActivity
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // 监听密码输入框editTextPassword焦点，获得焦点将图片改为捂脸
        editTextPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    imageViewLogin.setImageResource(R.drawable.login_password);
                } else {
                    imageViewLogin.setImageResource(R.drawable.login_username);
                }
            }
        });
    }
}
