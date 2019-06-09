package com.geetopod.models;

import java.io.PrintWriter;
import java.io.StringWriter;

public class BasicResponse {
    public boolean isError = false;
    public String errorCode = "";
    public String errorMessage = "";

    public BasicResponse setError(String errorCode, String errorMessage) {
        this.isError = true;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        return this;
    }

    public BasicResponse setError(String errorCode, Throwable e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        this.setError(errorCode, sw.toString());
        return this;
    }
}
