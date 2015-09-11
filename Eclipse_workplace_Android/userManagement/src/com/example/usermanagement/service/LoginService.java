package com.example.usermanagement.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.os.Environment;

public class LoginService {

	public boolean saveUserInfo(Context context, String username, String password, int mode) {

		try {

			File file = new File(Environment.getExternalStorageDirectory(), "info.txt"); // 使用系统函数获取SD卡的目录,并在SD卡上建立info.txt
			FileOutputStream fos = new FileOutputStream(file);

			fos.write((username + "##" + password).getBytes());

			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return true;
	}

	public static Map<String, String> getSavedUserInfo(Context context) {
		try {
			FileReader fileReader = new FileReader(new File(context.getFilesDir(), "info.txt"));
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String str = bufferedReader.readLine();
			String strs[] = str.split("##");
			Map<String, String> map = new HashMap<String, String>();
			map.put("username", strs[0]);
			map.put("pwd", strs[1]);

			bufferedReader.close();
			fileReader.close();
			return map;

		} catch (FileNotFoundException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
	}

}
