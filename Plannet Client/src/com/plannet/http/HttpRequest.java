package com.plannet.http;

import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class HttpRequest {
	
	
	public void signInPost() {
		
		// 범용적으로 쓸 수 있게 Post 요청 기본 빼기
		
		String url = "http://xxx.xxx.xxx.xxx:xxxx/login.jsp"; // strings.xml로 빼고
		HttpClient http = new DefaultHttpClient();
		
		try {
			ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("name", "유재석"));

			HttpParams params = http.getParams();
			HttpConnectionParams.setConnectionTimeout(params, 5000);
			HttpConnectionParams.setSoTimeout(params, 5000);

			HttpPost httpPost = new HttpPost(url);
			
			UrlEncodedFormEntity entityRequest = new UrlEncodedFormEntity(nameValuePairs, "UTF-8");
			httpPost.setEntity(entityRequest);

			HttpResponse responsePost = http.execute(httpPost);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
