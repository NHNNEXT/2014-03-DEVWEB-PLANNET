package net.plannet.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtil {
	public static Gson getGsonConverter() {
		return new GsonBuilder().setPrettyPrinting().create();
	}

	public static String getJsonFromRequest(HttpServletRequest req)
			throws Exception {
		InputStreamReader streamReader = new InputStreamReader(
				req.getInputStream(), "UTF-8");
		BufferedReader bufferedReader = new BufferedReader(streamReader);
		StringBuilder stringBuilder = new StringBuilder();
		String lineReader;
		while ((lineReader = bufferedReader.readLine()) != null) {
			stringBuilder.append(lineReader);
		}
		bufferedReader.close();
		streamReader.close();
		return stringBuilder.toString();
	}

	public static String getJSONJSON(HttpServletRequest req) throws Exception {
		DataInputStream reader = new DataInputStream(req.getInputStream());
		String result = reader.readUTF();
		reader.close();
		return result;
	}
}