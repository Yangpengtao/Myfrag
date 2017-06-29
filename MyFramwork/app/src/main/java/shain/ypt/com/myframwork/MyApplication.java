package shain.ypt.com.myframwork;

import android.app.Application;

import shain.ypt.com.myframwork.httpprocessor.HttpHelper;
import shain.ypt.com.myframwork.httpprocessor.XUtilsProcessor;

/**
 * Created by admin on 2017/6/22.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//      HttpHelper.init(new VolleyProcessor(this));
//      HttpHelper.init(new OKHttpProcessor());
        HttpHelper.init(new XUtilsProcessor(this));
    }


}
