package ru.smeleyka.bgrebooter.model.entity;

import com.google.gson.annotations.SerializedName;

public class HostgroupGetRequest extends ZabbixApiRequest {
    private Params params;

    public HostgroupGetRequest(String auth) {
        this.params = new Params();
        params.setRealHosts(1);
        params.setOutput("extend");
        params.setSortfield("name");
        params.setSortorder("ASC");
        setAuth(auth);
        setMethod("hostgroup.get");
    }

    public Params getParams() {
        return params;
    }

    public void setParams(Params params) {
        this.params = params;
    }

    private class Params {
        int realHosts;
        String output;
        @SerializedName("selectHosts")
        SelectHosts selectHosts;

        String sortfield;
        String sortorder;

        public Params() {
            this.selectHosts = new SelectHosts();
        }

        public int getRealHosts() {
            return realHosts;
        }

        public void setRealHosts(int realHosts) {
            this.realHosts = realHosts;
        }

        public String getOutput() {
            return output;
        }

        public void setOutput(String output) {
            this.output = output;
        }

        public SelectHosts getSelectHosts() {
            return selectHosts;
        }

        public void setSelectHosts(SelectHosts selectHosts) {
            this.selectHosts = selectHosts;
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
