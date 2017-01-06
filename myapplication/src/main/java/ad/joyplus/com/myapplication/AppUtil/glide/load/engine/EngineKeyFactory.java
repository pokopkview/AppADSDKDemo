package ad.joyplus.com.myapplication.AppUtil.glide.load.engine;


import ad.joyplus.com.myapplication.AppUtil.glide.load.Encoder;
import ad.joyplus.com.myapplication.AppUtil.glide.load.Key;
import ad.joyplus.com.myapplication.AppUtil.glide.load.ResourceDecoder;
import ad.joyplus.com.myapplication.AppUtil.glide.load.ResourceEncoder;
import ad.joyplus.com.myapplication.AppUtil.glide.load.Transformation;
import ad.joyplus.com.myapplication.AppUtil.glide.load.resource.transcode.ResourceTranscoder;

class EngineKeyFactory {

    @SuppressWarnings("rawtypes")
    public EngineKey buildKey(String id, Key signature, int width, int height, ResourceDecoder cacheDecoder,
                              ResourceDecoder sourceDecoder, Transformation transformation, ResourceEncoder encoder,
                              ResourceTranscoder transcoder, Encoder sourceEncoder) {
        return new EngineKey(id, signature, width, height, cacheDecoder, sourceDecoder, transformation, encoder,
                transcoder, sourceEncoder);
    }

}
