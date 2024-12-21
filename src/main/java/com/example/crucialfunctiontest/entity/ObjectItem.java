package com.example.crucialfunctiontest.entity;

import lombok.Data;

/**
 * @Author xushupeng
 * @Date 2024-07-17 13:09
 */
//minio 自定义文件对象类
@Data
public class ObjectItem {
private String objectName;
private Long size;
}
