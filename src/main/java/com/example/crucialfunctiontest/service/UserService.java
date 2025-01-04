package com.example.crucialfunctiontest.service;

import com.example.crucialfunctiontest.model.dto.PageParam;
import com.example.crucialfunctiontest.model.entity.User;
import com.example.crucialfunctiontest.model.vo.PageResult;

/**
 * @Author xushupeng
 * @Date 2024-12-31 10:19
 */
public interface UserService {

    PageResult<User> getUsersByPage(PageParam pageParam);
}
