//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ad.joyplus.com.myapplication.compoment;

import android.content.Context;
import android.util.AttributeSet;

import ad.joyplus.com.myapplication.entity.ImageViewModel;
import ad.joyplus.com.myapplication.managers.AppADSDKManager;

public class SimpleImageView extends BaseComponent implements IComponent {
    private ImageViewModel simpleModel;

    public SimpleImageView(Context context) {
        super(context);
        this.disPlay();
    }

    public SimpleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.disPlay();
    }

    public SimpleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.disPlay();
    }

    public void disPlay() {
        this.simpleModel = AppADSDKManager.getInstance(this.mContext).getSimpleImgModel();
        if(!simpleModel.getImpressionurl().isEmpty()) {
            reportInfo(simpleModel.getImpressionurl(), simpleModel);
        }
        if(this.simpleModel.getImageurl() != null) {
            this.request.getImgFromGlide(this, this.simpleModel.getImageurl(), this.mContext);
        }
    }

    public void removeclick() {
        this.setOnClickListener((OnClickListener)null);
    }

}
