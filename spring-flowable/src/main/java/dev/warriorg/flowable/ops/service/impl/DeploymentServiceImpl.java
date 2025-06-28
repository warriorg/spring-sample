package dev.warriorg.flowable.ops.service.impl;

import dev.warriorg.dto.PageDTO;
import dev.warriorg.dto.R;
import dev.warriorg.flowable.ops.service.DeploymentService;
import java.util.List;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.DeploymentQuery;
import org.springframework.stereotype.Service;

@Service
public class DeploymentServiceImpl implements DeploymentService {
    private final RepositoryService repositoryService;

    public DeploymentServiceImpl(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    /**
     * model list
     *
     * @param pageDTO page dto
     * @return result
     */
    @Override
    public R<List<Deployment>> list(PageDTO pageDTO) {
        DeploymentQuery query = repositoryService.createDeploymentQuery();
        return R.of(
                query.listPage(pageDTO.getPage().intValue(), pageDTO.getSize().intValue()), query.count());
    }
}
