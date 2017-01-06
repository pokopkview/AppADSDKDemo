package ad.joyplus.com.myapplication.compoment;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import ad.joyplus.com.myapplication.entity.OpeningAdModel;
import ad.joyplus.com.myapplication.managers.AppADSDKManager;

/**
 * 用于开屏广告的组件
 * <p/>
 * <p/>
 * Created by UPC on 2016/8/29.
 */
public class OpeningView extends BaseComponent implements IComponent, View.OnClickListener {
    private OpeningAdModel openmodel;

    public OpeningView(Context context) {
        this(context, null);
    }

    public OpeningView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OpeningView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    // TODO: 2016/11/21 解析html5_url
    @Override
    public void disPlay() {
        openmodel = AppADSDKManager.getInstance(mContext).getOpeningModel();
        request.getImgFromGlide(this,openmodel.getImageurl(),mContext);
        if(!openmodel.getImpressionurl().isEmpty()) {
            reportInfo(openmodel.getImpressionurl(), openmodel);//上报
        }
        this.setOnClickListener(this);
    }

    @Override
    public void removeclick() {
        this.setOnClickListener(null);
    }

    @Override
    public void onClick(View v) {
        if (openmodel == null) {
            return;
        }
    }

}
