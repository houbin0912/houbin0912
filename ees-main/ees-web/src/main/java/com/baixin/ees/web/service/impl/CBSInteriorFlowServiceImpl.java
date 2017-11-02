package com.baixin.ees.web.service.impl;

import com.baixin.ees.web.dao.hbase.CbsFlowHbaseDao;
import com.baixin.ees.web.service.CBSInteriorFlowService;
import org.apache.hadoop.hbase.client.Scan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CBSInteriorFlowServiceImpl implements CBSInteriorFlowService {

    @Autowired
    private CbsFlowHbaseDao cbsFlowHbaseDao;


    @Override
    public List<Map<String, String>> listData() throws Exception {
        Scan scan = new Scan();

        cbsFlowHbaseDao.getFromScan(scan);
        return null;
    }

    @Override
    public List<Map<String, String>> listDataByReference() {
        //拼接scan

        return null;
    }
}
