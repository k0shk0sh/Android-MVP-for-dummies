package com.access.fast.mvp.login;

import android.content.Context;

/**
 * Created by Kosh on 10/16/2015. copyrights are reserved
 */
public interface LoginView {

    Context getContext();

    void onEmailError(String msg);

    void onPasswordError(String msg);

    void onShowProgress();

    void onHideProgress();

    void onSuccess(String email);

    void onError(String errorMsg);

}
