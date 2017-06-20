package com.ssm.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.mapper.ResumeMapper;
import com.ssm.pojo.Resume;
import com.ssm.service.ResumeService;
@Service
public class ResumeServiceImpl implements ResumeService{
	@Autowired
	private ResumeMapper resumeMapper;

//	添加简历
	public void addResume(Resume resume) {
		if(resume.getId()==null||resume.getId()==""){
			String id = UUID.randomUUID().toString().replace("-", "");
			resume.setId(id);
			resumeMapper.insert(resume);
		}else{
			resumeMapper.updateResumeById(resume);
		}
	}
}
