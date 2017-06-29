package shain.ypt.com.myframwork.httpprocessor;


/**
 * Created by admin on 2017/6/22.
 * 参考
 */
public interface ICallback {
    void onSuccess(String result);
    void onFailure(String e);
}
