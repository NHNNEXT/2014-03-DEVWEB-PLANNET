package com.plannet.clientdb;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.plannet.model.Plan;

public class PlanDAO extends AbstractDAO {

	// private String[] columns = { "pid", "uid", "cid", "title", "summary", "complete", "private" };
	// db.query 두번째 인자로 넣어서 특정 컬럼만 가져올 수 있음
	// db.query 마지막 인자로 "pid DESC" 넣어주면 pid 내림차 순으로 정렬된 결과 나옴
	// if(c.getCount() > 0) // 레코드가 있는지 없는지 검사!!!
	// 쿼리한 다음 처음엔 항상 next로 가야함

	public PlanDAO(Context context) {
		super(context);
	}

	public long insert(int pid, int uid, int cid, String title, String summary, boolean isComplete, boolean isPrivate) {
		db = helper.getWritableDatabase();
		cv = new ContentValues();

		cv.put("pid", pid);
		cv.put("uid", uid);
		cv.put("cid", cid);
		cv.put("title", title);
		cv.put("summary", summary);
		cv.put("complete", isComplete);
		cv.put("private", isPrivate);

		return db.insert("plan", null, cv);
	}

	public long insert(int cid, String title, String summary) {
		db = helper.getWritableDatabase();
		cv = new ContentValues();

		cv.put("cid", cid);
		cv.put("title", title);
		cv.put("summary", summary);

		return db.insert("plan", null, cv);
	}

	public int update(int targetPid, int uid, int cid, String title, String summary, boolean isComplete,
			boolean isPrivate) {
		db = helper.getWritableDatabase();
		cv = new ContentValues();

		cv.put("uid", uid);
		cv.put("cid", cid);
		cv.put("title", title);
		cv.put("summary", summary);
		cv.put("complete", isComplete);
		cv.put("private", isPrivate);

		return db.update("plan", cv, "pid=?", new String[] { Integer.toString(targetPid) });
	}

	public int update(int targetPid, String title, String summary) {
		db = helper.getWritableDatabase();
		cv = new ContentValues();

		cv.put("title", title);
		cv.put("summary", summary);

		return db.update("plan", cv, "pid=?", new String[] { Integer.toString(targetPid) });
	}

	public int update(int targetPid, int newPid) {
		db = helper.getWritableDatabase();
		cv = new ContentValues();
		cv.put("pid", newPid);
		return db.update("plan", cv, "pid=?", new String[] { Integer.toString(targetPid) });
	}

	public ArrayList<Plan> select() {
		ArrayList<Plan> result = new ArrayList<Plan>();
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

	public Plan selectByPid(int targetPid) {
		db = helper.getReadableDatabase();
		Cursor c = db.query("plan", null, "pid=?", new String[] { Integer.toString(targetPid) }, null, null, null);

		c.moveToNext();
		int pid = c.getInt(c.getColumnIndex("pid"));
		int uid = c.getInt(c.getColumnIndex("uid"));
		int cid = c.getInt(c.getColumnIndex("cid"));
		String title = c.getString(c.getColumnIndex("title"));
		String summary = c.getString(c.getColumnIndex("summary"));
		boolean isComplete = c.getInt(c.getColumnIndex("complete")) > 0;
		boolean isPrivate = c.getInt(c.getColumnIndex("private")) > 0;

		Plan result = new Plan(pid, uid, cid, title, summary, isComplete, isPrivate);
		return result;
	}

	public ArrayList<Plan> selectByCid(int targetCid) {
		ArrayList<Plan> result = new ArrayList<Plan>();
		db = helper.getReadableDatabase();
		Cursor c = db.query("plan", null, "cid=?", new String[] { Integer.toString(targetCid) }, null, null, null);

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
}