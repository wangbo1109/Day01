package skr.com.day01.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

public class MyAppLication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
