package com.access.fast.mvp.login;

import android.os.AsyncTask;

/**
 * Created by Kosh on 10/17/2015. copyrights are reserved
 */
public class LoginInteractor extends AsyncTask<Boolean, String, Boolean> {

    private String email;
    private String password;
    private LoginPresenter loginPresenter;

    public LoginInteractor(String email, String password, LoginPresenter loginPresenter) {
        this.email = email;
        this.password = password;
        this.loginPresenter = loginPresenter;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        loginPresenter.onProcessStart();
    }

    @Override
    protected void onPostExecute(Boolean response) {
        super.onPostExecute(response);
        if (response) {
            loginPresenter.onLoginSuccess(email);
        } else {
            loginPresenter.onLoginError("Oops incorrect account details.");
        }
    }

    @Override
    protected Boolean doInBackground(Boolean... params) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return params[0];
    }
}
