package com.ssm.mapper;

import com.ssm.pojo.Resume;
import com.ssm.pojo.ResumeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResumeMapper {
	int countByExample(ResumeExample example);

	int deleteByExample(ResumeExample example);

	int deleteByPrimaryKey(String id);

	

	int insertSelective(Resume record);

	List<Resume> selectByExample(ResumeExample example);

	Resume selectByPrimaryKey(String id);

	int updateByExampleSelective(@Param("record") Resume record,
			@Param("example") ResumeExample example);

	int updateByExample(@Param("record") Resume record,
			@Param("example") ResumeExample example);

	int updateByPrimaryKeySelective(Resume record);

	int updateByPrimaryKey(Resume record);

	// 添加简历
	int insert(Resume record);

	// 更新简历
	void updateResumeById(Resume resume);
}