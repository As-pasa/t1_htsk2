package com.as_pasa.htsk_2.aspects;

import com.as_pasa.htsk_2.annotations.LogCall;
import com.as_pasa.htsk_2.domain.LogContext;
import com.as_pasa.htsk_2.logProcessors.LogProcessor;


import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Aspect
@Component
@Order(1)
public class LoggingAspect {

    private LogProcessor logProcessor;

    @Autowired
    public LoggingAspect(LogProcessor logProcessor) {
        this.logProcessor = logProcessor;
    }


    private final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("@annotation(logCall)")
    public void logPointCut(LogCall logCall) {
    }

    @Around("logPointCut(logCall)")
    public Object around(ProceedingJoinPoint joinPoint, LogCall logCall) throws Throwable {
        long startTime = System.currentTimeMillis();
        String responseBodyStr="";
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        try {
            Object responseBody=joinPoint.proceed();
            responseBodyStr=responseBody.toString();
            return responseBody;
        } finally {
            long duration = System.currentTimeMillis() - startTime;
            ServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
            int status = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse().getStatus();
            LogContext context = new LogContext("", request.getParameterMap(), duration, status, request, response, responseBodyStr);
            logProcessor.handle(context);
            String log = context.getLog();
            if(!log.isEmpty()){
                logger.info(log);
            }
        }
    }
}
