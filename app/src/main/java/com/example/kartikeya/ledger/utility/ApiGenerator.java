package com.example.kartikeya.ledger.utility;

import com.example.kartikeya.ledger.model.UserLoginItem;
import com.example.kartikeya.ledger.model.UserSignUpItem;
import com.example.kartikeya.ledger.model.response.ShopCategoryItem;
import com.example.kartikeya.ledger.model.response.UserLoginResponse;

import simplifii.framework.asyncmanager.HttpParamObject;
import simplifii.framework.utility.AppConstants;
import simplifii.framework.utility.JsonUtil;

/**
 * Created by kartikeya on 11/8/17.
 */

public class ApiGenerator {
    public static HttpParamObject getShopCategoryList() {
        HttpParamObject httpParamObject = new HttpParamObject();
        httpParamObject.setUrl(AppConstants.PAGE_URL.GET_SHOP_CATEOGRY);
        httpParamObject.setClassType(ShopCategoryItem.class);
        return httpParamObject;
    }

    public static HttpParamObject setUserRegister(UserSignUpItem userSignUpItem) {
        HttpParamObject httpParamObject = getHttpRequest();
        httpParamObject.setJson(JsonUtil.toJson(userSignUpItem));
        httpParamObject.setUrl(AppConstants.PAGE_URL.USER_REGISTER);
        httpParamObject.setClassType(UserSignUpItem.class);

        return httpParamObject;
    }

    public static HttpParamObject setUserLogin(UserLoginItem userLoginItem) {
        HttpParamObject httpParamObject = getHttpRequest();
        httpParamObject.setJson(JsonUtil.toJson(userLoginItem));
        httpParamObject.setUrl(AppConstants.PAGE_URL.USER_LOGIN);
        httpParamObject.setClassType(UserLoginResponse.class);
        return httpParamObject;
    }

    public static HttpParamObject getHttpRequest(){
        HttpParamObject httpParamObject = new HttpParamObject();
        httpParamObject.setPostMethod();
        httpParamObject.setJSONContentType();
        return httpParamObject;
    }
}
