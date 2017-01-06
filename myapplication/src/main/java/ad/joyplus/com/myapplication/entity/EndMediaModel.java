package ad.joyplus.com.myapplication.entity;

import java.util.List;

/**
 * Created by UPC on 2016/9/7.
 */
public class EndMediaModel implements IHtmlInfo {

    private String videourl;
    private String impressionurl;
    private String clickurl;
    private String showtime;
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

    public String getVideourl() {
        return videourl;
    }

    public void setVideourl(String videourl) {
        this.videourl = videourl;
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

    public String getShowtime() {
        return showtime;
    }

    public void setShowtime(String showtime) {
        this.showtime = showtime;
    }

    @Override
    public String getHtml5Info() {
        return this.html5_url;
    }

    @Override
    public String getReportinfo() {
        return null;
    }

}
