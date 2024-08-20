package com.as_pasa.htsk_2.logProcessors;

import com.as_pasa.htsk_2.domain.LogContext;
import jakarta.servlet.http.HttpServletRequest;

public class SignatureProcessor extends LogProcessor {
    private String SignatureFormat;


    public SignatureProcessor(String signatureFormat) {

        SignatureFormat = signatureFormat;

    }

    @Override
    public void process(LogContext context) {
        context.addLog(formatLogMessage(context.getRequest(), context.getStatus(), context.getDuration()));
    }

    private String formatLogMessage(HttpServletRequest request, int status, long duration) {
        String message = SignatureFormat;
        message = message.replace("%method", request.getMethod());
        message = message.replace("%url", request.getRequestURI());
        message = message.replace("%status", String.valueOf(status));
        message = message.replace("%duration", String.valueOf(duration) + "ms");
        return message;
    }

}
