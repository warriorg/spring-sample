package me.warriorg.dubbo.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Employee implements Serializable {
    private Integer id;
    private String name;
    private int age;
}
