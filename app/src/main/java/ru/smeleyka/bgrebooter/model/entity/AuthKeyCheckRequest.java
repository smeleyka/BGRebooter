package ru.smeleyka.bgrebooter.model.entity;

/**
 * Created by smeleyka on 14.03.18.
 */

public class AuthKeyCheckRequest extends ZabbixApiRequest {
    private Params params;
    private String auth;

    public AuthKeyCheckRequest(String authKey) {
        this.auth=authKey;
        this.params = new Params();
        setMethod("user.get");
    }

    public Params getParams() {
        return params;
    }

    public void setParams(Params params) {
        this.params = params;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    class Params{

    }

}
