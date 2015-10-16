package com.access.fast.mvp.login;

import android.text.TextUtils;
import android.util.Patterns;

import com.access.fast.mvp.R;

/**
 * Created by Kosh on 10/17/2015. copyrights are reserved
 */
public class LoginPresenterImpl implements LoginPresenter {

    private LoginView loginView;

    public LoginPresenterImpl(LoginView loginView) {this.loginView = loginView;}

    @Override
    public void onLoginClick(String email, String password, boolean isError) {
        if (TextUtils.isEmpty(email)) {
            loginView.onEmailError(loginView.getContext().getString(R.string.error_field_required));
            return;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            loginView.onEmailError(loginView.getContext().getString(R.string.error_invalid_email));
            return;
        } else {
            loginView.onEmailError(null);
        }
        if (TextUtils.isEmpty(password)) {
            loginView.onPasswordError(loginView.getContext().getString(R.string.error_field_required));
            return;
        } else {
            loginView.onPasswordError(null);
        }
        new LoginInteractor(email, password, this).execute(isError);
    }

    @Override
    public void onProcessStart() {
        loginView.onShowProgress();
    }

    @Override
    public void onLoginSuccess(String email) {
        loginView.onHideProgress();
        loginView.onSuccess(email);
    }

    @Override
    public void onLoginError(String error) {
//        loginView.onEmailError(null); it's preferably to be called, but then i don't want to make things to be harder.
//        loginView.onPasswordError(null); it's preferably to be called, but then i don't want to make things to be harder.
        loginView.onHideProgress();
        loginView.onError(error);
    }
}
