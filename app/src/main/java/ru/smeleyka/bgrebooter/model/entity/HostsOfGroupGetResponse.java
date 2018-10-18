package ru.smeleyka.bgrebooter.model.entity;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HostsOfGroupGetResponse extends ZabbixApiObject {
    @SerializedName("result")
    Host[] hosts;

    public Host[] getHosts() {
        return hosts;
    }

    public void setHosts(Host[] hosts) {
        this.hosts = hosts;
    }

    public class Host {
        int hostid;
        String name;
        String host;
        int status;

        public int getHostid() {
            return hostid;
        }

        public void setHostid(int hostid) {
            this.hostid = hostid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
