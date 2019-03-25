package com.own.order.admin.repository;

import com.own.order.admin.entity.User;
import com.own.order.admin.entity.UserExample;
import java.util.List;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByPrimaryKey(String userId);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}