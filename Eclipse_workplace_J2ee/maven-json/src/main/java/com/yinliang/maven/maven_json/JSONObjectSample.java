package com.yinliang.maven.maven_json;

import org.json.JSONObject;

/**
 * Hello world!
 *
 */
public class JSONObjectSample {

	// 创建JSONObject对象
	private static JSONObject createJSONObject() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("username", "huangwuyi");
		jsonObject.put("sex", "男");
		jsonObject.put("QQ", "413425430");
		jsonObject.put("Min.score", new Integer(99));
		jsonObject.put("nickname", "梦中心境");
		return jsonObject;
	}

	public static void main(String[] args) {
		JSONObject jsonObject = JSONObjectSample.createJSONObject();// 静待方法，直接通过类名+方法调用

		// 输出jsonobject对象
		System.out.println("jsonObject：" + jsonObject);

		System.out.println(jsonObject.get("username"));

	}
}
