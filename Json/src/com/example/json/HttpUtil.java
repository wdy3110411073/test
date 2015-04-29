package com.example.json;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {

	private static HttpUtil instance;
	private static int TIMEOUT = 3 * 1000;
	private static int RETRY_TIME = 3;

	public static HttpUtil getInstance() {
		if (instance == null) {
			instance = new HttpUtil();
		}
		return instance;
	}

	public String doGetStr(String urlStr) {
		int time = 0;
		HttpURLConnection conn = null;
		String result = null;
		BufferedReader reader = null;
		do {
			try {
				URL url = new URL(urlStr);
				// 得到HttpURLConnection对象
				conn = (HttpURLConnection) url.openConnection();
				conn.setConnectTimeout(TIMEOUT);
				conn.setReadTimeout(TIMEOUT);
				conn.setDoInput(true);
				// 设置为GET方式
				conn.setRequestMethod("GET");
				if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
					// 得到响应消息
					InputStream in = conn.getInputStream();
					
					InputStreamReader inputStreamReader = new InputStreamReader(in);
					reader = new BufferedReader(inputStreamReader);// 读字符串用的。
					
					String inputLine = null;
					result = "";
					while (((inputLine = reader.readLine()) != null)) {
						// 我们在每一行后面加上一个"\n"来换行
						// result += inputLine + "\n";
						result += inputLine;
					}
					break;
					
				}
			} catch (Exception e) {
				time++;
				if (time < RETRY_TIME) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
					}
					continue;
				}
				e.printStackTrace();
			}
			finally{
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if(conn != null){
					conn.disconnect();
				}
			}
		} while (time < RETRY_TIME);

		return result;
	}

}
