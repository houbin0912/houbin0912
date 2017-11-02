package com.baixin.ees.web.dao.hbase;

import org.apache.hadoop.hbase.client.Scan;

import java.util.List;
import java.util.Map;

public interface CbsFlowHbaseDao {

    /**
     * 通过rowkey获取单条记录
     * @return
     */
    Map<String,String> getFromRowkey(byte[] rowkey) throws Exception;

    /**
     * 通过scan来获取记录
     * @return
     */
    List<Map<String,String>> getFromScan(Scan scan) throws Exception;

}
