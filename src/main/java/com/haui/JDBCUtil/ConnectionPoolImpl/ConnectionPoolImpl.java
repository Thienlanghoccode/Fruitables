package com.haui.JDBCUtil.ConnectionPoolImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Stack;

import com.haui.JDBCUtil.ConnectionPool;

public class ConnectionPoolImpl implements ConnectionPool {
	// Trình điều khiển làm việc với SQL Server
	private String driver;

	// Đường dẫn thực thi
	private String url;

	// Tài khoản làm việc
	private String username;
	private String userpass;

	// Đối tượng lưu trữ kết nối
	private Stack<Connection> pool;

	public ConnectionPoolImpl() {
		// Xác định trình điều khiển
		this.driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

		// Nạp trình điều khiển - Đăng ký Driver Manager
		try {
			Class.forName(this.driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Xác định đường dẫn
		this.url = "jdbc:sqlserver://LAPTOP-7FA5MVMC\\SQLEXPRESS:1433;databaseName=Fruitables"
				+ ";encrypt=true;trustServerCertificate=true;";

		// Xác định tài khoản
		this.username = "sa";
		this.userpass = "123456";

		// Xác định bộ nhớ
		this.pool = new Stack<>();
	}

	@Override
	public Connection getConnection(String objectName) throws SQLException {
		// TODO Auto-generated method stub
		if (this.pool.isEmpty()) {
			System.out.println(objectName + " have created a new Connection");
			return DriverManager.getConnection(this.url, this.username, this.userpass);
		} else {
			System.out.println(objectName + " have pooped the Connection");
			return this.pool.pop();
		}
	}

	@Override
	public void releaseConnection(Connection con, String objectName) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println(objectName + " have pushed the Connection");
		this.pool.push(con);
	}

	@Override
	protected void finalize() throws Throwable {
		// loại bỏ các kết nối trong pool
		this.pool.clear();
		this.pool = null;
		System.out.println("ConnectionPool is closed");
	}

}
