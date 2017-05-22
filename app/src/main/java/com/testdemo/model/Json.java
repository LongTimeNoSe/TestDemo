package com.testdemo.model;

import java.util.List;

/**
 * Created by XuYanping on 2017/3/22.
 */

public class Json {

    private List<JsonBean> json;

    public List<JsonBean> getJson() {
        return json;
    }

    public void setJson(List<JsonBean> json) {
        this.json = json;
    }

    public static class JsonBean {
        /**
         * deviceId : 设备3
         * position : 1
         * user : kimi
         */

        private String deviceId;
        private int position;
        private String user;

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }
    }
}
