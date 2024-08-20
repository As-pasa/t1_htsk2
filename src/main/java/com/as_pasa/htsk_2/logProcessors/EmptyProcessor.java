package com.as_pasa.htsk_2.logProcessors;

import com.as_pasa.htsk_2.domain.LogContext;

public class EmptyProcessor extends LogProcessor{
    public EmptyProcessor() {

    }

    @Override
    public void process(LogContext context) {}
}
