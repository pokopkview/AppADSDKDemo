package ad.joyplus.com.myapplication.network;

import android.content.Context;
import android.widget.ImageView;

/**
 * 所有需要请求到网络的接口
 * Created by UPC on 2016/8/29.
 */
public interface IRequest {
    /**
     * 获取配置信息
     * @param url
     * @param callBack
     */
    void getConfigInfo(String url, RequestIterface.IModelCallBack callBack);

    /**
     * 获取开屏图片信息
     * @param url
     * @param callBack
     */
    void getOpeningInfo(String url, RequestIterface.IOpeningInfoCallBack callBack);

    /**
     * 下载文件资源
     * @param targetFilepath
     * @param url
     * @param progress
     * @param success
     */
    void DownLoadFiles(String targetFilepath, String url, RequestIterface.IDownLoadProgress progress, RequestIterface.IDownLoadSuccess success);


    /**
     * 获取前贴片广告信息
     * @param url
     * @param callback
     */
    void getStartMediaInfo(String url, RequestIterface.IStartMediaCallBack callback);

    /**
     * 获取后贴片信息
     * @param url
     * @param callBack
     */
    void getEndMediaInfo(String url, RequestIterface.IEndMediaCallBack callBack);

    /**
     * 上报相关信息
     * @param url
     * @param callBack
     */
    void reportInfo(String url, RequestIterface.IReportInfoCallBack callBack);

    /**
     * 获取BitMap资源
     * @param url
     * @param callBack
     */
    void getImageBitMap(String url, RequestIterface.IImageBitMap callBack);

    void getBaseModel(int type, String url, RequestIterface.IADmodel models);

    void getImgFromGlide(ImageView view,String url,Object object);

    void getGifFromGlide(ImageView view, String url, Context context);
    void getHitmlInfo(String url,RequestIterface.IHtmlInfoCallBack callBack);
}
