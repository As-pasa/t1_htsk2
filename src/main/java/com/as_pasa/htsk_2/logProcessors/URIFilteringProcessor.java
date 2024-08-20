package com.as_pasa.htsk_2.logProcessors;

import com.as_pasa.htsk_2.domain.LogContext;

import java.util.Set;

public class URIFilteringProcessor extends LogProcessor{
    private Set<String> ignoredUri;

    public URIFilteringProcessor(Set<String> ignoredUri) {
        this.ignoredUri = ignoredUri;
    }

    @Override
    public void process(LogContext context) {
        if(ignoredUri.contains(context.getRequest().getRequestURI())){
            context.setLogCanceled(true);
        }
    }
}
