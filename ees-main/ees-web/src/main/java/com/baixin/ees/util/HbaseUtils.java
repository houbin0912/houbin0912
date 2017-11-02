package com.baixin.ees.util;

import com.baixin.ees.Constants;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.Properties;

public class HbaseUtils {
    public static Connection hbaseConn;

    /**
     * 初始化hbase连接
     */
    public static void initConn() throws IOException {
        Properties prop = PropertyUtil.getProp(Constants.PropertiesFileName.HBASE_PROP);
        Configuration conf = HBaseConfiguration.create();
        for(Object key:prop.keySet()) {
            String keyStr = (String) key;
            conf.set(keyStr, prop.getProperty(keyStr));
        }
        hbaseConn = ConnectionFactory.createConnection(conf);
    }

    /**
     * 关闭hbase连接
     */
    public static void closeConn(){
        if(hbaseConn != null){
            try {
                hbaseConn.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 通过表名获取table对象
     * @param tableName
     * @return
     */
    public static Table getTable(TableName tableName){
        try {
            return hbaseConn.getTable(tableName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 关闭table对象
     * @param table
     */
    public static void closeTable(Table table){
        try {
            if(table != null){
                table.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取admin对象
     * @return
     */
    public static Admin getAdmin(){
        try {
            return hbaseConn.getAdmin();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭admin连接
     * @param admin
     */
    public static void closeAdmin(Admin admin){
        try {
            if(admin != null){
                admin.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args ) throws IOException {
        initConn();
        Table table = getTable(TableName.valueOf(Bytes.toBytes("testH"),Bytes.toBytes("tranHistDay")));
        ResultScanner scanner = table.getScanner(new Scan());
        Result result = scanner.next();
        Cell cell = result.getColumnLatestCell(Bytes.toBytes("cf"),Bytes.toBytes("REFERENCE"));
        System.out.println(Bytes.toString(CellUtil.cloneValue(cell)));
        table.close();
        closeConn();
    }
}
