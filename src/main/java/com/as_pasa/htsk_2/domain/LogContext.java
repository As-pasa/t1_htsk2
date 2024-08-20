package com.as_pasa.htsk_2.domain;

import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;

public class LogContext {
    private String log="";
    private boolean LogCanceled=false;
    private Map<String, String[]> parameters;
    private long duration;
    private int status;
    private HttpServletRequest request;
    private ServletResponse response;
    private String responseBody;

    public LogContext(String logFormat, Map<String, String[]> parameters, long duration, int status, HttpServletRequest request, ServletResponse response, String responseBody) {
        this.parameters = parameters;

        this.duration = duration;
        this.status = status;
        this.request = request;
        this.response = response;
        this.responseBody = responseBody;
    }
    public String getLog(){
        if(isLogCanceled()){
            return "";
        }
        else{
            return log;
        }

    }

    public void addLog(String log) {
        this.log=this.log+" "+ log;
    }


    public long getDuration() {
        return duration;
    }

    public int getStatus() {
        return status;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public ServletResponse getResponse() {
        return response;
    }

    public Map<String, String[]> getParameters() {
        return parameters;
    }

    public boolean isLogCanceled() {
        return LogCanceled;
    }

    public void setLogCanceled(boolean logCanceled) {
        LogCanceled = logCanceled;
    }

    public String getResponseBody() {
        return responseBody;
    }
}
