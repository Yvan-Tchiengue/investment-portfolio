package com.java.test2;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/workflow")
public class WorkflowController {

    private final WorkflowManagerService workflowManagerService;

    public WorkflowController(WorkflowManagerService workflowManagerService) {
        this.workflowManagerService = workflowManagerService;
    }

    @PostMapping("/execute/{name}")
    public String executeWorkflow(@PathVariable String name) {
        workflowManagerService.startWorkflow(name);
        return "Workflow '" + name + "' lancé.";
    }
}

