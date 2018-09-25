package ru.smeleyka.bgrebooter.model.entity;

/**
 * Created by smeleyka on 14.03.18.
 */

public class UserGetResponse extends ZabbixApiObject {

    Result [] result;

    public class Result {
        String userid;
        String alias;
        String name;
        String surname;
        String url;
        int autologin;
        String autologout;
        String lang;
        String refresh;
        int type;
        String theme;
        int attemptFailed;
        String attemptIp;
        int attemptClock;
        int rowsPerPage;

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getAutologin() {
            return autologin;
        }

        public void setAutologin(int autologin) {
            this.autologin = autologin;
        }

        public String getAutologout() {
            return autologout;
        }

        public void setAutologout(String autologout) {
            this.autologout = autologout;
        }

        public String getLang() {
            return lang;
        }

        public void setLang(String lang) {
            this.lang = lang;
        }

        public String getRefresh() {
            return refresh;
        }

        public void setRefresh(String refresh) {
            this.refresh = refresh;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getTheme() {
            return theme;
        }

        public void setTheme(String theme) {
            this.theme = theme;
        }

        public int getAttemptFailed() {
            return attemptFailed;
        }

        public void setAttemptFailed(int attemptFailed) {
            this.attemptFailed = attemptFailed;
        }

        public String getAttemptIp() {
            return attemptIp;
        }

        public void setAttemptIp(String attemptIp) {
            this.attemptIp = attemptIp;
        }

        public int getAttemptClock() {
            return attemptClock;
        }

        public void setAttemptClock(int attemptClock) {
            this.attemptClock = attemptClock;
        }

        public int getRowsPerPage() {
            return rowsPerPage;
        }

        public void setRowsPerPage(int rowsPerPage) {
            this.rowsPerPage = rowsPerPage;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "userid='" + userid + '\'' +
                    ", alias='" + alias + '\'' +
                    ", name='" + name + '\'' +
                    ", surname='" + surname + '\'' +
                    ", url='" + url + '\'' +
                    ", autologin=" + autologin +
                    ", autologout=" + autologout +
                    ", lang='" + lang + '\'' +
                    ", refresh='" + refresh + '\'' +
                    ", type=" + type +
                    ", theme='" + theme + '\'' +
                    ", attemptFailed=" + attemptFailed +
                    ", attemptIp='" + attemptIp + '\'' +
                    ", attemptClock=" + attemptClock +
                    ", rowsPerPage=" + rowsPerPage +
                    '}';
        }
    }

    public Result[] getResult() {
        return result;
    }

    public void setResult(Result[] result) {
        this.result = result;
    }

    @Override
    public String toString() {
        int count=0;
        StringBuilder resultString = new StringBuilder();
        for (Result r:result) {
            resultString.append(count+"\n");
            resultString.append(r.toString());
            count++;
        }

        return resultString.toString();
    }
}
