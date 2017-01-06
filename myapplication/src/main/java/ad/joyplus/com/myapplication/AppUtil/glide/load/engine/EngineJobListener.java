package ad.joyplus.com.myapplication.AppUtil.glide.load.engine;


import ad.joyplus.com.myapplication.AppUtil.glide.load.Key;

interface EngineJobListener {

    void onEngineJobComplete(Key key, EngineResource<?> resource);

    void onEngineJobCancelled(EngineJob engineJob, Key key);
}
