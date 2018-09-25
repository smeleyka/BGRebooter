package ru.smeleyka.bgrebooter.model.entity;

/**
 * Created by smeleyka on 14.03.18.
 */

public class UserGetRequest extends ZabbixApiRequest {
    private Params params;

    public UserGetRequest(String authKey) {
        this.params=new Params();
        setAuth(authKey);
        setMethod("user.get");
    }

    public Params getParams() {
        return params;
    }

    public void setParams(Params params) {
        this.params = params;
    }

    class Params{

    }

}
