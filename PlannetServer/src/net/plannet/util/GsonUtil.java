package net.plannet.util;

import java.io.DataInputStream;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtil {
	public static Gson getGsonConverter() {
		return new GsonBuilder().setPrettyPrinting().create();
	}

	public static String getJsonFromRequest(HttpServletRequest req) throws Exception {
		DataInputStream reader = new DataInputStream(req.getInputStream());
		String result = reader.readUTF();
		reader.close();
		return result;
	}
}