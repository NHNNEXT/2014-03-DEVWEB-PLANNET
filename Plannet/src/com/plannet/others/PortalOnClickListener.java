package com.plannet.others;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

public class PortalOnClickListener implements OnClickListener {

	private Activity currentActivity;
	private Class targetActivity;

	public PortalOnClickListener(Activity currentActivity, Class targetActivity) {
		this.currentActivity = currentActivity;
		this.targetActivity = targetActivity;
	}

	@Override
	public void onClick(View v) {
		Utilities.moveToAnotherActivity(currentActivity, targetActivity);
	}
}