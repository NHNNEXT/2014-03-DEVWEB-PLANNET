package com.plannet.category;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import com.plannet.clientdb.CategoryDAO;
import com.plannet.model.Category;

public class EditCategoryPopUp extends DialogFragment {

	@Override
	@NonNull
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("카테고리 수정");

		final ArrayList<Category> categoryList = new CategoryDAO(getActivity().getApplicationContext()).select();
		final String[] categoryStringList = new String[categoryList.size()];

		for (int i = 0; i < categoryList.size(); i++) {
			categoryStringList[i] = categoryList.get(i).getName();
		}

		builder.setItems(categoryStringList, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {
				Bundle bundle = new Bundle();
				bundle.putString("name", categoryList.get(id).getName());
				bundle.putInt("cid", categoryList.get(id).getCid());
				bundle.putInt("tabPosition", id);
				EditTextCategoryPopUp popup = new EditTextCategoryPopUp();
				popup.setArguments(bundle);
				popup.show(getFragmentManager(), "EditCategoryCool");
			}
		});

		return builder.create();
	}
}