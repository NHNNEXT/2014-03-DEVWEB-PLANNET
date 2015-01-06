package com.plannet.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import com.plannet.clientdb.SubplanDAO;
import com.plannet.model.Subplan;

public class SubplanContentsActivity extends ActionBarActivity {

	private int subpid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_subplan_contents);

		TextView planTitle = (TextView) findViewById(R.id.subplan_subplan_title);
		TextView planSummary = (TextView) findViewById(R.id.subplan_subplan_summary);

		subpid = getIntent().getIntExtra("subpid", 0);

		Subplan subplan = new SubplanDAO(this).selectBySubpid(subpid);
		planTitle.setText(subplan.getTitle());
		planSummary.setText(subplan.getSummary());
	}
}