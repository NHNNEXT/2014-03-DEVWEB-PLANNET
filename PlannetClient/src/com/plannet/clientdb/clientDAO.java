package com.plannet.clientdb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class clientDAO {
	Context context;
	SQLiteDatabase database;

	public clientDAO(Context context) {
		this.context = context;
		database = context.openOrCreateDatabase("plannet_client", SQLiteDatabase.CREATE_IF_NECESSARY, null);
	}
	// databases close 해주기
}