package com.ultrapower.policyweb.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 数据库连接工具类
 * @author llh
 *
 */
public class DBUtil {  
	  
    private Connection conn = null;  
    private Statement stmt = null;  
    private ResultSet rs = null;  
    /** 数据库驱动*/  
    private String DB_DRIVER = "org.postgresql.Driver";
  
    /** 数据库 URL */  
    private String DB_URL = "jdbc:postgresql://127.0.0.1:5432/postgres";
  
    /** 用户名 */  
    private String DB_USERNAME = "postgres";
  
    /** 密码 */  
    private String DB_PASSWORD = "123";
  
    public DBUtil(){
    	
    }
    
    public DBUtil( String driver, String url, String userName, String password){
    	this.DB_DRIVER = driver;
    	this.DB_URL = url;
    	this.DB_USERNAME = userName;
    	this.DB_PASSWORD = password;
    }
    /** 
     * 获取连接
     *  
     * @return 
     */  
    public Connection getConnection() {  
        /** 初始化connection */  
        Connection conn = null;  
        try {  
            Class.forName(DB_DRIVER);  
            conn = DriverManager  
                    .getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            stmt = conn.createStatement();
        } catch (Exception ex) {  
            ex.printStackTrace();  
        }  
        return conn;  
    } 
    
    /** 
     * 执行sql语句
     *  
     * @return ResultSet 
     */  
    public ResultSet executeQuery(String sqlStr) {  
        if (sqlStr == null || sqlStr.length() == 0)  
            return null;  
        try {  
            this.getConnection();  
            rs = stmt.executeQuery(sqlStr);  
            return rs;  
        } catch (SQLException ex) {  
            ex.printStackTrace();  
            return null;  
        }  
  
    }  
  
    /** 
     * 执行更新
     *  
     * @return 更新结果 
     */  
    public boolean executeUpdate(String sqlStr) {  
  
        if (sqlStr == null || sqlStr.length() == 0)  
            return false;  
        try {  
            this.getConnection();  
            stmt.executeUpdate(sqlStr);
            return true;  
        } catch (SQLException ex) {  
            ex.printStackTrace();  
            return false;  
        } finally {  
            try {  
                if (stmt != null) {  
                    stmt.close();  
                }  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
            try {  
                if (conn != null) {  
                    conn.close();  
                }  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
  
        }  
  
    }  
    /** 
     * 执行更新
     *  
     * @return 返回影响行数 
     */  
    public int executeUpdateCount(String sqlStr) {  
    	
    	if (sqlStr == null || sqlStr.length() == 0)  
    		return 0;  
    	try {  
    		this.getConnection();  
    		return stmt.executeUpdate(sqlStr);
    	} catch (SQLException ex) {  
    		ex.printStackTrace();  
    		return 0;
    	} finally {  
    		try {  
    			if (stmt != null) {  
    				stmt.close();  
    			}  
    		} catch (SQLException e) {  
    			e.printStackTrace();  
    		}  
    		try {  
    			if (conn != null) {  
    				conn.close();  
    			}  
    		} catch (SQLException e) {  
    			e.printStackTrace();  
    		}  
    		
    	}  
    	
    }  
  
    public void closeStmt() {  
        try {  
            if (stmt != null) {  
                stmt.close();  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    /** 
     * 关闭连接
     *  
     * @param connect 
     */  
    public void closeConnection() {  
        try {  
            if (conn != null) {  
                if (!conn.isClosed()) {  
                    conn.close();  
                }  
            }  
        } catch (Exception ex) {  
            ex.printStackTrace();  
        }  
    }
}