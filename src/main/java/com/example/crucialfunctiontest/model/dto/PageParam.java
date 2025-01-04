package com.example.crucialfunctiontest.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author xushupeng
 * @Date 2024-12-31 10:09
 */
@Data
@AllArgsConstructor
public class PageParam {
    private int pageNum; // 当前页码，从1开始
    private int pageSize; // 每页显示的条数

}
