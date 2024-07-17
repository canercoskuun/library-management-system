package com.camunda.project;

import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StartProcessService {
    @Autowired
    private final RuntimeService runtime;

    public StartProcessService(RuntimeService runtime) {
        this.runtime = runtime;
    }
    public void getRequiredParameters(){
        runtime.startProcessInstanceById("StajProjectModeler-process");
    }
}
