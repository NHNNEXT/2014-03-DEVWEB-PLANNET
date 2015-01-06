package com.plannet.http;

import java.net.HttpURLConnection;
import java.net.URL;

import android.util.Log;

import com.plannet.model.Plan;
import com.plannet.model.Subplan;
import com.plannet.model.User;
import com.plannet.others.Utilities;

public class HttpRequest {

	public static HttpURLConnection getConnection(String servletName) {
		String servletURL = "http://10.73.42.200:8080/" + servletName;

		try {
			URL url = new URL(servletURL);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(10 * 1000);
			conn.setReadTimeout(10 * 1000);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("Cache-Control", "no-cache");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("charset", "euc-kr");
			conn.setRequestProperty("Accept-Charset", "UTF-8");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
			Log.e("getConnection : ", "Error!");
			return null;
		}
	}

	public static String[] SignIn(String email, String password) {
		HttpURLConnection conn = getConnection("SignIn");

		User user = new User(email, password);
		String uuidJson = Utilities.GsonConvertToString(user);
		Utilities.setRequestBody(conn, uuidJson);

		try {
			conn.connect();
			Log.e("SignInProxy : ", "" + conn.getResponseCode());
		} catch (Exception e) {
			e.printStackTrace();
			Log.e("SignInProxy : ", "Connection Error!");
		}

		String result = conn.getHeaderField("result");
		String uuid = conn.getHeaderField("uuid");
		String[] response = new String[] { result, uuid };

		return response;
	}

	public static String UUIDSignIn(String uuid) {
		HttpURLConnection conn = getConnection("UUIDSignIn");

		conn.addRequestProperty("uuid", uuid);
		// setRequestProperty와 차이점 : 같은 key 값 있을 경우 덮어쓸건지 안쓸건지

		try {
			conn.connect();
			Log.e("SignInProxy : ", "" + conn.getResponseCode());
		} catch (Exception e) {
			e.printStackTrace();
			Log.e("SignInProxy : ", "Connection Error!");
		}

		String result = conn.getHeaderField("result");
		Log.e("SignInResult : ", result);
		return result;
	}

	public static String SignUp(String email, String name, String password) {
		HttpURLConnection conn = getConnection("SignUp");

		User user = new User(email, name, password);

		String uuidJson = Utilities.GsonConvertToString(user);
		Utilities.setRequestBody(conn, uuidJson);

		try {
			conn.connect();
			Log.e("SignUpProxy : ", "" + conn.getResponseCode());
		} catch (Exception e) {
			e.printStackTrace();
			Log.e("SignUpProxy : ", "Connection Error!");
		}

		String response = conn.getHeaderField("result");
		return response;
	}

	public static String[] PushPlan(int cid, String title, String summary) {
		HttpURLConnection conn = getConnection("PushPlan");

		Plan plan = new Plan(cid, title, summary);
		String planJson = Utilities.GsonConvertToString(plan);
		Utilities.setRequestBody(conn, planJson);

		try {
			conn.connect();
			Log.e("PushPlanProxy : ", "" + conn.getResponseCode());
		} catch (Exception e) {
			e.printStackTrace();
			Log.e("PushPlanProxy : ", "Connection Error!");
		}

		String result = conn.getHeaderField("result");
		String pid = conn.getHeaderField("pid");
		String[] response = new String[] { result, pid };

		return response;
	}

	public static String[] PushSubplan(int pid, String title, String summary, int percent) {
		HttpURLConnection conn = getConnection("PushSubplan");

		Subplan subplan = new Subplan(pid, title, summary, percent);
		String subplanJson = Utilities.GsonConvertToString(subplan);
		Utilities.setRequestBody(conn, subplanJson);

		try {
			conn.connect();
			Log.e("PushSubplanProxy : ", "" + conn.getResponseCode());
		} catch (Exception e) {
			e.printStackTrace();
			Log.e("PushSubplanProxy : ", "Connection Error!");
		}

		String result = conn.getHeaderField("result");
		String subpid = conn.getHeaderField("subpid");
		String[] response = new String[] { result, subpid };

		return response;
	}

	public static Plan[] PullPlan() {
		HttpURLConnection conn = getConnection("PullPlan");

		try {
			conn.connect();
			Log.e("PullPlanProxy : ", "" + conn.getResponseCode());
		} catch (Exception e) {
			e.printStackTrace();
			Log.e("PullPlanProxy : ", "Connection Error!");
		}

		Plan[] planList = Utilities.GsonConvertFromString(Utilities.getResponseBody(conn), Plan[].class);
		return planList;
	}
}