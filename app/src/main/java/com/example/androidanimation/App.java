package com.example.androidanimation;
import android.app.Application;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Bugly.init(this,"076a38b40e",BuildConfig.DEBUG);
        Beta.autoCheckAppUpgrade = true;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
