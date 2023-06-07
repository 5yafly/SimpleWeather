package com.example.luoxiongzhang.object_class;

import java.util.List;

public class Daily {

    /**
     * code : 200
     * updateTime : 2021-11-15T16:35+08:00
     * fxLink : http://hfx.link/2ax1
     * daily : [{"fxDate":"2021-11-15","sunrise":"06:58","sunset":"16:59","moonrise":"15:16","moonset":"03:40","moonPhase":"盈凸月","moonPhaseIcon":"803","tempMax":"12","tempMin":"-1","iconDay":"101","textDay":"多云","iconNight":"150","textNight":"晴","wind360Day":"45","windDirDay":"东北风","windScaleDay":"1-2","windSpeedDay":"3","wind360Night":"0","windDirNight":"北风","windScaleNight":"1-2","windSpeedNight":"3","humidity":"65","precip":"0.0","pressure":"1020","vis":"25","cloud":"4","uvIndex":"3"},{"fxDate":"2021-11-16","sunrise":"07:00","sunset":"16:58","moonrise":"15:38","moonset":"04:40","moonPhase":"盈凸月","moonPhaseIcon":"803","tempMax":"13","tempMin":"0","iconDay":"100","textDay":"晴","iconNight":"101","textNight":"多云","wind360Day":"225","windDirDay":"西南风","windScaleDay":"1-2","windSpeedDay":"3","wind360Night":"225","windDirNight":"西南风","windScaleNight":"1-2","windSpeedNight":"3","humidity":"74","precip":"0.0","pressure":"1016","vis":"25","cloud":"1","uvIndex":"3"},{"fxDate":"2021-11-17","sunrise":"07:01","sunset":"16:57","moonrise":"16:01","moonset":"05:41","moonPhase":"盈凸月","moonPhaseIcon":"803","tempMax":"13","tempMin":"0","iconDay":"100","textDay":"晴","iconNight":"150","textNight":"晴","wind360Day":"225","windDirDay":"西南风","windScaleDay":"1-2","windSpeedDay":"3","wind360Night":"225","windDirNight":"西南风","windScaleNight":"1-2","windSpeedNight":"3","humidity":"56","precip":"0.0","pressure":"1009","vis":"25","cloud":"0","uvIndex":"3"}]
     * refer : {"sources":["QWeather","NMC","ECMWF"],"license":["QWeather Developers License"]}
     */

    private String code;
    private String updateTime;
    private String fxLink;
    private ReferBean refer;
    private List<DailyBean> daily;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getFxLink() {
        return fxLink;
    }

    public void setFxLink(String fxLink) {
        this.fxLink = fxLink;
    }

    public ReferBean getRefer() {
        return refer;
    }

    public void setRefer(ReferBean refer) {
        this.refer = refer;
    }

    public List<DailyBean> getDaily() {
        return daily;
    }

    public void setDaily(List<DailyBean> daily) {
        this.daily = daily;
    }

    public static class ReferBean {
        private List<String> sources;
        private List<String> license;

        public List<String> getSources() {
            return sources;
        }

        public void setSources(List<String> sources) {
            this.sources = sources;
        }

        public List<String> getLicense() {
            return license;
        }

        public void setLicense(List<String> license) {
            this.license = license;
        }
    }

    public static class DailyBean {
        /**
         * fxDate : 2021-11-15
         * sunrise : 06:58
         * sunset : 16:59
         * moonrise : 15:16
         * moonset : 03:40
         * moonPhase : 盈凸月
         * moonPhaseIcon : 803
         * tempMax : 12
         * tempMin : -1
         * iconDay : 101
         * textDay : 多云
         * iconNight : 150
         * textNight : 晴
         * wind360Day : 45
         * windDirDay : 东北风
         * windScaleDay : 1-2
         * windSpeedDay : 3
         * wind360Night : 0
         * windDirNight : 北风
         * windScaleNight : 1-2
         * windSpeedNight : 3
         * humidity : 65
         * precip : 0.0
         * pressure : 1020
         * vis : 25
         * cloud : 4
         * uvIndex : 3
         */

        private String fxDate;
        private String sunrise;
        private String sunset;
        private String moonrise;
        private String moonset;
        private String moonPhase;
        private String moonPhaseIcon;
        private String tempMax;
        private String tempMin;
        private String iconDay;
        private String textDay;
        private String iconNight;
        private String textNight;
        private String wind360Day;
        private String windDirDay;
        private String windScaleDay;
        private String windSpeedDay;
        private String wind360Night;
        private String windDirNight;
        private String windScaleNight;
        private String windSpeedNight;
        private String humidity;
        private String precip;
        private String pressure;
        private String vis;
        private String cloud;
        private String uvIndex;

        public String getFxDate() {
            return fxDate;
        }

        public void setFxDate(String fxDate) {
            this.fxDate = fxDate;
        }

        public String getSunrise() {
            return sunrise;
        }

        public void setSunrise(String sunrise) {
            this.sunrise = sunrise;
        }

        public String getSunset() {
            return sunset;
        }

        public void setSunset(String sunset) {
            this.sunset = sunset;
        }

        public String getMoonrise() {
            return moonrise;
        }

        public void setMoonrise(String moonrise) {
            this.moonrise = moonrise;
        }

        public String getMoonset() {
            return moonset;
        }

        public void setMoonset(String moonset) {
            this.moonset = moonset;
        }

        public String getMoonPhase() {
            return moonPhase;
        }

        public void setMoonPhase(String moonPhase) {
            this.moonPhase = moonPhase;
        }

        public String getMoonPhaseIcon() {
            return moonPhaseIcon;
        }

        public void setMoonPhaseIcon(String moonPhaseIcon) {
            this.moonPhaseIcon = moonPhaseIcon;
        }

        public String getTempMax() {
            return tempMax;
        }

        public void setTempMax(String tempMax) {
            this.tempMax = tempMax;
        }

        public String getTempMin() {
            return tempMin;
        }

        public void setTempMin(String tempMin) {
            this.tempMin = tempMin;
        }

        public String getIconDay() {
            return iconDay;
        }

        public void setIconDay(String iconDay) {
            this.iconDay = iconDay;
        }

        public String getTextDay() {
            return textDay;
        }

        public void setTextDay(String textDay) {
            this.textDay = textDay;
        }

        public String getIconNight() {
            return iconNight;
        }

        public void setIconNight(String iconNight) {
            this.iconNight = iconNight;
        }

        public String getTextNight() {
            return textNight;
        }

        public void setTextNight(String textNight) {
            this.textNight = textNight;
        }

        public String getWind360Day() {
            return wind360Day;
        }

        public void setWind360Day(String wind360Day) {
            this.wind360Day = wind360Day;
        }

        public String getWindDirDay() {
            return windDirDay;
        }

        public void setWindDirDay(String windDirDay) {
            this.windDirDay = windDirDay;
        }

        public String getWindScaleDay() {
            return windScaleDay;
        }

        public void setWindScaleDay(String windScaleDay) {
            this.windScaleDay = windScaleDay;
        }

        public String getWindSpeedDay() {
            return windSpeedDay;
        }

        public void setWindSpeedDay(String windSpeedDay) {
            this.windSpeedDay = windSpeedDay;
        }

        public String getWind360Night() {
            return wind360Night;
        }

        public void setWind360Night(String wind360Night) {
            this.wind360Night = wind360Night;
        }

        public String getWindDirNight() {
            return windDirNight;
        }

        public void setWindDirNight(String windDirNight) {
            this.windDirNight = windDirNight;
        }

        public String getWindScaleNight() {
            return windScaleNight;
        }

        public void setWindScaleNight(String windScaleNight) {
            this.windScaleNight = windScaleNight;
        }

        public String getWindSpeedNight() {
            return windSpeedNight;
        }

        public void setWindSpeedNight(String windSpeedNight) {
            this.windSpeedNight = windSpeedNight;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public String getPrecip() {
            return precip;
        }

        public void setPrecip(String precip) {
            this.precip = precip;
        }

        public String getPressure() {
            return pressure;
        }

        public void setPressure(String pressure) {
            this.pressure = pressure;
        }

        public String getVis() {
            return vis;
        }

        public void setVis(String vis) {
            this.vis = vis;
        }

        public String getCloud() {
            return cloud;
        }

        public void setCloud(String cloud) {
            this.cloud = cloud;
        }

        public String getUvIndex() {
            return uvIndex;
        }

        public void setUvIndex(String uvIndex) {
            this.uvIndex = uvIndex;
        }
    }
}
