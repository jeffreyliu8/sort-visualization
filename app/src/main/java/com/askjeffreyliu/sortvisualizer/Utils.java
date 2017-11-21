package com.askjeffreyliu.sortvisualizer;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import static com.askjeffreyliu.sortvisualizer.Constant.FPS_DEFAULT;

/**
 * Created by jeff on 11/21/17.
 */

public class Utils {

    public static void setFps(final Context context, int fps) {
        if (context == null)
            return;

        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(context);
        sharedPreferences.edit().putInt(Constant.USER_PREF_FPS, fps).apply();
    }

    public static int getFps(final Context context) {
        if (context == null)
            return FPS_DEFAULT;
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getInt(Constant.USER_PREF_FPS, FPS_DEFAULT);
    }

    public static void setIsPlaying(final Context context, boolean setIsPlaying) {
        if (context == null)
            return;

        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(context);
        sharedPreferences.edit().putBoolean(Constant.USER_PREF_IS_PLAYING, setIsPlaying).apply();
    }

    public static boolean getIsPlaying(final Context context) {
        if (context == null)
            return true;
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getBoolean(Constant.USER_PREF_IS_PLAYING, true);
    }
}
