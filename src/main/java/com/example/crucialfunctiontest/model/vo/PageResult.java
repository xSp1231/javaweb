package com.example.crucialfunctiontest.model.vo;

import lombok.Data;

import java.util.List;

/**
 * @Author xushupeng
 * @Date 2024-12-31 10:10
 */
@Data
public class PageResult<T> {
        private int pageNum; // 当前页码
        private int pageSize; // 每页条数
        private int totalRecord; // 总记录数
        private int totalPage; // 总页数
        private List<T> list; // 当前页的数据列表

    }

