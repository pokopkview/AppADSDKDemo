package ad.joyplus.com.myapplication.AppUtil;

import android.util.Log;

import ad.joyplus.com.myapplication.AppUtil.volley.AuthFailureError;
import ad.joyplus.com.myapplication.AppUtil.volley.NetworkError;
import ad.joyplus.com.myapplication.AppUtil.volley.NoConnectionError;
import ad.joyplus.com.myapplication.AppUtil.volley.ParseError;
import ad.joyplus.com.myapplication.AppUtil.volley.ServerError;
import ad.joyplus.com.myapplication.AppUtil.volley.TimeoutError;
import ad.joyplus.com.myapplication.AppUtil.volley.VolleyError;


/**
 * Created by UPC on 2016/9/18.
 */
public class AdExceptionUtil {

    private static final String TAG = "AppADSDK";

    public AdExceptionUtil(){
    }

    public void checkErrorFactory(VolleyError error){
        if(error instanceof AuthFailureError){
            Log.e(TAG,"Http身份认证（授权）错误");
        }else if(error instanceof NetworkError){
            Log.e(TAG,"网络错误");
        }else if(error instanceof NoConnectionError){
            Log.e(TAG,"网络连接错误");
        }else if(error instanceof ParseError){
            Log.e(TAG,"数据解析错误");
        }else if(error instanceof ServerError){
            Log.e(TAG,"服务端错误");
        }else if(error instanceof TimeoutError){
            Log.e(TAG,"超时错误");
        }
    }
}
