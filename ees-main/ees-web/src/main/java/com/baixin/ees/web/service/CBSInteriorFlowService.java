package com.baixin.ees.web.service;

import java.util.List;
import java.util.Map;

public interface CBSInteriorFlowService {
    /**
     * 没有条件时查询数据列表
     * @return
     */
    List<Map<String,String>> listData() throws Exception;

    /**
     * 根据查询条件查询数据
     * @return
     */
    List<Map<String,String>> listDataByReference();
}
