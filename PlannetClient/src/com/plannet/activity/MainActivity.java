package com.plannet.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.plannet.clientdb.uuidDAO;
import com.plannet.http.HttpRequest;
import com.plannet.others.PortalTimerHandler;

public class MainActivity extends ActionBarActivity {
	private String response;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final String uuid = new uuidDAO(this).select();
		Log.e("uuid", "현재 uuid : " + uuid);

		// "default"는 DBOpenHelper.onCreate() 참고. 처음 설치될 때 이 값으로 초기화 된다.
		if (uuid.equals("default")) {
			Log.e("uuid", "정보 없음 : SignInActivity로 이동함");
			PortalTimerHandler handler = new PortalTimerHandler(this, SignInActivity.class);
			handler.execute(3000);
		} else {
			// uuid로 http post 요청, 서버에서 응답값 받아와서 result에 저장
			new Thread() {
				public void run() {
					response = HttpRequest.UUIDSignIn(uuid);
				}
			}.run();

			if (response == "UUIDNotFound") {
				// 응답값이 UUIDNotFound인 경우엔 SignIn으로 보내줌
				PortalTimerHandler handler = new PortalTimerHandler(this, SignInActivity.class);
				handler.execute(3000);
			} else if (response == "Success") {
				// 응답값이 "Success"인 경우엔 MyPlan으로 보내줌 -> 응답값 확인요망
				PortalTimerHandler handler = new PortalTimerHandler(this, MyPlanActivity.class);
				handler.execute(3000);
			}
		}

		// TODO:: TEST용 로직 맞춰서 다른 코드들 바꾸기
		
		// ### TEST용 코드
		// final Context context = this;
		// new Thread() {
		// public void run() {
		
		// Sign Up
		// HttpRequest.SignUp("333eplan@gmail.com", "고예찬", "wow2000");
		
		// Sign In with email, pw
		// String uuid = HttpRequest.SignIn("333eplan@gmail.com", "wow2000");
		// new uuidDAO(context).delete();
		// new uuidDAO(context).insert(uuid);
		
		// Sign In with uuid
		// String uuidFromDB = new uuidDAO(context).select();
		// Log.e("uuid after select:", uuidFromDB);
		// String responseResult = HttpRequest.UUIDSignIn(uuidFromDB);
		// if(responseResult == ){
		//
		// }
		// };
		// }.start();
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
