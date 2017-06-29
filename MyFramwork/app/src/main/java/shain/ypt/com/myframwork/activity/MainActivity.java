package shain.ypt.com.myframwork.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import shain.ypt.com.myframwork.R;
import shain.ypt.com.myframwork.bean.PhotoSetInfo;
import shain.ypt.com.myframwork.httpprocessor.HttpCallback;
import shain.ypt.com.myframwork.httpprocessor.HttpHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "MainActivity";
    private String url ="http://c.3g.163.com/photo/api/set/0001%2F2250173.json";
    private Map<String,Object> params = new HashMap<>();
    TextView textView;
    Button button;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView= (TextView) findViewById(R.id.text);
        button = (Button) findViewById(R.id.btGo);
        button.setOnClickListener(this);
        textView.setText(isPalindrome(-2147483648)+"");



    }

    public boolean isPalindrome(int x) {
        String str = String.valueOf(x);
        if (str.contains("-")) str=str.replace("-","");
        int length = str.length();
        if(length==0) return true;
        for(int i=0;i<length/2;i++){
            Log.e("tag",str.charAt(i)+"---+"+str.charAt(length-i-1)+"---+"+str);
            if(str.charAt(i)!=str.charAt(length-i-1)){
                return false;
            }
        }
        return true;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btGo:
                HttpHelper.obtain().get(url, params, new HttpCallback<PhotoSetInfo>() {
                    @Override
                    public void onSuccess(PhotoSetInfo objResult) {

                    }

                    @Override
                    public void onFailure(String e) {


                    }
                });
            break;
        }
    }
}
