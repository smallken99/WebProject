package com.smallken.db;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class DBUtil {
	
	private static Properties props = null;
	private  Connection conn = null;
	private  Statement stmt = null;
	
	/**
	 * 
	 * @param isBatch
	 * @throws SQLException
	 */
	public DBUtil(boolean isBatch) throws SQLException {
		super();		
		if(isBatch){
			createConnection(true);
		}else{
			createConnection2(true);
		}		
		 stmt = conn.createStatement();
	}
	
	/**
	 * 
	 * @param isBatch
	 * @param istAutoCommit
	 * @throws SQLException
	 */
	public DBUtil(boolean isBatch,boolean istAutoCommit) throws SQLException {
		super();
		if(isBatch){
			createConnection(istAutoCommit);
		}else{
			createConnection2(istAutoCommit);
		}		
		stmt = conn.createStatement();
	}
		
	private void createConnection(boolean istAutoCommit) {
		
		try {
			loadProperties();
	        String driver = getProperties("driver");
	        String url = getProperties("url");  
	        String user = getProperties("user");  
	        String password = getProperties("password");
	        Class.forName(driver);
			conn = 	DriverManager.getConnection(url,user, password);
			conn.setAutoCommit(istAutoCommit);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	private void createConnection2(boolean istAutoCommit) {
		
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/DB");
			conn = ds.getConnection(); 
			conn.setAutoCommit(istAutoCommit);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public ResultSet Query(String sql) throws SQLException{		
		return stmt.executeQuery(sql);
	}
	
	public int  executeUpdate(String sql) throws SQLException{
		return  stmt.executeUpdate(sql);
	}
	
	
	public List<Map<String, Object>>  dsToMaps(ResultSet rs ){
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		try {
			ResultSetMetaData md = rs.getMetaData();
			int columnCount = md.getColumnCount();
			while (rs.next()) { 
				Map<String,Object> rowData = new HashMap<String,Object>();
				for (int i = 1; i <= columnCount; i++) {  
					rowData.put(md.getColumnName(i), rs.getObject(i));
				}		
				list.add(rowData);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	public Connection getConnection(){
		return conn;
	}
	
	public void commit(){
		try {
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void rollback(){
		try {
			conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 
	 */
	public void close(){
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	private static void loadProperties() {
		props = new Properties();
		try {
			File f = new File("WebContent/WEB-INF/DatabaseConf.properties");
			//File f = new File("C:\\Users\\023779\\workspace\\WebProject\\WebContent\\WEB-INF\\DatabaseConf.properties");
			//System.out.println(System.getProperty("user.dir"));
			System.out.println(f.getAbsolutePath());
			props.load(new FileInputStream(f));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    private static String getProperties(String key) {
        return props.getProperty(key);
    }
	
}
