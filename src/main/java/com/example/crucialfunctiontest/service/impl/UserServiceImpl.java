package com.example.crucialfunctiontest.service.impl;

import com.example.crucialfunctiontest.mapper.UserMapper;
import com.example.crucialfunctiontest.model.dto.PageParam;
import com.example.crucialfunctiontest.model.entity.User;
import com.example.crucialfunctiontest.model.vo.PageResult;
import com.example.crucialfunctiontest.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author xushupeng
 * @Date 2024-12-31 10:20
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Override
    public PageResult<User> getUsersByPage(PageParam pageParam) {

//        SELECT 字段列表 FROM 表名 LIMIT 起始索引，查询记录数;
//        .起始索引从0开始，起始索引=（查询页码-1）*每页显示记录数。

        int offset = (pageParam.getPageNum() - 1) * pageParam.getPageSize();
        int pageSize = pageParam.getPageSize();
        List<User> list=userMapper.selectUsersByPage(offset,pageSize);
        int totalRecords=userMapper.countUsers();
        PageResult<User> pageResult=new PageResult<>();
        pageResult.setList(list);
        pageResult.setTotalRecord(totalRecords);
        pageResult.setPageNum(pageParam.getPageNum()); //第几页
        pageResult.setPageSize(pageParam.getPageSize()); //一页多少条数据
        //总页数计算
        pageResult.setTotalPage(totalRecords%pageParam.getPageSize()==0?totalRecords/pageParam.getPageSize():totalRecords/pageParam.getPageSize()+1);

        return pageResult;
    }
}
