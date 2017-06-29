package shain.ypt.com.myframwork.httpprocessor;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2017/6/22.
 */

public class HttpHelper  implements IHttpProcessor  {
    private static IHttpProcessor mHttpProcessor = null;
    private Map<String,Object> mParams;
    private static HttpHelper _instance;

    private  HttpHelper(){
        mParams = new  HashMap<String,Object>();
    }

    public static HttpHelper obtain(){
        synchronized (HttpHelper.class){
            if (_instance==null){
                _instance=new HttpHelper();
            }
        }
        return _instance;
    }

    public static void init(IHttpProcessor httpProcessor){
        mHttpProcessor = httpProcessor;
    }

    @Override
    public void post(String url, Map<String, Object> params, ICallback callback) {
        final String finalUrl =appendParams(url,params);
        mHttpProcessor.post(  finalUrl,   params,  callback);
    }

    @Override
    public void get(String url, Map<String, Object> params, ICallback callback) {
        final String finalUrl =appendParams(url,params);
        mHttpProcessor.get(  url,   params,  callback);
    }

    private String appendParams(String url, Map<String, Object> params) {
        if (params == null || params.isEmpty()) return url;

        StringBuilder urlBuilder=new StringBuilder(url);
        if (url.indexOf("?")<=0)
            urlBuilder.append("?");
        else{
            if (!urlBuilder.toString().endsWith("?")){
                urlBuilder.append("&");
            }
        }
        for (Map.Entry<String,Object> entry:params.entrySet()){
            urlBuilder.append(entry.getKey()).append("=").append(encode(entry.getValue().toString()));

        }
        return urlBuilder.toString();

    }

    //URL不允许有空格等字符，如果参数值有空格，需要此方法转换
    private static String encode(String str){
        try {
            return URLEncoder.encode(str,"utf-8");
        } catch (UnsupportedEncodingException e) {
            throw  new RuntimeException(e);
        }
    }


}
