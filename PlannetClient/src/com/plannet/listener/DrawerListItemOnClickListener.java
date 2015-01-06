package com.plannet.listener;

import com.plannet.activity.MyPlanActivity;
import com.plannet.activity.SignInActivity;
import com.plannet.http.HttpRequest;
import com.plannet.others.PortalOnClickListener;
import com.plannet.others.Utilities;

import android.app.Activity;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class DrawerListItemOnClickListener implements ListView.OnItemClickListener {
	private static final int My_Plan = 0;
	private static final int Following = 1;
	private static final int Find = 2;
	private static final int Logout = 3;
	Activity currentActivity;
	private DrawerLayout drawerLayout;

	public DrawerListItemOnClickListener(DrawerLayout drawerLayout, Activity currentActivity) {
		this.drawerLayout = drawerLayout;
		this.currentActivity = currentActivity;
	}

	@Override
	public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {

		switch (position) {
		case My_Plan:
			break;
		case Following:
			break;
		case Find:
			break;
		case Logout:
			new Thread() {
				public void run() {
					HttpRequest.Logout();
				};
			}.start();
			Utilities.moveToAnotherActivity(currentActivity, SignInActivity.class, null);
			break;
		}
		drawerLayout.closeDrawers();
	}
}