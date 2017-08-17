package com.example.kartikeya.ledger.utility;

import com.example.kartikeya.ledger.fragment.AccountInformation;
import com.example.kartikeya.ledger.model.AccountInfoItem;
import com.example.kartikeya.ledger.model.UserLoginItem;
import com.example.kartikeya.ledger.model.UserSignUpItem;
import com.example.kartikeya.ledger.model.response.AccountInfoList;
import com.example.kartikeya.ledger.model.response.AccountInfoResponse;
import com.example.kartikeya.ledger.model.response.ShopCategoryItem;
import com.example.kartikeya.ledger.model.response.UserLoginResponse;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import simplifii.framework.asyncmanager.FileParamObject;
import simplifii.framework.asyncmanager.HttpParamObject;
import simplifii.framework.utility.AppConstants;
import simplifii.framework.utility.JsonUtil;

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

    public static HttpParamObject isValidToken(String userToken) {
        HttpParamObject httpParamObject = new HttpParamObject();
        httpParamObject.setUrl(AppConstants.PAGE_URL.USER_TOKEN+userToken);
        return httpParamObject;

    }

    public static FileParamObject fileUploadToServer(File file) {
        FileParamObject fileParamObject = new FileParamObject(file,"UserImage","user.png");
        fileParamObject.setPostMethod();
        fileParamObject.setContentType("");
        fileParamObject.setUrl(AppConstants.PAGE_URL.FILE_UPLOAD);
        return fileParamObject;

    }

    public static FileParamObject userImage(File fileName,String name) {
        FileParamObject fileParamObject = new FileParamObject(fileName,"UserImage","user.png");
        fileParamObject.setPostMethod();
        fileParamObject.setContentType("");
        fileParamObject.setUrl(AppConstants.PAGE_URL.USER_IMAGE+name);
        return fileParamObject;
    }

    public static HttpParamObject saveUserInfo(AccountInfoItem accountInfoItem) {
        HttpParamObject httpParamObject = new HttpParamObject();
        httpParamObject.setJson(JsonUtil.toJson(accountInfoItem));
        httpParamObject.setUrl(AppConstants.PAGE_URL.ACCOUNT_INFO);
        httpParamObject.setPostMethod();
        httpParamObject.setJSONContentType();
        httpParamObject.setClassType(AccountInfoResponse.class);
        return httpParamObject;

    }

    public static HttpParamObject getUserAccountList(String ownerID) {
        HttpParamObject httpParamObject = new HttpParamObject();
        StringBuilder whereClause = new StringBuilder();
        whereClause.append( "ownerID='" ).append(ownerID).append( "'" );
        httpParamObject.addParameter("where",whereClause.toString());
        httpParamObject.setContentType("");
        httpParamObject.setUrl(AppConstants.PAGE_URL.ACCOUNT_INFO);
        return httpParamObject;

    }
}
