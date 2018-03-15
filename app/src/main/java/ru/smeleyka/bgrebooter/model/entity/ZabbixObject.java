package ru.smeleyka.bgrebooter.model.entity;

/**
 * Created by smeleyka on 15.03.18.
 */

public abstract class ZabbixObject {
    int id;
    String jsonrpc;

    ZabbixObject(){
        this.id = 1;
        this.jsonrpc = "2.0";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJsonrpc() {
        return jsonrpc;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }
}
