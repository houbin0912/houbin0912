package com.baixin.ees.web.dao.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QuartzJobExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public QuartzJobExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimitStart(int limitStart) {
        this.limitStart=limitStart;
    }

    public int getLimitStart() {
        return limitStart;
    }

    public void setLimitEnd(int limitEnd) {
        this.limitEnd=limitEnd;
    }

    public int getLimitEnd() {
        return limitEnd;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andJobIdIsNull() {
            addCriterion("JOB_ID is null");
            return (Criteria) this;
        }

        public Criteria andJobIdIsNotNull() {
            addCriterion("JOB_ID is not null");
            return (Criteria) this;
        }

        public Criteria andJobIdEqualTo(Integer value) {
            addCriterion("JOB_ID =", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdNotEqualTo(Integer value) {
            addCriterion("JOB_ID <>", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdGreaterThan(Integer value) {
            addCriterion("JOB_ID >", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("JOB_ID >=", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdLessThan(Integer value) {
            addCriterion("JOB_ID <", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdLessThanOrEqualTo(Integer value) {
            addCriterion("JOB_ID <=", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdIn(List<Integer> values) {
            addCriterion("JOB_ID in", values, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdNotIn(List<Integer> values) {
            addCriterion("JOB_ID not in", values, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdBetween(Integer value1, Integer value2) {
            addCriterion("JOB_ID between", value1, value2, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdNotBetween(Integer value1, Integer value2) {
            addCriterion("JOB_ID not between", value1, value2, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobNameIsNull() {
            addCriterion("JOB_NAME is null");
            return (Criteria) this;
        }

        public Criteria andJobNameIsNotNull() {
            addCriterion("JOB_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andJobNameEqualTo(String value) {
            addCriterion("JOB_NAME =", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameNotEqualTo(String value) {
            addCriterion("JOB_NAME <>", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameGreaterThan(String value) {
            addCriterion("JOB_NAME >", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameGreaterThanOrEqualTo(String value) {
            addCriterion("JOB_NAME >=", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameLessThan(String value) {
            addCriterion("JOB_NAME <", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameLessThanOrEqualTo(String value) {
            addCriterion("JOB_NAME <=", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameLike(String value) {
            addCriterion("JOB_NAME like", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameNotLike(String value) {
            addCriterion("JOB_NAME not like", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameIn(List<String> values) {
            addCriterion("JOB_NAME in", values, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameNotIn(List<String> values) {
            addCriterion("JOB_NAME not in", values, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameBetween(String value1, String value2) {
            addCriterion("JOB_NAME between", value1, value2, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameNotBetween(String value1, String value2) {
            addCriterion("JOB_NAME not between", value1, value2, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobGroupNameIsNull() {
            addCriterion("JOB_GROUP_NAME is null");
            return (Criteria) this;
        }

        public Criteria andJobGroupNameIsNotNull() {
            addCriterion("JOB_GROUP_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andJobGroupNameEqualTo(String value) {
            addCriterion("JOB_GROUP_NAME =", value, "jobGroupName");
            return (Criteria) this;
        }

        public Criteria andJobGroupNameNotEqualTo(String value) {
            addCriterion("JOB_GROUP_NAME <>", value, "jobGroupName");
            return (Criteria) this;
        }

        public Criteria andJobGroupNameGreaterThan(String value) {
            addCriterion("JOB_GROUP_NAME >", value, "jobGroupName");
            return (Criteria) this;
        }

        public Criteria andJobGroupNameGreaterThanOrEqualTo(String value) {
            addCriterion("JOB_GROUP_NAME >=", value, "jobGroupName");
            return (Criteria) this;
        }

        public Criteria andJobGroupNameLessThan(String value) {
            addCriterion("JOB_GROUP_NAME <", value, "jobGroupName");
            return (Criteria) this;
        }

        public Criteria andJobGroupNameLessThanOrEqualTo(String value) {
            addCriterion("JOB_GROUP_NAME <=", value, "jobGroupName");
            return (Criteria) this;
        }

        public Criteria andJobGroupNameLike(String value) {
            addCriterion("JOB_GROUP_NAME like", value, "jobGroupName");
            return (Criteria) this;
        }

        public Criteria andJobGroupNameNotLike(String value) {
            addCriterion("JOB_GROUP_NAME not like", value, "jobGroupName");
            return (Criteria) this;
        }

        public Criteria andJobGroupNameIn(List<String> values) {
            addCriterion("JOB_GROUP_NAME in", values, "jobGroupName");
            return (Criteria) this;
        }

        public Criteria andJobGroupNameNotIn(List<String> values) {
            addCriterion("JOB_GROUP_NAME not in", values, "jobGroupName");
            return (Criteria) this;
        }

        public Criteria andJobGroupNameBetween(String value1, String value2) {
            addCriterion("JOB_GROUP_NAME between", value1, value2, "jobGroupName");
            return (Criteria) this;
        }

        public Criteria andJobGroupNameNotBetween(String value1, String value2) {
            addCriterion("JOB_GROUP_NAME not between", value1, value2, "jobGroupName");
            return (Criteria) this;
        }

        public Criteria andJobTriggerNameIsNull() {
            addCriterion("JOB_TRIGGER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andJobTriggerNameIsNotNull() {
            addCriterion("JOB_TRIGGER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andJobTriggerNameEqualTo(String value) {
            addCriterion("JOB_TRIGGER_NAME =", value, "jobTriggerName");
            return (Criteria) this;
        }

        public Criteria andJobTriggerNameNotEqualTo(String value) {
            addCriterion("JOB_TRIGGER_NAME <>", value, "jobTriggerName");
            return (Criteria) this;
        }

        public Criteria andJobTriggerNameGreaterThan(String value) {
            addCriterion("JOB_TRIGGER_NAME >", value, "jobTriggerName");
            return (Criteria) this;
        }

        public Criteria andJobTriggerNameGreaterThanOrEqualTo(String value) {
            addCriterion("JOB_TRIGGER_NAME >=", value, "jobTriggerName");
            return (Criteria) this;
        }

        public Criteria andJobTriggerNameLessThan(String value) {
            addCriterion("JOB_TRIGGER_NAME <", value, "jobTriggerName");
            return (Criteria) this;
        }

        public Criteria andJobTriggerNameLessThanOrEqualTo(String value) {
            addCriterion("JOB_TRIGGER_NAME <=", value, "jobTriggerName");
            return (Criteria) this;
        }

        public Criteria andJobTriggerNameLike(String value) {
            addCriterion("JOB_TRIGGER_NAME like", value, "jobTriggerName");
            return (Criteria) this;
        }

        public Criteria andJobTriggerNameNotLike(String value) {
            addCriterion("JOB_TRIGGER_NAME not like", value, "jobTriggerName");
            return (Criteria) this;
        }

        public Criteria andJobTriggerNameIn(List<String> values) {
            addCriterion("JOB_TRIGGER_NAME in", values, "jobTriggerName");
            return (Criteria) this;
        }

        public Criteria andJobTriggerNameNotIn(List<String> values) {
            addCriterion("JOB_TRIGGER_NAME not in", values, "jobTriggerName");
            return (Criteria) this;
        }

        public Criteria andJobTriggerNameBetween(String value1, String value2) {
            addCriterion("JOB_TRIGGER_NAME between", value1, value2, "jobTriggerName");
            return (Criteria) this;
        }

        public Criteria andJobTriggerNameNotBetween(String value1, String value2) {
            addCriterion("JOB_TRIGGER_NAME not between", value1, value2, "jobTriggerName");
            return (Criteria) this;
        }

        public Criteria andJobTriggerGroupNameIsNull() {
            addCriterion("JOB_TRIGGER_GROUP_NAME is null");
            return (Criteria) this;
        }

        public Criteria andJobTriggerGroupNameIsNotNull() {
            addCriterion("JOB_TRIGGER_GROUP_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andJobTriggerGroupNameEqualTo(String value) {
            addCriterion("JOB_TRIGGER_GROUP_NAME =", value, "jobTriggerGroupName");
            return (Criteria) this;
        }

        public Criteria andJobTriggerGroupNameNotEqualTo(String value) {
            addCriterion("JOB_TRIGGER_GROUP_NAME <>", value, "jobTriggerGroupName");
            return (Criteria) this;
        }

        public Criteria andJobTriggerGroupNameGreaterThan(String value) {
            addCriterion("JOB_TRIGGER_GROUP_NAME >", value, "jobTriggerGroupName");
            return (Criteria) this;
        }

        public Criteria andJobTriggerGroupNameGreaterThanOrEqualTo(String value) {
            addCriterion("JOB_TRIGGER_GROUP_NAME >=", value, "jobTriggerGroupName");
            return (Criteria) this;
        }

        public Criteria andJobTriggerGroupNameLessThan(String value) {
            addCriterion("JOB_TRIGGER_GROUP_NAME <", value, "jobTriggerGroupName");
            return (Criteria) this;
        }

        public Criteria andJobTriggerGroupNameLessThanOrEqualTo(String value) {
            addCriterion("JOB_TRIGGER_GROUP_NAME <=", value, "jobTriggerGroupName");
            return (Criteria) this;
        }

        public Criteria andJobTriggerGroupNameLike(String value) {
            addCriterion("JOB_TRIGGER_GROUP_NAME like", value, "jobTriggerGroupName");
            return (Criteria) this;
        }

        public Criteria andJobTriggerGroupNameNotLike(String value) {
            addCriterion("JOB_TRIGGER_GROUP_NAME not like", value, "jobTriggerGroupName");
            return (Criteria) this;
        }

        public Criteria andJobTriggerGroupNameIn(List<String> values) {
            addCriterion("JOB_TRIGGER_GROUP_NAME in", values, "jobTriggerGroupName");
            return (Criteria) this;
        }

        public Criteria andJobTriggerGroupNameNotIn(List<String> values) {
            addCriterion("JOB_TRIGGER_GROUP_NAME not in", values, "jobTriggerGroupName");
            return (Criteria) this;
        }

        public Criteria andJobTriggerGroupNameBetween(String value1, String value2) {
            addCriterion("JOB_TRIGGER_GROUP_NAME between", value1, value2, "jobTriggerGroupName");
            return (Criteria) this;
        }

        public Criteria andJobTriggerGroupNameNotBetween(String value1, String value2) {
            addCriterion("JOB_TRIGGER_GROUP_NAME not between", value1, value2, "jobTriggerGroupName");
            return (Criteria) this;
        }

        public Criteria andJobImplClassIsNull() {
            addCriterion("JOB_IMPL_CLASS is null");
            return (Criteria) this;
        }

        public Criteria andJobImplClassIsNotNull() {
            addCriterion("JOB_IMPL_CLASS is not null");
            return (Criteria) this;
        }

        public Criteria andJobImplClassEqualTo(String value) {
            addCriterion("JOB_IMPL_CLASS =", value, "jobImplClass");
            return (Criteria) this;
        }

        public Criteria andJobImplClassNotEqualTo(String value) {
            addCriterion("JOB_IMPL_CLASS <>", value, "jobImplClass");
            return (Criteria) this;
        }

        public Criteria andJobImplClassGreaterThan(String value) {
            addCriterion("JOB_IMPL_CLASS >", value, "jobImplClass");
            return (Criteria) this;
        }

        public Criteria andJobImplClassGreaterThanOrEqualTo(String value) {
            addCriterion("JOB_IMPL_CLASS >=", value, "jobImplClass");
            return (Criteria) this;
        }

        public Criteria andJobImplClassLessThan(String value) {
            addCriterion("JOB_IMPL_CLASS <", value, "jobImplClass");
            return (Criteria) this;
        }

        public Criteria andJobImplClassLessThanOrEqualTo(String value) {
            addCriterion("JOB_IMPL_CLASS <=", value, "jobImplClass");
            return (Criteria) this;
        }

        public Criteria andJobImplClassLike(String value) {
            addCriterion("JOB_IMPL_CLASS like", value, "jobImplClass");
            return (Criteria) this;
        }

        public Criteria andJobImplClassNotLike(String value) {
            addCriterion("JOB_IMPL_CLASS not like", value, "jobImplClass");
            return (Criteria) this;
        }

        public Criteria andJobImplClassIn(List<String> values) {
            addCriterion("JOB_IMPL_CLASS in", values, "jobImplClass");
            return (Criteria) this;
        }

        public Criteria andJobImplClassNotIn(List<String> values) {
            addCriterion("JOB_IMPL_CLASS not in", values, "jobImplClass");
            return (Criteria) this;
        }

        public Criteria andJobImplClassBetween(String value1, String value2) {
            addCriterion("JOB_IMPL_CLASS between", value1, value2, "jobImplClass");
            return (Criteria) this;
        }

        public Criteria andJobImplClassNotBetween(String value1, String value2) {
            addCriterion("JOB_IMPL_CLASS not between", value1, value2, "jobImplClass");
            return (Criteria) this;
        }

        public Criteria andCronExpressionIsNull() {
            addCriterion("CRON_EXPRESSION is null");
            return (Criteria) this;
        }

        public Criteria andCronExpressionIsNotNull() {
            addCriterion("CRON_EXPRESSION is not null");
            return (Criteria) this;
        }

        public Criteria andCronExpressionEqualTo(String value) {
            addCriterion("CRON_EXPRESSION =", value, "cronExpression");
            return (Criteria) this;
        }

        public Criteria andCronExpressionNotEqualTo(String value) {
            addCriterion("CRON_EXPRESSION <>", value, "cronExpression");
            return (Criteria) this;
        }

        public Criteria andCronExpressionGreaterThan(String value) {
            addCriterion("CRON_EXPRESSION >", value, "cronExpression");
            return (Criteria) this;
        }

        public Criteria andCronExpressionGreaterThanOrEqualTo(String value) {
            addCriterion("CRON_EXPRESSION >=", value, "cronExpression");
            return (Criteria) this;
        }

        public Criteria andCronExpressionLessThan(String value) {
            addCriterion("CRON_EXPRESSION <", value, "cronExpression");
            return (Criteria) this;
        }

        public Criteria andCronExpressionLessThanOrEqualTo(String value) {
            addCriterion("CRON_EXPRESSION <=", value, "cronExpression");
            return (Criteria) this;
        }

        public Criteria andCronExpressionLike(String value) {
            addCriterion("CRON_EXPRESSION like", value, "cronExpression");
            return (Criteria) this;
        }

        public Criteria andCronExpressionNotLike(String value) {
            addCriterion("CRON_EXPRESSION not like", value, "cronExpression");
            return (Criteria) this;
        }

        public Criteria andCronExpressionIn(List<String> values) {
            addCriterion("CRON_EXPRESSION in", values, "cronExpression");
            return (Criteria) this;
        }

        public Criteria andCronExpressionNotIn(List<String> values) {
            addCriterion("CRON_EXPRESSION not in", values, "cronExpression");
            return (Criteria) this;
        }

        public Criteria andCronExpressionBetween(String value1, String value2) {
            addCriterion("CRON_EXPRESSION between", value1, value2, "cronExpression");
            return (Criteria) this;
        }

        public Criteria andCronExpressionNotBetween(String value1, String value2) {
            addCriterion("CRON_EXPRESSION not between", value1, value2, "cronExpression");
            return (Criteria) this;
        }

        public Criteria andJobStatusIsNull() {
            addCriterion("JOB_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andJobStatusIsNotNull() {
            addCriterion("JOB_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andJobStatusEqualTo(String value) {
            addCriterion("JOB_STATUS =", value, "jobStatus");
            return (Criteria) this;
        }

        public Criteria andJobStatusNotEqualTo(String value) {
            addCriterion("JOB_STATUS <>", value, "jobStatus");
            return (Criteria) this;
        }

        public Criteria andJobStatusGreaterThan(String value) {
            addCriterion("JOB_STATUS >", value, "jobStatus");
            return (Criteria) this;
        }

        public Criteria andJobStatusGreaterThanOrEqualTo(String value) {
            addCriterion("JOB_STATUS >=", value, "jobStatus");
            return (Criteria) this;
        }

        public Criteria andJobStatusLessThan(String value) {
            addCriterion("JOB_STATUS <", value, "jobStatus");
            return (Criteria) this;
        }

        public Criteria andJobStatusLessThanOrEqualTo(String value) {
            addCriterion("JOB_STATUS <=", value, "jobStatus");
            return (Criteria) this;
        }

        public Criteria andJobStatusLike(String value) {
            addCriterion("JOB_STATUS like", value, "jobStatus");
            return (Criteria) this;
        }

        public Criteria andJobStatusNotLike(String value) {
            addCriterion("JOB_STATUS not like", value, "jobStatus");
            return (Criteria) this;
        }

        public Criteria andJobStatusIn(List<String> values) {
            addCriterion("JOB_STATUS in", values, "jobStatus");
            return (Criteria) this;
        }

        public Criteria andJobStatusNotIn(List<String> values) {
            addCriterion("JOB_STATUS not in", values, "jobStatus");
            return (Criteria) this;
        }

        public Criteria andJobStatusBetween(String value1, String value2) {
            addCriterion("JOB_STATUS between", value1, value2, "jobStatus");
            return (Criteria) this;
        }

        public Criteria andJobStatusNotBetween(String value1, String value2) {
            addCriterion("JOB_STATUS not between", value1, value2, "jobStatus");
            return (Criteria) this;
        }

        public Criteria andJobDescIsNull() {
            addCriterion("JOB_DESC is null");
            return (Criteria) this;
        }

        public Criteria andJobDescIsNotNull() {
            addCriterion("JOB_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andJobDescEqualTo(String value) {
            addCriterion("JOB_DESC =", value, "jobDesc");
            return (Criteria) this;
        }

        public Criteria andJobDescNotEqualTo(String value) {
            addCriterion("JOB_DESC <>", value, "jobDesc");
            return (Criteria) this;
        }

        public Criteria andJobDescGreaterThan(String value) {
            addCriterion("JOB_DESC >", value, "jobDesc");
            return (Criteria) this;
        }

        public Criteria andJobDescGreaterThanOrEqualTo(String value) {
            addCriterion("JOB_DESC >=", value, "jobDesc");
            return (Criteria) this;
        }

        public Criteria andJobDescLessThan(String value) {
            addCriterion("JOB_DESC <", value, "jobDesc");
            return (Criteria) this;
        }

        public Criteria andJobDescLessThanOrEqualTo(String value) {
            addCriterion("JOB_DESC <=", value, "jobDesc");
            return (Criteria) this;
        }

        public Criteria andJobDescLike(String value) {
            addCriterion("JOB_DESC like", value, "jobDesc");
            return (Criteria) this;
        }

        public Criteria andJobDescNotLike(String value) {
            addCriterion("JOB_DESC not like", value, "jobDesc");
            return (Criteria) this;
        }

        public Criteria andJobDescIn(List<String> values) {
            addCriterion("JOB_DESC in", values, "jobDesc");
            return (Criteria) this;
        }

        public Criteria andJobDescNotIn(List<String> values) {
            addCriterion("JOB_DESC not in", values, "jobDesc");
            return (Criteria) this;
        }

        public Criteria andJobDescBetween(String value1, String value2) {
            addCriterion("JOB_DESC between", value1, value2, "jobDesc");
            return (Criteria) this;
        }

        public Criteria andJobDescNotBetween(String value1, String value2) {
            addCriterion("JOB_DESC not between", value1, value2, "jobDesc");
            return (Criteria) this;
        }

        public Criteria andCreatedateIsNull() {
            addCriterion("CREATEDATE is null");
            return (Criteria) this;
        }

        public Criteria andCreatedateIsNotNull() {
            addCriterion("CREATEDATE is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedateEqualTo(Date value) {
            addCriterion("CREATEDATE =", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateNotEqualTo(Date value) {
            addCriterion("CREATEDATE <>", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateGreaterThan(Date value) {
            addCriterion("CREATEDATE >", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATEDATE >=", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateLessThan(Date value) {
            addCriterion("CREATEDATE <", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateLessThanOrEqualTo(Date value) {
            addCriterion("CREATEDATE <=", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateIn(List<Date> values) {
            addCriterion("CREATEDATE in", values, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateNotIn(List<Date> values) {
            addCriterion("CREATEDATE not in", values, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateBetween(Date value1, Date value2) {
            addCriterion("CREATEDATE between", value1, value2, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateNotBetween(Date value1, Date value2) {
            addCriterion("CREATEDATE not between", value1, value2, "createdate");
            return (Criteria) this;
        }

        public Criteria andLastupdatetimeIsNull() {
            addCriterion("LASTUPDATETIME is null");
            return (Criteria) this;
        }

        public Criteria andLastupdatetimeIsNotNull() {
            addCriterion("LASTUPDATETIME is not null");
            return (Criteria) this;
        }

        public Criteria andLastupdatetimeEqualTo(Date value) {
            addCriterion("LASTUPDATETIME =", value, "lastupdatetime");
            return (Criteria) this;
        }

        public Criteria andLastupdatetimeNotEqualTo(Date value) {
            addCriterion("LASTUPDATETIME <>", value, "lastupdatetime");
            return (Criteria) this;
        }

        public Criteria andLastupdatetimeGreaterThan(Date value) {
            addCriterion("LASTUPDATETIME >", value, "lastupdatetime");
            return (Criteria) this;
        }

        public Criteria andLastupdatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("LASTUPDATETIME >=", value, "lastupdatetime");
            return (Criteria) this;
        }

        public Criteria andLastupdatetimeLessThan(Date value) {
            addCriterion("LASTUPDATETIME <", value, "lastupdatetime");
            return (Criteria) this;
        }

        public Criteria andLastupdatetimeLessThanOrEqualTo(Date value) {
            addCriterion("LASTUPDATETIME <=", value, "lastupdatetime");
            return (Criteria) this;
        }

        public Criteria andLastupdatetimeIn(List<Date> values) {
            addCriterion("LASTUPDATETIME in", values, "lastupdatetime");
            return (Criteria) this;
        }

        public Criteria andLastupdatetimeNotIn(List<Date> values) {
            addCriterion("LASTUPDATETIME not in", values, "lastupdatetime");
            return (Criteria) this;
        }

        public Criteria andLastupdatetimeBetween(Date value1, Date value2) {
            addCriterion("LASTUPDATETIME between", value1, value2, "lastupdatetime");
            return (Criteria) this;
        }

        public Criteria andLastupdatetimeNotBetween(Date value1, Date value2) {
            addCriterion("LASTUPDATETIME not between", value1, value2, "lastupdatetime");
            return (Criteria) this;
        }

        public Criteria andJobNameLikeInsensitive(String value) {
            addCriterion("upper(JOB_NAME) like", value.toUpperCase(), "jobName");
            return (Criteria) this;
        }

        public Criteria andJobGroupNameLikeInsensitive(String value) {
            addCriterion("upper(JOB_GROUP_NAME) like", value.toUpperCase(), "jobGroupName");
            return (Criteria) this;
        }

        public Criteria andJobTriggerNameLikeInsensitive(String value) {
            addCriterion("upper(JOB_TRIGGER_NAME) like", value.toUpperCase(), "jobTriggerName");
            return (Criteria) this;
        }

        public Criteria andJobTriggerGroupNameLikeInsensitive(String value) {
            addCriterion("upper(JOB_TRIGGER_GROUP_NAME) like", value.toUpperCase(), "jobTriggerGroupName");
            return (Criteria) this;
        }

        public Criteria andJobImplClassLikeInsensitive(String value) {
            addCriterion("upper(JOB_IMPL_CLASS) like", value.toUpperCase(), "jobImplClass");
            return (Criteria) this;
        }

        public Criteria andCronExpressionLikeInsensitive(String value) {
            addCriterion("upper(CRON_EXPRESSION) like", value.toUpperCase(), "cronExpression");
            return (Criteria) this;
        }

        public Criteria andJobStatusLikeInsensitive(String value) {
            addCriterion("upper(JOB_STATUS) like", value.toUpperCase(), "jobStatus");
            return (Criteria) this;
        }

        public Criteria andJobDescLikeInsensitive(String value) {
            addCriterion("upper(JOB_DESC) like", value.toUpperCase(), "jobDesc");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}