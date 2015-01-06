package net.plannet.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtil {
	public static Gson gson = new GsonBuilder().setPrettyPrinting().create();

	public static Gson getGsonConverter() {
		return new GsonBuilder().setPrettyPrinting().create();
	}

	public static <T> T getObjectFromRequest(HttpServletRequest req,
			Class<T> objClass) throws IOException {
		DataInputStream reader = new DataInputStream(req.getInputStream());
		String json = reader.readUTF();
		reader.close();
		return gson.fromJson(json, objClass);
	}

	public static void writeObjectOnResponse(HttpServletResponse resp,
			Object obj) throws IOException {
		DataOutputStream writer = new DataOutputStream(resp.getOutputStream());
		String json = gson.toJson(obj);
		writer.writeUTF(json);
		writer.close();
	}
}