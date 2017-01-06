package ad.joyplus.com.myapplication.AppUtil;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.lang.reflect.Constructor;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Created by UPC on 2016/12/23.
 */
public class TvManagerUtil {

    private static TvManagerUtil mTvManager;
    private static Context mContext;

    private TvManagerUtil(Context context){
        this.mContext = context;
    }

    public static TvManagerUtil getInstance(Context context) {
        if(mTvManager == null){
            mTvManager = new TvManagerUtil(context);
        }
        return mTvManager;
    }
    public String getVersionCode(){
        return Consts.SDK_VERSION;
    }

    public String getDeviceID(){
        TelephonyManager tm = (TelephonyManager)mContext.getSystemService(Context.TELEPHONY_SERVICE);
        String DEVICE_ID = tm.getDeviceId();
        return DEVICE_ID;
    }
    public String getMacAdress(){
        String macSerial = null;
        String str = "";
        try {
            Process pp = Runtime.getRuntime().exec(
                    "cat /sys/class/net/wlan0/address ");
            InputStreamReader ir = new InputStreamReader(pp.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            for (; null != str;) {
                str = input.readLine();
                if (str != null) {
                    macSerial = str.trim();// 去空格
                    break;
                }
            }
        } catch (IOException ex) {
            // 赋予默认值
            ex.printStackTrace();
        }
        return MD5Util.GetMD5Code(macSerial);
    }
    public String getIPAdress(){
        String hostIp = null;
        try {
            Enumeration nis = NetworkInterface.getNetworkInterfaces();
            InetAddress ia = null;
            while (nis.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) nis.nextElement();
                Enumeration<InetAddress> ias = ni.getInetAddresses();
                while (ias.hasMoreElements()) {
                    ia = ias.nextElement();
                    if (ia instanceof Inet6Address) {
                        continue;// skip ipv6
                    }
                    String ip = ia.getHostAddress();
                    if (!"127.0.0.1".equals(ip)) {
                        hostIp = ia.getHostAddress();
                        break;
                    }
                }
            }
        } catch (SocketException e) {
            Log.i("yao", "SocketException");
            e.printStackTrace();
        }
        return MD5Util.GetMD5Code(hostIp);
    }
    public String getDefaultUserAgentString() {
        try {
            try {
                Constructor<WebSettings> constructor = WebSettings.class
                        .getDeclaredConstructor(Context.class, WebView.class);
                constructor.setAccessible(true);
                try {
                    WebSettings settings = constructor.newInstance(mContext, null);
                    return settings.getUserAgentString();
                } finally {
                    constructor.setAccessible(false);
                }
            } catch (Exception e) {
                return new WebView(mContext).getSettings().getUserAgentString();
            }
        }catch (Exception e){
            return e.getMessage();
        }
    }
    public CONNECTION_TYPE getConnectionType() {
        int networkStatePermission = mContext
                .checkCallingOrSelfPermission(Manifest.permission.ACCESS_NETWORK_STATE);
        if (networkStatePermission == PackageManager.PERMISSION_GRANTED) {
            ConnectivityManager cm = (ConnectivityManager) mContext
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = cm.getActiveNetworkInfo();
            if (info == null) {
                return CONNECTION_TYPE.UNKNOWN;
            }
            int netType = info.getType();
            int netSubtype = info.getSubtype();
            if (netType == ConnectivityManager.TYPE_WIFI) {
                return CONNECTION_TYPE.WIFI;
            } else if (netType == 6) {
                return CONNECTION_TYPE.WIMAX;
            } else if (netType == ConnectivityManager.TYPE_MOBILE) {
                switch (netSubtype) {
                    case  TelephonyManager.NETWORK_TYPE_1xRTT:
                        return CONNECTION_TYPE.MOBILE_1xRTT;
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                        return CONNECTION_TYPE.MOBILE_CDMA;
                    case TelephonyManager.NETWORK_TYPE_EDGE:
                        return CONNECTION_TYPE.MOBILE_EDGE;
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:
                        return CONNECTION_TYPE.MOBILE_EVDO_0;
                    case TelephonyManager.NETWORK_TYPE_EVDO_A:
                        return CONNECTION_TYPE.MOBILE_EVDO_A;
                    case TelephonyManager.NETWORK_TYPE_GPRS:
                        return CONNECTION_TYPE.MOBILE_GPRS;
                    case TelephonyManager.NETWORK_TYPE_UMTS:
                        return CONNECTION_TYPE.MOBILE_UMTS;
                    case 14:
                        return CONNECTION_TYPE.MOBILE_EHRPD;
                    case 12:
                        return CONNECTION_TYPE.MOBILE_EVDO_B;
                    case 8:
                        return CONNECTION_TYPE.MOBILE_HSDPA;
                    case 10:
                        return CONNECTION_TYPE.MOBILE_HSPA;
                    case 15:
                        return CONNECTION_TYPE.MOBILE_HSPAP;
                    case 9:
                        return CONNECTION_TYPE.MOBILE_HSUPA;
                    case 11:
                        return CONNECTION_TYPE.MOBILE_IDEN;
                    case 13:
                        return CONNECTION_TYPE.MOBILE_LTE;
                    default:
                        return CONNECTION_TYPE.UNKNOWN;
                }
            } else {
                return CONNECTION_TYPE.UNKNOWN;
            }
        } else {
            return CONNECTION_TYPE.UNKNOWN;
        }
    }
    public enum CONNECTION_TYPE {
        UNKNOWN("UNKNOW"),
        WIFI("WIFI"),
        WIMAX("WIMAX"),
        MOBILE_UNKNOWN("MOBILE"),
        MOBILE_1xRTT("1xRTT"),
        MOBILE_CDMA("CDMA"),
        MOBILE_EDGE("EDGE"),
        MOBILE_EHRPD("EHRPD"),
        MOBILE_EVDO_0("EVDO_0"),
        MOBILE_EVDO_A("EVDO_A"),
        MOBILE_EVDO_B("EVDO_B"),
        MOBILE_GPRS("GPRS"),
        MOBILE_HSDPA("HSDPA"),
        MOBILE_HSPA("HSPA"),
        MOBILE_HSPAP("HSPAP"),
        MOBILE_HSUPA("HSUPA"),
        MOBILE_IDEN("MOBILE_IDEN"),
        MOBILE_LTE("LTE"),
        MOBILE_UMTS("UMTS");
        private String TYPE;

        CONNECTION_TYPE(String type) {
            TYPE = type;
        }

        public String toString() {
            return TYPE;
        }
    }
}
