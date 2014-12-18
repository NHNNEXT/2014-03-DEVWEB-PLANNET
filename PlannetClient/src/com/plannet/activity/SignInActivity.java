package com.plannet.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.plannet.clientdb.uuidDAO;
import com.plannet.others.Utilities;

public class SignInActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		new uuidDAO(this).insert("aaa");
		new uuidDAO(this).insert("bbb");
		new uuidDAO(this).insert("ccc");
		new uuidDAO(this).insert("ddd");
		new uuidDAO(this).insert("eee");

		String uuid = new uuidDAO(this).select();

		if (uuid != null) {
			// uuid 포스트 요청
			Log.e("ersdlkafjlkjds", uuid);
		}
		
		setContentView(R.layout.activity_sign_in);

		// signin button에 onclicklistener 달기
		Utilities.addPortalToButton(findViewById(R.id.signUpButton), this, SignUpActivity.class);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sign_in, menu);
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
}