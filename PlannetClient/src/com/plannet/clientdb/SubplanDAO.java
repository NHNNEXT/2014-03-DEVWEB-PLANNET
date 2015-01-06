package com.plannet.clientdb;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.plannet.model.Subplan;

public class SubplanDAO extends AbstractDAO {

	public SubplanDAO(Context context) {
		super(context);
	}

	public long insert(int pid, String title, String summary, int percent) {
		db = helper.getWritableDatabase();
		cv = new ContentValues();

		cv.put("pid", pid);
		cv.put("title", title);
		cv.put("summary", summary);
		cv.put("percent", percent);

		return db.insert("subplan", null, cv);
	}

	public int update(int targetSubpid, int pid, String title, String summary, int percent) {
		db = helper.getWritableDatabase();
		cv = new ContentValues();

		cv.put("pid", pid);
		cv.put("title", title);
		cv.put("summary", summary);
		cv.put("percent", percent);

		return db.update("subplan", cv, "subpid=?", new String[] { Integer.toString(targetSubpid) });
	}

	public int update(int targetSubpid, int newSubpid) {
		db = helper.getWritableDatabase();
		cv = new ContentValues();
		cv.put("subpid", newSubpid);
		return db.update("subplan", cv, "subpid=?", new String[] { Integer.toString(targetSubpid) });
	}

	public ArrayList<Subplan> select() {
		ArrayList<Subplan> result = new ArrayList<Subplan>();
		db = helper.getReadableDatabase();
		Cursor c = db.query("subplan", null, null, null, null, null, null);

		while (c.moveToNext()) {
			int subpid = c.getInt(c.getColumnIndex("subpid"));
			int pid = c.getInt(c.getColumnIndex("pid"));
			String title = c.getString(c.getColumnIndex("title"));
			String summary = c.getString(c.getColumnIndex("summary"));
			int percent = c.getInt(c.getColumnIndex("percent"));
			boolean isComplete = c.getInt(c.getColumnIndex("complete")) > 0;

			Subplan sp = new Subplan(subpid, pid, title, summary, percent, isComplete);
			result.add(sp);
		}

		return result;
	}

	public ArrayList<Subplan> selectByPid(int targetPid) {
		ArrayList<Subplan> result = new ArrayList<Subplan>();
		db = helper.getReadableDatabase();
		Cursor c = db.query("subplan", null, "pid=?", new String[] { Integer.toString(targetPid) }, null, null, null);

		while (c.moveToNext()) {
			int subpid = c.getInt(c.getColumnIndex("subpid"));
			int pid = c.getInt(c.getColumnIndex("pid"));
			String title = c.getString(c.getColumnIndex("title"));
			String summary = c.getString(c.getColumnIndex("summary"));
			int percent = c.getInt(c.getColumnIndex("percent"));
			boolean isComplete = c.getInt(c.getColumnIndex("complete")) > 0;

			Subplan sp = new Subplan(subpid, pid, title, summary, percent, isComplete);
			result.add(sp);
		}

		return result;
	}

	public Subplan selectBySubpid(int targetSubpid) {
		db = helper.getReadableDatabase();
		Cursor c = db.query("subplan", null, "subpid=?", new String[] { Integer.toString(targetSubpid) }, null, null,
				null);

		c.moveToNext();
		int subpid = c.getInt(c.getColumnIndex("subpid"));
		int pid = c.getInt(c.getColumnIndex("pid"));
		String title = c.getString(c.getColumnIndex("title"));
		String summary = c.getString(c.getColumnIndex("summary"));
		int percent = c.getInt(c.getColumnIndex("percent"));
		boolean isComplete = c.getInt(c.getColumnIndex("complete")) > 0;

		Subplan result = new Subplan(subpid, pid, title, summary, percent, isComplete);
		return result;
	}

	public long delete(int subpid) {
		db = helper.getWritableDatabase();
		return db.delete("subplan", "subpid=?", new String[] { Integer.toString(subpid) });
	}
}
