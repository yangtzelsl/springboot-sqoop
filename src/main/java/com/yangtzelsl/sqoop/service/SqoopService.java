package com.yangtzelsl.sqoop.service;


import com.yangtzelsl.sqoop.entity.SqoopBean;

public interface SqoopService {
    /**
     * mysql到hdfs
     *
     * @param connect
     * @param driver
     * @param username
     * @param password
     * @param table
     * @param m
     * @param targetdir
     * @param hdfsAddr
     * @return
     * @throws Exception
     */
    SqoopBean mysqlTohdfs(String connect, String driver, String username, String password, String table, int m, String targetdir, String hdfsAddr) throws Exception;

    /**
     * mysql到hbase
     *
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
    SqoopBean mysql2Hbase(String jdbc, String driver, String username, String password, String mysqlTable, String hbaseTableName, String columnFamily, String rowkey, int m) throws Exception;


}
