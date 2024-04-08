package me.s1204.benesse.touch.support;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;

public class DevelopmentOptions extends Activity {
    private boolean getEnabled(String variable) throws Settings.SettingNotFoundException {
        return Settings.Global.getInt(getContentResolver(), variable) == 1;
    }
    private void setDchaState(int value) {
        Settings.System.putInt(getContentResolver(), "dcha_state", value);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent;
        finishAndRemoveTask();
        if (Settings.System.canWrite(this)) {
            setDchaState(3);
            intent = new Intent(Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS);
            try {
                if (getEnabled(Settings.Global.ADB_ENABLED) && getEnabled(Settings.Global.DEVELOPMENT_SETTINGS_ENABLED)) {
                    new Handler(Looper.getMainLooper()).postDelayed(() -> setDchaState(0), 1000);
                }
            } catch (Settings.SettingNotFoundException ignored) {
            }
        } else {
            intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS, Uri.parse("package:" + getPackageName())).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        startActivity(intent);
    }
}
