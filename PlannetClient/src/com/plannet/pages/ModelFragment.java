package com.plannet.pages;

import java.util.ArrayList;
import java.util.Collections;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.plannet.activity.R;
import com.plannet.clientdb.PlanDAO;
import com.plannet.model.Plan;

public class ModelFragment extends Fragment {
	private PlanAdapter adapter;
	private ArrayList<Plan> planList;
	private int categoryId;

	public int getCategoryId() {
		return categoryId;
	}

	public ModelFragment(int categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.model_page_fragment, container, false);
	}

	@Override
	public void onStart() {
		super.onStart();
		planList = new PlanDAO(getActivity()).selectByCategory(categoryId);
		Collections.reverse(planList);
		ListView listView = (ListView) getView().findViewById(R.id.plan_listview);
		adapter = new PlanAdapter(getActivity(), R.layout.plan_listview_item, planList);
		listView.setAdapter(adapter);
	}
}