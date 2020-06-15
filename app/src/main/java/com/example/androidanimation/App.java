package com.example.androidanimation;
import android.app.Application;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Bugly.init(this,"076a38b40e",BuildConfig.DEBUG);
        Beta.autoCheckAppUpgrade = true;

        UMConfigure.init(this, "5ee32fc4dbc2ec076dd46d92", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "");

        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO);

        UMConfigure.setLogEnabled(true);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
