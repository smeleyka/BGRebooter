package ru.smeleyka.bgrebooter.model.entity;

/**
 * Created by smeleyka on 16.03.18.
 */

public abstract class ZabbixApiRequest extends ZabbixApiObject{

    private String method;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
