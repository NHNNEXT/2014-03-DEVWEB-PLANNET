package com.plannet.pages;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.plannet.activity.R;
import com.plannet.model.Plan;

public class PageFragment1 extends Fragment {
	private PlanAdapter adapter;
	private ArrayList<Plan> planList = new ArrayList<Plan>();
	
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.pager_page_1, container, false);
	}
	
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		ListView listView = (ListView)getView().findViewById(R.id.plan_list);
		adapter = new PlanAdapter(getActivity(), R.layout.plan_node, planList);
		listView.setAdapter(adapter);
		adapter.add(new Plan("Test Plan 7 "));
	}
}