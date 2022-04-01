package com.yangtzelsl.sqoop.controller;

import com.yangtzelsl.sqoop.entity.SqoopBean;
import com.yangtzelsl.sqoop.service.SqoopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
public class SqoopController {
    @Autowired
    private SqoopService sqoopService;

    /**
     * MYSQL数据到HDFS
     *
     * @param connect   jdbc:mysql://127.0.0.1:3306/test
     * @param driver    com.mysql.cj.jdbc.Driver
     * @param username  /
     * @param password  /
     * @param table     /
     * @param m         MR的并行度
     * @param targetdir hdfs目录
     * @param hdfsAddr  hdfs地址
     * @return
     * @throws Exception
     */
    @PostMapping("/mysqlTohdfs")
    @ResponseBody
    public SqoopBean mysqlTohdfs(String connect, String driver, String username, String password, String table, int m, String targetdir, String hdfsAddr) throws Exception {
        return sqoopService.mysqlTohdfs(connect, driver, username, password, table, m, targetdir, hdfsAddr);
    }

    /**
     * @param jdbc
     * @param driver
     * @param username
     * @param password
     * @param mysqlTable
     * @param hbaseTableName
     * @param columnFamily
     * @param rowkey
     * @param m
     * @return
     * @throws Exception
     */
    @PostMapping("/mysql2hbase")
    @ResponseBody
    public SqoopBean transformMysql2Hbase(String jdbc, String driver, String username, String password, String mysqlTable, String hbaseTableName, String columnFamily, String rowkey, int m) throws Exception {
        return sqoopService.mysql2Hbase(jdbc, driver, username, password, mysqlTable, hbaseTableName, columnFamily, rowkey, m);
    }


}
