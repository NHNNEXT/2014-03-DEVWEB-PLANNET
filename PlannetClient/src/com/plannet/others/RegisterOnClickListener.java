package com.plannet.others;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class RegisterOnClickListener implements OnClickListener {
	private String email;
	private String email2;
	private String password;
	private String password2;
	private Activity currentActivity;
	private Class<?> targetActivity;
	Context context;

	public RegisterOnClickListener(String email, String email2, String password, String password2, Context context,
			Activity currentActivity, Class<?> targetActivity) {
		this.email = email;
		this.email2 = email2;
		this.password = password;
		this.password2 = password2;
		this.context = context;
		this.currentActivity = currentActivity;
		this.targetActivity = targetActivity;
	}

	@Override
	public void onClick(View v) {
		if (!Utilities.isEmail(email)) {
			Toast.makeText(context, "이메일 형식이 올바르지 않습니다.", Toast.LENGTH_SHORT).show();
			return;
		}

		if (!email.equals(email2) && !password.equals(password2)) {
			Toast.makeText(context, "이메일과 패스워드가 일치하지않습니다.", Toast.LENGTH_SHORT).show();
			return;
		}

		if (!email.equals(email2)) {
			Toast.makeText(context, "이메일이 일치하지않습니다.", Toast.LENGTH_SHORT).show();
			return;
		}

		if (!password.equals(password2)) {
			Toast.makeText(context, "패스워드가 일치하지않습니다.", Toast.LENGTH_SHORT).show();
			return;
		}

		if (!(email == null) || !(email2 == null) || !(password == null) || !(password2 == null)) {
			Toast.makeText(context, "빈 공간이 있습니다.", Toast.LENGTH_SHORT).show();
			return;
		}

		Intent intent = new Intent(currentActivity, targetActivity);
		currentActivity.startActivity(intent);
	}
}
