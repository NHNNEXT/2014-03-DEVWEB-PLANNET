
package com.plannet.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.plannet.clientdb.PlanDAO;
import com.plannet.clientdb.SubplanDAO;
import com.plannet.model.Plan;
import com.plannet.model.Subplan;
import com.plannet.others.Utilities;
import com.plannet.pages.PopUpFragment;
import com.plannet.subplan.SubplanAdapter;

public class SubplanActivity extends ActionBarActivity implements PopUpFragment.OnPopUpListener {

	private SubplanAdapter adapter;
	private int pid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_subplan);

		TextView planTitle = (TextView) findViewById(R.id.subplan_plan_title);
		TextView planSummary = (TextView) findViewById(R.id.subplan_plan_summary);

		pid = getIntent().getIntExtra("pid", 0);

		Plan plan = new PlanDAO(this).selectByPid(pid);
		planTitle.setText(plan.getTitle());
		planSummary.setText(plan.getSummary());

		ArrayList<Subplan> subplanList = new SubplanDAO(this).selectByPid(pid); // TODO #3. DEBUG
		adapter = new SubplanAdapter(this, R.layout.subplan_listview_item, subplanList);

		ListView listView = (ListView) findViewById(R.id.subplan_listview);
		listView.setAdapter(adapter);

		// 롱클릭 리스너 추가
		listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
				String title = adapter.getItem(i).getTitle();
				PopUpFragment popUp = new PopUpFragment(i, title);
				popUp.setOnPopUpListener(SubplanActivity.this); // 구현한 팝업 리스너를 넣어줌
				popUp.show(getSupportFragmentManager(), "PlanLongClickPopUp");
				return true;
			}
		});

		// 숏클릭 리스너 추가
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				Utilities.moveToAnotherActivity(SubplanActivity.this, SubplanContentsActivity.class, "subpid", adapter
						.getItem(i).getSubpid());
			}
		});

		Utilities.addPortalToButton(findViewById(R.id.add_subplan_button), this, AddSubplanActivity.class, "pid", pid);
	}

	@Override
	public void onDelete(int listItemId) {
		// delete subplan in DB
		Subplan subplan = adapter.getItem(listItemId);
		new SubplanDAO(this).delete(subplan.getSubpid());

		// delete plan in adapter
		adapter.getSubplanList().remove(listItemId);
		adapter.notifyDataSetChanged();
	}

	@Override
	public void onEdit(int listItemId) {
		// move to edit subplan activity
		Subplan subplan = adapter.getSubplanList().get(listItemId);
		Utilities.moveToAnotherActivity(this, EditSubplanActivity.class, "subpid", subplan.getSubpid(), "title",
				subplan.getTitle(), "percent", subplan.getPercent(), "summary", subplan.getSummary());
	}
}