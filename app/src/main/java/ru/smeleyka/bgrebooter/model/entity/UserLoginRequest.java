package ru.smeleyka.bgrebooter.model.entity;

/**
 * Created by smeleyka on 14.03.18.
 */

public class UserLoginRequest extends ZabbixApiRequest {
    private Params params;

    public UserLoginRequest(String login, String password) {
        this.params = new Params();
        params.setUser(login);
        params.setPassword(password);
        setMethod("user.login");
    }

    public Params getParams() {
        return params;
    }

    public void setParams(Params params) {
        this.params = params;
    }

    private class Params{
        String user;
        String password;

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
