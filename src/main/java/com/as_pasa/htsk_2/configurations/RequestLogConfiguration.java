package com.as_pasa.htsk_2.configurations;

import com.as_pasa.htsk_2.logProcessors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RequestLogConfiguration {

    private LogConfigurationProperties properties;
    @Autowired
    public RequestLogConfiguration(LogConfigurationProperties properties) {
        this.properties = properties;
    }

    @Bean
    public LogProcessor getLogProcessor(){
        LogProcessor processor=new EmptyProcessor();
        LogProcessor last=processor;
        if(properties.getSignature().isEnableSignatureLogging()){
            LogProcessor nxt=new SignatureProcessor(properties.getSignature().getFormat());
            last.next=nxt;
            last=nxt;
        }
        if(properties.getArguments().isEnableArgumentLogging()){
            LogProcessor nxt=new ArgsLogProcessor(properties.getArguments().getSecuredKeys());
            last.next=nxt;
            last=nxt;
        }
        if(properties.getUriProperties().isEnableURIFiltering()){
            LogProcessor nxt = new URIFilteringProcessor(properties.getUriProperties().getIgnoredURI());
            last.next=nxt;
            last=nxt;
        }
        if(properties.getResponse().isEnableResponseLogging()){
            LogProcessor nxt = new ResponseLogProcessor(properties.getResponse().getIgnoreResponseFor());
            last.next=nxt;
            last=nxt;
        }
        return processor;
    }
}
