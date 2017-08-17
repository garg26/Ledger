package com.example.kartikeya.ledger.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.kartikeya.ledger.R;
import com.example.kartikeya.ledger.model.UserSignUpItem;
import com.example.kartikeya.ledger.model.response.ShopCategoryItem;
import com.example.kartikeya.ledger.utility.ApiGenerator;

import java.util.ArrayList;
import java.util.List;

import simplifii.framework.activity.BaseActivity;
import simplifii.framework.asyncmanager.HttpParamObject;
import simplifii.framework.exceptionhandler.RestException;
import simplifii.framework.utility.AppConstants;
import simplifii.framework.utility.CollectionUtils;

public class SignUpActivity extends BaseActivity {


    private List<String> shop_category_list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        setOnClickListener(R.id.btn_register);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register:
                onSignUp();
                break;
        }
    }

    private void onSignUp() {
        String shopName = getEditText(R.id.et_shop_name);
        if (CollectionUtils.isNotEmpty(shopName)){
            String shopCategory = getSpinnerText(R.id.sp_shop_category);
            if (CollectionUtils.isNotEmpty(shopCategory) && !shopCategory.equals("Select Shop Category")){
                String phoneNumber = getEditText(R.id.et_phone_number);
                if (CollectionUtils.isNotEmpty(phoneNumber) && phoneNumber.length()==10){
                    String password = getEditText(R.id.et_password);
                    if (CollectionUtils.isNotEmpty(password)){
                        String confirmPassword = getEditText(R.id.et_confirm_password);
                        if (CollectionUtils.isNotEmpty(confirmPassword) && confirmPassword.equals(password)){
                            UserSignUpItem userSignUpItem = new UserSignUpItem(shopName,shopCategory,phoneNumber,password);
                            HttpParamObject httpParamObject = ApiGenerator.setUserRegister(userSignUpItem);
                            executeTask(AppConstants.TASKCODES.USER_REGISTER,httpParamObject);
                        }
                        else{
                            showToast(getString(R.string.error_password_not_match));
                        }
                    }
                    else{
                        showToast(getString(R.string.error_empty_password));
                    }
                }
                else{
                    showToast(getString(R.string.error_invalid_phone_number));
                }
            }
            else{
                showToast(getString(R.string.error_empty_shop_cateogry));
            }
        }
        else{
            showToast(getString(R.string.error_empty_shop_name));
        }
    }



    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        getShopCategoryList();
    }



    private void getShopCategoryList() {
        HttpParamObject httpParamObject = ApiGenerator.getShopCategoryList();
        executeTask(AppConstants.TASKCODES.GET_SHOP_CATEOGRY,httpParamObject);
    }

    @Override
    public void onBackgroundError(RestException re, Exception e, int taskCode, Object... params) {
        super.onBackgroundError(re, e, taskCode, params);

        switch (taskCode){
            case AppConstants.TASKCODES.GET_SHOP_CATEOGRY:
                shop_category_list.add("Select Shop Category");
                setSpinAdapter(shop_category_list,R.id.sp_shop_category);
                break;
        }

    }

    @Override
    public void onPostExecute(Object response, int taskCode, Object... params) {
        super.onPostExecute(response, taskCode, params);

        if (response==null){
            return;
        }

        switch (taskCode){
            case AppConstants.TASKCODES.GET_SHOP_CATEOGRY:
                List<ShopCategoryItem> categoryItems = (List<ShopCategoryItem>) response;
                shop_category_list.add("Select Shop Category");
                for (ShopCategoryItem shopCateogryItem : categoryItems){
                    String label = shopCateogryItem.getLabel();
                    if (CollectionUtils.isNotEmpty(label)){
                        shop_category_list.add(label);
                    }
                }
                setSpinAdapter(shop_category_list,R.id.sp_shop_category);
                break;

            case AppConstants.TASKCODES.USER_REGISTER:
                UserSignUpItem userSignUpItem = (UserSignUpItem) response;
                if (userSignUpItem.getUserStatus().equals("ENABLED")){
                    finish();
                }
//                else if(userSignUpItem.getMessage().equals(getString(R.string.user_already_register))){
//                    showToast(getString(R.string.user_already_register));
//                }
                else {
                    showToast(getString(R.string.fail));
                }
                break;

        }
    }


}
