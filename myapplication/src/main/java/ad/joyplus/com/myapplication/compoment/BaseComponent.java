package ad.joyplus.com.myapplication.compoment;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import ad.joyplus.com.myapplication.entity.IHtmlInfo;
import ad.joyplus.com.myapplication.entity.URLModel;
import ad.joyplus.com.myapplication.network.IRequest;
import ad.joyplus.com.myapplication.network.IURLCallBack;
import ad.joyplus.com.myapplication.network.NetworkFactory;
import ad.joyplus.com.myapplication.network.RequestIterface;

/**
 * Created by UPC on 2016/9/6.
 */
public class BaseComponent extends ImageView {
    protected IRequest request;
    protected Context mContext;

    public BaseComponent(Context context) {
        this(context, null);
    }

    public BaseComponent(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseComponent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        this.request = NetworkFactory.newInstance(context);
    }

    protected void reportInfo(final String impressionurl, IHtmlInfo model) {
        //TODO
        //doHtmlUrl(model);
        request.reportInfo(impressionurl, new RequestIterface.IReportInfoCallBack() {
            @Override
            public void onReportBack(boolean state) {
                if (state) {
                    Log.i("AppADSDK","report_successed");
                } else {
                    //失败处理
                    Log.e("AppADSDK","report_filed_"+impressionurl);
                }
            }
        });
    }

    private void doHtmlUrl(IHtmlInfo model) {
        if (model.getHtml5Info() != "") {
            doNetWork(model.getHtml5Info(), new IURLCallBack() {
                @Override
                public void URLCallBack(URLModel model) {
                    for (URLModel.UrlInfoEntity entity : model.getUrl_info()) {
                        request.reportInfo(entity.getUrl() + "&url_id=" + entity.getId(), new RequestIterface.IReportInfoCallBack() {
                            @Override
                            public void onReportBack(boolean state) {
                                /**
                                 * state callback false or true
                                 */
                            }
                        });
                    }
                }
            });
        }
    }

    private void doNetWork(final String urls, final IURLCallBack callBack) {
        request.getHitmlInfo(urls, new RequestIterface.IHtmlInfoCallBack() {
            @Override
            public void OnHtmlBack(URLModel modle) {
                callBack.URLCallBack(modle);
            }
        });
    }
}
