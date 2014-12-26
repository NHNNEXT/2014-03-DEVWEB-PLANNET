package com.plannet.others;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.plannet.activity.R;
import com.plannet.clientdb.uuidDAO;
import com.plannet.http.HttpRequest;

public class SignInOnClickListener implements OnClickListener {

	private Activity currentActivity;
	private Class<?> targetActivity;
	private String uuid;
	private String result;

	public SignInOnClickListener(Activity currentActivity, Class<?> targetActivity) {
		this.currentActivity = currentActivity;
		this.targetActivity = targetActivity;
	}

	@Override
	public void onClick(View v) {
		final String email = ((EditText) currentActivity.findViewById(R.id.emailEdit)).getText().toString();
		final String password = ((EditText) currentActivity.findViewById(R.id.passwordEdit)).getText().toString();

		new Thread() {
			public void run() {
				// 실제로 return하는 건 uuid, 여기 서버 응답값 뭐인지 수정 필수!!!
				uuid = HttpRequest.SignIn(email, password);
			};
		}.start();

		if (result == "Success") {
			new uuidDAO(currentActivity).delete();
			new uuidDAO(currentActivity).insert(uuid);
			Utilities.moveToAnotherActivity(currentActivity, targetActivity);
		} else if (result == "EmailNotFound") {
			Utilities.toastPopUp(currentActivity, "등록되지 않은 이메일입니다!");
		} else if (result == "PasswordDismatch") {
			Utilities.toastPopUp(currentActivity, "비밀번호를 다시 입력해 주세요!");
		} else {
			Utilities.toastPopUp(currentActivity, "알 수 없는 오류입니다!");
		}
	}
}