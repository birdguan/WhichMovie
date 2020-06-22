package com.birdguan.whichmovie.ui.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.birdguan.whichmovie.MainActivity;

import com.birdguan.whichmovie.R;
import com.birdguan.whichmovie.base.BaseActivity;
import com.birdguan.whichmovie.model.User;
import com.birdguan.whichmovie.util.AESUtil;

import org.litepal.LitePal;

import java.util.List;

/**
 * @Author: birdguan
 * @Date: 2020/6/18 17:50
 */
public class LoginActivity extends BaseActivity {
    private static final String TAG = "WhichMovie/LoginActivity";
    private SharedPreferences pref;     // 存储记住密码
    private SharedPreferences.Editor editor;
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
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        imageViewLogin = (ImageView) findViewById(R.id.iv_login);
        editTextUsername = (EditText) findViewById(R.id.et_username);
        editTextPassword = (EditText) findViewById(R.id.et_password);
        checkBoxRemember = (CheckBox) findViewById(R.id.cb_remember);
        buttonSignUp = (Button) findViewById(R.id.btn_sign_up);
        buttonLogin = (Button) findViewById(R.id.btn_login);

        // 若之前用户选择了记住密码，则恢复用户名和密码以及CheckBox勾选状态
        final boolean isRemember = pref.getBoolean("remember_password", false);
        if (isRemember) {
            String username = pref.getString("username", "");
            String password = pref.getString("password", "");
            // 对密码进行AES解密
            try {
                password = AESUtil.decrypt("41227677", password);
            } catch (Exception e) {
                Log.d(TAG, "AES密码解密失败");
                e.printStackTrace();
            }
            editTextUsername.setText(username);
            editTextPassword.setText(password);
            checkBoxRemember.setChecked(true);
        }

        // 监听注册按钮buttonSignUp，按下时将用户名和密码存到SQLite
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取用户名和密码
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();

                // 对要储存到数据库的密码进行AES加密
                try {
                    password = AESUtil.encrypt("41227677", password);
                } catch (Exception e) {
                    Log.d(TAG, "对要储存到数据库的密码进行AES加密时失败");
                }

                // 将用户名和加密后的密码插入到数据库db_whichfilm
                User user = new User();
                user.setUsername(username);
                user.setPassword(password);
                user.save();
            }
        });

        // 监听登录按钮buttonLogin，按下时从SQLite验证，验证成功进入MainActivity
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();
                // 从数据库查询用户密码是否匹配
                List<User> users = LitePal.where("username=?", username).find(User.class);
                boolean isValid = false;
                if (users != null) {
                    for (User user : users) {
                        String storedPassword = user.getPassword();

                        // 对数据库存储的密码进行AES解密
                        try {
                            storedPassword = AESUtil.decrypt("41227677", storedPassword);
                        } catch (Exception e) {
                            Log.d(TAG, "对数据库存储的密码进行AES解密失败");
                        }

                        if (password.equals(storedPassword)) {
                            isValid = true;
                            break;
                        }
                    }
                }
                if (isValid) {
                    // 若用户勾选checkBoxRemember，SharedPreferences记住密码
                    // 否则清除SharedPreferences存储的数据
                    editor = pref.edit();
                    if (checkBoxRemember.isChecked()) {
                        editor.putBoolean("remember_password", true);
                        editor.putString("username", username);

                        // 对用户密码进行AES加密
                        try {
                            password = AESUtil.encrypt("41227677", password);
                        } catch (Exception e) {
                            Log.d(TAG, "对SharedPreferences中存储的用户密码进行加密时失败");
                            password = "";
                        }

                        editor.putString("password", password);
                    } else {
                        editor.clear();
                    }
                    editor.apply();
                    // 跳转Activity
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                }
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
