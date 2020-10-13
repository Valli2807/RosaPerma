package com.sample.rosaperma;

import android.content.Context;

public class SharedPreferences {

    /**
     * Gets the user name saved in SharedPreferences.
     *
     * @param context               REQUIRED: Android application context.
     * @return                      user name saved in SharedPreferences.
     */
    public static String getSavedUserName(Context context) {
        android.content.SharedPreferences savedValues = context.getSharedPreferences(Constants.SHARED_PREFERENCE, Context.MODE_PRIVATE);
        return savedValues.getString(Constants.PREFERENCE_USER_NAME, "");
    }

    /**
     * Gets the user e-mail saved in SharedPreferences.
     *
     * @param context               REQUIRED: Android application context.
     * @return                      user e-mail saved in SharedPreferences.
     */
    public static String getSavedUserEmail(Context context) {
        android.content.SharedPreferences savedValues = context.getSharedPreferences(Constants.SHARED_PREFERENCE, Context.MODE_PRIVATE);
        return savedValues.getString(Constants.PREFERENCE_USER_EMAIL, "");
    }

    public static String getSavedMobileNumber(Context context) {
        android.content.SharedPreferences savedValues = context.getSharedPreferences(Constants.SHARED_PREFERENCE, Context.MODE_PRIVATE);
        return savedValues.getString(Constants.PREFERENCE_MOBILE_NO, "");
    }

    public static boolean getLogginStatus(Context context) {
        android.content.SharedPreferences savedValues = context.getSharedPreferences(Constants.SHARED_PREFERENCE, Context.MODE_PRIVATE);
        boolean b =  savedValues.getBoolean(Constants.IS_USER_LOGGED_IN, false);
        return savedValues.getBoolean(Constants.IS_USER_LOGGED_IN, false);
    }
}
