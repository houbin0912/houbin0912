package com.baixin.ees.web.dao.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SysAuthExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public SysAuthExample() {
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

        public Criteria andAuthIdIsNull() {
            addCriterion("AUTH_ID is null");
            return (Criteria) this;
        }

        public Criteria andAuthIdIsNotNull() {
            addCriterion("AUTH_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAuthIdEqualTo(Integer value) {
            addCriterion("AUTH_ID =", value, "authId");
            return (Criteria) this;
        }

        public Criteria andAuthIdNotEqualTo(Integer value) {
            addCriterion("AUTH_ID <>", value, "authId");
            return (Criteria) this;
        }

        public Criteria andAuthIdGreaterThan(Integer value) {
            addCriterion("AUTH_ID >", value, "authId");
            return (Criteria) this;
        }

        public Criteria andAuthIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("AUTH_ID >=", value, "authId");
            return (Criteria) this;
        }

        public Criteria andAuthIdLessThan(Integer value) {
            addCriterion("AUTH_ID <", value, "authId");
            return (Criteria) this;
        }

        public Criteria andAuthIdLessThanOrEqualTo(Integer value) {
            addCriterion("AUTH_ID <=", value, "authId");
            return (Criteria) this;
        }

        public Criteria andAuthIdIn(List<Integer> values) {
            addCriterion("AUTH_ID in", values, "authId");
            return (Criteria) this;
        }

        public Criteria andAuthIdNotIn(List<Integer> values) {
            addCriterion("AUTH_ID not in", values, "authId");
            return (Criteria) this;
        }

        public Criteria andAuthIdBetween(Integer value1, Integer value2) {
            addCriterion("AUTH_ID between", value1, value2, "authId");
            return (Criteria) this;
        }

        public Criteria andAuthIdNotBetween(Integer value1, Integer value2) {
            addCriterion("AUTH_ID not between", value1, value2, "authId");
            return (Criteria) this;
        }

        public Criteria andRoleIdIsNull() {
            addCriterion("ROLE_ID is null");
            return (Criteria) this;
        }

        public Criteria andRoleIdIsNotNull() {
            addCriterion("ROLE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andRoleIdEqualTo(Integer value) {
            addCriterion("ROLE_ID =", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotEqualTo(Integer value) {
            addCriterion("ROLE_ID <>", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdGreaterThan(Integer value) {
            addCriterion("ROLE_ID >", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ROLE_ID >=", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdLessThan(Integer value) {
            addCriterion("ROLE_ID <", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdLessThanOrEqualTo(Integer value) {
            addCriterion("ROLE_ID <=", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdIn(List<Integer> values) {
            addCriterion("ROLE_ID in", values, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotIn(List<Integer> values) {
            addCriterion("ROLE_ID not in", values, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdBetween(Integer value1, Integer value2) {
            addCriterion("ROLE_ID between", value1, value2, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ROLE_ID not between", value1, value2, "roleId");
            return (Criteria) this;
        }

        public Criteria andMenuIdsIsNull() {
            addCriterion("MENU_IDS is null");
            return (Criteria) this;
        }

        public Criteria andMenuIdsIsNotNull() {
            addCriterion("MENU_IDS is not null");
            return (Criteria) this;
        }

        public Criteria andMenuIdsEqualTo(String value) {
            addCriterion("MENU_IDS =", value, "menuIds");
            return (Criteria) this;
        }

        public Criteria andMenuIdsNotEqualTo(String value) {
            addCriterion("MENU_IDS <>", value, "menuIds");
            return (Criteria) this;
        }

        public Criteria andMenuIdsGreaterThan(String value) {
            addCriterion("MENU_IDS >", value, "menuIds");
            return (Criteria) this;
        }

        public Criteria andMenuIdsGreaterThanOrEqualTo(String value) {
            addCriterion("MENU_IDS >=", value, "menuIds");
            return (Criteria) this;
        }

        public Criteria andMenuIdsLessThan(String value) {
            addCriterion("MENU_IDS <", value, "menuIds");
            return (Criteria) this;
        }

        public Criteria andMenuIdsLessThanOrEqualTo(String value) {
            addCriterion("MENU_IDS <=", value, "menuIds");
            return (Criteria) this;
        }

        public Criteria andMenuIdsLike(String value) {
            addCriterion("MENU_IDS like", value, "menuIds");
            return (Criteria) this;
        }

        public Criteria andMenuIdsNotLike(String value) {
            addCriterion("MENU_IDS not like", value, "menuIds");
            return (Criteria) this;
        }

        public Criteria andMenuIdsIn(List<String> values) {
            addCriterion("MENU_IDS in", values, "menuIds");
            return (Criteria) this;
        }

        public Criteria andMenuIdsNotIn(List<String> values) {
            addCriterion("MENU_IDS not in", values, "menuIds");
            return (Criteria) this;
        }

        public Criteria andMenuIdsBetween(String value1, String value2) {
            addCriterion("MENU_IDS between", value1, value2, "menuIds");
            return (Criteria) this;
        }

        public Criteria andMenuIdsNotBetween(String value1, String value2) {
            addCriterion("MENU_IDS not between", value1, value2, "menuIds");
            return (Criteria) this;
        }

        public Criteria andEnPowerTimeIsNull() {
            addCriterion("EN_POWER_TIME is null");
            return (Criteria) this;
        }

        public Criteria andEnPowerTimeIsNotNull() {
            addCriterion("EN_POWER_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andEnPowerTimeEqualTo(Date value) {
            addCriterion("EN_POWER_TIME =", value, "enPowerTime");
            return (Criteria) this;
        }

        public Criteria andEnPowerTimeNotEqualTo(Date value) {
            addCriterion("EN_POWER_TIME <>", value, "enPowerTime");
            return (Criteria) this;
        }

        public Criteria andEnPowerTimeGreaterThan(Date value) {
            addCriterion("EN_POWER_TIME >", value, "enPowerTime");
            return (Criteria) this;
        }

        public Criteria andEnPowerTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("EN_POWER_TIME >=", value, "enPowerTime");
            return (Criteria) this;
        }

        public Criteria andEnPowerTimeLessThan(Date value) {
            addCriterion("EN_POWER_TIME <", value, "enPowerTime");
            return (Criteria) this;
        }

        public Criteria andEnPowerTimeLessThanOrEqualTo(Date value) {
            addCriterion("EN_POWER_TIME <=", value, "enPowerTime");
            return (Criteria) this;
        }

        public Criteria andEnPowerTimeIn(List<Date> values) {
            addCriterion("EN_POWER_TIME in", values, "enPowerTime");
            return (Criteria) this;
        }

        public Criteria andEnPowerTimeNotIn(List<Date> values) {
            addCriterion("EN_POWER_TIME not in", values, "enPowerTime");
            return (Criteria) this;
        }

        public Criteria andEnPowerTimeBetween(Date value1, Date value2) {
            addCriterion("EN_POWER_TIME between", value1, value2, "enPowerTime");
            return (Criteria) this;
        }

        public Criteria andEnPowerTimeNotBetween(Date value1, Date value2) {
            addCriterion("EN_POWER_TIME not between", value1, value2, "enPowerTime");
            return (Criteria) this;
        }

        public Criteria andMenuIdsLikeInsensitive(String value) {
            addCriterion("upper(MENU_IDS) like", value.toUpperCase(), "menuIds");
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