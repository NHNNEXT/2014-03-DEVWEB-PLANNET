package net.plannet.db;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import net.plannet.dbutil.ConnectionFactory;
import net.plannet.dbutil.QuerySet;

public abstract class DAO {
	private Connection conn;
	private PreparedStatement pstmt;
	protected ResultSet queryRs;

	protected DAO() {
		conn = ConnectionFactory.getConnection();
	}

	protected <T> ArrayList<T> selectQueryExecute(QuerySet querySet,
			Class<T> objClass) {
		try {
			pstmt = conn.prepareStatement(querySet.getSql());
			setParameter(querySet);
			queryRs = pstmt.executeQuery();

			ArrayList<T> result = new ArrayList<T>();
			Field[] fields = objClass.getDeclaredFields();
			Class[] fieldsClass = new Class[fields.length];
			
			for (int i = 0; i < fieldsClass.length; i++)
				fieldsClass[i] = fields[i].getType();
			Constructor<T> constructor = objClass
					.getDeclaredConstructor(fieldsClass);

			while (queryRs.next()) {
				ArrayList<Object> args = new ArrayList<Object>();
				
				for (Field member : fields)
					args.add(queryRs.getObject(member.getName()));
				
				T item = constructor.newInstance(args.toArray());
				result.add(item);
			}

			return result;
		} catch (Exception e) {
			System.out.println("[Select SQL Execution Failed] : "
					+ e.getMessage());
			return null;
		}
	}

	protected void nonSelectQueryExecute(QuerySet querySet) {
		try {
			pstmt = conn.prepareStatement(querySet.getSql());
			setParameter(querySet);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("[NonSelect SQL Execution Failed] : "
					+ e.getMessage());
		}
	}

	protected void closeResource() {
		try {
			if (queryRs != null) {
				queryRs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			System.out.println("[Close SQL Execution Failed] : "
					+ e.getMessage());
		}
	}

	private void setParameter(QuerySet querySet) {
		try {
			for (int idx = 0; idx < querySet.getValueCount(); idx++) {
				pstmt.setObject((idx+1), querySet.getValue(idx));
			}
		} catch (Exception e) {
			System.out.println("[SQL Creation Failed] : " + e.getMessage());
		}
	}
}
