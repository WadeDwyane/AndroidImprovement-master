package com.wadedwyane.customokhttp;

/**
 * @author kui.liu
 * @time 2019/4/10 11:18
 */
public class Response {
    private String resultcode;
    private String error_code;
    private String reason;
    private String result;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }


    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    @Override
    public String toString() {
        return "Response{" +
                "resultcode='" + resultcode + '\'' +
                ", error_code='" + error_code + '\'' +
                ", reason='" + reason + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
