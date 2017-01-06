package ad.joyplus.com.myapplication.managers;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;

import com.miaozhen.mzmonitor.MZMonitor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import ad.joyplus.com.myapplication.AppUtil.Consts;
import ad.joyplus.com.myapplication.AppUtil.TvManagerUtil;
import ad.joyplus.com.myapplication.AppUtil.gson.Gson;
import ad.joyplus.com.myapplication.AppUtil.gson.reflect.TypeToken;
import ad.joyplus.com.myapplication.compoment.OpeningView;
import ad.joyplus.com.myapplication.entity.AdModel;
import ad.joyplus.com.myapplication.entity.ConfigModel;
import ad.joyplus.com.myapplication.entity.EndMediaModel;
import ad.joyplus.com.myapplication.entity.IHtmlInfo;
import ad.joyplus.com.myapplication.entity.ImageViewModel;
import ad.joyplus.com.myapplication.entity.OpeningAdModel;
import ad.joyplus.com.myapplication.entity.StartMediaModel;
import ad.joyplus.com.myapplication.entity.URLModel;
import ad.joyplus.com.myapplication.network.IRequest;
import ad.joyplus.com.myapplication.network.NetworkFactory;
import ad.joyplus.com.myapplication.network.RequestIterface;
import cn.com.mma.mobile.tracking.api.Countly;


/**
 * Manager类具体来管理相关广告的请求及配置信息的读写、更新等
 * <p/>
 * Created by UPC on 2016/8/31.
 */
public class AppADSDKManager {

    private Context mContext;
    private IRequest request;
    private ConfigModel<Map<String ,String>> basemodel;
    private OpeningAdModel openingAdModel;
    private StartMediaModel startMediaModel;
    private EndMediaModel endMediaModel;
    private ImageViewModel videoimgmodel;
    private ImageViewModel cornerimgmodel;
    private ImageViewModel simpleimgmodel;
    private String sdkKey;

    private OpeningView view ;
    private ILoadListener loadlistener;
    private static AppADSDKManager appADSDKManager;

    //常量

    /**
     * 调用manager的initOPeningAD，使用者传入开屏页面的view，SDK将会下载并展示到该View上面
     * 使用该方法时，是需要自己先建立ImageView的，另外的方法是直接调用OpeningScreenAD在布局上使用
     */
    private AppADSDKManager(Context context){
        this.mContext = context.getApplicationContext();
    }

    public static synchronized AppADSDKManager getInstance(Context context){
        if(appADSDKManager == null){
            if(context == null){
                throw new NullPointerException("context was null");
            }
            appADSDKManager = new AppADSDKManager(context);
        }
        return appADSDKManager;
    }

    public void init(String sdkkey) {
        sdkKey = sdkkey;
        view = new OpeningView(mContext);
        Countly.sharedInstance().init(mContext,getAdmaster());
        request = NetworkFactory.newInstance(mContext);
        basemodel = getModelFromSp();
        if (basemodel == null) {
                getInfoFromShare();
        }else{
            getAllModelFromConfig();
        }
    }

    /**
     * 进行上报的时候调用该方法
     * @param info 将请求到的Model传入
     */
    public void sendReprot(IHtmlInfo info) {
        System.out.println(info);
        String reportURL = "";
        String reportTojoy = "";
        List<String> thridList = new ArrayList<>();
        if (info instanceof OpeningAdModel) {
            reportURL = ((OpeningAdModel) info).getHtml5_url();
            reportTojoy = ((OpeningAdModel) info).getImpressionurl();
            thridList = ((OpeningAdModel) info).getThridreport();
        } else if (info instanceof ImageViewModel) {
            reportURL = ((ImageViewModel) info).getHtml5_url();
            reportTojoy = ((ImageViewModel) info).getImpressionurl();
            thridList = ((ImageViewModel) info).getThridreport();
        } else if (info instanceof StartMediaModel) {
            reportURL = ((StartMediaModel) info).getHtml5_url();
            reportTojoy = ((StartMediaModel) info).getImpressionurl();
            thridList = ((StartMediaModel) info).getThridreport();
        } else if (info instanceof EndMediaModel) {
            reportURL = ((EndMediaModel) info).getHtml5_url();
            reportTojoy = ((EndMediaModel) info).getImpressionurl();
            thridList = ((EndMediaModel) info).getThridreport();
        }
        if (null!=reportTojoy&&!reportTojoy.isEmpty()) {
            reportToJoy(reportTojoy);
        } else {
            Log.e("AppADSDK", "Impressionurl was null");
        }
        if (null!=reportURL&&!reportURL.isEmpty()){
            System.out.println(reportURL);
        request.getHitmlInfo(reportURL, new RequestIterface.IHtmlInfoCallBack() {
            @Override
            public void OnHtmlBack(URLModel modle) {
                for (URLModel.UrlInfoEntity entity : modle.getUrl_info()) {
                    request.reportInfo(entity.getUrl() + "&id=" + entity.getId(), new RequestIterface.IReportInfoCallBack() {
                        @Override
                        public void onReportBack(boolean state) {
                            Log.i("AppADSDK", "Html5_report state " + state);
                        }
                    });
                }
            }
        });
        }
        if(null!=thridList&&!thridList.isEmpty()) {
            for (String thrids : thridList) {
                reportToThrid(thrids);
            }
        }

    }

    // TODO: 2016/12/26 添加上报及第三方上报，待测试。
    private void reportToThrid(String thridURL){
        if(thridURL.contains("ns=__IP__")){
            thridURL = thridURL.replace("ns=__IP__","ns="+TvManagerUtil.getInstance(mContext).getIPAdress());
        }else{
            thridURL = thridURL+"&ns="+TvManagerUtil.getInstance(mContext).getIPAdress();
        }
        if(thridURL.contains("m6a=__MAC__")){
            thridURL = thridURL.replace("m6a=__MAC__","m6a="+TvManagerUtil.getInstance(mContext).getMacAdress());
        }else{
            thridURL = thridURL+"&m6a="+TvManagerUtil.getInstance(mContext).getMacAdress();
        }
        if(thridURL.contains("m1a=__ANDROIDID__")){
            thridURL = thridURL.replace("m1a=__ANDROIDID__","m1a="+TvManagerUtil.getInstance(mContext).getDeviceID());
        }else{
            thridURL = thridURL+"&m1a="+TvManagerUtil.getInstance(mContext).getDeviceID();
        }
        if(thridURL.contains(Consts.MIAOZHEN)){
            MZMonitor.retryCachedRequests(mContext);
            MZMonitor.adTrack(mContext, thridURL);
        }else if(thridURL.contains(Consts.ADMASTER)){
            Countly.sharedInstance().onExpose(thridURL);
        }else{
            request.reportInfo(thridURL, new RequestIterface.IReportInfoCallBack() {
                @Override
                public void onReportBack(boolean state) {
                    Log.i("AppADSDK","ThridReport state "+state);
                }
            });
        }
    }

    /**
     * 给自己服务器上报
     * @param url
     */
    private void reportToJoy(String url){
        if(url.contains("i=%mac%")) {
            url = url.replace("%mac%",TvManagerUtil.getInstance(mContext).getMacAdress());
        }else {
            url = url+"&i="+TvManagerUtil.getInstance(mContext).getMacAdress();
        }
        if(url.contains("appkey=")){
            url = url.replace("appkey=","appkey="+sdkKey);
        }else{
            url = url+"&appkey="+sdkKey;
        }
        System.out.println(url+"url");
        request.reportInfo(url, new RequestIterface.IReportInfoCallBack() {
            @Override
            public void onReportBack(boolean state) {

            }
        });
    }

    public OpeningView getOpenView(ILoadListener listener) {
        this.loadlistener = listener;
        if(openingAdModel != null){
            this.loadlistener.hasLoad();
        }
        return view;
    }
    public ImageViewModel getVideoImgModel(){
        if(videoimgmodel == null){
            Log.e("APPADSDK","NO_AD");
            return null;
        }
        refreshVideoImgView();
        return videoimgmodel;
    }
    public ImageViewModel getCornerImgModel(){
        if(cornerimgmodel == null){
            Log.e("APPADSDK","NO_AD");
            return null;
        }
        refreshCornerImgView();
        return cornerimgmodel;
    }
    public ImageViewModel getSimpleImgModel(){
        if(simpleimgmodel == null){
            Log.e("APPADSDK","NO_AD");
            return null;
        }
        refreshSimpleImgView();
        return simpleimgmodel;
    }

    public OpeningAdModel getOpeningModel(){
        if(openingAdModel == null){
            Log.e("APPADSDK","NO_AD");
            return null;
        }
        refreshOpenImage();
        return openingAdModel;
    }
    public String getStartMediaModel(){
        if(startMediaModel == null){
            Log.e("APPADSDK","NO_AD");
            return null;
        }
        refreshStartMedia();
        return startMediaModel.getVideourl();
    }
    public String getEndMediaModel(){
        if(endMediaModel == null){
            Log.e("APPADSDK","NO_AD");
            return null;
        }
        refreshEndMedia();
        return endMediaModel.getVideourl();
    }
    private void getAllModelFromConfig(){
        request.getBaseModel(Consts.OPEN, BuildURL(basemodel.getPublishID().get(Consts.AD_OPEN)), new RequestIterface.IADmodel() {
            @Override
            public void onAdModelBack(AdModel model) {
                openingAdModel = (OpeningAdModel) model.data;
                if(loadlistener != null) {
                    loadlistener.hasLoad();
                }
                view.disPlay();
                checkversionCode(model.VersonCode);
                getOtherModelInfo();
            }
        });
    }
    private void getOtherModelInfo(){
        if(basemodel.getPublishID().containsKey(Consts.AD_VIDEOIMG)){
            request.getBaseModel(Consts.VIDEOIMG, BuildURL(basemodel.getPublishID().get(Consts.AD_VIDEOIMG)), new RequestIterface.IADmodel() {
                @Override
                public void onAdModelBack(AdModel model) {
                    if(null != model) {
                        videoimgmodel = (ImageViewModel) model.data;
                    }
                }
            });
        }
        if(basemodel.getPublishID().containsKey(Consts.AD_STARTMEDIA)){
            request.getBaseModel(Consts.STARTMEDIA, BuildURL(basemodel.getPublishID().get(Consts.AD_STARTMEDIA)), new RequestIterface.IADmodel() {
                @Override
                public void onAdModelBack(AdModel model) {
                    if(null != model) {
                        startMediaModel = (StartMediaModel) model.data;
                    }
                }
            });
        }
        if(basemodel.getPublishID().containsKey(Consts.AD_ENDMEDIA)){
            request.getBaseModel(Consts.ENDMEDIA, BuildURL(basemodel.getPublishID().get(Consts.AD_ENDMEDIA)), new RequestIterface.IADmodel() {
                @Override
                public void onAdModelBack(AdModel model) {
                    if(null != model) {
                        endMediaModel = (EndMediaModel) model.data;
                    }
                }
            });
        }
        if(basemodel.getPublishID().containsKey(Consts.AD_CORNERIMG)){
            request.getBaseModel(Consts.CORNERIMG, BuildURL(basemodel.getPublishID().get(Consts.AD_CORNERIMG)), new RequestIterface.IADmodel() {
                @Override
                public void onAdModelBack(AdModel model) {
                    if(null != model) {
                        cornerimgmodel = (ImageViewModel) model.data;
                    }
                }
            });
        }
        if(basemodel.getPublishID().containsKey(Consts.AD_SIMPLEIMG)){
            request.getBaseModel(Consts.SIMPLEIMG, BuildURL(basemodel.getPublishID().get(Consts.AD_SIMPLEIMG)), new RequestIterface.IADmodel() {
                @Override
                public void onAdModelBack(AdModel model) {
                    if(null != model) {
                        simpleimgmodel = (ImageViewModel) model.data;
                    }
                }
            });
        }
    }
    //添加参数
    private String BuildURL(String type){

        Uri.Builder b = Uri.parse(basemodel.getBaseURL().substring(0,basemodel.getBaseURL().length()-2)).buildUpon();
        if(type != null) {
            b.appendQueryParameter("z", type);//广告位ID
        }
        b.appendQueryParameter(Consts.I_,TvManagerUtil.getInstance(mContext).getMacAdress());//Mac的Md5加密地址
        b.appendQueryParameter(Consts.KEY,sdkKey);//App的key
        b.appendQueryParameter(Consts.DEV,TvManagerUtil.getInstance(mContext).getDeviceID());//机器的DevicceID
        b.appendQueryParameter(Consts.U_,TvManagerUtil.getInstance(mContext).getDefaultUserAgentString());//UA
        b.appendQueryParameter(Consts.T_,String.valueOf(System.currentTimeMillis()));//当前系统时间
        b.appendQueryParameter(Consts.CONNECTION_TYPE,TvManagerUtil.getInstance(mContext).getConnectionType().toString());//网络连接方式

        return b.toString();
    }
    /**
     * 但版本号有变化则直接调该方法来更新当前的Config配置
     */
    private void getInfoFromShare() {
        /**
         * 获取baseurl第一次默认从properties文件内获取
         * 之后直接从服务器获取baseurl
         */
        String baseurl = "";
        if(basemodel != null){
            baseurl = basemodel.getBaseURL();
        }else{
            baseurl = getBaseURL();
        }
        String requesturl = baseurl+ Consts.REQUEST_HEAD_LINK;
        Uri.Builder b = Uri.parse(requesturl).buildUpon();
        if(sdkKey!=null){
            b.appendQueryParameter("sdkkey",sdkKey);
        }
        if(!TvManagerUtil.getInstance(mContext).getDeviceID().isEmpty()){
            b.appendQueryParameter(Consts.DEV,TvManagerUtil.getInstance(mContext).getDeviceID());
        }
        if(!TvManagerUtil.getInstance(mContext).getIPAdress().isEmpty()){
            b.appendQueryParameter(Consts.IP_,TvManagerUtil.getInstance(mContext).getIPAdress());
        }
        if(!TvManagerUtil.getInstance(mContext).getMacAdress().isEmpty()){
            b.appendQueryParameter(Consts.I_,TvManagerUtil.getInstance(mContext).getMacAdress());
        }
        if(!TvManagerUtil.getInstance(mContext).getDefaultUserAgentString().isEmpty()){
            b.appendQueryParameter(Consts.U_,TvManagerUtil.getInstance(mContext).getDefaultUserAgentString());
        }
        if(!TvManagerUtil.getInstance(mContext).getConnectionType().toString().isEmpty()){
            b.appendQueryParameter(Consts.CONNECTION_TYPE,TvManagerUtil.getInstance(mContext).getConnectionType().toString());
        }
        if(!TvManagerUtil.getInstance(mContext).getVersionCode().isEmpty()){
            b.appendQueryParameter(Consts.V_,TvManagerUtil.getInstance(mContext).getVersionCode());
        }

        request.getConfigInfo(b.toString(), new RequestIterface.IModelCallBack() {
            @Override
            public void onConfigModelBack(AdModel<ConfigModel<Map<String,String>>> adModel) {
                setConfigInfotoPrefrence(adModel);
                basemodel = adModel.data;
                getAllModelFromConfig();
            }
        });
    }

    /**
     * 将Config配置信息写入shareprefrence
     * @param model
     */
    private void setConfigInfotoPrefrence(AdModel<ConfigModel<Map<String,String>>> model) {
        SharedPreferences preferences = mContext.getSharedPreferences(Consts.SP_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Consts.CONFIG_VERSION, model.VersonCode);
        editor.putString(Consts.SP_JSON_INFO, new Gson().toJson(model.data));
        editor.commit();
    }
    /**
     * 从shareprefrence里面获取Config配置信息
     * @return ConfigModel
     */
    private ConfigModel getModelFromSp() {
        SharedPreferences preferences = mContext.getSharedPreferences(Consts.SP_NAME, Activity.MODE_PRIVATE);
        String info_json = preferences.getString(Consts.SP_JSON_INFO, "");
        return new Gson().fromJson(info_json, new TypeToken<ConfigModel<Map<String ,String>>>(){}.getType());
    }
    /**
     * 判断传入的版本号，如果版本号有升级则请求AppSDk的配置文件
     * @param newcode 传入的版本
     */
    private void checkversionCode(String newcode){
        SharedPreferences preferences = mContext.getSharedPreferences(Consts.SP_NAME, Activity.MODE_PRIVATE);
        String version = preferences.getString(Consts.CONFIG_VERSION,"");
        if(!newcode.equals(version)){
            getInfoFromShare();
        }
    }

    private void refreshVideoImgView(){
        request.getBaseModel(Consts.VIDEOIMG, BuildURL(basemodel.getPublishID().get(Consts.AD_VIDEOIMG)), new RequestIterface.IADmodel() {
            @Override
            public void onAdModelBack(AdModel model) {
                if(null != model) {
                    videoimgmodel = (ImageViewModel) model.data;
                }
            }
        });
    }
    private void refreshCornerImgView(){
        request.getBaseModel(Consts.VIDEOIMG, BuildURL(basemodel.getPublishID().get(Consts.AD_CORNERIMG)), new RequestIterface.IADmodel() {
            @Override
            public void onAdModelBack(AdModel model) {
                if(null != model) {
                    cornerimgmodel = (ImageViewModel) model.data;
                }
            }
        });
    }
    private void refreshSimpleImgView(){
        request.getBaseModel(Consts.VIDEOIMG, BuildURL(basemodel.getPublishID().get(Consts.AD_SIMPLEIMG)), new RequestIterface.IADmodel() {
            @Override
            public void onAdModelBack(AdModel model) {
                if(null != model) {
                    simpleimgmodel = (ImageViewModel) model.data;
                }
            }
        });
    }
    private void refreshStartMedia(){
       request.getBaseModel(Consts.STARTMEDIA, BuildURL(basemodel.getPublishID().get(Consts.AD_STARTMEDIA)), new RequestIterface.IADmodel() {
           @Override
           public void onAdModelBack(AdModel model) {
               if(null != model) {
                   startMediaModel = (StartMediaModel) model.data;
               }
           }
       });
    }
    private void refreshEndMedia(){
        request.getBaseModel(Consts.ENDMEDIA, BuildURL(basemodel.getPublishID().get(Consts.AD_ENDMEDIA)), new RequestIterface.IADmodel() {
            @Override
            public void onAdModelBack(AdModel model) {
                if(null != model) {
                    endMediaModel = (EndMediaModel) model.data;
                }
            }
        });
    }
    private void refreshOpenImage(){
        request.getBaseModel(Consts.OPEN, BuildURL(basemodel.getPublishID().get(Consts.AD_OPEN)), new RequestIterface.IADmodel() {
            @Override
            public void onAdModelBack(AdModel model) {
                if(null != model) {
                    openingAdModel = (OpeningAdModel) model.data;
                }
            }
        });
    }
    private String getBaseURL(){
        Properties properties = new Properties();
        String baseurl = "";
        try {
            properties.load(mContext.getAssets().open(Consts.CONFIG_NAME));
            baseurl = (String) properties.get(Consts.BASEURL);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return baseurl;
    }
    private String getAdmaster(){
        Properties properties = new Properties();
        String admaster = "";
        try {
            properties.load(mContext.getAssets().open(Consts.CONFIG_NAME));
            admaster = (String) properties.get(Consts.BASEURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return admaster;
    }
}
