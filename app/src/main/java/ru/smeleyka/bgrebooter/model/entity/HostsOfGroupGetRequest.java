package ru.smeleyka.bgrebooter.model.entity;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class HostsOfGroupGetRequest extends ZabbixApiRequest {
    private Params params;

    public HostsOfGroupGetRequest(String auth, int groupId) {
        this.params = new Params();
        params.setGroupids(""+groupId);
        params.addOutputParam("hostid", "name", "host", "status");
        params.setSortfield("name");
        params.setSortorder("ASC");
        setAuth(auth);
        setMethod("host.get");
    }

    public Params getParams() {
        return params;
    }

    public void setParams(Params params) {
        this.params = params;
    }

    private class Params {

        String groupids;
        ArrayList<String> output;
        String sortfield;
        String sortorder;

        public ArrayList<String> getOutput() {
            return output;
        }

        public void setOutput(ArrayList<String> output) {
            this.output = output;
        }

        public void addOutputParam(String... param) {
            if (this.output == null) {
                this.output = new ArrayList<>();
            }
            for (String p : param) {
                output.add(p);
            }
        }

        public String getGroupids() {
            return groupids;
        }

        public void setGroupids(String groupids) {
            this.groupids = groupids;
        }

        public String getSortfield() {
            return sortfield;
        }

        public void setSortfield(String sortfield) {
            this.sortfield = sortfield;
        }

        public String getSortorder() {
            return sortorder;
        }

        public void setSortorder(String sortorder) {
            this.sortorder = sortorder;
        }

        private class SelectHosts {
        }
    }
}
