package com.ssm.mapper;

import com.ssm.pojo.Usercart;
import com.ssm.pojo.UsercartExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UsercartMapper {
    int countByExample(UsercartExample example);

    int deleteByExample(UsercartExample example);

    int deleteByPrimaryKey(String id);

    int insert(Usercart record);

    int insertSelective(Usercart record);

    List<Usercart> selectByExample(UsercartExample example);

    Usercart selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Usercart record, @Param("example") UsercartExample example);

    int updateByExample(@Param("record") Usercart record, @Param("example") UsercartExample example);

    int updateByPrimaryKeySelective(Usercart record);

    int updateByPrimaryKey(Usercart record);
}