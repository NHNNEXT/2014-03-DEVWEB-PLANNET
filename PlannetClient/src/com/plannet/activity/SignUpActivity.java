package com.plannet.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.plannet.others.PortalOnClickListener;
import com.plannet.others.Utilities;

public class SignUpActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);

		final Button registerButton = (Button) findViewById(R.id.registerButton);
		registerButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				final String email = ((EditText) findViewById(R.id.email)).getText().toString();
				final String name = ((EditText) findViewById(R.id.name)).getText().toString();
				final String password = ((EditText) findViewById(R.id.password)).getText().toString();
				String passwordCheck = ((EditText) findViewById(R.id.passwordCheck)).getText().toString();

				if (!Utilities.isEmail(email)) {
					Toast.makeText(getApplicationContext(), "이메일 형식이 올바르지 않습니다.", Toast.LENGTH_SHORT).show();
					return;
				}

				if (!password.equals(passwordCheck)) {
					Toast.makeText(getApplicationContext(), "패스워드가 일치하지않습니다.", Toast.LENGTH_SHORT).show();
					return;
				}

				if ((email.equals(new String(""))) || (name.equals(new String("")))
						|| (password.equals(new String(""))) || (passwordCheck.equals(new String("")))) {
					Toast.makeText(getApplicationContext(), "빈 공간이 있습니다.", Toast.LENGTH_SHORT).show();
					return;
				}

				// new Thread() {
				// public void run() {
				// SignUpProxy signUpProxy = new SignUpProxy();
				// String result = signUpProxy.SignUp(email, password);
				// Log.e("SignUp", result);
				// };
				// }.start();

				// String result = HTTP.SignUp();
				// result == "success" --> startActivity(PlanActvity);
				// result == "fail" --> Toast.make("이미 존재하는 이메일 입니다.") return;
				// finish();
				return;
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sign_up, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public static void addPortalToButton(View Button, Activity currentActivity, Class<?> targetActivity) {
		Button button = (Button) Button;
		PortalOnClickListener listener = new PortalOnClickListener(currentActivity, targetActivity);
		button.setOnClickListener(listener);
	}
}
