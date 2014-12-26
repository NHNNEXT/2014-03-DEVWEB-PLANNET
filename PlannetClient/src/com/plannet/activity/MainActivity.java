package com.plannet.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.plannet.clientdb.uuidDAO;
import com.plannet.http.HttpRequest;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final Context context = this;

		new Thread() {
			public void run() {
				// Sign Up
				// HttpRequest.SignUp("333eplan@gmail.com", "고예찬", "wow2000");

				// Sign In with email, pw
				 String uuid = HttpRequest.SignIn("333eplan@gmail.com", "wow2000");
				 new uuidDAO(context).delete();
				 new uuidDAO(context).insert(uuid);

				// Sign In with uuid
				 String uuidFromDB = new uuidDAO(context).select();
				 Log.e("uuid after select:", uuidFromDB);
				// String responseResult = HttpRequest.UUIDSignIn(uuidFromDB);
				// if(responseResult == ){
				//
				// }
			};
		}.start();

		// String uuid = new uuidDAO(this).select();
		// Log.e("uuid", "현재 uuid : " + uuid);
		//
		// if (uuid.equals("default")) {
		// Log.e("uuid", "정보 없음 : SignInActivity로 이동함");
		// PortalTimerHandler handler = new PortalTimerHandler(this, SignInActivity.class);
		// handler.execute(500);
		// } else {
		// // 여기서 http 요청하기 - uuid로 post 요청
		// PortalTimerHandler handler = new PortalTimerHandler(this, MyPlanActivity.class);
		// handler.execute(3000);
		// }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
