package com.example.kartikeya.ledger.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.kartikeya.ledger.R;
import com.example.kartikeya.ledger.model.UserLoginItem;
import com.example.kartikeya.ledger.model.response.UserLoginResponse;
import com.example.kartikeya.ledger.utility.ApiGenerator;

import simplifii.framework.activity.BaseActivity;
import simplifii.framework.asyncmanager.HttpParamObject;
import simplifii.framework.utility.AppConstants;
import simplifii.framework.utility.CollectionUtils;
import simplifii.framework.utility.Preferences;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setOnClickListener(R.id.btn_register,R.id.btn_sign_in);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                startNextActivity(SignUpActivity.class);
                break;
            case R.id.btn_sign_in:
                onLogin();
                break;
        }
    }

    private void onLogin() {
        String phoneNumber = getEditText(R.id.et_phone_number);
        if (CollectionUtils.isNotEmpty(phoneNumber) && phoneNumber.length()==10){
            String password = getEditText(R.id.et_password);
            if (CollectionUtils.isNotEmpty(password)){
                UserLoginItem userLoginItem = new UserLoginItem(phoneNumber,password);
                HttpParamObject httpParamObject = ApiGenerator.setUserLogin(userLoginItem);
                executeTask(AppConstants.TASKCODES.USER_LOGIN,httpParamObject);
            }
            else{
                showToast(R.string.error_empty_password);
            }
        }
        else{
            showToast(R.string.error_invalid_phone_number);
        }
    }

    @Override
    public void onPostExecute(Object response, int taskCode, Object... params) {
        super.onPostExecute(response, taskCode, params);

        if (response==null){
            return;
        }
        switch (taskCode){
            case AppConstants.TASKCODES.USER_LOGIN:
                UserLoginResponse userLoginResponse = (UserLoginResponse) response;
                String userToken = userLoginResponse.getUserToken();
                if (CollectionUtils.isNotEmpty(userToken)){
                    Preferences.saveData(Preferences.KEY_AUTH_TOKEN,userToken);
                    Preferences.saveData(Preferences.LOGIN_RESPONSE,userLoginResponse.getOwnerId());
                    showToast("User Successfully Login");
                    startNextActivity(HomeActivity.class);
                    finish();
                }
                else{
                    showToast(R.string.fail);
                }
                break;
        }

    }
}
