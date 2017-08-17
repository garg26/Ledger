package com.example.kartikeya.ledger.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kartikeya on 15/8/17.
 */

public class AccountInfoItem {
    @SerializedName("fileName")
    @Expose
    private String fileName;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("accountName")
    @Expose
    private String accountName;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("accountPhoneNumber")
    @Expose
    private String accountPhoneNumber;
    @SerializedName("longititude")
    @Expose
    private Double longititude;
    @SerializedName("alternateNumber")
    @Expose
    private String alternateNumber;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    @SerializedName("userID")
    @Expose

    private String userID;


    public AccountInfoItem(String userID,String fileName,String accountName, String account_phone_number, String et_alternate_phone_number, String gender, String address, double latitude, double longitude) {
        this.userID=userID;
        this.fileName=fileName;
        this.accountName = accountName;
        this.accountPhoneNumber=account_phone_number;
        this.alternateNumber=et_alternate_phone_number;
        this.gender=gender;
        this.address=address;
        this.latitude=latitude;
        this.latitude=longitude;
    }



    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getAccountPhoneNumber() {
        return accountPhoneNumber;
    }

    public void setAccountPhoneNumber(String accountPhoneNumber) {
        this.accountPhoneNumber = accountPhoneNumber;
    }

    public Double getLongititude() {
        return longititude;
    }

    public void setLongititude(Double longititude) {
        this.longititude = longititude;
    }

    public String getAlternateNumber() {
        return alternateNumber;
    }

    public void setAlternateNumber(String alternateNumber) {
        this.alternateNumber = alternateNumber;
    }

}

