package com.plannet.activity;

import com.plannet.others.Utilities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends Activity implements OnClickListener{
	EditText titleEdit;
	EditText summaryEdit;
	Button button;
	Intent intent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_plan);
		intent = new Intent();
		titleEdit = (EditText)findViewById(R.id.add_title_edit);
		summaryEdit = (EditText)findViewById(R.id.add_summary_edit);
		button = (Button)findViewById(R.id.add_commit_button);
		button.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.add_commit_button:
			String title = titleEdit.getText().toString();
			String summmary = summaryEdit.getText().toString();
			//server에 전송
			intent.putExtra("title", title);
			setResult(RESULT_OK, intent);
			finish();
			break;
		default:
			break;
		}
	}
}
