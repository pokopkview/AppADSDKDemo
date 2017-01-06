package ad.joyplus.com.myapplication.entity;

/**
 * Config内的格式或是参数需要再具体定义
 *
 *
 * Created by UPC on 2016/8/29.
 */
public class ConfigModel<T> {

    /**
     * 广告为ID
     */
    private T publishID;
    /**
     *请求的服务器地址
     */
    private String baseURL;
    /**
     *储存广告的空间
     */
    private String maxSize;

    /**
     * 配置文件版本
     */
    private String versionCode;

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public T getPublishID() {
        return publishID;
    }

    public void setPublishID(T publishID) {
        this.publishID = publishID;
    }

    public String getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(String maxSize) {
        this.maxSize = maxSize;
    }

    public String getBaseURL() {
        return baseURL;
    }

    public void setBaseURL(String baseURL) {
        this.baseURL = baseURL;
    }
}
