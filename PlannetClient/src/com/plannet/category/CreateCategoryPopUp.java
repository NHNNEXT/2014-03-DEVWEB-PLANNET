package com.plannet.category;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.EditText;

import com.plannet.clientdb.CategoryDAO;
import com.plannet.others.GlobalVariables;
import com.plannet.pages.PagerAdapter;

public class CreateCategoryPopUp extends DialogFragment {

	@Override
	@NonNull
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("카테고리 생성");
		final EditText input = new EditText(getActivity());
		builder.setView(input);

		builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int whichButton) {
				String name = input.getText().toString();
				int rowId = (int) new CategoryDAO(getActivity()).insert(1, name); // TODO #1. uid 사용부분
				PagerAdapter pagerAdapter = GlobalVariables.getPagerAdapter();
				pagerAdapter.addTab(getActivity().getActionBar().newTab().setText(name), rowId);
			}
		});

		builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int whichButton) {
			}
		});

		return builder.create();
	}
}