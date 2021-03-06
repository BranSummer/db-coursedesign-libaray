package org.bran.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * 
 *<p>Title: DBOperation.java</p>
 *<p>Description:封装关于jdbc的一些操作 </p>
 * @author BranSummer
 * @date 2017年5月6日
 */
public class DBOperation {
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	/**
	 * constructor
	 */
	public DBOperation(){
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=library";
			String user="sa";
			String password="202150818";
			connection=DriverManager.getConnection(url,user,password);
		}catch(ClassNotFoundException e){
			System.out.println("数据库加载出错");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("数据库连接出错");
			e.printStackTrace();
		}
	}
	/**
	 * 
	 *<p>Description:更新操作 </p>
	 * @param sql
	 * @return 受影响的行数
	 * @throws SQLException
	 */
	public int executeUpdate(String sql) throws SQLException{
		statement=connection.createStatement();
		return statement.executeUpdate(sql);
	}
	/**
	 * 
	 *<p>Description: 查询操作</p>
	 * @param sql
	 * @return 查询的结果集
	 * @throws SQLException
	 */
	public ResultSet executeQuery(String sql) throws SQLException{
		statement=connection.createStatement();
		return statement.executeQuery(sql);
	}
	
	/**
	 * 
	 *<p>Description: </p>
	 * @param sql
	 * @return 预编译处理对象
	 * @throws SQLException
	 */
	public PreparedStatement getPreparedStatement(String sql) throws SQLException{
		return connection.prepareStatement(sql);
	}
	/**
	 * 
	 *<p>Description:用来执行存储过程 </p>
	 * @param sql
	 * @return CallableStatement对象
	 * @throws SQLException
	 */
	public CallableStatement getCallableStatement(String sql) throws SQLException{
		return connection.prepareCall(sql);
	}
	/**
	 * 
	 *<p>Description:关闭数据库连接 </p>
	 * @throws SQLException
	 */
	public void close() throws SQLException{
		if(resultSet!=null){
			resultSet.close();
		}
		if(statement!=null){
			statement.close();
		}
		connection.close();
	}
}
