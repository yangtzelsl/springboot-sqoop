package com.yangtzelsl.sqoop.service.impl;

import com.yangtzelsl.sqoop.entity.SqoopBean;
import com.yangtzelsl.sqoop.service.SqoopService;
import org.apache.hadoop.conf.Configuration;
import org.apache.sqoop.Sqoop;
import org.apache.sqoop.tool.SqoopTool;
import org.apache.sqoop.util.OptionsFileUtil;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class SqoopServiceImpl implements SqoopService {

    @Override
    public SqoopBean mysqlTohdfs(String connect, String driver, String username,
                                 String password, String table, int m, String targetdir,
                                 String hdfsAddr) throws Exception {
        String[] args = new String[]{
                "--connect", connect,
                "--driver", driver,
                "-username", username,
                "-password", password,
                "--table", table,
                "-m", String.valueOf(m),
                "--target-dir", targetdir,
        };
        SqoopBean sqoopBean = new SqoopBean();
        String[] expandArguments = OptionsFileUtil.expandArguments(args);
        SqoopTool tool = SqoopTool.getTool("import");
        Configuration conf = new Configuration();
        conf.set("fs.default.name", hdfsAddr);
        Configuration loadPlugins = SqoopTool.loadPlugins(conf);
        Sqoop sqoop = new Sqoop((com.cloudera.sqoop.tool.SqoopTool) tool, loadPlugins);
        sqoopBean.setI(Sqoop.runSqoop(sqoop, expandArguments));
        sqoopBean.setTs(new Timestamp(new Date().getTime()));
        return sqoopBean;


    }

    @Override
    public SqoopBean mysql2Hbase(String jdbc, String driver, String username, String password, String mysqlTable, String hbaseTableName, String columnFamily, String rowkey, int m) throws Exception {
        String[] args = new String[]{
                "--connect", jdbc,
                "--driver", driver,
                "-username", username,
                "-password", password,
                "--table", mysqlTable,
                "--hbase-table", hbaseTableName,
                "--column-family", columnFamily,
                "--hbase-create-table",
                "--hbase-row-key", rowkey,
                "-m", String.valueOf(m),
        };
        SqoopBean sqoopBean = new SqoopBean();
        String[] expandArguments = OptionsFileUtil.expandArguments(args);
        SqoopTool tool = SqoopTool.getTool("import");
        Configuration conf = new Configuration();
        Configuration loadPlugins = SqoopTool.loadPlugins(conf);
        Sqoop sqoop = new Sqoop((com.cloudera.sqoop.tool.SqoopTool) tool, loadPlugins);
        sqoopBean.setI(Sqoop.runSqoop(sqoop, expandArguments));
        sqoopBean.setTs(new Timestamp(new Date().getTime()));
        return sqoopBean;
    }
}
