package ru.smeleyka.bgrebooter.model.entity;

import java.util.zip.ZipError;

/**
 * Created by smeleyka on 15.03.18.
 */

public class LoginResponse extends ZabbixObject {

    String result;
    Zerror error;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
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
