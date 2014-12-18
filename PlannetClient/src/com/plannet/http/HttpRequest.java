package com.plannet.http;

import java.net.HttpURLConnection;
import java.net.URL;

import android.util.Log;

import com.plannet.others.Utilities;

public class HttpRequest {

	// DataOutputStream writer = new DataOutputStream(conn.getOutputStream());
	// writer.writeBytes(JSON);
	// writer.flush();
	// writer.close();
	// conn.connect();

	public static HttpURLConnection getConnection(String servletName) {
		String servletURL = "http://10.73.39.207:8080/" + servletName;

		try {
			URL url = new URL(servletURL);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(10 * 1000);
			conn.setReadTimeout(10 * 1000);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("Cache-Control", "no-cache");
			conn.setRequestProperty("Accept", "application/json");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
			Log.e("Proxy : ", "getConnection Error");
			return null;
		}
	}

	public static String SignUp(String email, String password) {
		HttpURLConnection conn = getConnection("SignUp");
		conn.setRequestProperty("email", email);
		conn.setRequestProperty("password", password);

		try {
			conn.connect();
			Log.e("SignUpProxy : ", "" + conn.getResponseCode());
		} catch (Exception e) {
			e.printStackTrace();
			Log.e("SignUpProxy : ", "getConnection Error!");
		}

		String response = Utilities.getResponseBody(conn);
		return response;
	}
}