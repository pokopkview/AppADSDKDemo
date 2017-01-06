package ad.joyplus.com.myapplication.network;

import android.graphics.Bitmap;

import java.util.Map;

import ad.joyplus.com.myapplication.entity.AdModel;
import ad.joyplus.com.myapplication.entity.ConfigModel;
import ad.joyplus.com.myapplication.entity.OpeningAdModel;
import ad.joyplus.com.myapplication.entity.StartMediaModel;
import ad.joyplus.com.myapplication.entity.URLModel;

/**
 *所有返回CallBack的接口回调
 * Created by UPC on 2016/8/29.
 */
public class RequestIterface {
    public interface ISuccessRequest{
        void onSuccess(Object reqeust);
    }
    public interface IFaileRequest{
        void onFailed(Object request);
    }
    public interface IDownLoadSuccess{
        void onDownLoadSuccess(String msg);
    }
    public interface IDownLoadProgress{
        void onDownLoadProgress(int progress);
    }
    public interface IModelCallBack{
        void onConfigModelBack(AdModel<ConfigModel<Map<String,String>>> model);
    }
    public interface IStartMediaCallBack{
        void onStartMediaBack(AdModel<StartMediaModel> model);
    }
    public interface IEndMediaCallBack{
        void onEndMediaBack(AdModel<StartMediaModel> model);
    }
    public interface IReportInfoCallBack{
        void onReportBack(boolean state);
    }
    public interface IOpeningInfoCallBack{
        void onOpeningBack(OpeningAdModel model);
    }
    public interface IImageBitMap{
        void onImageBitMap(Bitmap bitmap);
    }
    public interface IADmodel{
        void onAdModelBack(AdModel model);
    }
    public interface IHtmlInfoCallBack{
        void OnHtmlBack(URLModel modle);
    }

}
