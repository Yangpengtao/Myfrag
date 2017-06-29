package shain.ypt.com.myframwork.httpprocessor;

import android.util.Log;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Map;

import shain.ypt.com.myframwork.MyApplication;

/**
 * Created by admin on 2017/6/23.
 */

public class XUtilsProcessor implements IHttpProcessor {

    public final String TAG ="XUtilsProcessor";
    public XUtilsProcessor(MyApplication application){
        x.Ext.init(application);
    }
    @Override
    public void post(String url, Map<String, Object> params, final ICallback callback) {
        RequestParams requestParams =new RequestParams(url);
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                callback.onSuccess(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                callback.onFailure(ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }

    @Override
    public void get(String url, Map<String, Object> params,final ICallback callback) {
        RequestParams requestParams =new RequestParams(url);
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e(TAG,"--"+result);
                callback.onSuccess(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                callback.onFailure(ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}
