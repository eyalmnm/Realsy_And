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
import android.widget.Toast;

import io.ideaction.raelsy.R;
import io.ideaction.raelsy.screens.fragments.MainMenuFragment;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;


public class LocationEnableActivity extends Activity {
    private static final String TAG = "LocationEnableActivity";
    // ********* Handling permissions section *********
    private static final int PERMISSION_REQUEST_CODE = 200;
    // UI Components
    private Button locationEnableContinueButton;
    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_enable);
        context = this;

        locationEnableContinueButton = findViewById(R.id.locationEnableContinueButton);
        locationEnableContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestPermission();
            }
        });

        // Check permissions
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkPermissions()) {
                continueAppLoading(true);
            }
        } else {
            continueAppLoading(true);
        }
    }

    private void continueAppLoading(boolean previouslyGranted) {
        int delay = previouslyGranted ? 100 : 10;
        new Handler(getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO Move to next screen
                Intent intent = new Intent(context, MainScreenActivity.class);
                overridePendingTransition(R.anim.activity_slide_in, R.anim.activity_slide_out);
                startActivity(intent);
                Log.d(TAG, "continueAppLoading");
            }
        }, delay);
    }

    @TargetApi(Build.VERSION_CODES.M)
    private boolean checkPermissions() {
        int fineLocationRes = ContextCompat.checkSelfPermission(getApplicationContext(), ACCESS_COARSE_LOCATION);
        int coarseLocationRes = ContextCompat.checkSelfPermission(getApplicationContext(), ACCESS_COARSE_LOCATION);
        return fineLocationRes == PackageManager.PERMISSION_GRANTED &&
                coarseLocationRes == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_COARSE_LOCATION, ACCESS_COARSE_LOCATION}, PERMISSION_REQUEST_CODE);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0) {
                    boolean fineLocationRes = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean coarseLocationRes = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (true == fineLocationRes && coarseLocationRes) {
                        continueAppLoading(false);
                    } else {
                        Toast.makeText(context, "Permission Denied, You cannot access the application.", Toast.LENGTH_LONG).show();
                        if (!hasPermissions(context, ACCESS_COARSE_LOCATION, ACCESS_COARSE_LOCATION)) {
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
