package me.s1204.benesse.touch.support;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.BenesseExtension;
import android.os.Bundle;
import android.provider.Settings;

public class DchaStateChanger extends Activity {

    private void setDchaState(int value) {
        Settings.System.putInt(getContentResolver(), "dcha_state", value);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        finishAndRemoveTask();
        if (Settings.System.canWrite(this)) {
            if (BenesseExtension.getDchaState() == 3) {
                setDchaState(0);
            } else {
                setDchaState(3);
            }
        } else {
            startActivity(new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS, Uri.parse("package:" + getPackageName())).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }
    }

}
