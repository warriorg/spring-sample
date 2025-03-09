package dev.warriorg.flowable.ops.service;

import java.util.List;

import dev.warriorg.dto.PageDTO;
import dev.warriorg.dto.R;
import org.flowable.engine.repository.Deployment;

public interface DeploymentService {

    /**
     * model list
     * @param pageDTO  page dto
     * @return result
     */
    R<List<Deployment>> list(PageDTO pageDTO);
}
