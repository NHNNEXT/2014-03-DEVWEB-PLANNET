package com.plannet.others;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Utilities {

	public static void addPortalToButton(View Button, Activity currentActivity, Class<?> targetActivity,
			Object... putExtras) {
		Button button = (Button) Button;
		PortalOnClickListener listener = new PortalOnClickListener(currentActivity, targetActivity, putExtras);
		button.setOnClickListener(listener);
	}

	public static void moveToAnotherActivity(Activity currentActivity, Class<?> targetActivity, Object... putExtras) {
		Intent intent = new Intent(currentActivity, targetActivity);

		if (putExtras != null) {
			for (int i = 0; i < putExtras.length; i += 2) {
				Object key = putExtras[i];
				Object value = putExtras[i + 1];
				if (value instanceof Integer) {
					intent.putExtra((String) key, (Integer) value);
				} else if (value instanceof String) {
					intent.putExtra((String) key, (String) value);
				}
			}
		}

		currentActivity.startActivity(intent);
	}

	public static boolean isValidEmail(String email) {
		if (email == null)
			return false;
		boolean b = Pattern.matches("[\\w\\~\\-\\.]+@[\\w\\~\\-]+(\\.[\\w\\~\\-]+)+", email.trim());
		return b;
	}

	public static String GsonConvertToString(Object bodyContent) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(bodyContent);
	}

	public static <T> T GsonConvertFromString(String json, Class<T> objClass) {
		Gson gson = new Gson();
		return gson.fromJson(json, objClass);
	}

	public static void setRequestBody(HttpURLConnection conn, String bodyContent) {
		try {
			DataOutputStream writer = new DataOutputStream(conn.getOutputStream());
			writer.writeUTF(bodyContent); // 서버 쪽에서도 받을 때 DataInputStream 써서 reader.readUTF
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getResponseBody(HttpURLConnection conn) {
		String responseBody = null;
		try {
			DataInputStream reader = new DataInputStream(conn.getInputStream());
			responseBody = reader.readUTF(); // 서버 쪽에서도 받을 때 DataInputStream 써서 reader.readUTF
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return responseBody;
	}

	public static void toastPopUp(Activity currentActivity, String message) {
		Toast.makeText(currentActivity.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
	}
}