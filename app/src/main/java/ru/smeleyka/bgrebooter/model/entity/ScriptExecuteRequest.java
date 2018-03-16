package ru.smeleyka.bgrebooter.model.entity;

/**
 * Created by smeleyka on 16.03.18.
 */

public class ScriptExecuteRequest extends ZabbixApiRequest {
    private String auth;
    private Params params;

    public ScriptExecuteRequest(String auth, int scriptid, int hostid) {
        this.auth = auth;
        this.params = new Params(scriptid,hostid);
        setMethod("script.execute");
    }

    class Params{
    private int scriptid;
    private int hostid;

        public Params(int scriptid, int hostid) {
            this.scriptid = scriptid;
            this.hostid = hostid;
        }

        public int getScriptid() {
            return scriptid;
        }

        public void setScriptid(int scriptid) {
            this.scriptid = scriptid;
        }

        public int getHostid() {
            return hostid;
        }

        public void setHostid(int hostid) {
            this.hostid = hostid;
        }
    }
}
