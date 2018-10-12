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

    public List<Hostgroup> getHostgroupList() {
        if (hostgroup == null) {
            return null;
        }
        return converter(hostgroup);
    }

    private List<Hostgroup> converter(Hostgroup hostgroup) {
        ArrayList<Hostgroup> hostgroupList = new ArrayList<>();
        hostgroupList.add(hostgroup);
        return hostgroupList;
    }

    private List<Hostgroup> converter(Hostgroup[] hostgroup) {
        ArrayList<Hostgroup> hostgroupList = new ArrayList<>(Arrays.asList(hostgroup));
        return hostgroupList;
    }

    public class Hostgroup {
        String groupid;
        String name;
        Hosts[] hosts;

        public String getGroupid() {
            return groupid;
        }

        public void setGroupid(String groupid) {
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
