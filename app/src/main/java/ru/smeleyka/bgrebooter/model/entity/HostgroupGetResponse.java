package ru.smeleyka.bgrebooter.model.entity;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HostgroupGetResponse extends ZabbixApiObject {
    @SerializedName("result")
    Hostgroup[] hostgroup;

    public Hostgroup[] getResult() {
        return hostgroup;
    }

    public void setResult(Hostgroup[] result) {
        this.hostgroup = result;
    }

    public class Hostgroup {
        int groupid;
        String name;
        Hosts[] hosts;

        public int getGroupid() {
            return groupid;
        }

        public void setGroupid(int groupid) {
            this.groupid = groupid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Hosts[] getHosts() {
            return hosts;
        }

        public void setHosts(Hosts[] hosts) {
            this.hosts = hosts;
        }

        public class Hosts {
            int hostid;

            public int getHostid() {
                return hostid;
            }

            public void setHostid(int hostid) {
                this.hostid = hostid;
            }
        }
    }
}
