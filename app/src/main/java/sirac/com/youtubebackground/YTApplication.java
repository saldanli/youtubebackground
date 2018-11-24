package sirac.com.youtubebackground;

import android.app.Application;
import android.content.Context;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;



public class YTApplication extends Application {

    private static Context mContext;

    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        mContext = getApplicationContext();
    }

    public static Context getAppContext() {
        return mContext;
    }

}
