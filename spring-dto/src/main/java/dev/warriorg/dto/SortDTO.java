/*
 * Copyright (c) 2011 longnows.cn. All rights reserved.
 */
package dev.warriorg.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import lombok.Data;
import lombok.NonNull;

/**
 * 排序信息
 *
 * @author gaoshiyong
 */
@Data
public class SortDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 构建默认排序
     */
    private static final Map<String, Order> MAPPING;

    static {
        MAPPING = new HashMap<>(5);
        MAPPING.put("ascend", Order.ASC);
        MAPPING.put("undefined", Order.ASC);
        MAPPING.put("asc", Order.ASC);
        MAPPING.put("descend", Order.DESC);
        MAPPING.put("desc", Order.DESC);
    }

    /**
     * property 数据库对应的属性
     */
    private String name;
    /**
     * 排序的方向
     */
    private Order order;

    /**
     * 解析SortDTO
     *
     * @param val value
     * @return sort
     */
    public static SortDTO valueOf(String val) {
        if (val == null || "".equals(val.trim())) {
            return null;
        }

        // replace 处理 sql 注入
        String[] sort = val.replace("'", "''").split("-");
        String name = sort[0].trim();
        if ("".equals(name) || "undefined".equals(name)) {
            return null;
        }

        SortDTO param = new SortDTO();
        param.setName(name);
        if (sort.length > 1) {
            param.setOrder(Order.fromString(sort[1]));
        } else {
            param.setOrder(Order.ASC);
        }
        return param;
    }

    /**
     * 拼接成功后的内容
     *
     * @return sort contact string
     */
    public String content() {
        return this.name + " " + this.order.name();
    }

    /**
     * 排序的方向
     */
    public enum Order {
        /**
         * 正序
         */
        ASC,
        /**
         * 反序
         */
        DESC;

        /**
         * parse from string
         * @param value order string
         * @return {@link Order}
         */
        public static Order fromString(@NonNull String value) {

            try {
                String key = value.toLowerCase();
                if (MAPPING.containsKey(key)) {
                    return MAPPING.get(key);
                }
                return Order.valueOf(key);
            } catch (Exception e) {
                return Order.ASC;
            }
        }
    }
}
