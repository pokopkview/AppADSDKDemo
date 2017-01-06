package ad.joyplus.com.myapplication.compoment;

import android.content.Context;
import android.util.AttributeSet;

import ad.joyplus.com.myapplication.entity.ImageViewModel;
import ad.joyplus.com.myapplication.managers.AppADSDKManager;

/**
 * Created by UPC on 2016/9/21.
 * 视频暂停时的暂停图片广告
 */
public class VideoShowImageView extends BaseComponent implements IComponent {
    private ImageViewModel imageViewModel;
    public VideoShowImageView(Context context) {
        super(context);
    }

    public VideoShowImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VideoShowImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        disPlay();
    }

    @Override
    public void disPlay() {
        imageViewModel = AppADSDKManager.getInstance(mContext).getVideoImgModel();
        if(this.imageViewModel.getImageurl() != null) {
            this.request.getImgFromGlide(this, this.imageViewModel.getImageurl(), this.mContext);
        }
        //reportInfo(imageViewModel.getImpressionurl());
    }

    @Override
    public void removeclick() {
        this.setOnClickListener(null);
    }
}
