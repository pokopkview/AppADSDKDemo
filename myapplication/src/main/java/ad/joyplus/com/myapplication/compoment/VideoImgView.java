package ad.joyplus.com.myapplication.compoment;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import ad.joyplus.com.myapplication.entity.ImageViewModel;
import ad.joyplus.com.myapplication.managers.AppADSDKManager;

/**
 * Created by UPC on 2016/9/9.
 */
public class VideoImgView extends BaseComponent implements IComponent,View.OnClickListener {
    private ImageViewModel imageViewModel;

    public VideoImgView(Context context) {
        this(context,null);
    }

    public VideoImgView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public VideoImgView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        disPlay();

    }

    @Override
    public void disPlay() {
        imageViewModel = AppADSDKManager.getInstance(mContext).getVideoImgModel();
        request.getImgFromGlide(this,imageViewModel.getImageurl(),mContext);
        if(!imageViewModel.getImpressionurl().isEmpty()) {
            reportInfo(imageViewModel.getImpressionurl(), imageViewModel);//上报
        }
        this.setOnClickListener(this);
    }

    @Override
    public void removeclick() {
        this.setOnClickListener(null);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(mContext,"图片被点击了",Toast.LENGTH_LONG).show();
    }
}
