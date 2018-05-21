package io.ideaction.raelsy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import io.ideaction.raelsy.screens.IntroActivity;
import io.ideaction.raelsy.screens.LoginActivity;
import io.ideaction.raelsy.utils.PreferencesUtils;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private static final int SPLASH_TIME = 3000; // 3 Sec

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        new Handler(getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean introShown = PreferencesUtils.getInstance(context).getIntroView();
                Intent intent = null;
                if (true == introShown) {
                    intent = new Intent(context, LoginActivity.class);
                } else {
                    intent = new Intent(context, IntroActivity.class);
                }
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_slide_in, R.anim.activity_slide_out);
                finish();
            }
        }, SPLASH_TIME);
    }
}
