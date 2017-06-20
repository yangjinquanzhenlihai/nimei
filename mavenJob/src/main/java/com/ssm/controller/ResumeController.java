package com.ssm.controller;

import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ssm.pojo.Resume;
import com.ssm.service.ResumeService;

@Controller
public class ResumeController {
	@Autowired
	private ResumeService resumeService;
	@RequestMapping("create_resume.action")
	public ModelAndView create_resume(Resume resume){
		System.out.println("进入添加简历--------------------");
		resumeService.addResume(resume);
		ModelAndView mav = new ModelAndView();
//		mav.addObject("resume", resume);
		mav.setViewName("personal/resume_2");
		return mav;
	}

}
