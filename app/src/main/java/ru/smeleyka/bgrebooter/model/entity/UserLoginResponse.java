package ru.smeleyka.bgrebooter.model.entity;

/**
 * Created by smeleyka on 15.03.18.
 */

public class UserLoginResponse extends ZabbixApiObject {

    String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
