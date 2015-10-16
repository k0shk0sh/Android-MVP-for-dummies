package com.access.fast.mvp.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.access.fast.mvp.MainActivity;
import com.access.fast.mvp.R;
import com.access.fast.mvp.helper.PrefHelper;

import java.util.Random;

/**
 * Created by Kosh on 10/16/2015. copyrights are reserved
 */
public class LoginActivity extends AppCompatActivity implements LoginView {

    private ProgressDialog progressDialog;
    private AlertDialog alertDialog;
    private LoginPresenterImpl loginPresenter;

    private TextInputLayout email;
    private TextInputLayout password;
    private Button login;

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onEmailError(String msg) {
        email.setError(msg);
    }

    @Override
    public void onPasswordError(String msg) {
        password.setError(msg);
    }

    @Override
    public void onShowProgress() {
        if (getProgress().isShowing()) return;
        getProgress().show();
    }

    @Override
    public void onHideProgress() {
        if (getProgress().isShowing()) getProgress().dismiss();
    }

    @Override
    public void onSuccess(String email) {
        Toast.makeText(LoginActivity.this, "Successfully Logged-in as " + email, Toast.LENGTH_LONG).show();
        PrefHelper.setUserEmail(this, email);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void onError(String errorMsg) {
        getAlertDialog().setMessage(errorMsg);
        if (!getAlertDialog().isShowing()) getAlertDialog().show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = (TextInputLayout) findViewById(R.id.email);
        password = (TextInputLayout) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                getLoginPresenter().onLoginClick(email.getEditText().getText().toString(),
                        password.getEditText().getText().toString(), random.nextInt(2) == 1);
            }
        });
    }

    /**
     * @return ProgressDialog that initialized only one time.
     */
    private ProgressDialog getProgress() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Loading, Please Wait...");
        }
        return progressDialog;
    }

    /**
     * @return AlertDialog that initialized only one time.
     */
    private AlertDialog getAlertDialog() {
        if (alertDialog == null) {
            alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Close", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    alertDialog.dismiss();
                }
            });
        }
        return alertDialog;
    }

    public LoginPresenterImpl getLoginPresenter() {
        if (loginPresenter == null) loginPresenter = new LoginPresenterImpl(this);
        return loginPresenter;
    }
}
