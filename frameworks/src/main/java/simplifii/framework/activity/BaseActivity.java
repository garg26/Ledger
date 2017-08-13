package simplifii.framework.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import simplifii.framework.R;
import simplifii.framework.exceptionhandler.RestException;
import simplifii.framework.fragments.TaskFragment;
import simplifii.framework.utility.Util;
import simplifii.framework.widgets.CustomFontEditText;
import simplifii.framework.widgets.CustomTextInputLayout;


public class BaseActivity extends AppCompatActivity implements
        OnClickListener, TaskFragment.AsyncTaskListener {

    protected ActionBar bar;
    protected View actionBarView;
    ProgressDialog dialog;
    protected boolean isCartIconNeeded = false;
    private TaskFragment taskFragment;
    protected Toolbar toolbar;

    protected final String TAG = getTag();

    public static boolean isInternetDialogVisible = false;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    public String getActionTitle() {
        return getResources().getString(R.string.app_name);
    }

    protected String getTag() {
        return this.getClass().getSimpleName();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        // initActionBar();
        taskFragment = new TaskFragment();
        getSupportFragmentManager().beginTransaction().add(taskFragment, "task").commit();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        if (getIntent().getExtras() != null) {
            loadBundle(getIntent().getExtras());
        }
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);

    }

    public void clearBackStackAndStartNextActivity(Class<? extends Activity> activityClass) {
        Intent intent = new Intent(this, activityClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    protected void loadBundle(Bundle bundle) {
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onPostCreate(savedInstanceState);
//        Bundle bundle = getIntent().getExtras();
//        if (bundle != null) {
//            loadBundle(bundle);
//        }
    }

    protected void initWindow() {
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawableResource(R.color.white);
    }


    public void onActionItemClicked(View v) {
        Log.i(TAG, "onActionItemClicked");
        switch (v.getId()) {
            default:
                break;
        }

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            // do nothing
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void setFullScreenWindow() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams attrs = this.getWindow().getAttributes();
        attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        this.getWindow().setAttributes(attrs);
    }


    public void onBackIconClicked(View v) {
        super.onBackPressed();
    }

    public void showToast(String message) {
        if (!TextUtils.isEmpty(message))
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void showToast(int message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public AsyncTask executeTask(int taskCode, Object... params) {
        if (Util.isConnectingToInternet(this)) {
            taskFragment.createAsyncTaskManagerObject(taskCode)
                    .executeOnExecutor(TaskFragment.AsyncManager.THREAD_POOL_EXECUTOR, params);
        } else {
            Log.i("Base Activity", "Not Connected to internet");
            showToast("Internet not connected...");
//            if (isInternetDialogVisible) {
//                Util.createAlertDialog(this, "Please Connect to Internet",
//                        "Not Connected to internet", false, "OK", "Cancel",
//                        internetDialogListener).show();
//                isInternetDialogVisible = true;
//            }
            onInternetException();
        }
        return null;
    }


    protected void onInternetException() {
//        findViewById(R.id.frame_noInternet).setVisibility(View.VISIBLE);
    }

    public boolean isNetworkAvailable() {
        if (Util.isConnectingToInternet(this)) {
            return true;
        } else {
            Log.i("Base Activity", "Not Connected to internet");
            if (isInternetDialogVisible) {
                Util.createAlertDialog(this, "Please Connect to Internet",
                        "Not Connected to internet", false, "OK", "Cancel",
                        internetDialogListener).show();
                isInternetDialogVisible = true;
            }
            return false;
        }
    }

    public static Util.DialogListener internetDialogListener = new Util.DialogListener() {

        @Override
        public void onOKPressed(DialogInterface dialog, int which) {
            // TODO Auto-generated method stub
            isInternetDialogVisible = false;
        }

        @Override
        public void onCancelPressed(DialogInterface dialog, int which) {
            // TODO Auto-generated method stub
            isInternetDialogVisible = false;
        }
    };


    public void hideProgressBar() {
        Log.i(TAG + "Dialog", Thread.currentThread().getName());
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public void showProgressDialog() {
        if (dialog != null && dialog.isShowing()) {
        } else {
            dialog = new ProgressDialog(this);
            dialog.setMessage("Loading...");
            dialog.setCancelable(false);
            dialog.show();
        }
    }

    @Override
    public void onClick(View v) {

        // TODO Auto-generated method stub

    }

    public void setText(String text, int textViewId) {
        TextView view = (TextView) findViewById(textViewId);
        view.setText(text);
    }

    public void setText(String text, int textViewId, View v) {
        TextView view = (TextView) v.findViewById(textViewId);
        view.setText(text);
    }


    private int cartCount = 0;


    public void startNextActivity(Class<? extends Activity> activityClass) {
        Intent i = new Intent(this, activityClass);
        startActivity(i);
    }

    public void startNextActivityForResult(Bundle bundle,
                                           Class<? extends Activity> activityClass, int reqCode) {
        Intent i = new Intent(this, activityClass);
        if (null != bundle) {
            i.putExtras(bundle);
        }
        startActivityForResult(i, reqCode);
    }

    public void startNextActivity(Bundle bundle,
                                  Class<? extends Activity> activityClass) {

        Intent i = new Intent(this, activityClass);
        if (null != bundle) {
            i.putExtras(bundle);
        }
        startActivity(i);
    }


    protected void setOnClickListener(int... viewIds) {
        for (int i : viewIds) {
            findViewById(i).setOnClickListener(this);
        }
    }

    protected String getEditText(int editTextId) {
        return ((EditText) findViewById(editTextId)).getText().toString()
                .trim();
    }

    protected String getTextFromTV(int textViewId) {
        return ((TextView) findViewById(textViewId)).getText().toString()
                .trim();
    }

    @Override
    public void onPreExecute(int taskCode) {
        showProgressDialog();
    }

    @Override
    public void onPostExecute(Object response, int taskCode, Object... params) {
        hideProgressBar();
    }

    @Override
    public void onBackgroundError(RestException re, Exception e, int taskCode, Object... params) {
        hideProgressBar();
        if (re != null) {
            String message = re.getMessage();
            if (!TextUtils.isEmpty(message)) {
                try {
                    JSONArray jsonArray = new JSONArray(message);
                    if (jsonArray.length() > 0) {
                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                        if (jsonObject.has("msg")) {
                            String msg = jsonObject.getString("msg");
                            if (!TextUtils.isEmpty(msg)) {
                                message = msg;
                            }
                        }
                    }

                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }
            showToast(message);
        }
    }

    public void initToolBar(String title) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(getHomeIcon());
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

//        toolbar.setBackgroundColor(colorCode);
//        setStatusBarColor(colorCode);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
    }

    protected int getHomeIcon() {
        return 0;
    }

    protected void setStatusBarColor(int color) {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(color);
        }
    }

    public void onRetryClicked(View view) {
        if (Util.isConnectingToInternet(this)) {
            findViewById(R.id.frame_noInternet).setVisibility(View.GONE);
        }
    }

    public String getSpinnerText(int id){
        Spinner spinner = (Spinner) findViewById(id);
        String text = spinner.getSelectedItem().toString();
        return text;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onHomePressed();
                return true;
        }
        return false;
    }

    protected void onHomePressed() {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        Util.hideKeyboard(this);
    }

    protected void onServerError() {
//        FrameLayout errorLayout = (FrameLayout) findViewById(R.id.frame_noInternet);
//        if (errorLayout != null) {
//            errorLayout.setVisibility(View.VISIBLE);
//            ImageView errorImage = (ImageView) errorLayout.findViewById(R.id.iv_error);
//            TextView errorMsg = (TextView) errorLayout.findViewById(R.id.tv_errorMsg);
//            TextView errorInfo = (TextView) errorLayout.findViewById(R.id.tv_errorInfo);
//
//            errorImage.setImageResource(R.drawable.icon_server_error);
//            errorMsg.setText("SERVER ERROR");
//            errorInfo.setText("Oops! Something went wrong...");
//        }
    }

    protected void hideVisibility(int... viewIds) {
        for (int i : viewIds) {
            findViewById(i).setVisibility(View.GONE);
        }
    }

    protected void showVisibility(int... viewIds) {
        for (int i : viewIds) {
            findViewById(i).setVisibility(View.VISIBLE);
        }
    }

    protected int getResourceColor(int colorId) {
        return ContextCompat.getColor(this, colorId);
    }

    protected void setError(int editTextId, String error) {
        EditText editText = (EditText) findViewById(editTextId);
        editText.setError(error);
    }

    protected boolean isValidate(int... editTextIds) {
        for (int x = 0; x < editTextIds.length; x++) {
            int id = editTextIds[x];
            CustomFontEditText customFontEditText = (CustomFontEditText) findViewById(id);
            if (!customFontEditText.isValidate(this)) {
                return false;
            }
        }
        return true;
    }


    protected boolean isValidateTIL(int... textInputLayoutIds) {
        for (int x = 0; x < textInputLayoutIds.length; x++) {
            int id = textInputLayoutIds[x];
            CustomTextInputLayout customTextInputLayout = (CustomTextInputLayout) findViewById(id);
            customTextInputLayout.setError("");
            if (!customTextInputLayout.isValidate(this)) {
                return false;
            }
        }
        return true;
    }

    protected void setTilError(int tilId, int error) {
        TextInputLayout layout = (TextInputLayout) findViewById(tilId);
        layout.setError(getString(error));
    }

    protected void clearTilError(int... tilIds) {
        for (int i : tilIds) {
            TextInputLayout layout = (TextInputLayout) findViewById(i);
            layout.setError("");
        }
    }

    protected String getTilText(int id) {
        TextInputLayout viewId = (TextInputLayout) findViewById(id);
        return viewId.getEditText().getText().toString();
    }

    protected String getTextFromTil(int tilId) {
        TextInputLayout viewId = (TextInputLayout) findViewById(tilId);
        return viewId.getEditText().getText().toString();
    }

    protected void setEditText(int editTextId, String text) {
        EditText et = (EditText) findViewById(editTextId);
        et.setText(text);
    }

    protected void setEditText(String text, int editTextId) {
        EditText et = (EditText) findViewById(editTextId);
        et.setText(text);
    }

    protected ArrayAdapter setSpinAdapter(List<String> list, int spinnerID) {

        Spinner spinner = (Spinner) findViewById(spinnerID);
        ArrayAdapter dataAdapter = null;
        if (list != null) {
            dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(dataAdapter);
        }
        return dataAdapter;
    }

}
