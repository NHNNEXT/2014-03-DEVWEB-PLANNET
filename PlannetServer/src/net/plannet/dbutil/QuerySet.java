package net.plannet.dbutil;

import java.io.File;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuerySet {
	private String sql;
	private ArrayList<Object> queryParams = new ArrayList<Object>();

	public QuerySet(String sql) {
		this.sql = sql;
	}

	public QuerySet(String sql, Object... queryParams) {
		this(sql);
		this.queryParams = (ArrayList<Object>) Arrays.asList(queryParams);
	}

	public PreparedStatement preparePstmt(Connection conn) {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			for (Object params : queryParams)
				pstmt.setObject(1, params);
		} catch (Exception e) {
			System.out.println("[PreparedStatement Execution Failed] : "
					+ e.getMessage());
		}
		return pstmt;
	}
	
	public <T> ArrayList<String> getColumnNames(Class<T> objClass) {
		objClass.getDeclaredFields();
		sql = sql.trim();
		int end = sql.indexOf("from");
		String section = sql.substring(6, end);
		String[] fragment = section.split(",");
		ArrayList<String> columns = new ArrayList<String>();
		for(String column : fragment) {
			column = column.trim();
			if(!column.equals("")) 
				columns.add(column);
		}
		if(columns.get(0).equals("*")) {
			columns.remove(0);
			Field[] fields = objClass.getDeclaredFields();
			for(Field field : fields)
				columns.add(field.getName());
		}
		return columns;
	}
	
	public ArrayList<String> getSetterNames(ArrayList<String> columns) {
		ArrayList<String> setters = new ArrayList<String>();
		for(String column : columns) {
			char firstChar = Character.toUpperCase(column.charAt(0));
			String setterName = "set" + firstChar + column.substring(1);
			setters.add(setterName);
		}
		return setters;
	}
}
