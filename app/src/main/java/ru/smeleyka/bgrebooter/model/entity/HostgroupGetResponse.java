package ru.smeleyka.bgrebooter.model.entity;

public class HostgroupGetResponse extends ZabbixApiObject {
    Result result;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public class Result {
        int groupid;
        String name;
        Hosts hosts;

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

        public Hosts getHosts() {
            return hosts;
        }

        public void setHosts(Hosts hosts) {
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
