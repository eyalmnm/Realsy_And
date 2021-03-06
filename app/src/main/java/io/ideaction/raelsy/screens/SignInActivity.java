package io.ideaction.raelsy.screens;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import io.ideaction.raelsy.R;

public class SignInActivity extends AppCompatActivity {
    private static final String TAG = "SignInActivity";

    // UI Components
    private EditText signInInputEmail;
    private EditText signInInputPassword;
    private Button signInSubmitButton;
    private TextView signInRegistarionMessage;

    private Toolbar topToolbar;

    private Context context;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        context = this;

        // Init UI components
        signInInputEmail = findViewById(R.id.signInInputEmail);
        signInInputPassword = findViewById(R.id.signInInputPassword);

        signInSubmitButton = findViewById(R.id.signInSubmitButton);
        signInRegistarionMessage = findViewById(R.id.signInRegistarionMessage);

        // Tool Bar initialization
        topToolbar = findViewById(R.id.toolbar);
        topToolbar.setTitle("");
        TextView toolbar_title = topToolbar.findViewById(R.id.toolbar_title);
        toolbar_title.setText(R.string.sign_in_activity_label);
        setSupportActionBar(topToolbar);

        // Clicks actions
        signInInputPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEND) {
                    signInSubmitButton.performClick();
                    return true;
                }
                return false;
            }
        });

        signInSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Implement this Click
                Log.d(TAG, "registerByMailSubmitButton pressed");
                Intent intent = new Intent(context, LocationEnableActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_slide_in, R.anim.activity_slide_out);
                finish();
            }
        });

        signInRegistarionMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Implement this click
                Intent intent = new Intent(context, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_slide_in, R.anim.activity_slide_out);
                finish();
            }
        });

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE |
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    // Add Menu to screen
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.general_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_cancel:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
