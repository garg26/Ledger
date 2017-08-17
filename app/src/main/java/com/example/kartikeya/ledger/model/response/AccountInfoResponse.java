package com.example.kartikeya.ledger.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kartikeya on 15/8/17.
 */

public class AccountInfoResponse {
    @SerializedName("fileName")
    @Expose
    private String fileName;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("gender")
    @Expose
    private Object gender;
    @SerializedName("accountName")
    @Expose
    private String accountName;
    @SerializedName("created")
    @Expose
    private Long created;
    @SerializedName("latitude")
    @Expose
    private Object latitude;
    @SerializedName("ownerId")
    @Expose
    private String ownerId;
    @SerializedName("__meta")
    @Expose
    private String meta;
    @SerializedName("accountPhoneNumber")
    @Expose
    private String accountPhoneNumber;
    @SerializedName("phone")
    @Expose
    private List<Object> phone = null;
    @SerializedName("longititude")
    @Expose
    private Object longititude;
    @SerializedName("___class")
    @Expose
    private String _class;
    @SerializedName("alternateNumber")
    @Expose
    private String alternateNumber;
    @SerializedName("updated")
    @Expose
    private Object updated;
    @SerializedName("objectId")
    @Expose
    private String objectId;
    @SerializedName("userID")
    @Expose
    private String userID;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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

    public Object getGender() {
        return gender;
    }

    public void setGender(Object gender) {
        this.gender = gender;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public Object getLatitude() {
        return latitude;
    }

    public void setLatitude(Object latitude) {
        this.latitude = latitude;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public String getAccountPhoneNumber() {
        return accountPhoneNumber;
    }

    public void setAccountPhoneNumber(String accountPhoneNumber) {
        this.accountPhoneNumber = accountPhoneNumber;
    }

    public List<Object> getPhone() {
        return phone;
    }

    public void setPhone(List<Object> phone) {
        this.phone = phone;
    }

    public Object getLongititude() {
        return longititude;
    }

    public void setLongititude(Object longititude) {
        this.longititude = longititude;
    }

    public String getClass_() {
        return _class;
    }

    public void setClass_(String _class) {
        this._class = _class;
    }

    public String getAlternateNumber() {
        return alternateNumber;
    }

    public void setAlternateNumber(String alternateNumber) {
        this.alternateNumber = alternateNumber;
    }

    public Object getUpdated() {
        return updated;
    }

    public void setUpdated(Object updated) {
        this.updated = updated;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

}
