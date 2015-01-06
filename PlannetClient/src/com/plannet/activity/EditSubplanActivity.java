package com.plannet.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.plannet.clientdb.SubplanDAO;
import com.plannet.http.HttpRequest;
import com.plannet.others.Utilities;

public class EditSubplanActivity extends Activity implements OnClickListener {
	private EditText titleEdit;
	private EditText summaryEdit;
	private EditText percentEdit;
	private Button button;

	private String[] response;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_subplan);

		titleEdit = (EditText) findViewById(R.id.add_subplan_title_edit);
		summaryEdit = (EditText) findViewById(R.id.add_subplan_summary_edit);
		percentEdit = (EditText) findViewById(R.id.add_subplan_percent_edit);

		titleEdit.setText(getIntent().getStringExtra("title"));
		summaryEdit.setText(getIntent().getStringExtra("summary"));
		percentEdit.setText(""+getIntent().getIntExtra("percent", 0)); // 이 부분 조심! int는 꼭 String으로 바꿔서 setText에 넣어줘야 된다

		button = (Button) findViewById(R.id.add_subplan_ok_button);
		button.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		final int pid = getIntent().getIntExtra("subpid", 0);
		final String title = titleEdit.getText().toString();
		final String summary = summaryEdit.getText().toString();
		final int percent = Integer.parseInt(percentEdit.getText().toString());

		Log.e("add subplan : ", pid + "   " + title + "   " + summary + "   " + percent);

		if (title.isEmpty()) {
			Utilities.toastPopUp(this, "제목을 입력해주세요!");
			return;
		}

		int oldSubpid = (int) new SubplanDAO(this).insert(pid, title, summary, percent);

		// Thread thread = new Thread() {
		// public void run() {
		// response = HttpRequest.PushSubplan(pid, title, summary, percent); // result 받아와서 처리해줘야 한다 // PushSubplan
		// }
		// };
		// thread.start();
		//
		// try {
		// thread.join();
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		//
		// // String result = response[0]; // 응답값에 따라 처리해주기
		// int newSubpid = Integer.parseInt(response[1]);
		// new SubplanDAO(this).update(oldSubpid, newSubpid);

		Utilities.moveToAnotherActivity(this, SubplanActivity.class, "pid", pid);
	}
}