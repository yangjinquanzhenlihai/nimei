package com.ssm.mapper;

import com.ssm.pojo.User;
import com.ssm.pojo.UserExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(String id);

    

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);



//	添加用户
	int insert(User record);
//  根据account查找用户名
	User findUserByAccount(String account);
//  更新用户
	void updateById(User user);
	
}