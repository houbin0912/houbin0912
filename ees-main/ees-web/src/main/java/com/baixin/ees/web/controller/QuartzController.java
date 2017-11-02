package com.baixin.ees.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baixin.ees.util.MyException;
import com.baixin.ees.util.ResultMessage;
import com.baixin.ees.web.dao.model.Page;
import com.baixin.ees.web.dao.model.QuartzJob;
import com.baixin.ees.web.dao.model.QuartzJobResult;
import com.baixin.ees.web.service.QuartzService;

@Controller
@RequestMapping("/sys/quartz")
public class QuartzController{

	@Autowired
	private QuartzService quartzService;
	/**
	 * 定时任务列表
	 * @param quartzJob
	 * @return
	 * @throws MyException 
	 */
	@RequestMapping("/list")
	@ResponseBody
	public Page<QuartzJobResult> quaryQuartzJob(QuartzJobResult quartzJob) throws MyException {
		return quartzService.quaryQuartzJob(quartzJob);
	}
	/**
	 * 定时任务编辑
	 * @param quartzJob
	 * @return
	 */
	@RequestMapping("/updateQuartzJob")
	@ResponseBody
	public ResultMessage updateQuartzJob(QuartzJob quartzJob) {
		return quartzService.updateQuartzJob(quartzJob);
	}
	/**
	 * 定时任务启用和禁用
	 * @param jobId
	 * @param jobStatus
	 * @return
	 */
	@RequestMapping("/updateJobStatus")
	@ResponseBody
	public ResultMessage updateJobStatus(int jobId, String jobStatus) {
		return quartzService.updateJobStatus(jobId, jobStatus);
	}
	/**
	 * 添加定时任务
	 * @param quartzJob
	 * @return
	 */
	@RequestMapping("/addQuartzJob")
	@ResponseBody
	public ResultMessage addQuartzJob(QuartzJob quartzJob) {
		return quartzService.addQuartzJob(quartzJob);
	}
	
	/**
	 * 删除定时任务
	 * @param quartzJob
	 * @return
	 */
	@RequestMapping("/deleteQuartzJob")
	@ResponseBody
	public ResultMessage deleteQuartzJob(QuartzJob quartzJob) {
		return quartzService.deleteQuartzJob(quartzJob);
	}
	/**
	 * 检测任务名称和任务组名唯一
	 * @param quartzJob
	 * @return
	 */
	@RequestMapping("/checkJobNameAndGroup")
	@ResponseBody
	public ResultMessage checkJobNameAndGroup(QuartzJobResult quartzJob) {
		return quartzService.checkJobNameAndGroup(quartzJob);
	}
	/**
	 * 检测触发器名称和触发器组唯一
	 * @param quartzJob
	 * @return
	 */
	@RequestMapping("/checkTriggerNameAndGroup")
	@ResponseBody
	public ResultMessage checkTriggerNameAndGroup(QuartzJobResult quartzJob) {
		return quartzService.checkTriggerNameAndGroup(quartzJob);
	}
}
