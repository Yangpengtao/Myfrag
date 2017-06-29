package shain.ypt.com.myframwork.httpprocessor;

import android.util.Log;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by admin on 2017/6/22.
 */

public abstract class HttpCallback<Result> implements ICallback {

    @Override
    public void onSuccess(String result) {
        Gson gson =new Gson();
        Class<?> clz = analysisClassInfo(this);
        Result objResult = (Result) gson.fromJson(result,clz);
        onSuccess(objResult);
        Log.e("TAG",objResult.toString());
    }

    public abstract void onSuccess(Result objResult);

    private static Class<?> analysisClassInfo(Object obj) {
        Type getType =  obj.getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType)getType).getActualTypeArguments();
        return (Class<?>) params[0];
    }
}
