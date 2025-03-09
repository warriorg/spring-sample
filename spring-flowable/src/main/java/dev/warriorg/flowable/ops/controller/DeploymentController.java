package dev.warriorg.flowable.ops.controller;

import java.util.List;

import dev.warriorg.dto.PageDTO;
import dev.warriorg.dto.R;
import dev.warriorg.flowable.ops.service.impl.DeploymentServiceImpl;
import org.flowable.engine.repository.Deployment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/deployment")
public class DeploymentController {
    private final DeploymentServiceImpl deploymentService;

    public DeploymentController(DeploymentServiceImpl deploymentService) {
        this.deploymentService = deploymentService;
    }


    @GetMapping
    public R<List<Deployment>> list(PageDTO pageDTO) {
        return deploymentService.list(pageDTO);
    }
}
