package com.plannet.clientdb;

public class SQLStatements {
	public final static String createPersonalTable = "CREATE TABLE IF NOT EXISTS personal("
			+ "uuid TEXT"
			+ ");";
	
	public final static String createUserTable = "CREATE TABLE IF NOT EXISTS user("
			+ "uid INTEGER PRIMARY KEY NOT NULL,"
			+ "name TEXT"
			+ ");";

	public final static String createCategoryTable = "CREATE TABLE IF NOT EXISTS category("
			+ "cid INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
			+ "uid INTEGER," // NOT NULL이어야 하지 않을까, 다른 테이블 외래키도 다
			+ "name TEXT NOT NULL,"
			+ "FOREIGN KEY(uid) REFERENCES user(uid)"
			+ ");";

	public final static String createPlanTable = "CREATE TABLE IF NOT EXISTS plan("
			+ "pid INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
			+ "uid INTEGER,"
			+ "cid INTEGER,"
			+ "title TEXT NOT NULL,"
			+ "summary TEXT,"
			+ "complete NUMERIC,"
			+ "private NUMERIC,"
			+ "FOREIGN KEY(uid) REFERENCES user(uid),"
			+ "FOREIGN KEY(cid) REFERENCES category(cid)"
			+ ");";
	
	public final static String createSubplanTable = "CREATE TABLE IF NOT EXISTS subplan("
			+ "subpid INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
			+ "pid INTEGER,"
			+ "title TEXT NOT NULL,"
			+ "summary TEXT,"
			+ "percent INTEGER,"
			+ "complete NUMERIC,"
			+ "FOREIGN KEY(pid) REFERENCES plan(pid)"
			+ ");";
}