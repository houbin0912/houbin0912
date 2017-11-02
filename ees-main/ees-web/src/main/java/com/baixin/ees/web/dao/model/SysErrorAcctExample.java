package com.baixin.ees.web.dao.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SysErrorAcctExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public SysErrorAcctExample() {
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

        public Criteria andAcctErrIdIsNull() {
            addCriterion("ACCT_ERR_ID is null");
            return (Criteria) this;
        }

        public Criteria andAcctErrIdIsNotNull() {
            addCriterion("ACCT_ERR_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAcctErrIdEqualTo(Integer value) {
            addCriterion("ACCT_ERR_ID =", value, "acctErrId");
            return (Criteria) this;
        }

        public Criteria andAcctErrIdNotEqualTo(Integer value) {
            addCriterion("ACCT_ERR_ID <>", value, "acctErrId");
            return (Criteria) this;
        }

        public Criteria andAcctErrIdGreaterThan(Integer value) {
            addCriterion("ACCT_ERR_ID >", value, "acctErrId");
            return (Criteria) this;
        }

        public Criteria andAcctErrIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ACCT_ERR_ID >=", value, "acctErrId");
            return (Criteria) this;
        }

        public Criteria andAcctErrIdLessThan(Integer value) {
            addCriterion("ACCT_ERR_ID <", value, "acctErrId");
            return (Criteria) this;
        }

        public Criteria andAcctErrIdLessThanOrEqualTo(Integer value) {
            addCriterion("ACCT_ERR_ID <=", value, "acctErrId");
            return (Criteria) this;
        }

        public Criteria andAcctErrIdIn(List<Integer> values) {
            addCriterion("ACCT_ERR_ID in", values, "acctErrId");
            return (Criteria) this;
        }

        public Criteria andAcctErrIdNotIn(List<Integer> values) {
            addCriterion("ACCT_ERR_ID not in", values, "acctErrId");
            return (Criteria) this;
        }

        public Criteria andAcctErrIdBetween(Integer value1, Integer value2) {
            addCriterion("ACCT_ERR_ID between", value1, value2, "acctErrId");
            return (Criteria) this;
        }

        public Criteria andAcctErrIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ACCT_ERR_ID not between", value1, value2, "acctErrId");
            return (Criteria) this;
        }

        public Criteria andChannelSeqNoIsNull() {
            addCriterion("CHANNEL_SEQ_NO is null");
            return (Criteria) this;
        }

        public Criteria andChannelSeqNoIsNotNull() {
            addCriterion("CHANNEL_SEQ_NO is not null");
            return (Criteria) this;
        }

        public Criteria andChannelSeqNoEqualTo(String value) {
            addCriterion("CHANNEL_SEQ_NO =", value, "channelSeqNo");
            return (Criteria) this;
        }

        public Criteria andChannelSeqNoNotEqualTo(String value) {
            addCriterion("CHANNEL_SEQ_NO <>", value, "channelSeqNo");
            return (Criteria) this;
        }

        public Criteria andChannelSeqNoGreaterThan(String value) {
            addCriterion("CHANNEL_SEQ_NO >", value, "channelSeqNo");
            return (Criteria) this;
        }

        public Criteria andChannelSeqNoGreaterThanOrEqualTo(String value) {
            addCriterion("CHANNEL_SEQ_NO >=", value, "channelSeqNo");
            return (Criteria) this;
        }

        public Criteria andChannelSeqNoLessThan(String value) {
            addCriterion("CHANNEL_SEQ_NO <", value, "channelSeqNo");
            return (Criteria) this;
        }

        public Criteria andChannelSeqNoLessThanOrEqualTo(String value) {
            addCriterion("CHANNEL_SEQ_NO <=", value, "channelSeqNo");
            return (Criteria) this;
        }

        public Criteria andChannelSeqNoLike(String value) {
            addCriterion("CHANNEL_SEQ_NO like", value, "channelSeqNo");
            return (Criteria) this;
        }

        public Criteria andChannelSeqNoNotLike(String value) {
            addCriterion("CHANNEL_SEQ_NO not like", value, "channelSeqNo");
            return (Criteria) this;
        }

        public Criteria andChannelSeqNoIn(List<String> values) {
            addCriterion("CHANNEL_SEQ_NO in", values, "channelSeqNo");
            return (Criteria) this;
        }

        public Criteria andChannelSeqNoNotIn(List<String> values) {
            addCriterion("CHANNEL_SEQ_NO not in", values, "channelSeqNo");
            return (Criteria) this;
        }

        public Criteria andChannelSeqNoBetween(String value1, String value2) {
            addCriterion("CHANNEL_SEQ_NO between", value1, value2, "channelSeqNo");
            return (Criteria) this;
        }

        public Criteria andChannelSeqNoNotBetween(String value1, String value2) {
            addCriterion("CHANNEL_SEQ_NO not between", value1, value2, "channelSeqNo");
            return (Criteria) this;
        }

        public Criteria andSysSeqNoIsNull() {
            addCriterion("SYS_SEQ_NO is null");
            return (Criteria) this;
        }

        public Criteria andSysSeqNoIsNotNull() {
            addCriterion("SYS_SEQ_NO is not null");
            return (Criteria) this;
        }

        public Criteria andSysSeqNoEqualTo(String value) {
            addCriterion("SYS_SEQ_NO =", value, "sysSeqNo");
            return (Criteria) this;
        }

        public Criteria andSysSeqNoNotEqualTo(String value) {
            addCriterion("SYS_SEQ_NO <>", value, "sysSeqNo");
            return (Criteria) this;
        }

        public Criteria andSysSeqNoGreaterThan(String value) {
            addCriterion("SYS_SEQ_NO >", value, "sysSeqNo");
            return (Criteria) this;
        }

        public Criteria andSysSeqNoGreaterThanOrEqualTo(String value) {
            addCriterion("SYS_SEQ_NO >=", value, "sysSeqNo");
            return (Criteria) this;
        }

        public Criteria andSysSeqNoLessThan(String value) {
            addCriterion("SYS_SEQ_NO <", value, "sysSeqNo");
            return (Criteria) this;
        }

        public Criteria andSysSeqNoLessThanOrEqualTo(String value) {
            addCriterion("SYS_SEQ_NO <=", value, "sysSeqNo");
            return (Criteria) this;
        }

        public Criteria andSysSeqNoLike(String value) {
            addCriterion("SYS_SEQ_NO like", value, "sysSeqNo");
            return (Criteria) this;
        }

        public Criteria andSysSeqNoNotLike(String value) {
            addCriterion("SYS_SEQ_NO not like", value, "sysSeqNo");
            return (Criteria) this;
        }

        public Criteria andSysSeqNoIn(List<String> values) {
            addCriterion("SYS_SEQ_NO in", values, "sysSeqNo");
            return (Criteria) this;
        }

        public Criteria andSysSeqNoNotIn(List<String> values) {
            addCriterion("SYS_SEQ_NO not in", values, "sysSeqNo");
            return (Criteria) this;
        }

        public Criteria andSysSeqNoBetween(String value1, String value2) {
            addCriterion("SYS_SEQ_NO between", value1, value2, "sysSeqNo");
            return (Criteria) this;
        }

        public Criteria andSysSeqNoNotBetween(String value1, String value2) {
            addCriterion("SYS_SEQ_NO not between", value1, value2, "sysSeqNo");
            return (Criteria) this;
        }

        public Criteria andProofTypeIsNull() {
            addCriterion("PROOF_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andProofTypeIsNotNull() {
            addCriterion("PROOF_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andProofTypeEqualTo(String value) {
            addCriterion("PROOF_TYPE =", value, "proofType");
            return (Criteria) this;
        }

        public Criteria andProofTypeNotEqualTo(String value) {
            addCriterion("PROOF_TYPE <>", value, "proofType");
            return (Criteria) this;
        }

        public Criteria andProofTypeGreaterThan(String value) {
            addCriterion("PROOF_TYPE >", value, "proofType");
            return (Criteria) this;
        }

        public Criteria andProofTypeGreaterThanOrEqualTo(String value) {
            addCriterion("PROOF_TYPE >=", value, "proofType");
            return (Criteria) this;
        }

        public Criteria andProofTypeLessThan(String value) {
            addCriterion("PROOF_TYPE <", value, "proofType");
            return (Criteria) this;
        }

        public Criteria andProofTypeLessThanOrEqualTo(String value) {
            addCriterion("PROOF_TYPE <=", value, "proofType");
            return (Criteria) this;
        }

        public Criteria andProofTypeLike(String value) {
            addCriterion("PROOF_TYPE like", value, "proofType");
            return (Criteria) this;
        }

        public Criteria andProofTypeNotLike(String value) {
            addCriterion("PROOF_TYPE not like", value, "proofType");
            return (Criteria) this;
        }

        public Criteria andProofTypeIn(List<String> values) {
            addCriterion("PROOF_TYPE in", values, "proofType");
            return (Criteria) this;
        }

        public Criteria andProofTypeNotIn(List<String> values) {
            addCriterion("PROOF_TYPE not in", values, "proofType");
            return (Criteria) this;
        }

        public Criteria andProofTypeBetween(String value1, String value2) {
            addCriterion("PROOF_TYPE between", value1, value2, "proofType");
            return (Criteria) this;
        }

        public Criteria andProofTypeNotBetween(String value1, String value2) {
            addCriterion("PROOF_TYPE not between", value1, value2, "proofType");
            return (Criteria) this;
        }

        public Criteria andOrgCodeIsNull() {
            addCriterion("ORG_CODE is null");
            return (Criteria) this;
        }

        public Criteria andOrgCodeIsNotNull() {
            addCriterion("ORG_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andOrgCodeEqualTo(String value) {
            addCriterion("ORG_CODE =", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotEqualTo(String value) {
            addCriterion("ORG_CODE <>", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeGreaterThan(String value) {
            addCriterion("ORG_CODE >", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_CODE >=", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeLessThan(String value) {
            addCriterion("ORG_CODE <", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeLessThanOrEqualTo(String value) {
            addCriterion("ORG_CODE <=", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeLike(String value) {
            addCriterion("ORG_CODE like", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotLike(String value) {
            addCriterion("ORG_CODE not like", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeIn(List<String> values) {
            addCriterion("ORG_CODE in", values, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotIn(List<String> values) {
            addCriterion("ORG_CODE not in", values, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeBetween(String value1, String value2) {
            addCriterion("ORG_CODE between", value1, value2, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotBetween(String value1, String value2) {
            addCriterion("ORG_CODE not between", value1, value2, "orgCode");
            return (Criteria) this;
        }

        public Criteria andInferReasonIsNull() {
            addCriterion("INFER_REASON is null");
            return (Criteria) this;
        }

        public Criteria andInferReasonIsNotNull() {
            addCriterion("INFER_REASON is not null");
            return (Criteria) this;
        }

        public Criteria andInferReasonEqualTo(String value) {
            addCriterion("INFER_REASON =", value, "inferReason");
            return (Criteria) this;
        }

        public Criteria andInferReasonNotEqualTo(String value) {
            addCriterion("INFER_REASON <>", value, "inferReason");
            return (Criteria) this;
        }

        public Criteria andInferReasonGreaterThan(String value) {
            addCriterion("INFER_REASON >", value, "inferReason");
            return (Criteria) this;
        }

        public Criteria andInferReasonGreaterThanOrEqualTo(String value) {
            addCriterion("INFER_REASON >=", value, "inferReason");
            return (Criteria) this;
        }

        public Criteria andInferReasonLessThan(String value) {
            addCriterion("INFER_REASON <", value, "inferReason");
            return (Criteria) this;
        }

        public Criteria andInferReasonLessThanOrEqualTo(String value) {
            addCriterion("INFER_REASON <=", value, "inferReason");
            return (Criteria) this;
        }

        public Criteria andInferReasonLike(String value) {
            addCriterion("INFER_REASON like", value, "inferReason");
            return (Criteria) this;
        }

        public Criteria andInferReasonNotLike(String value) {
            addCriterion("INFER_REASON not like", value, "inferReason");
            return (Criteria) this;
        }

        public Criteria andInferReasonIn(List<String> values) {
            addCriterion("INFER_REASON in", values, "inferReason");
            return (Criteria) this;
        }

        public Criteria andInferReasonNotIn(List<String> values) {
            addCriterion("INFER_REASON not in", values, "inferReason");
            return (Criteria) this;
        }

        public Criteria andInferReasonBetween(String value1, String value2) {
            addCriterion("INFER_REASON between", value1, value2, "inferReason");
            return (Criteria) this;
        }

        public Criteria andInferReasonNotBetween(String value1, String value2) {
            addCriterion("INFER_REASON not between", value1, value2, "inferReason");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("STATUS like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("STATUS not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("STATUS not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andReportTimeIsNull() {
            addCriterion("REPORT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andReportTimeIsNotNull() {
            addCriterion("REPORT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andReportTimeEqualTo(Date value) {
            addCriterion("REPORT_TIME =", value, "reportTime");
            return (Criteria) this;
        }

        public Criteria andReportTimeNotEqualTo(Date value) {
            addCriterion("REPORT_TIME <>", value, "reportTime");
            return (Criteria) this;
        }

        public Criteria andReportTimeGreaterThan(Date value) {
            addCriterion("REPORT_TIME >", value, "reportTime");
            return (Criteria) this;
        }

        public Criteria andReportTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("REPORT_TIME >=", value, "reportTime");
            return (Criteria) this;
        }

        public Criteria andReportTimeLessThan(Date value) {
            addCriterion("REPORT_TIME <", value, "reportTime");
            return (Criteria) this;
        }

        public Criteria andReportTimeLessThanOrEqualTo(Date value) {
            addCriterion("REPORT_TIME <=", value, "reportTime");
            return (Criteria) this;
        }

        public Criteria andReportTimeIn(List<Date> values) {
            addCriterion("REPORT_TIME in", values, "reportTime");
            return (Criteria) this;
        }

        public Criteria andReportTimeNotIn(List<Date> values) {
            addCriterion("REPORT_TIME not in", values, "reportTime");
            return (Criteria) this;
        }

        public Criteria andReportTimeBetween(Date value1, Date value2) {
            addCriterion("REPORT_TIME between", value1, value2, "reportTime");
            return (Criteria) this;
        }

        public Criteria andReportTimeNotBetween(Date value1, Date value2) {
            addCriterion("REPORT_TIME not between", value1, value2, "reportTime");
            return (Criteria) this;
        }

        public Criteria andProcessResultIsNull() {
            addCriterion("PROCESS_RESULT is null");
            return (Criteria) this;
        }

        public Criteria andProcessResultIsNotNull() {
            addCriterion("PROCESS_RESULT is not null");
            return (Criteria) this;
        }

        public Criteria andProcessResultEqualTo(String value) {
            addCriterion("PROCESS_RESULT =", value, "processResult");
            return (Criteria) this;
        }

        public Criteria andProcessResultNotEqualTo(String value) {
            addCriterion("PROCESS_RESULT <>", value, "processResult");
            return (Criteria) this;
        }

        public Criteria andProcessResultGreaterThan(String value) {
            addCriterion("PROCESS_RESULT >", value, "processResult");
            return (Criteria) this;
        }

        public Criteria andProcessResultGreaterThanOrEqualTo(String value) {
            addCriterion("PROCESS_RESULT >=", value, "processResult");
            return (Criteria) this;
        }

        public Criteria andProcessResultLessThan(String value) {
            addCriterion("PROCESS_RESULT <", value, "processResult");
            return (Criteria) this;
        }

        public Criteria andProcessResultLessThanOrEqualTo(String value) {
            addCriterion("PROCESS_RESULT <=", value, "processResult");
            return (Criteria) this;
        }

        public Criteria andProcessResultLike(String value) {
            addCriterion("PROCESS_RESULT like", value, "processResult");
            return (Criteria) this;
        }

        public Criteria andProcessResultNotLike(String value) {
            addCriterion("PROCESS_RESULT not like", value, "processResult");
            return (Criteria) this;
        }

        public Criteria andProcessResultIn(List<String> values) {
            addCriterion("PROCESS_RESULT in", values, "processResult");
            return (Criteria) this;
        }

        public Criteria andProcessResultNotIn(List<String> values) {
            addCriterion("PROCESS_RESULT not in", values, "processResult");
            return (Criteria) this;
        }

        public Criteria andProcessResultBetween(String value1, String value2) {
            addCriterion("PROCESS_RESULT between", value1, value2, "processResult");
            return (Criteria) this;
        }

        public Criteria andProcessResultNotBetween(String value1, String value2) {
            addCriterion("PROCESS_RESULT not between", value1, value2, "processResult");
            return (Criteria) this;
        }

        public Criteria andErrReasonIsNull() {
            addCriterion("ERR_REASON is null");
            return (Criteria) this;
        }

        public Criteria andErrReasonIsNotNull() {
            addCriterion("ERR_REASON is not null");
            return (Criteria) this;
        }

        public Criteria andErrReasonEqualTo(String value) {
            addCriterion("ERR_REASON =", value, "errReason");
            return (Criteria) this;
        }

        public Criteria andErrReasonNotEqualTo(String value) {
            addCriterion("ERR_REASON <>", value, "errReason");
            return (Criteria) this;
        }

        public Criteria andErrReasonGreaterThan(String value) {
            addCriterion("ERR_REASON >", value, "errReason");
            return (Criteria) this;
        }

        public Criteria andErrReasonGreaterThanOrEqualTo(String value) {
            addCriterion("ERR_REASON >=", value, "errReason");
            return (Criteria) this;
        }

        public Criteria andErrReasonLessThan(String value) {
            addCriterion("ERR_REASON <", value, "errReason");
            return (Criteria) this;
        }

        public Criteria andErrReasonLessThanOrEqualTo(String value) {
            addCriterion("ERR_REASON <=", value, "errReason");
            return (Criteria) this;
        }

        public Criteria andErrReasonLike(String value) {
            addCriterion("ERR_REASON like", value, "errReason");
            return (Criteria) this;
        }

        public Criteria andErrReasonNotLike(String value) {
            addCriterion("ERR_REASON not like", value, "errReason");
            return (Criteria) this;
        }

        public Criteria andErrReasonIn(List<String> values) {
            addCriterion("ERR_REASON in", values, "errReason");
            return (Criteria) this;
        }

        public Criteria andErrReasonNotIn(List<String> values) {
            addCriterion("ERR_REASON not in", values, "errReason");
            return (Criteria) this;
        }

        public Criteria andErrReasonBetween(String value1, String value2) {
            addCriterion("ERR_REASON between", value1, value2, "errReason");
            return (Criteria) this;
        }

        public Criteria andErrReasonNotBetween(String value1, String value2) {
            addCriterion("ERR_REASON not between", value1, value2, "errReason");
            return (Criteria) this;
        }

        public Criteria andProcessTimeIsNull() {
            addCriterion("PROCESS_TIME is null");
            return (Criteria) this;
        }

        public Criteria andProcessTimeIsNotNull() {
            addCriterion("PROCESS_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andProcessTimeEqualTo(Date value) {
            addCriterion("PROCESS_TIME =", value, "processTime");
            return (Criteria) this;
        }

        public Criteria andProcessTimeNotEqualTo(Date value) {
            addCriterion("PROCESS_TIME <>", value, "processTime");
            return (Criteria) this;
        }

        public Criteria andProcessTimeGreaterThan(Date value) {
            addCriterion("PROCESS_TIME >", value, "processTime");
            return (Criteria) this;
        }

        public Criteria andProcessTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("PROCESS_TIME >=", value, "processTime");
            return (Criteria) this;
        }

        public Criteria andProcessTimeLessThan(Date value) {
            addCriterion("PROCESS_TIME <", value, "processTime");
            return (Criteria) this;
        }

        public Criteria andProcessTimeLessThanOrEqualTo(Date value) {
            addCriterion("PROCESS_TIME <=", value, "processTime");
            return (Criteria) this;
        }

        public Criteria andProcessTimeIn(List<Date> values) {
            addCriterion("PROCESS_TIME in", values, "processTime");
            return (Criteria) this;
        }

        public Criteria andProcessTimeNotIn(List<Date> values) {
            addCriterion("PROCESS_TIME not in", values, "processTime");
            return (Criteria) this;
        }

        public Criteria andProcessTimeBetween(Date value1, Date value2) {
            addCriterion("PROCESS_TIME between", value1, value2, "processTime");
            return (Criteria) this;
        }

        public Criteria andProcessTimeNotBetween(Date value1, Date value2) {
            addCriterion("PROCESS_TIME not between", value1, value2, "processTime");
            return (Criteria) this;
        }

        public Criteria andChannelSeqNoLikeInsensitive(String value) {
            addCriterion("upper(CHANNEL_SEQ_NO) like", value.toUpperCase(), "channelSeqNo");
            return (Criteria) this;
        }

        public Criteria andSysSeqNoLikeInsensitive(String value) {
            addCriterion("upper(SYS_SEQ_NO) like", value.toUpperCase(), "sysSeqNo");
            return (Criteria) this;
        }

        public Criteria andProofTypeLikeInsensitive(String value) {
            addCriterion("upper(PROOF_TYPE) like", value.toUpperCase(), "proofType");
            return (Criteria) this;
        }

        public Criteria andOrgCodeLikeInsensitive(String value) {
            addCriterion("upper(ORG_CODE) like", value.toUpperCase(), "orgCode");
            return (Criteria) this;
        }

        public Criteria andInferReasonLikeInsensitive(String value) {
            addCriterion("upper(INFER_REASON) like", value.toUpperCase(), "inferReason");
            return (Criteria) this;
        }

        public Criteria andStatusLikeInsensitive(String value) {
            addCriterion("upper(STATUS) like", value.toUpperCase(), "status");
            return (Criteria) this;
        }

        public Criteria andProcessResultLikeInsensitive(String value) {
            addCriterion("upper(PROCESS_RESULT) like", value.toUpperCase(), "processResult");
            return (Criteria) this;
        }

        public Criteria andErrReasonLikeInsensitive(String value) {
            addCriterion("upper(ERR_REASON) like", value.toUpperCase(), "errReason");
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