package com.plannet.others;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import com.plannet.activity.R;
import com.plannet.http.HttpRequest;

public class SignInOnClickListener implements OnClickListener {

	private Activity currentActivity;
	private Class<?> targetActivity;

	public SignInOnClickListener(Activity currentActivity, Class<?> targetActivity) {
		this.currentActivity = currentActivity;
		this.targetActivity = targetActivity;
	}

	@Override
	public void onClick(View v) {
		String email = ((EditText) currentActivity.findViewById(R.id.emailEditCheck)).getText().toString();
		String password = ((EditText) currentActivity.findViewById(R.id.passwordEdit)).getText().toString();

		String result = HttpRequest.SignIn(email, password);
		if(result == "success"){
			Utilities.moveToAnotherActivity(currentActivity, targetActivity);
		}
		else if(result == "fail"){
			Toast.makeText(currentActivity, "로그인이 실패했습니다", Toast.LENGTH_SHORT).show();
		}
		
	}
}