package dev.warriorg.flowable.ops.service;

import dev.warriorg.dto.PageDTO;
import dev.warriorg.dto.R;
import dev.warriorg.flowable.ops.dto.ModelDTO;
import java.util.List;
import org.flowable.engine.repository.Model;

public interface ModelService {

    /**
     * model list
     * @param pageDTO  page dto
     * @return result
     */
    R<List<Model>> list(PageDTO pageDTO);

    /**
     * model save
     * @param dto model dto
     */
    void save(ModelDTO dto);

    /**
     * deploy model
     * @param modelId model id
     */
    void deploy(String modelId);
}
