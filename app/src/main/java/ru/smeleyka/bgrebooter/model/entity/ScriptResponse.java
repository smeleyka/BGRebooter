package ru.smeleyka.bgrebooter.model.entity;

/**
 * Created by smeleyka on 16.03.18.
 */

public class ScriptResponse extends ZabbixApiObject {

    private Result result;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public class Result{
    private String response;
    private String value;

        public String getResponse() {
            return response;
        }

        public void setResponse(String response) {
            this.response = response;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
