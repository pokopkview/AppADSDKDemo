//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ad.joyplus.com.myapplication.compoment;

import android.content.Context;
import android.util.AttributeSet;

import ad.joyplus.com.myapplication.entity.ImageViewModel;
import ad.joyplus.com.myapplication.managers.AppADSDKManager;

public class CornerImageView extends BaseComponent implements IComponent {
    private ImageViewModel cornerModel;

    public CornerImageView(Context context) {
        super(context);
        this.disPlay();
    }

    public CornerImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.disPlay();
    }

    public CornerImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.disPlay();
    }

    public void disPlay() {
        this.cornerModel = AppADSDKManager.getInstance(this.mContext).getCornerImgModel();
        if(!cornerModel.getImpressionurl().isEmpty()) {
            reportInfo(cornerModel.getImpressionurl(), cornerModel);

        }
        if(this.cornerModel.getImageurl() != null) {
            this.request.getImgFromGlide(this, this.cornerModel.getImageurl(), this.mContext);
        }
    }
    public void removeclick() {
        this.setOnClickListener((OnClickListener)null);
    }
}
