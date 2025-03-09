package dev.warriorg.flowable.ops.service.impl;

import java.nio.charset.StandardCharsets;
import java.util.List;

import dev.warriorg.dto.PageDTO;
import dev.warriorg.dto.R;
import dev.warriorg.flowable.ops.dto.ModelDTO;
import dev.warriorg.flowable.ops.service.ModelService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.Model;
import org.flowable.engine.repository.ModelQuery;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class ModelServiceImpl implements ModelService {

    private final RepositoryService repositoryService;

    public ModelServiceImpl(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    @Override
    public R<List<Model>> list(PageDTO pageDTO) {
        ModelQuery modelQuery = repositoryService.createModelQuery();

        R<List<Model>> r = R.of(modelQuery.listPage(pageDTO.getPage().intValue(), pageDTO.getSize().intValue()), modelQuery.count());
        return r;
    }

    @Override
    public void save(ModelDTO dto) {
        if (ObjectUtils.isEmpty(dto.getBpmnXml())) {
            throw new IllegalArgumentException("BpmnXml cannot be empty.");
        }

        Model model = null;
        if (ObjectUtils.isEmpty(dto.getId())) {
            model = repositoryService.newModel();
            model.setVersion(1);
        } else {
            model = repositoryService.getModel(model.getId());
            if (model == null) {
                throw new IllegalArgumentException("Corresponding model not found.");
            }
        }

        model.setName(dto.getName());
        model.setKey(dto.getKey());
        model.setCategory(dto.getCategory());
        model.setMetaInfo(dto.getMetaInfo());

        repositoryService.addModelEditorSource(model.getId(), dto.getBpmnXml().getBytes(StandardCharsets.UTF_8));
        repositoryService.saveModel(model);
    }

    /**
     * deploy model
     *
     * @param modelId model id
     */
    @Override
    public void deploy(String modelId) {
        Model model = repositoryService.createModelQuery().modelId(modelId).singleResult();

    }
}
