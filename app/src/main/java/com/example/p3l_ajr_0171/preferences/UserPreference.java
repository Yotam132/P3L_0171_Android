package com.example.p3l_ajr_0171.preferences;

import android.content.Context;
import android.content.SharedPreferences;


public class UserPreference {
    SharedPreferences sharedPreference;
    SharedPreferences.Editor editor;
    Context con;

    public static final String IS_LOGIN = "IsLogin";
    public static final String KEY_ID = "0";
    public static final String KEY_NOLOGIN = "-";

    public UserPreference(Context C)
    {
        con = C;
        sharedPreference = C.getSharedPreferences("userPreference", Context.MODE_PRIVATE);
        editor = sharedPreference.edit();
    }


    public void SetLogin(int id, String no)
    {
        editor.putInt(KEY_ID, id);
        editor.putString(KEY_NOLOGIN, no);
        editor.putBoolean(IS_LOGIN, true);

        editor.commit();
    }

    public int GetUserID()
    {
        return sharedPreference.getInt(KEY_ID, -1);
    }

    public String getKeyNologin() {
        return sharedPreference.getString(KEY_NOLOGIN, "0");
    }

    public boolean CheckLogin()
    {
        return sharedPreference.getBoolean(IS_LOGIN, false);
    }

    public void Logout()
    {
        editor.clear();
        editor.commit();
    }
}
