package example.yzz.recyclerviewdemo;

/**
 * 描述:
 * Created by yzz on 2018/3/27.
 */

public class BaseBean {

    /**
     * code :
     * msg :
     * status : Y
     * res : {"expires":21600,"token":"f00c0a47f1aa716616c3812ea0f19439"}
     */

    private String code;
    private String msg;
    private String status;
    private ResBean res;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ResBean getRes() {
        return res;
    }

    public void setRes(ResBean res) {
        this.res = res;
    }

    public static class ResBean {
        /**
         * expires : 21600
         * token : f00c0a47f1aa716616c3812ea0f19439
         */

        private int expires;
        private String token;

        public int getExpires() {
            return expires;
        }

        public void setExpires(int expires) {
            this.expires = expires;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
