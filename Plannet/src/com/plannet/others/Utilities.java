package com.plannet.others;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class Utilities {

	public static void addPortalToButton(View Button, Activity currentActivity, Class targetActivity) {
		Button button = (Button) Button;
		PortalOnClickListener listener = new PortalOnClickListener(currentActivity, targetActivity);
		button.setOnClickListener(listener);
	}

	public static void moveToAnotherActivity(Activity currentActivity, Class targetActivity) {
		Intent intent = new Intent(currentActivity, targetActivity);
		currentActivity.startActivity(intent);
	}
}