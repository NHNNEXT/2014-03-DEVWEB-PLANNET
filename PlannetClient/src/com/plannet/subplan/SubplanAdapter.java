package com.plannet.subplan;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.plannet.activity.R;
import com.plannet.model.Subplan;

public class SubplanAdapter extends ArrayAdapter<Subplan> {

	private Context context;
	private int layoutResourceId;
	private ArrayList<Subplan> subplanList;

	public ArrayList<Subplan> getSubplanList() {
		return subplanList;
	}

	public SubplanAdapter(Context context, int resource, ArrayList<Subplan> objects) {
		super(context, resource, objects);
		this.context = context;
		this.layoutResourceId = resource;
		this.subplanList = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;

		if (row == null) {
			LayoutInflater inflator = ((Activity) context).getLayoutInflater();
			row = inflator.inflate(layoutResourceId, parent, false);
		}

		TextView title = (TextView) row.findViewById(R.id.subplan_title);
		title.setText(subplanList.get(position).getTitle());

		return row;
	}
}