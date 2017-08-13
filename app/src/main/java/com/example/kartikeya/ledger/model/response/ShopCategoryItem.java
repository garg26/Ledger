package com.example.kartikeya.ledger.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import simplifii.framework.utility.JsonUtil;

/**
 * Created by kartikeya on 11/8/17.
 */

public class ShopCategoryItem {
    @SerializedName("___class")
    @Expose
    private String _class;
    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("value")
    @Expose
    private Integer value;
    @SerializedName("__meta")
    @Expose
    private String meta;

    public String getClass_() {
        return _class;
    }

    public void setClass_(String _class) {
        this._class = _class;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public static List<ShopCategoryItem> parseJson(String json){
        List<ShopCategoryItem> shopCateogryItems = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                ShopCategoryItem cateogryItem = (ShopCategoryItem) JsonUtil.parseJson(jsonObject.toString(),ShopCategoryItem.class);
                shopCateogryItems.add(cateogryItem);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return shopCateogryItems;
    }
}
