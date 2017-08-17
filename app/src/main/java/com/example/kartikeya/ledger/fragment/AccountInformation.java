package com.example.kartikeya.ledger.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kartikeya.ledger.R;
import com.example.kartikeya.ledger.activity.HomeActivity;
import com.example.kartikeya.ledger.activity.LoginActivity;
import com.example.kartikeya.ledger.model.AccountInfoItem;
import com.example.kartikeya.ledger.model.response.AccountInfoResponse;
import com.example.kartikeya.ledger.utility.ApiGenerator;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;

import simplifii.framework.activity.BaseActivity;
import simplifii.framework.asyncmanager.FileParamObject;
import simplifii.framework.asyncmanager.HttpParamObject;
import simplifii.framework.fragments.MediaFragment;
import simplifii.framework.utility.AppConstants;
import simplifii.framework.utility.CollectionUtils;
import simplifii.framework.utility.Preferences;
import simplifii.framework.utility.Util;

/**
 * Created by kartikeya on 14/8/17.
 */

public class AccountInformation extends BaseActivity {

    private TextView tv_male, tv_female;
    private MediaFragment mediaFragment;
    private ImageView iv_profile_pic;
    private String gender;
    private double latitude = 0;
    private double longitude = 0;
    private String et_alternate_phone_number;
    private String fileName;
    private String ownerID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_information);

        tv_male = (TextView) findViewById(R.id.tv_male);
        tv_female = (TextView) findViewById(R.id.tv_female);
        iv_profile_pic = (ImageView) findViewById(R.id.iv_profile_pic);

        ownerID = Preferences.getData(Preferences.LOGIN_RESPONSE, "");
        if (CollectionUtils.isEmpty(ownerID)){
            startNextActivity(LoginActivity.class);
        }


        mediaFragment = new MediaFragment();
        getSupportFragmentManager().beginTransaction().add(mediaFragment, "Profile image").commit();


        setOnClickListener(R.id.tv_male, R.id.tv_female, R.id.et_address, R.id.iv_edit_image, R.id.btn_save);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_male:
                gender = "male";
                tv_female.setTextColor(ContextCompat.getColor(AccountInformation.this, R.color.gray));
                tv_male.setTextColor(Color.BLACK);
                break;
            case R.id.tv_female:
                gender = "female";
                tv_male.setTextColor(ContextCompat.getColor(AccountInformation.this, R.color.gray));
                tv_female.setTextColor(Color.BLACK);
                break;
            case R.id.et_address:
                showPlacePicker();
                break;
            case R.id.iv_edit_image:
                setProfileImage();
                break;
            case R.id.btn_save:
                saveAccountInformation();
                break;
        }
    }

    private void saveAccountInformation() {

        if (CollectionUtils.isNotEmpty(fileName)) {
            isValid(fileName);

        }else{
            isValid("");
        }

    }

    private void isValid(String fileName) {
        String accountName = getEditText(R.id.et_account_name);
        if (CollectionUtils.isNotEmpty(accountName)) {
            String account_phone_number = getEditText(R.id.et_account_phone_number);
            if (CollectionUtils.isNotEmpty(account_phone_number)) {
                et_alternate_phone_number = getEditText(R.id.et_alternate_phone_number);
                if (CollectionUtils.isNotEmpty(et_alternate_phone_number)) {

                    isValidWithAlternateNumber(fileName,et_alternate_phone_number,account_phone_number,accountName);

                }else{
                    isValidWithAlternateNumber(fileName,"", account_phone_number,accountName);
                }

            } else {
                showToast("Account Phone Number is empty");
            }
        } else {
            showToast("Account Name is empty");
        }
    }

    private void isValidWithAlternateNumber(String fileName,String et_alternate_phone_number, String account_phone_number, String accountName) {
        if (CollectionUtils.isNotEmpty(gender)) {
            String address = getEditText(R.id.et_address);
            if (CollectionUtils.isNotEmpty(address)) {
                AccountInfoItem accountInfoItem = new AccountInfoItem(ownerID,fileName, accountName, account_phone_number, et_alternate_phone_number, gender, address, latitude, longitude);
                HttpParamObject httpParamObject = ApiGenerator.saveUserInfo(accountInfoItem);
                executeTask(AppConstants.TASKCODES.USER_ACCOUNT, httpParamObject);
            } else {
                showToast("Address is empty");
            }
        } else {
            showToast("Gender is empty");
        }
    }

    private void setProfileImage() {
        new TedPermission(this)
                .setPermissions(android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .setPermissionListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        onPermissionVerify();
                    }

                    @Override
                    public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                        Log.e("Denied", "denied");
                    }
                }).check();

    }

    private void onPermissionVerify() {
        mediaFragment.getImage(new MediaFragment.MediaListener() {
            @Override
            public void setUri(Uri uri, String MediaType) {
                try {
                    String filePath = Util.getFilePath(getApplicationContext(), uri);

                    File fileName = new File(filePath);
                    if (fileName.exists()) {
                        fileUploadToServer(fileName);


                    }

                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void setBitmap(Bitmap bitmap) {

            }
        }, this);
    }

    private void fileUploadToServer(File fileName) {
        String name = fileName.getName();
        if (CollectionUtils.isNotEmpty(name)) {
            FileParamObject fileParamObject = ApiGenerator.userImage(fileName, name);
            executeTask(AppConstants.TASKCODES.FILE_UPLOAD, fileParamObject);
        }
    }

    private void showPlacePicker() {
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try {
            startActivityForResult(builder.build(this), AppConstants.PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case AppConstants.PLACE_PICKER_REQUEST:
                Place place = PlacePicker.getPlace(this, data);
                LatLng latLng = place.getLatLng();
                latitude = latLng.latitude;
                longitude = latLng.longitude;
                CharSequence address = place.getAddress();
                if (CollectionUtils.isNotEmpty(address.toString())) {
                    setEditText(R.id.et_address, address.toString());
                }
                break;



        }
    }

    @Override
    public void onPostExecute(Object response, int taskCode, Object... params) {
        super.onPostExecute(response, taskCode, params);
        if (response == null) {
            return;
        }
        switch (taskCode) {
            case AppConstants.TASKCODES.FILE_UPLOAD:
                try {
                    JSONObject jsonObject = new JSONObject(response.toString());
                     fileName = jsonObject.get("fileURL").toString();
                    if (CollectionUtils.isNotEmpty(fileName)) {
                        Picasso.with(getApplicationContext()).load(fileName).into(iv_profile_pic);
                        showToast("File Uploaded Successfully");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case AppConstants.TASKCODES.USER_ACCOUNT:
                AccountInfoResponse accountInfoResponse = (AccountInfoResponse) response;
                String objectId = accountInfoResponse.getObjectId();
                if (CollectionUtils.isNotEmpty(objectId)){
                    showToast("Account Created Successfully");
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
