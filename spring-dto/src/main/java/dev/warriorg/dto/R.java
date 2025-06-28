/*
 * Copyright (c) 2011 longnows.cn. All rights reserved.
 */
package dev.warriorg.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gaoshiyong
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(title = "响应数据")
public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /***
     * success code
     */
    public static final int SUCCESS_CODE = 0;
    /***
     * failure code
     */
    public static final int FAILURE_CODE = 1;

    /***
     * 降级方案
     */
    public static final String FALLBACK_CODE = "fallback";

    /***
     * 返回的code
     */
    @Schema(title = "Code", description = "0 成功， 非0 失败")
    private int code;

    /***
     * 返回的消息
     */
    @Schema(title = "消息")
    private String message;

    /***
     * 返回的数据
     */
    private T data;

    /***
     * 返回的数据总条数
     */
    @Schema(title = "数据条数")
    @Builder.Default
    private Long total = 0L;

    /***
     * 扩展数据
     */
    @Schema(title = "扩展数据")
    private Object meta;

    /***
     * 返回一个包含成功的包装
     *
     * @param data 返回的结果
     * @return 返回一个请求
     * @param <T> 范型
     */
    public static <T> R<T> of(T data) {
        long total = data == null ? 0 : 1;
        if (data instanceof Collection) {
            total = ((Collection<?>) data).size();
        }
        return of(data, total);
    }

    /***
     * 返回一个包含成功的包装
     *
     * @param data 返回的结果
     * @param total 数量
     * @return 返回一个请求
     * @param <T> 范型
     */
    public static <T> R<T> of(T data, long total) {
        return new R<>(SUCCESS_CODE, data, total);
    }

    /***
     * copy
     * @param origin 原始的 result
     * @return duplication result
     * @param <T> 范型
     */
    @SuppressWarnings("unchecked")
    public static <T> R<T> copy(R<?> origin) {
        R<T> result = new R<>();
        result.setMeta(origin.getMeta());
        result.setCode(origin.getCode());
        result.setTotal(origin.getTotal());
        result.setMessage(origin.getMessage());
        result.setData((T) origin.getData());
        return result;
    }

    /***
     * success
     * @return result
     * @param <T> 范型
     */
    public static <T> R<T> success() {
        return new R<>(SUCCESS_CODE, "操作成功");
    }

    /***
     * success
     * @param message message
     * @return Result
     * @param <T> 范型
     */
    public static <T> R<T> success(String message) {
        return new R<>(SUCCESS_CODE, message);
    }

    /***
     * success
     * @param data  data
     * @param message message
     * @return Result
     */
    public static <T> R<T> success(T data, String message) {
        R<T> result = R.of(data);
        result.setMessage(message);
        return result;
    }

    /***
     * 构造失败消息
     * @param message message
     * @return failure result
     * @param <T> 范型
     */
    public static <T> R<T> failure(String message) {
        return new R<>(FAILURE_CODE, message);
    }

    /***
     * 构造失败
     *
     * @param code code
     * @param message message
     * @return failure result
     */
    public static <T> R<T> failure(int code, String message) {
        return new R<>(code, message);
    }

    /***
     * 降级返回
     * 请在 OpenFeign 调用的降级场景使用，不要在其他位置使用
     * @return fallback result
     * @param <T> 范型
     */
    public static <T> R<T> fallback() {
        R<T> result = new R<>(SUCCESS_CODE, "降级");
        result.setMeta(FALLBACK_CODE);
        result.setTotal(0L);
        return result;
    }

    /**
     * constructor
     * @param code code
     * @param message message
     */
    public R(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * constructor
     * @param code code
     * @param data data
     * @param total total
     */
    public R(int code, T data, long total) {
        this.code = code;
        this.data = data;
        this.total = total;
    }

    /***
     * 请求成功
     *
     * @return 请求是否成功，注意，可能包含降级
     */
    public boolean ok() {
        return SUCCESS_CODE == code;
    }

    /***
     * 是否是降级方案
     * @return 当前的返回是否是降级
     */
    public boolean isFallback() {
        return FALLBACK_CODE.equals(meta);
    }
}
