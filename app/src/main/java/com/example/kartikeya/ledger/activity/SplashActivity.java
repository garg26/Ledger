package com.example.kartikeya.ledger.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.kartikeya.ledger.R;
import com.example.kartikeya.ledger.utility.ApiGenerator;

import simplifii.framework.activity.BaseActivity;
import simplifii.framework.asyncmanager.HttpParamObject;
import simplifii.framework.exceptionhandler.RestException;
import simplifii.framework.utility.AppConstants;
import simplifii.framework.utility.CollectionUtils;
import simplifii.framework.utility.Preferences;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {

                checkLogin();

            }


        }.execute();
    }

    private void checkLogin() {
        String authToken = Preferences.getData(Preferences.KEY_AUTH_TOKEN, "");
        if(CollectionUtils.isNotEmpty(authToken)){
            HttpParamObject httpParamObject = ApiGenerator.isValidToken(authToken);
            executeTask(AppConstants.TASKCODES.USER_TOKEN,httpParamObject);

        }
        else{
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            finish();
        }

    }

    @Override
    public void onPreExecute(int taskCode) {

    }

    @Override
    public void onPostExecute(Object response, int taskCode, Object... params) {
        super.onPostExecute(response, taskCode, params);
        if (response==null){
            return;
        }
        switch (taskCode){
            case AppConstants.TASKCODES.USER_TOKEN:
                if (response.equals("true")){
                    startNextActivity(HomeActivity.class);
                    finish();
                }
                else{
                    startNextActivity(LoginActivity.class);
                    finish();
                }
                break;
        }
    }
}
