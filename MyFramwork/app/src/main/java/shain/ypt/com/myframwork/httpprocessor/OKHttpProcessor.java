package shain.ypt.com.myframwork.httpprocessor;

import android.os.Handler;
import android.util.Log;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by admin on 2017/6/22.
 */

public class OKHttpProcessor implements  IHttpProcessor{

    private final String TAG ="OKHttpProcessor";
    private OkHttpClient okHttpClient;
    private Handler mHandler;

    public OKHttpProcessor(){
        okHttpClient=new OkHttpClient();
        mHandler=new Handler();
    }

    @Override
    public void post(String url, Map<String, Object> params, final ICallback callback) {
        RequestBody requestBody= appendBody(params);
        final Request request =  new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call,final IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onFailure(e.getMessage().toString());
                    }
                });
            }

            @Override
            public void onResponse(Call call,final Response response) throws IOException {
                if (response.isSuccessful()){
                    final String result =response.body().string();
                    Log.e(TAG,"----"+result);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onSuccess(result);
                        }
                    });
                }else{
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onFailure(response.message().toString());
                        }
                    });
                }
            }
        });
    }



    @Override
    public void get(String url, Map<String, Object> params,final ICallback callback) {
        RequestBody requestBody= appendBody(params);
        final Request request =  new Request.Builder()
                .url(url)
                .post(requestBody)
                .header("User-Agent","a")
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call,final IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onFailure(e.getMessage().toString());
                    }
                });
            }

            @Override
            public void onResponse(Call call,final Response response) throws IOException {
                if (response.isSuccessful()){
                    final String result =response.body().string();
                    Log.e(TAG,"----"+result);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onSuccess(result);
                        }
                    });
                }else{
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onFailure(response.message().toString());
                        }
                    });
                }
            }
        });
    }


    private RequestBody appendBody(Map<String, Object> params) {
        FormBody.Builder body =  new FormBody.Builder();
        if (params==null || params.isEmpty())
            return body.build();
        for (Map.Entry<String,Object> entry:params.entrySet()){
            body.add(entry.getKey(),entry.getValue().toString());
        }
        return body.build();
    }
}
