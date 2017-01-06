package ad.joyplus.com.myapplication.entity;

import java.util.List;

/**
 * Created by UPC on 2016/9/9.
 */
public class ImageViewModel implements IHtmlInfo {
    private String imageurl;
    private String impressionurl;
    private String clickurl;
    private String showcount;
    private String html5_url;
    private List<String> thridreport;

    public List<String> getThridreport() {
        return thridreport;
    }

    public void setThridreport(List<String> thridreport) {
        this.thridreport = thridreport;
    }


    public String getHtml5_url() {
        return html5_url;
    }

    public void setHtml5_url(String html5_url) {
        this.html5_url = html5_url;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getImpressionurl() {
        return impressionurl;
    }

    public void setImpressionurl(String impressionurl) {
        this.impressionurl = impressionurl;
    }

    public String getClickurl() {
        return clickurl;
    }

    public void setClickurl(String clickurl) {
        this.clickurl = clickurl;
    }

    public String getShowcount() {
        return showcount;
    }

    public void setShowcount(String showcount) {
        this.showcount = showcount;
    }

    @Override
    public String getHtml5Info() {
        return this.html5_url;
    }

    @Override
    public String getReportinfo() {
        if(impressionurl!=null){
            return impressionurl;
        }
        return null;
    }
}
