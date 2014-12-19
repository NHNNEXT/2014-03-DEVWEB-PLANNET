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
		// Button button = (Button) Button;
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

	public static void setRequestBody(HttpURLConnection conn, ArrayList<Object> bodyContent) {
		String JSON = Utilities.GsonConvertToString(bodyContent);
		try {
			DataOutputStream writer = new DataOutputStream(conn.getOutputStream());
			if (bodyContent != null)
				writer.writeUTF(JSON); // 서버 쪽에서도 받을 때 DataInputStream 써서 reader.readUTF
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getResponseBody(HttpURLConnection conn) {
		BufferedReader bufferedReader = null;
		try {
			String line;
			bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			// bufferedReader/Writer의 경우에는 생성할 때 두번째 인자에 "UTF-8" 설정을 해주면 된다.
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