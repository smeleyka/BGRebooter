package ru.smeleyka.bgrebooter.model.entity;

/**
 * Created by smeleyka on 14.03.18.
 */

public class LoginRequest extends ZabbixObject {
    String method;
    Params params;
    String auth;

    public LoginRequest(String login, String password) {
      this.params = new Params(login,password);
      this.method = "user.login";
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
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
        String user;
        String password;

        Params(String user,String password){
            this.user=user;
            this.password=password;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

}
