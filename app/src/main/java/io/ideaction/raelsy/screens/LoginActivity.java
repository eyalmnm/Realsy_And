package io.ideaction.raelsy.screens;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

import io.ideaction.raelsy.R;
import io.ideaction.raelsy.utils.PreferencesUtils;
import io.ideaction.raelsy.utils.StringUtils;

import static android.Manifest.permission.INTERNET;
import static android.os.Build.VERSION;
import static android.os.Build.VERSION_CODES;

public class LoginActivity extends Activity {
    private static final String TAG = "LoginActivity";

    // UI Components
    private LoginButton faceBookLoginButton;
    private Button emailSignUpButton;
    private TextView loginMessageTextView;

    // Facebook Helper
    private CallbackManager callbackManager;
    private AccessToken accessToken;
    private AccessTokenTracker accessTokenTracker;
    private Context context;

    // Facebook Callback
    private FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            showToast("User ID: "
                    + loginResult.getAccessToken().getUserId()
                    + "\n" +
                    "Auth Token: "
                    + loginResult.getAccessToken().getToken());
            PreferencesUtils.getInstance(context).setFbToken(loginResult.getAccessToken().getToken());
            moveToNextScreen();
        }

        @Override
        public void onCancel() {
            showToast("Login attempt canceled.");
            finish();
        }

        @Override
        public void onError(FacebookException e) {
            showToast("Login attempt failed.");
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // FaceBook Components initialization.
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.activity_login);

        faceBookLoginButton = findViewById(R.id.faceBookLoginButton);
        emailSignUpButton = findViewById(R.id.emailSignUpButton);
        loginMessageTextView = findViewById(R.id.loginMessageTextView);

        context = this;
        // Check permissions
        if (VERSION.SDK_INT >= VERSION_CODES.M) {
            if (!checkPermissions()) {
                requestPermission();
            } else {
                continueAppLoading();
            }
        } else {
            continueAppLoading();
        }
    }

    // Continue loading the UI Components
    private void continueAppLoading() {
        // Facebook Login
        checkFbLoginStatus();
        faceBookLoginButton = (LoginButton) findViewById(R.id.faceBookLoginButton);
        faceBookLoginButton.setReadPermissions(Arrays.asList("email"));
        faceBookLoginButton.registerCallback(callbackManager, callback);

        emailSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RegisterByMailActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_slide_in, R.anim.activity_slide_out);
            }
        });

        loginMessageTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SignInActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_slide_in, R.anim.activity_slide_out);
            }
        });
    }

    private void checkFbLoginStatus() {
        String fbToken = PreferencesUtils.getInstance(context).getFbToken();

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                // Set the access token using
                // currentAccessToken when it's loaded or set.
                if (null != oldAccessToken && null != currentAccessToken) {
                    try {
                        PreferencesUtils.getInstance(context).setFbToken(currentAccessToken.getToken());
                    } catch (Exception e) {
                        Log.e(TAG, "onCurrentAccessTokenChanged", e);
                    }
                }
            }
        };
        // If the access token is available already assign it.
        accessToken = AccessToken.getCurrentAccessToken();
        if (null != accessToken && false == StringUtils.isNullOrEmpty(accessToken.getToken()) && false == StringUtils.isNullOrEmpty(fbToken)) {
            moveToNextScreen();
            return;
        }

    }

    private void moveToNextScreen() {
        // TODO Implement this Activity
//        Intent intent = new Intent(context, LocationEnableActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(intent);
//        PreferencesUtils.getInstance(context).setIntroView();
//        overridePendingTransition(R.anim.activity_slide_in, R.anim.activity_slide_out);
//        finish();
    }

    private void showToast(final String msg) {
        new Handler(getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    // ********* Handling permissions section *********
    private static final int PERMISSION_REQUEST_CODE = 200;

    @TargetApi(VERSION_CODES.M)
    private boolean checkPermissions() {
        int internetRes = ContextCompat.checkSelfPermission(getApplicationContext(), INTERNET);
        return internetRes == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{INTERNET}, PERMISSION_REQUEST_CODE);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0) {
                    boolean internetRes = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (true == internetRes) {
                        continueAppLoading();
                    } else {
                        Toast.makeText(context, "Permission Denied, You cannot access the application.", Toast.LENGTH_LONG).show();
                        if (!hasPermissions(context, INTERNET)) {
                            Toast.makeText(context, "You need to allow access to all the permissions", Toast.LENGTH_LONG).show();
                            requestPermission();
                        }
                    }
                }
        }
    }

    public boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }
    // ********* Handling permissions section *********
}
