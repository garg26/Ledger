package com.example.kartikeya.ledger.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kartikeya on 13/8/17.
 */

public class UserSignUpItem {

    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("shopName")
    @Expose
    private String shopName;
    @SerializedName("shopCategory")
    @Expose
    private String shopCategory;
//    @SerializedName("code")
//    @Expose
//    private Integer code;
//    @SerializedName("message")
//    @Expose
//    private String message;

    @SerializedName("userStatus")
    @Expose
    private String userStatus;



    public UserSignUpItem(String shopName, String shopCategory, String phoneNumber, String password) {
        this.shopName=shopName;
        this.shopCategory=shopCategory;
        this.phone=phoneNumber;
        this.password=password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopCategory() {
        return shopCategory;
    }

    public void setShopCategory(String shopCategory) {
        this.shopCategory = shopCategory;
    }

//    public Integer getCode() {
//        return code;
//    }
//
//    public void setCode(Integer code) {
//        this.code = code;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

}




