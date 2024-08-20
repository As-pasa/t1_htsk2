package com.as_pasa.htsk_2.logProcessors;

import com.as_pasa.htsk_2.domain.LogContext;

import java.util.Set;

public class ResponseLogProcessor extends LogProcessor{
    private Set<String> bannedURI;

    public ResponseLogProcessor(Set<String> bannedURI) {
        this.bannedURI = bannedURI;
    }

    @Override
    public void process(LogContext context) {
        if(!bannedURI.contains(context.getRequest().getRequestURI())){
            context.addLog("RESPONSE=["+ context.getResponseBody()+"]");
        }

    }
}
