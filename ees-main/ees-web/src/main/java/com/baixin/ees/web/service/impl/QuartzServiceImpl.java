package com.baixin.ees.web.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.quartz.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baixin.ees.quartz.QuartzManager;
import com.baixin.ees.util.MyException;
import com.baixin.ees.util.ResultMessage;
import com.baixin.ees.web.dao.mapper.QuartzJobMapper;
import com.baixin.ees.web.dao.model.Page;
import com.baixin.ees.web.dao.model.QuartzJob;
import com.baixin.ees.web.dao.model.QuartzJobExample;
import com.baixin.ees.web.dao.model.QuartzJobExample.Criteria;
import com.baixin.ees.web.dao.model.QuartzJobResult;
import com.baixin.ees.web.service.QuartzService;

@Service
public class QuartzServiceImpl implements QuartzService {

	@Autowired
	private QuartzManager quartzManager;

	@Autowired
	private SqlSession eesSqlSession;
	
	@Override
	public ResultMessage updateQuartzJob(QuartzJob quartzJob) {
		try {
			QuartzJobMapper mapper = eesSqlSession.getMapper(QuartzJobMapper.class);
			QuartzJob updateJob = mapper.selectByPrimaryKey(quartzJob.getJobId());
			quartzJob.setLastupdatetime(new Date());
			quartzJob.setCreatedate(updateJob.getCreatedate());
			if("0".equals(quartzJob.getJobStatus())){
				if(quartzManager.addJob(quartzJob)) {
					mapper.updateByPrimaryKey(quartzJob);
				}else {
					return ResultMessage.isError("内存添加定时任务失败");
				}
			}
			if("1".equals(quartzJob.getJobStatus())){
				if(quartzManager.removeJob(quartzJob)) {
					mapper.updateByPrimaryKey(quartzJob);			
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResultMessage.isError();
		}
		return ResultMessage.isOk();
	}

	@Override
	public ResultMessage updateJobStatus(int jobId, String jobStatus) {
		try {
			QuartzJobMapper mapper = eesSqlSession.getMapper(QuartzJobMapper.class);
			QuartzJob job = mapper.selectByPrimaryKey(jobId);
			job.setJobStatus(jobStatus);
			job.setLastupdatetime(new Date());
			if("0".equals(jobStatus)){
				if(quartzManager.addJob(job)) {
					mapper.updateByPrimaryKey(job);
				}else {
					return ResultMessage.isError("内存添加定时任务失败");
				}
			}
			if("1".equals(jobStatus)){
				if(quartzManager.removeJob(job)) {
					mapper.updateByPrimaryKey(job);				
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResultMessage.isError();
		}
		
		return ResultMessage.isOk();
	}
	
	@Override
	public ResultMessage addQuartzJob(QuartzJob quartzJob) {
		try {
			QuartzJobMapper mapper = eesSqlSession.getMapper(QuartzJobMapper.class);
			quartzJob.setCreatedate(new Date());
			quartzJob.setLastupdatetime(new Date());
			if("0".equals(quartzJob.getJobStatus())) {
				if(quartzManager.addJob(quartzJob)) {
					if(mapper.insert(quartzJob) > 0) {
						return ResultMessage.isOk();
					}
					if(quartzManager.removeJob(quartzJob)) {
						return ResultMessage.isError();
					}
				}
			}
			mapper.insert(quartzJob);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultMessage.isError();
		}
		return ResultMessage.isOk();
	}

	@Override
	public ResultMessage deleteQuartzJob(QuartzJob quartzJob) {
		try {
			QuartzJobMapper mapper = eesSqlSession.getMapper(QuartzJobMapper.class);
			if(quartzManager.removeJob(quartzJob)) {
				mapper.deleteByPrimaryKey(quartzJob.getJobId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResultMessage.isError();
		}
		return ResultMessage.isOk();
	}
	
	@Override
	public Page<QuartzJobResult> quaryQuartzJob(QuartzJobResult quartzJob) throws MyException {
		Page<QuartzJobResult> page = null;
		try {
			QuartzJobMapper mapper = eesSqlSession.getMapper(QuartzJobMapper.class);
			QuartzJobExample jobEcample = new QuartzJobExample();
			Criteria criteria = jobEcample.createCriteria();
			if(StringUtils.isNotBlank(quartzJob.getJobName())){
				criteria.andJobNameLike("%" + quartzJob.getJobName() +"%");
			}
			if(StringUtils.isNotBlank(quartzJob.getJobGroupName())){
				criteria.andJobGroupNameLike("%" + quartzJob.getJobGroupName() +"%");
			}
			if(StringUtils.isNotBlank(quartzJob.getJobTriggerName())){
				criteria.andJobTriggerNameLike("%" + quartzJob.getJobTriggerName() +"%");
			}
			if(StringUtils.isNotBlank(quartzJob.getJobTriggerGroupName())){
				criteria.andJobTriggerGroupNameLike("%" + quartzJob.getJobTriggerGroupName() +"%");
			}
			jobEcample.setLimitStart(quartzJob.getStart());
			jobEcample.setLimitEnd(quartzJob.getLength());
			List<QuartzJobResult> jobList = mapper.selectByExample(jobEcample);
			int countNum = mapper.countByExample(jobEcample);
			page = new Page<QuartzJobResult>(quartzJob.getDraw(), countNum, countNum, jobList);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyException(500,"查询定时任务列表时发生未知异常");
		}
		return page;
	}
	
	public static void main(String[] args) throws ClassNotFoundException {
		Class<?> quartzJobClass = Class.forName("com.baixin.ees.quartz.MyJob");
		boolean isSon = quartzJobClass.isAssignableFrom(Job.class);
		System.out.println(isSon);
		System.out.println(Job.class.isAssignableFrom(quartzJobClass));
	}

	@Override
	public ResultMessage checkJobNameAndGroup(QuartzJobResult quartzJob) {
		try {
			QuartzJobMapper mapper = eesSqlSession.getMapper(QuartzJobMapper.class);
			QuartzJobExample jobExample = new QuartzJobExample();
			Criteria criteria = jobExample.createCriteria();
			if(StringUtils.isNotBlank(quartzJob.getJobName())){
				criteria.andJobNameEqualTo(quartzJob.getJobName());
			}
			if(StringUtils.isNotBlank(quartzJob.getJobGroupName())){
				criteria.andJobGroupNameEqualTo(quartzJob.getJobGroupName());
			}
			List<QuartzJobResult> list = mapper.selectByExample(jobExample);
			if(list.size() == 1) {
				if(quartzJob.getJobId() != null) {
					// 修改操作，内容唯一性冲突时
					if(list.get(0).getJobId() != quartzJob.getJobId()) {
						return ResultMessage.isOk("任务名称和任务组名需唯一", null);
					}
				}else {
					return ResultMessage.isOk("任务名称和任务组名需唯一", null);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResultMessage.isError("校验发生错误");
		}
		return ResultMessage.isError();
	}

	@Override
	public ResultMessage checkTriggerNameAndGroup(QuartzJobResult quartzJob) {
		try {
			QuartzJobMapper mapper = eesSqlSession.getMapper(QuartzJobMapper.class);
			QuartzJobExample jobExample = new QuartzJobExample();
			Criteria criteria = jobExample.createCriteria();
			if(StringUtils.isNotBlank(quartzJob.getJobTriggerName())){
				criteria.andJobTriggerNameEqualTo(quartzJob.getJobTriggerName());
			}
			if(StringUtils.isNotBlank(quartzJob.getJobTriggerGroupName())){
				criteria.andJobTriggerGroupNameEqualTo(quartzJob.getJobTriggerGroupName());
			}
			List<QuartzJobResult> list = mapper.selectByExample(jobExample);
			if(list.size() == 1) {
				if(quartzJob.getJobId() != null) {
					if(list.get(0).getJobId() != quartzJob.getJobId()) {
						return ResultMessage.isOk("触发器名称和触发器组需唯一", null);
					}
				}else {
					return ResultMessage.isOk("触发器名称和触发器组需唯一", null);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResultMessage.isError("校验发生错误");
		}
		return ResultMessage.isError();
	}
}
