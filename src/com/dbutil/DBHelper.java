package com.dbutil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;


public class DBHelper {
  private static String driver = null;
  private static String url = null;
  private static String name = null;
  private static String pwd = null;
  private static Connection conn;
  private static Statement stmt;
  protected static ResultSet rs;
  
  static{
    getJUrl(); 
    try {
      stmt=getConn().createStatement();
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
    
  }
  
  public static ResultSet getComment(String sql) throws SQLException, Exception{
    try {
      rs=null;
      rs=stmt.executeQuery(sql);  
    } catch (Exception e) {
      throw e;
    }
    return rs;
  }

  public static Connection getConn() throws Exception{
    try {
      Class.forName(driver);
      conn=DriverManager.getConnection(url,name,pwd);
    } catch (Exception e) {
      throw e;
    }
    return conn;
  }

  public static void close() throws Exception{
    try {
      rs.close();
      stmt.close();
      conn.close();
    } catch (Exception e) {
      throw e;
    }
  }
  public static void getJUrl(){
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
    try  
    {  
      DocumentBuilder db = dbf.newDocumentBuilder();  
      Document doc = db.parse("src/generatorConfig.xml");  
      NodeList jnode = doc.getElementsByTagName("jdbcConnection"); 
      jnode.item(0).getAttributes();
      driver=jnode.item(0).getAttributes().getNamedItem("driverClass").getNodeValue();
      url=jnode.item(0).getAttributes().getNamedItem("connectionURL").getNodeValue();
      name=jnode.item(0).getAttributes().getNamedItem("userId").getNodeValue();
      pwd=jnode.item(0).getAttributes().getNamedItem("password").getNodeValue();
    }  
    catch (Exception e){  
      e.printStackTrace();  
    }
  }
}
