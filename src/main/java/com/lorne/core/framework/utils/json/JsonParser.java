package com.lorne.core.framework.utils.json;

import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

public class JsonParser {

	public static String getStringFromInputStream(InputStream is)
			throws Exception {
		if (is == null) {
			return null;
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line;
		try {
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			br.close();
		} catch (IOException e1) {
			throw new Exception("从流进行解析失败！");
		}

		String str = sb.toString();
		return str;

	}

	public static <T> T fromJsonStream(Class<T> cls, InputStream is)
			throws Exception {
		String jsonString = getStringFromInputStream(is);
		return fromJsonString(cls, jsonString);
	}

	public static <T> T fromJsonString(Class<T> cls, String jsonString, Map<String,Class> classMap)
			throws Exception {
		try {
			return (T)JSONObject.toBean(JSONObject.fromObject(jsonString),cls,classMap);
		} catch (Exception e) {
			throw new Exception(String.format("%s(%s:%s)", e.getMessage(),
					"解析的字符串为", jsonString));
		}
	}

	public static <T> T fromJsonString(Class<T> cls, String jsonString)
			throws Exception {
		return fromJsonString(cls,jsonString,null);
	}
}
