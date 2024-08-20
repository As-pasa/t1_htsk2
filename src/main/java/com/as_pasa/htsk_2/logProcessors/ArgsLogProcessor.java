package com.as_pasa.htsk_2.logProcessors;


import com.as_pasa.htsk_2.domain.LogContext;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ArgsLogProcessor extends LogProcessor {
    private final Set<String> sensitiveKeys;
    private LogProcessor next;

    public ArgsLogProcessor(Set<String> sensitiveKeys) {
        this.sensitiveKeys = sensitiveKeys;
    }

    @Override
    public void process(LogContext context) {

        Map<String, String[]> maskedParameters = new HashMap<>();
        for (Map.Entry<String, String[]> entry : context.getParameters().entrySet()) {
            if (sensitiveKeys.contains(entry.getKey())) {
                String[] maskedValues = new String[entry.getValue().length];
                for (int i = 0; i < entry.getValue().length; i++) {
                    maskedValues[i] = "****";
                }
                maskedParameters.put(entry.getKey(), maskedValues);
            } else {
                maskedParameters.put(entry.getKey(), entry.getValue());
            }
        }
        String result = maskedParameters.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + Arrays.toString(entry.getValue()))
                .collect(Collectors.joining(", "));
        context.addLog("ARGS=["+ result+"]");

    }
}
