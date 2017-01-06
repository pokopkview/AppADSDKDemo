package ad.joyplus.com.myapplication.AppUtil.glide;

import ad.joyplus.com.myapplication.AppUtil.glide.GenericRequestBuilder;

interface BitmapOptions {

    GenericRequestBuilder<?, ?, ?, ?> fitCenter();

    GenericRequestBuilder<?, ?, ?, ?> centerCrop();

}
