package com.baixin.ees.web.service;

import com.baixin.ees.util.MyException;
import com.baixin.ees.util.ResultMessage;
import com.baixin.ees.web.dao.model.Page;
import com.baixin.ees.web.dao.model.QuartzJob;
import com.baixin.ees.web.dao.model.QuartzJobResult;

public interface QuartzService {
	
	/**
	 * 更新定时表达式
	 * @param jobId 任务ID
	 * @param corn 定时表达式
	 * @return 是否更新成功
	 */
	ResultMessage updateQuartzJob(QuartzJob quartzJob);
	
	/**
	 * 更新任务的状态
	 * @param jobId jobId
	 * @param jobStatus 任务状态
	 * @return 更新是否成功
	 */
	ResultMessage updateJobStatus(int jobId,String jobStatus);
	/**
	 * 新增定时任务
	 * @param jobName 任务名称
	 * @param jobGroupName 任务组ID
	 * @param triggerName 触发器名称
	 * @param triggerGroupName 触发器组名称
	 * @param jobClass 定时任务实现类全程（包括包名），必须是org.quartz.Job的子类
	 * @param cron 定时触发器表达式
	 * @return 是否添加成功
	 */
	ResultMessage addQuartzJob(QuartzJob quartzJob);
	
	/**
	 * 移除定时任务
	 * @param jobName 任务名称
	 * @param jobGroupName 任务组名
	 * @param triggerName 触发器名称
	 * @param triggerGroupName 触发器组名
	 * @return 是否移除成功
	 */
	ResultMessage deleteQuartzJob(QuartzJob quartzJob);
	
	/**
	 * 分页查询job列表
	 * @param quartzJob
	 * @return
	 * @throws MyException 
	 */
	Page<QuartzJobResult> quaryQuartzJob(QuartzJobResult quartzJob) throws MyException;
	/**
	 * 检测任务名称和任务组名唯一
	 * @param quartzJob
	 * @return
	 */
	ResultMessage checkJobNameAndGroup(QuartzJobResult quartzJob);
	
	/**
	 * 检测触发器名称和触发器组唯一
	 * @param quartzJob
	 * @return
	 */
	ResultMessage checkTriggerNameAndGroup(QuartzJobResult quartzJob);
}
