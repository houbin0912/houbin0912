package com.baixin.ees.quartz;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;

import com.baixin.ees.web.dao.mapper.QuartzJobMapper;
import com.baixin.ees.web.dao.model.QuartzJob;
import com.baixin.ees.web.dao.model.QuartzJobExample;
import com.baixin.ees.web.dao.model.QuartzJobExample.Criteria;
import com.baixin.ees.web.dao.model.QuartzJobResult;


public class QuartzManager {

    private Scheduler scheduler;
    @Autowired
	private SqlSession eesSqlSession;
    /**
     * 添加一个任务
     * @param quartzJob
     * @return
     */
    public boolean addJob(QuartzJob quartzJob) {  
		try {
			Class<?> quartzJobClass = Class.forName(quartzJob.getJobImplClass());
			if(Job.class.isAssignableFrom(quartzJobClass)){
				@SuppressWarnings("unchecked")
				Class<? extends Job> newJobClass = (Class<? extends Job>) quartzJobClass;
				 // 任务名，任务组，任务执行类
	            JobDetail jobDetail= JobBuilder.newJob(newJobClass).withIdentity(quartzJob.getJobName(), quartzJob.getJobGroupName()).build();
	            // 触发器  
	            TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
	            // 触发器名,触发器组  
	            triggerBuilder.withIdentity(quartzJob.getJobTriggerName(), quartzJob.getJobTriggerGroupName());
	            triggerBuilder.startNow();
	            // 触发器时间设定  
	            triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(quartzJob.getCronExpression()));
	            // 创建Trigger对象
	            CronTrigger trigger = (CronTrigger) triggerBuilder.build();

	            // 调度容器设置JobDetail和Trigger
	            scheduler.scheduleJob(jobDetail, trigger);  
	            // 启动  
	            if (!scheduler.isShutdown()) {  
	            	scheduler.start();  
	            }  
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
    }  
    
    
    /** 
     * @Description: 修改一个任务的触发时间
     *  
     * @param jobName 
     * @param jobGroupName
     * @param triggerName 触发器名
     * @param triggerGroupName 触发器组名 
     * @param cron   时间设置，参考quartz说明文档   
     * @throws SchedulerException 
     */  
    public boolean modifyJobTime(QuartzJob quartzJob) {  
            try {
				TriggerKey triggerKey = TriggerKey.triggerKey(quartzJob.getJobTriggerName(), quartzJob.getJobTriggerGroupName());
				CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);  
				if (trigger == null) {  
				    return false;  
				}  

				String oldTime = trigger.getCronExpression();  
				if (!oldTime.equalsIgnoreCase(quartzJob.getCronExpression())) { 
				    /** 方式一 ：调用 rescheduleJob 开始 */
				    // 触发器  
				    TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
				    // 触发器名,触发器组  
				    triggerBuilder.withIdentity(quartzJob.getJobTriggerName(), quartzJob.getJobTriggerGroupName());
				    triggerBuilder.startNow();
				    // 触发器时间设定  
				    triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(quartzJob.getCronExpression()));
				    // 创建Trigger对象
				    trigger = (CronTrigger) triggerBuilder.build();
				    // 方式一 ：修改一个任务的触发时间
				    scheduler.rescheduleJob(triggerKey, trigger);
				    /** 方式一 ：调用 rescheduleJob 结束 */

				    /** 方式二：先删除，然后在创建一个新的Job  */
				    //JobDetail jobDetail = sched.getJobDetail(JobKey.jobKey(jobName, jobGroupName));  
				    //Class<? extends Job> jobClass = jobDetail.getJobClass();  
				    //removeJob(jobName, jobGroupName, triggerName, triggerGroupName);  
				    //addJob(jobName, jobGroupName, triggerName, triggerGroupName, jobClass, cron); 
				    /** 方式二 ：先删除，然后在创建一个新的Job */
				}
			} catch (SchedulerException e) {
				e.printStackTrace();
				return false;
			}  
            return true;
    }  

    /**
     * 移除一个任务  
     * @param quartzJob
     * @return
     */
    public boolean removeJob(QuartzJob quartzJob) {  
        try {
			TriggerKey triggerKey = TriggerKey.triggerKey(quartzJob.getJobTriggerName(), quartzJob.getJobTriggerGroupName());
			scheduler.pauseTrigger(triggerKey);// 停止触发器  
			scheduler.unscheduleJob(triggerKey);// 移除触发器  
			scheduler.deleteJob(JobKey.jobKey(quartzJob.getJobName(), quartzJob.getJobGroupName()));// 删除任务  
		} catch (SchedulerException e) {
			e.printStackTrace();
			return false;
		}
        return true;
    }  
    
    /** 
     * 检查调度是否启动 
     * @return 
     * @throws SchedulerException 
     */  
    public  boolean isStarted() throws SchedulerException  
    {  
        return scheduler.isStarted();  
    }
    
    /** 
     * 关闭调度信息 
     * @throws SchedulerException 
     */  
    public  void shutdown() throws SchedulerException   {  
        scheduler.shutdown();  
    }
    
    /** 
     * 停止调度Job任务 
     * @param triggerkey 
     * @return 
     * @throws SchedulerException 
     */  
    public  boolean unscheduleJob(TriggerKey triggerkey)  
            throws SchedulerException{  
        return scheduler.unscheduleJob(triggerkey);  
    }
    
    /** 
     * 添加相关的job任务 
     * @param jobdetail 
     * @param flag 
     * @throws SchedulerException 
     */  
    public  void addJob(JobDetail jobdetail, boolean flag)  
            throws SchedulerException   {  
        scheduler.addJob(jobdetail, flag);  
    }
    
    /** 
     * 停止一个job任务 
     * @param jobkey 
     * @throws SchedulerException 
     */  
    public  void pauseJob(JobKey jobkey) throws SchedulerException  {  
        scheduler.pauseJob(jobkey);  
    }  
    /** 
     * 停止多个job任务 
     * @param groupmatcher 
     * @throws SchedulerException 
     */  
    public  void pauseJobs(GroupMatcher<JobKey> groupmatcher)  
            throws SchedulerException   {  
        scheduler.pauseJobs(groupmatcher);  
    }
    
    /** 
     * 恢复相关的job任务 
     * @param jobkey 
     * @throws SchedulerException 
     */  
    public  void resumeJob(JobKey jobkey) throws SchedulerException {  
        scheduler.pauseJob(jobkey);  
    }  
      
    public  void resumeJobs(GroupMatcher<JobKey> matcher)  
            throws SchedulerException   {  
        scheduler.resumeJobs(matcher);  
    }  
  
    public  void resumeTrigger(TriggerKey triggerkey)  
            throws SchedulerException   {  
        scheduler.resumeTrigger(triggerkey);  
    }  
     
    public  void resumeTriggers(GroupMatcher<TriggerKey>  groupmatcher)  
            throws SchedulerException  
    {  
        scheduler.resumeTriggers(groupmatcher);   
    }  
    /** 
     * 暂停调度中所有的job任务 
     * @throws SchedulerException 
     */  
    public  void pauseAll() throws SchedulerException  
    {  
        scheduler.pauseAll();  
    }  
    /** 
     * 恢复调度中所有的job的任务 
     * @throws SchedulerException 
     */  
    public  void resumeAll() throws SchedulerException  
    {  
        scheduler.resumeAll();  
    }  

    /** 
     * @Description:启动所有定时任务 
     */  
    public void startJobs() {  
        try {  
        	QuartzJobMapper mapper = eesSqlSession.getMapper(QuartzJobMapper.class);
        	QuartzJobExample jobEcample = new QuartzJobExample();
    		Criteria criteria = jobEcample.createCriteria();
    		criteria.andJobStatusEqualTo("0");
    		List<QuartzJobResult> list = mapper.selectByExample(jobEcample);
    		for (QuartzJobResult quartzJobResult : list) {
    			addJob(quartzJobResult);
			}
        	scheduler.start();  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }  

    /** 
     * @Description:关闭所有定时任务 
     */  
    public void shutdownJobs() {  
        try {  
            if (!scheduler.isShutdown()) {  
            	scheduler.shutdown();  
            }  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }

	public Scheduler getScheduler() {
		return scheduler;
	}

	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}

}
