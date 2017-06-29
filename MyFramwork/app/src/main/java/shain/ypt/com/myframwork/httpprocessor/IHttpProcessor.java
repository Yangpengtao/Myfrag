package shain.ypt.com.myframwork.httpprocessor;

import java.util.Map;

/**
 * Created by admin on 2017/6/22.
 */

public interface IHttpProcessor {
    //网络访问：POST,GET,DELETE,PUT,UPDATE
    void post(String url, Map<String,Object> params,ICallback callback);

    void get(String url,Map<String,Object> params,ICallback callback);
}
