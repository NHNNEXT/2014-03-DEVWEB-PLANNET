package net.plannet.dbutil;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Arrays;

public class QuerySet {
	private String sql;
	private ArrayList<Object> queryParams = new ArrayList<Object>();

	public QuerySet(String sql) {
		this.sql = sql;
	}

	public QuerySet(String sql, Object... queryParams) {
		this(sql);
		this.queryParams = new ArrayList<Object>(Arrays.asList(queryParams));
	}
	
	//QuerySet 멤버변수를 이용하여 conn으로부터 초기화 된 PreparedStatement를 얻어온다.
	public PreparedStatement getPstmt(Connection conn) {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			int i = 1;
			for (Object params : queryParams){
				pstmt.setObject(i++, params);
			}
		} catch (Exception e) {
			System.out.println("[PreparedStatement Execution Failed] : "
					+ e.getMessage());
		}
		return pstmt;
	}

	//sql select문으로부터 얻고자하는 멤버 이름들을 추출한다. 즉 select와 from사이의 문자열들을 추출함.
	public <T> ArrayList<String> getMemberNames(Class<T> objClass) {
		objClass.getDeclaredFields();
		sql = sql.trim();
		int end = sql.indexOf("from");
		if(end == -1)
			end = sql.indexOf("FROM");
		String section = sql.substring(6, end);
		String[] fragment = section.split(",");
		ArrayList<String> columns = new ArrayList<String>();
		for (String column : fragment) {
			column = column.trim();
			if (!column.equals(""))
				columns.add(column);
		}
		if (columns.get(0).equals("*")) {
			columns.remove(0);
			Field[] fields = objClass.getDeclaredFields();
			for (Field field : fields)
				columns.add(field.getName());
		}
		return columns;
	}

	//setter함수명을 가진 리스트를 반환한다. 멤버명이 uid이면 setUid으로 스트링을 만든다.
	public ArrayList<String> getSetterNames(ArrayList<String> members) {
		ArrayList<String> setters = new ArrayList<String>();
		for (String column : members) {
			char firstChar = Character.toUpperCase(column.charAt(0));
			String setterName = "set" + firstChar + column.substring(1);
			setters.add(setterName);
		}
		return setters;
	}
}
