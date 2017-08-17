package com.example.kartikeya.ledger.enums;

import com.example.kartikeya.ledger.R;
import com.example.kartikeya.ledger.model.DrawerItem;

import java.util.ArrayList;
import java.util.List;

import simplifii.framework.utility.AppConstants;

/**
 * Created by kartikeya on 13/8/17.
 */

public enum DrawerData {

    ACCOUNT(AppConstants.ACCOUNT.ACCOUNT, AppConstants.ACCOUNT.ACCOUNT_ID, R.mipmap.account_passkey),
    TRANSACTION(AppConstants.CREDITDEBIT.CREDITDEBIT, AppConstants.CREDITDEBIT.CREDITDEBIT_ID, R.mipmap.transaction),
    HISTORY(AppConstants.HISTORY.HISTORY, AppConstants.HISTORY.HISTORY_ID, R.mipmap.history),
    SETTINGS(AppConstants.SETTINGS.SETTINGS, AppConstants.SETTINGS.SETTINGS_ID, R.mipmap.settings);


    private String name;
    private int nameID, imageID;

    DrawerData(String name, int nameID, int imageID) {
        this.name = name;
        this.nameID = nameID;
        this.imageID = imageID;
    }

    public String getName() {
        return name;
    }


    public int getNameID() {
        return nameID;
    }


    public int getImageID() {
        return imageID;
    }

    public static List<DrawerItem> getAllDrawerItems() {
        List<DrawerItem> drawerItems = new ArrayList<>();
        for (DrawerData drawerData : values()) {
            DrawerItem drawerItem = new DrawerItem();
            drawerItem.setName(drawerData.getName());
            drawerItem.setNameID(drawerData.getNameID());
            drawerItem.setImageID(drawerData.getImageID());
            drawerItems.add(drawerItem);
        }
        return drawerItems;
    }
}
