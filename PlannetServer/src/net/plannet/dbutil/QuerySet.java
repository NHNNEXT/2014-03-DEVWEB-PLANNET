package net.plannet.dbutil;

import java.util.List;

public class QuerySet {
	private String sql;
	private List<Object> queryParams;
	private int paramsCount;
	
	public QuerySet(String sql, List<Object> queryParams) {
		this.sql = sql;
		
		if(queryParams == null)
			return;
		
		this.queryParams = queryParams;
		paramsCount = queryParams.size();
	}

	public String getSql() {
		return sql;
	}

	public Object getValue(int position) {
		return queryParams.get(position);
	}

	public int getValueCount() {
		return paramsCount;
	}
	
	
}
