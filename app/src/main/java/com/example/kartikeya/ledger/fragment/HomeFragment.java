package com.example.kartikeya.ledger.fragment;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.kartikeya.ledger.R;
import com.example.kartikeya.ledger.activity.LoginActivity;
import com.example.kartikeya.ledger.adapter.BaseRecycleAdapter;
import com.example.kartikeya.ledger.holder.BaseHolder;
import com.example.kartikeya.ledger.model.response.AccountInfoList;
import com.example.kartikeya.ledger.model.response.AccountInfoResponse;
import com.example.kartikeya.ledger.utility.ApiGenerator;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import simplifii.framework.asyncmanager.HttpParamObject;
import simplifii.framework.fragments.BaseFragment;
import simplifii.framework.utility.AppConstants;
import simplifii.framework.utility.CollectionUtils;
import simplifii.framework.utility.JsonUtil;
import simplifii.framework.utility.Preferences;

/**
 * Created by kartikeya on 13/8/17.
 */

public class HomeFragment extends BaseFragment implements BaseRecycleAdapter.RecyclerAdapterInterface {

    private List<AccountInfoResponse> accountInfoResponses = new ArrayList<>();
    private RelativeLayout rl_create;
    private BaseRecycleAdapter baseRecycleAdapter;
    private RecyclerView recyclerView;
    private String userID;

    @Override
    public void initViews() {

        rl_create = (RelativeLayout) findView(R.id.rl_create);

        baseRecycleAdapter = new BaseRecycleAdapter(getActivity(), accountInfoResponses, this);
        recyclerView = (RecyclerView) findView(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(baseRecycleAdapter);

        userID = Preferences.getData(Preferences.LOGIN_RESPONSE, "");
        if (CollectionUtils.isEmpty(userID)){
            startNextActivity(LoginActivity.class);
        }

        getUserAccountList();

    }

    private void getUserAccountList() {
        HttpParamObject httpParamObject = ApiGenerator.getUserAccountList(userID);
        executeTask(AppConstants.TASKCODES.USER_ACCOUNT, httpParamObject);
    }

    @Override
    public void onPostExecute(Object response, int taskCode, Object... params) {
        super.onPostExecute(response, taskCode, params);
        switch (taskCode) {
            case AppConstants.TASKCODES.USER_ACCOUNT:

                try {
                    JSONArray jsonArray = new JSONArray(response.toString());
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        AccountInfoResponse infoResponse = (AccountInfoResponse) JsonUtil.parseJson(jsonObject.toString(), AccountInfoResponse.class);
                        accountInfoResponses.add(infoResponse);
                    }
                    rl_create.setVisibility(View.GONE);
                    baseRecycleAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;

        }
    }

    @Override
    public int getViewID() {
        return R.layout.fragment_home;
    }


    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_home_item, parent, false);
        HomeHolder homeHolder = new HomeHolder(itemView);
        return homeHolder;
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
        HomeHolder homeHolder = (HomeHolder) holder;
        AccountInfoResponse accountInfoResponse = accountInfoResponses.get(position);
        String title = accountInfoResponse.getAccountName();
        String subTitle = accountInfoResponse.getAccountPhoneNumber();
        homeHolder.tv_user_name.setText(title);
        homeHolder.iv_user_subTitle.setText(subTitle);
        if (CollectionUtils.isNotEmpty(accountInfoResponse.getFileName())) {
            Picasso.with(getContext()).load(accountInfoResponse.getFileName()).into(((HomeHolder) holder).iv_user_image);
        }
    }

    private class HomeHolder extends BaseHolder {

        TextView tv_user_name, iv_user_subTitle;
        ImageView iv_user_image;

        HomeHolder(View itemView) {
            super(itemView);

            iv_user_image = (ImageView) findView(R.id.iv_user_image);
            tv_user_name = (TextView) findView(R.id.tv_user_name);
            iv_user_subTitle = (TextView) findView(R.id.iv_user_subTitle);


        }

        public View findView(int id) {
            return itemView.findViewById(id);
        }
    }
}
