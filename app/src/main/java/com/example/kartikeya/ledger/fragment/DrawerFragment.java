package com.example.kartikeya.ledger.fragment;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kartikeya.ledger.R;
import com.example.kartikeya.ledger.activity.HomeActivity;
import com.example.kartikeya.ledger.enums.DrawerData;
import com.example.kartikeya.ledger.model.DrawerItem;
import com.example.kartikeya.ledger.utility.ApiGenerator;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import simplifii.framework.ListAdapters.CustomListAdapter;
import simplifii.framework.ListAdapters.CustomListAdapterInterface;
import simplifii.framework.asyncmanager.FileParamObject;
import simplifii.framework.asyncmanager.HttpParamObject;
import simplifii.framework.fragments.BaseFragment;
import simplifii.framework.fragments.MediaFragment;
import simplifii.framework.utility.AppConstants;
import simplifii.framework.utility.CollectionUtils;
import simplifii.framework.utility.Util;

/**
 * Created by kartikeya on 13/8/17.
 */

public class DrawerFragment extends BaseFragment implements CustomListAdapterInterface {
    private HomeActivity homeActivity;
    private DrawerLayout drawerLayout;
    private List<DrawerItem> allDrawerItems = new ArrayList<>();
    private MediaFragment mediaFragment;
    private CircleImageView iv_profile_pic;

    @Override
    public void initViews() {
        View header_drawer = LayoutInflater.from(getActivity()).inflate(R.layout.header_drawer, null);
        View footer_drawer = LayoutInflater.from(getActivity()).inflate(R.layout.footer_drawer, null);
        ListView lv = (ListView) findView(R.id.lv_side_drawer);
        iv_profile_pic = (CircleImageView)header_drawer.findViewById(R.id.iv_profile_pic);

        allDrawerItems = DrawerData.getAllDrawerItems();
        CustomListAdapter listAdapter = new CustomListAdapter(getActivity(), R.layout.row_item_drawer, allDrawerItems, this);
        lv.addHeaderView(header_drawer);
        lv.addFooterView(footer_drawer);
        lv.setAdapter(listAdapter);

        mediaFragment = new MediaFragment();
        getActivity().getSupportFragmentManager().beginTransaction().add(mediaFragment, "Profile image").commit();


        setOnClickListener(R.id.iv_profile_pic);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_profile_pic:
                askPermission();
                break;
        }
    }

    private void askPermission() {
        new TedPermission(getActivity())
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
                    String filePath = Util.getFilePath(getActivity(), uri);

                    File fileName = new File(filePath);
                    if (fileName.exists()){
                        fileUploadToServer(fileName);
                        Picasso.with(getActivity()).load(fileName).into(iv_profile_pic);

                    }

                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void setBitmap(Bitmap bitmap) {

            }
        }, getActivity());
    }

    private void fileUploadToServer(File file) {
        FileParamObject fileParamObject = ApiGenerator.fileUploadToServer(file);
        executeTask(AppConstants.TASKCODES.FILE_UPLOAD,fileParamObject);
    }

    @Override
    public void onPostExecute(Object response, int taskCode, Object... params) {
        super.onPostExecute(response, taskCode, params);
        if (response==null){
            return;
        }
        switch (taskCode){
            case AppConstants.TASKCODES.FILE_UPLOAD:
                try {
                    JSONObject jsonObject = new JSONObject(response.toString());
                    String fileURL = jsonObject.get("fileURL").toString();
                    if (CollectionUtils.isNotEmpty(fileURL)){
                        showToast("File Uploaded Successfully");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    public int getViewID() {
        return R.layout.fragment_drawer;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent, int resourceID, LayoutInflater inflater) {

        final Holder holder;
        if (convertView == null) {
            convertView = inflater.inflate(resourceID, parent, false);
            holder = new Holder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        final DrawerItem drawerItem = allDrawerItems.get(position);
        holder.tv_drawer.setText(drawerItem.getName());
        holder.iv_drawer.setImageResource(drawerItem.getImageID());
        return convertView;
    }

    class Holder {
        TextView tv_drawer;
        ImageView iv_drawer;


        Holder(View view) {
            tv_drawer = (TextView) view.findViewById(R.id.tv_drawer_item);
            iv_drawer = (ImageView) view.findViewById(R.id.iv_drawer_image);
        }
    }

    public static DrawerFragment getInstance(HomeActivity homeActivity, DrawerLayout drawerLayout) {
        DrawerFragment drawerFragment = new DrawerFragment();
        drawerFragment.homeActivity = homeActivity;
        drawerFragment.drawerLayout = drawerLayout;
        return drawerFragment;

    }
}
