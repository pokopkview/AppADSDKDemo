package ad.joyplus.com.myapplication.entity;

import java.util.List;

/**
 * Created by UPC on 2016/11/21.
 */
public class URLModel {

    /**
     * id : 123
     * url : http://www.google.com
     */

    private List<UrlInfoEntity> url_info;

    public List<UrlInfoEntity> getUrl_info() {
        return url_info;
    }

    public void setUrl_info(List<UrlInfoEntity> url_info) {
        this.url_info = url_info;
    }

    public static class UrlInfoEntity {
        private String id;
        private String url;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
