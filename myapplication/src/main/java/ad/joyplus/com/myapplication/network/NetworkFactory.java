package ad.joyplus.com.myapplication.network;

import android.content.Context;

/**
 * Created by UPC on 2016/8/29.
 */
public class NetworkFactory {
    private static TrueHttpRequest request;

    /**
     *
     * @param context
     * @return
     */
    public static TrueHttpRequest newInstance(Context context){
        if(request == null){
            request = new TrueHttpRequest(context);
            return request;
        }
        return request;
    }


}
