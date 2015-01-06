package com.plannet.category;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.EditText;

import com.plannet.clientdb.CategoryDAO;

public class EditTextCategoryPopUp extends DialogFragment {

	@Override
	@NonNull
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		String name = getArguments().getString("name");
		final int cid = getArguments().getInt("cid");

		final EditText input = new EditText(getActivity());
		input.setText(name);
		builder.setView(input);

		builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				String newName = input.getText().toString();
				new CategoryDAO(getActivity()).update(cid, newName);
				getActivity().getActionBar().getTabAt(getArguments().getInt("tabPosition")).setText(newName);
			}
		});

		builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				return;
			}
		});

		return builder.create();
	}
}