/*
 * Copyright (c) 2011 longnows.cn. All rights reserved.
 */
package dev.warriorg.dto;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 分页参数
 *
 * @author gaoshiyong
 * @since 2023/6/20
 */
@Data
@Schema(title = "分页参数")
public class PageDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 一页的最大数
     */
    public static final int PAGE_SIZE_MAX = 1000;

    /**
     * 分页
     */
    @Schema(title = "当前页")
    private Long page = 1L;
    /**
     * 一页大小
     */
    @Schema(title = "页大小", description = "默认20条,最大1000条，超过1000按照1000计算")
    private Long size = 20L;
    /**
     * 不统计总数
     */
    @Schema(title = "不统计总数", description = "默认统计总数，如果不统计总数，则总数返回-1")
    private Boolean nonTotal = false;
    /**
     * 排序
     */
    @Schema(description = "排序")
    private List<String> sort;
    /**
     * constructor
     */
    public PageDTO() {}
    /**
     * 创建 Page
     * @param page 页码
     * @param size 大小
     */
    public PageDTO(long page, long size) {
        this.page = page;
        this.size = size;
    }
    /**
     * 创建 Page
     * @param page 页码
     * @param size 大小
     * @param nonTotal 不汇总 total
     */
    public PageDTO(long page, long size, boolean nonTotal) {
        this.page = page;
        this.size = size;
        this.nonTotal = nonTotal;
    }
    /**
     * 获取分页
     * @return 页码
     */
    public Long getPage() {
        if (page < 1L) {
            return 1L;
        }
        return page;
    }

    /**
     * 获取分页大小
     * @return 页大小
     */
    public Long getSize() {
        if (size > PAGE_SIZE_MAX) {
            size = (long) PAGE_SIZE_MAX;
        }
        return size;
    }

    /**
     * total
     *
     * @return total
     */
    public long total() {
        return getPage() * getSize();
    }

    /**
     * 获取排序
     *
     * @return 排序列表
     */
    public List<SortDTO> sortOrder() {
        if (this.sort == null || this.sort.isEmpty()) {
            return Collections.emptyList();
        }
        return this.sort.stream().map(SortDTO::valueOf).filter(Objects::nonNull).collect(Collectors.toList());
    }

    /**
     * 创建 Page
     *
     * @param page 页码
     * @param size 大小
     * @return {@link PageDTO}
     */
    public static PageDTO of(long page, long size) {
        return new PageDTO(page, size);
    }

    /**
     * 创建 Page
     *
     * @param page 页码
     * @param size 大小
     * @param nonTotal 不汇总 total
     * @return {@link PageDTO}
     */
    public static PageDTO of(long page, long size, boolean nonTotal) {
        return new PageDTO(page, size, nonTotal);
    }

    /**
     * 初始化最大页
     *
     * @return {@link PageDTO}
     */
    public static PageDTO ofMaxPage() {
        return PageDTO.of(1, PAGE_SIZE_MAX);
    }
}
