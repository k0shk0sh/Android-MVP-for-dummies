package com.access.fast.mvp.login;

/**
 * Created by Kosh on 10/17/2015. copyrights are reserved
 */
public interface LoginPresenter {

    /**
     * @param isError (to demonstrate different type of scenarios)
     */
    void onLoginClick(String email, String password, boolean isError);

    void onProcessStart();

    void onLoginSuccess(String email);

    void onLoginError(String error);
}
