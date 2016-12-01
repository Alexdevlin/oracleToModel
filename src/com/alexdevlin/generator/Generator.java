package com.alexdevlin.generator;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import com.dbutil.DBHelper;

public class Generator {
  
  /**
   * 
   * @Description:生成mapper 
   * @Auther: alexdevlin
   * @Date: 2015年4月30日 下午6:06:21
   */
  public static void main(String[] args) throws IOException, XMLParserException, SQLException, InterruptedException, InvalidConfigurationException {
    List<String> warnings = new ArrayList<String>();
    boolean overwrite = true;
    ConfigurationParser cp = new ConfigurationParser(warnings);
    Configuration config = cp.parseConfiguration(Generator.class.getResourceAsStream("/generatorConfig.xml"));
    DefaultShellCallback callback = new DefaultShellCallback(overwrite);
    try {
      MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
      myBatisGenerator.generate(null);
      DBHelper.close();
      System.out.println("success");
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}
