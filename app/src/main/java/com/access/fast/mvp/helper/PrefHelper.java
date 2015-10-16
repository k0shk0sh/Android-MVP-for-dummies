package com.access.fast.mvp.helper;

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * Created by Kosh on 10/17/2015. copyrights are reserved
 */
public class PrefHelper {

    private final static String USER_EMAIL = "user_email";

    public static void setUserEmail(Context context, String email) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(USER_EMAIL, email)
                .apply();
    }

    public static String getUserEmail(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(USER_EMAIL, null);
    }
}
