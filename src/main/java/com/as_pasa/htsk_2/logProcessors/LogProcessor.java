package com.as_pasa.htsk_2.logProcessors;

import com.as_pasa.htsk_2.domain.LogContext;

public abstract class LogProcessor {
    public LogProcessor next;


    public abstract void process(LogContext context);

    public void handle(LogContext context) {
        process(context);
        if (next != null) {
            next.handle(context);
        }
    }

}
