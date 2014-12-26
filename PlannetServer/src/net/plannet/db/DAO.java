package net.plannet.db;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
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

	public <T> ArrayList<T> selectQueryExecute(QuerySet querySet,
			Class<T> objClass) {
		try {
			pstmt = querySet.preparePstmt(conn);
			queryRs = pstmt.executeQuery();
			
			Method[] methods;
			ArrayList<String> columnNames = querySet.getColumnNames(objClass);
			ArrayList<String> setterNames = querySet.getSetterNames(columnNames);
			ArrayList<T> result = new ArrayList<T>();
			while (queryRs.next()) {
				Constructor<T> constructor = objClass.getConstructor();
				int idx = 0;
				T instance = constructor.newInstance();
				for(String setterName : setterNames) {
					String columnName = columnNames.get(idx++);
					Field field = objClass.getDeclaredField(columnName);
					Class paramType = field.getType();
					Object args = queryRs.getObject(columnName);
					Method setter = objClass.getDeclaredMethod(setterName, paramType);
					setter.invoke(instance, args);
				}
				result.add(instance);
			}
			return result;
		} catch (Exception e) {
			System.out.println("[Select SQL Execution Failed] : "
					+ e.getMessage());
			return null;
		}
	}
	
	private void convertRecordToObject() {
		
	}

	protected void nonSelectQueryExecute(QuerySet querySet) {
		try {
			pstmt = querySet.preparePstmt(conn);
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
			System.out.println("[Closing Resource Execution Failed] : "
					+ e.getMessage());
		}
	}
}
