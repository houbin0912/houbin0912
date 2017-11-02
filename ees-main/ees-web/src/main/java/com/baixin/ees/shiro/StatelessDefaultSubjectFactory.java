package com.baixin.ees.shiro;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;


/**
 * 自定义session的工厂，目前没有用
 * 
 * @Description
 * @author: xjj
 * @CreateDate:2016年1月23日
 */
public class StatelessDefaultSubjectFactory extends DefaultWebSubjectFactory {
    public Subject createSubject(SubjectContext context) {
        // 不创建session
        context.setSessionCreationEnabled(true);
        return super.createSubject(context);
    }
}