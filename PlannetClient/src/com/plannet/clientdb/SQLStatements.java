package com.plannet.clientdb;

import java.sql.PreparedStatement;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

public class SQLStatements {
	public final static String createUserTable = "CREATE TABLE IF NOT EXISTS user("
			+ "uid INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
			+ "email TEXT NOT NULL,"
			+ "pw TEXT NOT NULL,"
			+ "name TEXT"
			+ ");";

	public final static String createCategoryTable = "CREATE TABLE IF NOT EXISTS category("
			+ "cid INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
			+ "uid INTEGER,"
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
			+ "FOREIGN KEY(uid) REFERENCES user(uid)"
			+ "FOREIGN KEY(cid) REFERENCES category(cid)"
			+ ");";
	
//	public static PreparedStatement insertUser(SQLiteDatabase db){
//		SQLiteStatement stmt = db.compileStatement("INSERT INTO user VALUES(,?,?,?)");
//	}
}