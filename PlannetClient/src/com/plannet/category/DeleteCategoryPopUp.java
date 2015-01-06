package com.plannet.category;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import com.plannet.activity.MyPlanActivity;
import com.plannet.clientdb.CategoryDAO;
import com.plannet.model.Category;
import com.plannet.others.Utilities;

public class DeleteCategoryPopUp extends DialogFragment {

	@Override
	@NonNull
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("카테고리 삭제");

		final ArrayList<Category> categoryList = new CategoryDAO(getActivity().getApplicationContext()).select();
		final String[] categoryStringList = new String[categoryList.size()];

		for (int i = 0; i < categoryList.size(); i++) {
			categoryStringList[i] = categoryList.get(i).getName();
		}

		builder.setItems(categoryStringList, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {
				Activity currentActivity = DeleteCategoryPopUp.this.getActivity();
				new CategoryDAO(currentActivity).delete(categoryList.get(id).getCid());
				ActionBar actionBar = currentActivity.getActionBar();
				actionBar.removeTab(actionBar.getTabAt(id));

				Utilities.moveToAnotherActivity(currentActivity, MyPlanActivity.class);
			}
		});

		return builder.create();
	}

}