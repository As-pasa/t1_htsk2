package com.as_pasa.htsk_2.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@ConfigurationProperties(prefix = "http.logging")
public class LogConfigurationProperties {
    private SignatureProperties signature= new SignatureProperties();
    private ArgumentProperties arguments=new ArgumentProperties();
    private URILogProperties uriProperties=new URILogProperties();
    private ResponseLogProperties response=new ResponseLogProperties();


    public SignatureProperties getSignature() {
        return signature;
    }

    public ArgumentProperties getArguments() {
        return arguments;
    }

    public URILogProperties getUriProperties() {
        return uriProperties;
    }

    public ResponseLogProperties getResponse() {
        return response;
    }

    public void setSignature(SignatureProperties signature) {
        this.signature = signature;
    }

    public void setArguments(ArgumentProperties arguments) {
        this.arguments = arguments;
    }

    public void setUriProperties(URILogProperties uriProperties) {
        this.uriProperties = uriProperties;
    }

    public void setResponse(ResponseLogProperties response) {
        this.response = response;
    }

    public static class SignatureProperties {
        public SignatureProperties(){
            format = "%method %url %status %duration";
            enableSignatureLogging=true;
        }
        private String format;
        private boolean enableSignatureLogging;

        public String getFormat() {
            return format;
        }

        public boolean isEnableSignatureLogging() {
            return enableSignatureLogging;
        }

        public void setFormat(String format) {
            this.format = format;
        }

        public void setEnableSignatureLogging(boolean enableSignatureLogging) {
            this.enableSignatureLogging = enableSignatureLogging;
        }
    }

    public static class ArgumentProperties {
        private Set<String> securedKeys;
        private boolean enableArgumentLogging;
        public ArgumentProperties(){
            securedKeys=new HashSet<>();
            enableArgumentLogging=true;
        }

        public Set<String> getSecuredKeys() {
            return securedKeys;
        }

        public boolean isEnableArgumentLogging() {
            return enableArgumentLogging;
        }

        public void setSecuredKeys(Set<String> securedKeys) {
            this.securedKeys = securedKeys;
        }

        public void setEnableArgumentLogging(boolean enableArgumentLogging) {
            this.enableArgumentLogging = enableArgumentLogging;
        }
    }

    public static class URILogProperties {
        private Set<String> ignoredURI;
        private boolean enableURIFiltering;
        public URILogProperties(){
            ignoredURI=new HashSet<>();
            enableURIFiltering=true;
        }

        public Set<String> getIgnoredURI() {
            return ignoredURI;
        }

        public boolean isEnableURIFiltering() {
            return enableURIFiltering;
        }

        public void setIgnoredURI(Set<String> ignoredURI) {
            this.ignoredURI = ignoredURI;
        }

        public void setEnableURIFiltering(boolean enableURIFiltering) {
            this.enableURIFiltering = enableURIFiltering;
        }
    }

    public static class ResponseLogProperties {
        private boolean enableResponseLogging;
        private Set<String> ignoreResponseFor;
        public ResponseLogProperties(){
            enableResponseLogging=true;
            ignoreResponseFor=new HashSet<>();
        }

        public boolean isEnableResponseLogging() {
            return enableResponseLogging;
        }

        public Set<String> getIgnoreResponseFor() {
            return ignoreResponseFor;
        }

        public void setEnableResponseLogging(boolean enableResponseLogging) {
            this.enableResponseLogging = enableResponseLogging;
        }

        public void setIgnoreResponseFor(Set<String> ignoreResponseFor) {
            this.ignoreResponseFor = ignoreResponseFor;
        }
    }
}
