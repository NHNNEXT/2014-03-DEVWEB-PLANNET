package net.plannet.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import net.plannet.dbutil.ConnectionFactory;
import net.plannet.dbutil.QuerySet;
import net.plannet.util.DaoUtil;
import net.plannet.util.ErrorUtil;

public abstract class DAO {
	private Connection conn;
	private PreparedStatement pstmt;
	protected ResultSet rs;

	protected DAO() {
		conn = ConnectionFactory.getConnection();
	}

	public <T> ArrayList<T> selectQueryExecute(QuerySet querySet,
			Class<T> objClass) {
		ArrayList<T> result = new ArrayList<T>();
		try {
			pstmt = querySet.getPstmt(conn);
			rs = pstmt.executeQuery();
			result = new DaoUtil().convertToObject(querySet, rs, objClass);
		} catch (Exception e) {
			ErrorUtil.printError("Select SQL Execution Failed", e);
		}
		return result;
	}

	protected void nonSelectQueryExecute(QuerySet querySet) {
		try {
			pstmt = querySet.getPstmt(conn);
			pstmt.executeUpdate();
		} catch (Exception e) {
			ErrorUtil.printError("NonSelect SQL Execution Failed", e);
		}
	}

	protected void closeResource() {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			ErrorUtil.printError("Closing Resource Execution Failed", e);
		}
	}
}
