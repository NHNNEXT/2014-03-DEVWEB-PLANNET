package com.plannet.clientdb;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.plannet.model.Category;

public class CategoryDAO extends AbstractDAO {
	public CategoryDAO(Context context) {
		super(context);
	}

	public long insert(int cid, int uid, String name) {
		db = helper.getWritableDatabase();
		cv = new ContentValues();

		cv.put("cid", cid);
		cv.put("uid", uid);
		cv.put("name", name);

		return db.insert("category", null, cv);
	}

	public long insert(int uid, String name) {
		db = helper.getWritableDatabase();
		cv = new ContentValues();

		cv.put("uid", uid);
		cv.put("name", name);

		return db.insert("category", null, cv);
	}

	public int update(int targetCid, int uid, String name) {
		db = helper.getWritableDatabase();
		cv = new ContentValues();

		cv.put("uid", uid);
		cv.put("name", name);

		return db.update("category", cv, "cid=?", new String[] { Integer.toString(targetCid) });
	}

	public int update(int targetCid, String name) {
		db = helper.getWritableDatabase();

		cv = new ContentValues();
		cv.put("name", name);

		return db.update("category", cv, "cid=?", new String[] { Integer.toString(targetCid) });
	}

	public ArrayList<Category> select() {
		ArrayList<Category> result = new ArrayList<Category>();
		db = helper.getReadableDatabase();
		Cursor c = db.query("category", null, null, null, null, null, null);

		while (c.moveToNext()) {
			int cid = c.getInt(c.getColumnIndex("cid"));
			int uid = c.getInt(c.getColumnIndex("uid"));
			String name = c.getString(c.getColumnIndex("name"));

			Category cg = new Category(cid, uid, name);
			result.add(cg);
		}

		return result;
	}

	public long delete(int cid) {
		db = helper.getWritableDatabase();
		return db.delete("category", "cid=?", new String[] { Integer.toString(cid) });
	}
}
