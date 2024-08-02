package com.haui.JDBCUtil;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionPool {
		// Xin kết nối để làm việc
		public Connection getConnection(String objectName) throws SQLException;

		// Yêu cầu trả về kết nối
		public void releaseConnection(Connection con, String objectName) throws SQLException;
}
