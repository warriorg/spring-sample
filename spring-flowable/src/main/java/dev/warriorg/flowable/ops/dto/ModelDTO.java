package dev.warriorg.flowable.ops.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class ModelDTO implements Serializable {

    /**
     * model id
     */
    private String id;
    /**
     * model name
     */
    private String name;
    /**
     * Model unique identifier.
     */
    private String key;
    /**
     * model category
     */
    private String category;
    /**
     * model meta info
     */
    private String metaInfo;

    /**
     * model bpmn xml
     */
    private String bpmnXml;
}
