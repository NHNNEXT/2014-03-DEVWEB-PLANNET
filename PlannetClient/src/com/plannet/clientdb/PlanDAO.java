package com.plannet.clientdb;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.plannet.model.Plan;

public class PlanDAO extends AbstractDAO {

	public PlanDAO(Context context) {
		super(context);
	}

	public long insert(int uid, int cid, String title, String summary, boolean isComplete, boolean isPrivate) {
		db = helper.getWritableDatabase();
		cv = new ContentValues();

		cv.put("uid", uid);
		cv.put("cid", cid);
		cv.put("title", title);
		cv.put("summary", summary);
		cv.put("complete", isComplete);
		cv.put("private", isPrivate);

		return db.insert("plan", null, cv);
	}

	public int update(int pid, int uid, int cid, String title, String summary, boolean isComplete, boolean isPrivate) {
		db = helper.getWritableDatabase();
		cv = new ContentValues();

		cv.put("uid", uid);
		cv.put("cid", cid);
		cv.put("title", title);
		cv.put("summary", summary);
		cv.put("complete", isComplete);
		cv.put("private", isPrivate);

		return db.update("plan", cv, "pid=?", new String[] { Integer.toString(pid) });
	}

	public List<Plan> select() {
		List<Plan> result = new ArrayList<Plan>();
		db = helper.getReadableDatabase();
		Cursor c = db.query("plan", null, null, null, null, null, null);

		while (c.moveToNext()) {
			int pid = c.getInt(c.getColumnIndex("pid"));
			int uid = c.getInt(c.getColumnIndex("uid"));
			int cid = c.getInt(c.getColumnIndex("cid"));
			String title = c.getString(c.getColumnIndex("title"));
			String summary = c.getString(c.getColumnIndex("summary"));
			boolean isComplete = c.getInt(c.getColumnIndex("complete")) > 0;
			boolean isPrivate = c.getInt(c.getColumnIndex("private")) > 0;

			Plan p = new Plan(pid, uid, cid, title, summary, isComplete, isPrivate);
			result.add(p);
		}

		return result;
	}

	public long delete(int pid) {
		db = helper.getWritableDatabase();
		
		return db.delete("plan", "pid=?", new String[] { Integer.toString(pid) });
	}

	// 내장 db 작업 -> method 만들어놓기
	// add, edit, delete, sync(push), retrieve(카테고리 탭했을 때)

}
