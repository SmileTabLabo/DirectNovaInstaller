package jp.co.benesse.touch.setuplogin;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.provider.Settings;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import jp.co.benesse.dcha.dchaservice.IDchaService;

public class LoginSettingActivity extends Activity {

    private static final String DCHA_PACKAGE = "jp.co.benesse.dcha.dchaservice";
    private static final String DCHA_SERVICE = DCHA_PACKAGE + ".DchaService";
    private static final String CT2S = "TAB-A03-BS";
    private static final String CT2K = "TAB-A03-BR";
    private static final String CT2L = "TAB-A03-BR2";
    private static final String CT3 = "TAB-A03-BR3";
    private static final String CTX = "TAB-A05-BD";
    private static final String CTZ = "TAB-A05-BA1";
    private static final String APK_EXT = ".apk";

    IDchaService mDchaService;

    @Deprecated
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final String CT_MODEL = Build.MODEL;
        final String BC_PASSWORD_HIT_FLAG = "bc_password_hit";
        final int PASSWORD_FLAG = 1;
        final int UNDIGICHALIZE = 0;
        final int INSTALL_FLAG = 2;
        final int REBOOT_DEVICE = 0;
        final String DSS_PACKAGE = "jp.co.benesse.dcha.systemsettings";
        final String DSS_ACTIVITY = DSS_PACKAGE + ".TabletInfoSettingActivity";
        final String LAUNCHER2 = "com.android.launcher2";
        final String LAUNCHER3 = "com.android.launcher3";
        final String KOBOLD_STORE = "com.saradabar.vending";

        // DchaSystemSettings を呼び出し
        startActivity(new Intent().setClassName(DSS_PACKAGE, DSS_ACTIVITY));
        // 再起動時にADBの状態を保持する
        Settings.System.putInt(getContentResolver(), BC_PASSWORD_HIT_FLAG, PASSWORD_FLAG);

        // DchaService をバインド
        bindService(new Intent(DCHA_SERVICE).setPackage(DCHA_PACKAGE), new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                mDchaService = IDchaService.Stub.asInterface(iBinder);
                try {
                    // DchaState を変更
                    mDchaService.setSetupStatus(UNDIGICHALIZE);
                    // ナビゲーションバーを表示
                    mDchaService.hideNavigationBar(false);

                    // インストール部分 //
                    // TODO: インストール機能の実装(HTTP)

                    // 規定ランチャーの関連付けを解除
                    mDchaService.clearDefaultPreferredApp(LAUNCHER2);
                    mDchaService.clearDefaultPreferredApp(LAUNCHER3);
                    // 既定のランチャーを変更 (CTX/Z のみ機能)
                    mDchaService.setDefaultPreferredHomeApp(KOBOLD_STORE);
                    // 再起動
                    mDchaService.rebootPad(REBOOT_DEVICE, null);
                    // 自己アンインストール
                    mDchaService.uninstallApp(getPackageName(), INSTALL_FLAG+1);
                } catch (RemoteException ignored) {
                }
                unbindService(this);
            }
            @Override
            public void onServiceDisconnected(ComponentName componentName) {
            }
        }, BIND_ADJUST_WITH_ACTIVITY);
    }
}
