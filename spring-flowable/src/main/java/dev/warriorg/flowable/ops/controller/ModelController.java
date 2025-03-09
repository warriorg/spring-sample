package dev.warriorg.flowable.ops.controller;

import java.util.List;

import dev.warriorg.dto.PageDTO;
import dev.warriorg.dto.R;
import dev.warriorg.flowable.ops.dto.ModelDTO;
import dev.warriorg.flowable.ops.service.ModelService;
import org.flowable.engine.repository.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/model")
public class ModelController {

    private final ModelService modelService;

    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping
    public R<List<Model>> list(PageDTO pageDTO) {
        return modelService.list(pageDTO);
    }

    @PostMapping
    public R<Void> save(@RequestBody ModelDTO dto) {
        modelService.save(dto);
        return R.success();
    }

    @PostMapping("/deploy/{modelId}")
    public R<Void> deploy(@PathVariable String modelId) {
        modelService.deploy(modelId);
        return R.success();
    }
}
