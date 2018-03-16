package ru.smeleyka.bgrebooter.model.entity;

/**
 * Created by smeleyka on 15.03.18.
 */

public abstract class ZabbixApiObject {
    private int id;
    private String jsonrpc;
    private Zerror error;

    ZabbixApiObject(){
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

    public Zerror getError() {
        return error;
    }

    public void setError(Zerror error) {
        this.error = error;
    }

    public class Zerror{

        int code;
        String message;
        String data;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }

}
