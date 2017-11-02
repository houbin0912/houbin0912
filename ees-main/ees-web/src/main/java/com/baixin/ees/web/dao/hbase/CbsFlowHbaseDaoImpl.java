package com.baixin.ees.web.dao.hbase;

import com.baixin.ees.hbase.HbaseDao;
import com.baixin.ees.hbase.interfaces.RowMapper;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CbsFlowHbaseDaoImpl implements CbsFlowHbaseDao{

    @Autowired
    private HbaseDao hbaseDao;

    private RowMapper<Map<String, String>> rowMapper = new RowMapper<Map<String, String>>() {
        @Override
        public Map<String, String> mapRow(Result result, int rowNum) throws Exception {
            //将数据拼装成map对象
            Map<String,String> resultMap = new HashMap<String,String>();
            Cell cell = result.getColumnLatestCell(Bytes.toBytes("cf"),Bytes.toBytes("REFERENCE"));
            if(cell != null){
                resultMap.put("REFERENCE", Bytes.toString(CellUtil.cloneValue(cell)));
            }
            return resultMap;
        }
    };

    public String namespace = "testH";
    public String tablename = "tranHistDay";

    @Override
    public Map<String, String> getFromRowkey(final byte[] rowkey) throws Exception {
        Get get = new Get(rowkey);
        return hbaseDao.executeGet(namespace, tablename, get,rowMapper);
    }

    @Override
    public List<Map<String, String>> getFromScan(Scan scan) throws Exception {
        return hbaseDao.executeScan(namespace, tablename, scan, rowMapper);
    }
}
