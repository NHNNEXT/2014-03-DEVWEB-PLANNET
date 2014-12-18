package com.plannet.others;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Utilities {

	public static void addPortalToButton(View Button, Activity currentActivity, Class<?> targetActivity) {
		Button button = (Button) Button;
		PortalOnClickListener listener = new PortalOnClickListener(currentActivity, targetActivity);
		button.setOnClickListener(listener);
	}

	public static void CheckRegisterButton(View Button, Activity currentActivity, Class<?> targetActivity) {
		//Button button = (Button) Button;
	}

	public static void moveToAnotherActivity(Activity currentActivity, Class<?> targetActivity) {
		Intent intent = new Intent(currentActivity, targetActivity);
		currentActivity.startActivity(intent);
	}

	public static boolean isEmail(String email) {
		if (email == null)
			return false;
		boolean b = Pattern.matches("[\\w\\~\\-\\.]+@[\\w\\~\\-]+(\\.[\\w\\~\\-]+)+", email.trim());
		return b;
	}

	public static String GsonConvertToString(ArrayList<Object> bodyContent) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(bodyContent);
	}

	public static void setRequestBody(HttpURLConnection conn, ArrayList<Object> bodyContent) throws IOException {
		String JSON = Utilities.GsonConvertToString(bodyContent);
		DataOutputStream writer = new DataOutputStream(conn.getOutputStream());
		if (bodyContent != null)
			writer.writeBytes(JSON);
	}

	public static String getResponseBody(HttpURLConnection conn) {
		BufferedReader bufferedReader = null;
		try {
			String line;
			bufferedReader = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			StringBuilder stringBuilder = new StringBuilder();
			while ((line = bufferedReader.readLine()) != null) {
				stringBuilder.append(line);
			}
			bufferedReader.close();
			return stringBuilder.toString();
		} catch (IOException e) {
			e.printStackTrace();
			Log.e("Utilities : ", "GetResponseBody Error");
			return null;
		}
	}
}