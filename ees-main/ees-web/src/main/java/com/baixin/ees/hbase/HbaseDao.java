package com.baixin.ees.hbase;

import com.baixin.ees.hbase.interfaces.ResultsExtractor;
import com.baixin.ees.hbase.interfaces.RowMapper;
import com.baixin.ees.hbase.interfaces.TableCallback;
import com.baixin.ees.util.HbaseUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.util.Assert;

import java.util.List;

public class HbaseDao {
    private static final Log log = LogFactory.getLog(HbaseDao.class);

    /**
     * 执行表级别的操作的基础方法，管理table对象的打开和关闭
     *
     * @param namespace 命名空间
     * @param tableName 表名
     * @param action    执行的操作接口类
     * @param <T>       返回值类型
     * @return
     * @throws Exception
     */
    public <T> T execute(String namespace, String tableName, TableCallback<T> action) throws Exception {
        Assert.notNull(action, "Callback object must not be null");
        Assert.notNull(tableName, "No table specified");

        Table table = HbaseUtils.getTable(TableName.valueOf(Bytes.toBytes(namespace), Bytes.toBytes(tableName)));
        try {
            if (table != null) {
                T result = action.doInTable(table);
                return result;
            }
            throw new Exception("hbase execute action fail.");
        } catch (Throwable th) {
            if (th instanceof Error) {
                throw ((Error) th);
            }
            if (th instanceof RuntimeException) {
                throw ((RuntimeException) th);
            }
            throw new Exception(th);
        } finally {
            if (table != null) {
                table.close();
            }
        }
    }

    /**
     * 执行get方法的工具类
     *
     * @param namespace 命名空间
     * @param tableName 表名
     * @param get       get对象
     * @param rowMapper 每行的处理逻辑对象
     * @param <T>       返回值类型
     * @return
     * @throws Exception
     */
    public <T> T executeGet(String namespace, String tableName, final Get get, final RowMapper<T> rowMapper) throws Exception {
        return execute(namespace, tableName, new TableCallback<T>() {
            @Override
            public T doInTable(Table table) throws Throwable {
                Result result = table.get(get);
                return rowMapper.mapRow(result, 0);
            }
        });
    }

    /**
     * 通过scan获取hbase中的一批数据
     *
     * @param namespace 命名空间
     * @param tableName 表名
     * @param scan      scan对象
     * @param rowMapper rowmapper对每一行的处理逻辑对象
     * @param <T>       返回值类型
     * @return
     * @throws Exception
     */
    public <T> List<T> executeScan(String namespace, String tableName, final Scan scan, final RowMapper<T> rowMapper) throws Exception {
        return executeScan(namespace, tableName, scan, new RowMapperResultsExtractor<T>(rowMapper));
    }

    /**
     * 通过scan获取hbase中的一批数据
     *
     * @param namespace
     * @param tableName
     * @param scan
     * @param resultsExtractor
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> T executeScan(String namespace, String tableName, final Scan scan, final ResultsExtractor<T> resultsExtractor) throws Exception {
        return execute(namespace, tableName, new TableCallback<T>() {
            @Override
            public T doInTable(Table table) throws Throwable {
                ResultScanner resultScanner = table.getScanner(scan);
                try {
                    return resultsExtractor.extractData(resultScanner);
                } finally {
                    resultScanner.close();
                }
            }
        });
    }

    /**
     * 王hbase中put数据
     * @param put
     * @return
     */
    public boolean executePut(Put put){
        return false;
    }

    /**
     * 从hbase中删除数据
     * @return
     */
    public boolean executeDelete(Delete delete){
        return false;
    }

    /**
     * 校验数据是否存在
     * @return
     */
    public boolean checkExist(){
        return false;
    }
}
