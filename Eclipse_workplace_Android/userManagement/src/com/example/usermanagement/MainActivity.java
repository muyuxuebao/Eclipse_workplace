package com.example.usermanagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.usermanagement.service.LoginService;

public class MainActivity extends ActionBarActivity implements OnClickListener {
	CheckBox cb_save_password = null;

	EditText et_username = null;
	EditText et_password = null;

	Button bt_submit = null;
	Button bt_register = null;
	Button bt_view_permission = null;
	Button bt_show_sd_card_size = null;
	Button bt_show_TF_card_size = null;

	RadioGroup rg_mode = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_main);

		this.et_username = (EditText) this.findViewById(R.id.et_username);
		this.et_password = (EditText) this.findViewById(R.id.et_password);

		this.cb_save_password = (CheckBox) this.findViewById(R.id.cb_save_password);

		this.bt_submit = (Button) this.findViewById(R.id.bt_submit);
		this.bt_register = (Button) this.findViewById(R.id.bt_register);
		this.bt_view_permission = (Button) this.findViewById(R.id.bt_view_permission);
		this.bt_show_sd_card_size = (Button) this.findViewById(R.id.bt_show_sd_card_size);
		this.bt_show_TF_card_size = (Button) this.findViewById(R.id.bt_show_TF_card_size);

		this.bt_submit.setOnClickListener(this);
		this.bt_register.setOnClickListener(this);
		this.bt_view_permission.setOnClickListener(this);
		this.bt_show_sd_card_size.setOnClickListener(this);
		this.bt_show_TF_card_size.setOnClickListener(this);

		this.rg_mode = (RadioGroup) this.findViewById(R.id.rg_mode);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		this.getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		Button button = (Button) v;

		String name = this.et_username.getText().toString().trim();
		String password = this.et_password.getText().toString().trim();

		Context context = v.getContext();

		switch (v.getId()) {
		case R.id.bt_register:
			this.checkInput(name, password);

			int mode = 0;

			switch (this.rg_mode.getCheckedRadioButtonId()) {
			case R.id.rd_private:
				mode = Context.MODE_PRIVATE;
				break;
			case R.id.rd_read:
				mode = Context.MODE_WORLD_READABLE;
				break;
			case R.id.rd_write:
				mode = Context.MODE_WORLD_WRITEABLE;
				break;
			case R.id.rd_public:
				mode = Context.MODE_WORLD_READABLE + Context.MODE_WORLD_WRITEABLE;
				break;
			default:
				break;
			}

			boolean result = this.register(context, name, password, this.cb_save_password.isChecked(), mode);

			if (result) {
				Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(this, "注册失败", Toast.LENGTH_SHORT).show();
			}

			break;

		case R.id.bt_submit:
			this.checkInput(name, password);

			Map<String, String> map = new LoginService().getSavedUserInfo(v.getContext());
			String nameVerify = map.get("username");
			String passwordVerify = map.get("pwd");

			if (!TextUtils.isEmpty(nameVerify) && !TextUtils.isEmpty(passwordVerify) && nameVerify.equals(name)
					&& passwordVerify.equals(password)) {
				Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(this, "登陆失败", Toast.LENGTH_SHORT).show();
			}
			break;

		case R.id.bt_get_administrator_permission:
			try {
				this.getAdministratorPrivileges();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // 获取管理员权限
			break;
		case R.id.bt_view_permission:
			String path = context.getFilesDir() + "/info.txt";

			Log.d("MainActivity", path);

			String cmd = "ls –l " + path;

			try {
				Process p = Runtime.getRuntime().exec(cmd);
				BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
				String line = null;
				StringBuilder sb = new StringBuilder();
				while ((line = br.readLine()) != null) {
					sb.append(line + "\n");
				}
				Log.d("MainActivity", sb.toString());

				Toast.makeText(this, "cmd: " + cmd.toString(), Toast.LENGTH_SHORT).show();

			} catch (IOException e) {
				e.printStackTrace();
			}

			break;

		case R.id.bt_show_sd_card_size:
			this.showSDCardSize(context);
			break;

		case R.id.bt_show_TF_card_size:
			this.showTFCardSize(context);
			break;
		default:
			break;
		}

	}

	private void getAdministratorPrivileges() throws IOException {
		Process p1 = Runtime.getRuntime().exec("su"); // 获取管理员权限
	}

	private void checkInput(String name, String password) {
		if (TextUtils.isEmpty(name) || TextUtils.isEmpty(password)) {
			Toast.makeText(this, "用户名和密码都不可为空", Toast.LENGTH_SHORT).show();
			return;
		}
	}

	private boolean register(Context context, String name, String password, boolean savePassword, int mode) {
		if (savePassword == true) {
			return new LoginService().saveUserInfo(context, name, password, mode);
		} else {
			return new LoginService().saveUserInfo(context, name, null, mode);
		}
	}

	private void showSDCardSize(Context context) {
		File file = Environment.getExternalStorageDirectory();
		StatFs stat = new StatFs(file.getPath());
		long blockSize = stat.getBlockSize(); // 获取块的大小
		long totalBlocks = stat.getBlockCount(); // 获取总的块数
		long avaliableBlocks = stat.getAvailableBlocks(); // 获取可用的块数

		String totalstr = Formatter.formatFileSize(context, totalBlocks * blockSize); // 将内存大小格式化成字符串
																						// ,注意Formatter所在的包是import
																						// android.text.format
		String avaliablestr = Formatter.formatFileSize(context, avaliableBlocks * blockSize); // 将内存大小格式化成字符串
																								// ,注意Formatter所在的包是import
																								// android.text.format
		Toast.makeText(this, "total size: " + totalstr + " avaliable size: " + avaliablestr, Toast.LENGTH_SHORT).show();

	}

	private void showTFCardSize(Context context) {
		File file = Environment.getDataDirectory();
		StatFs stat = new StatFs(file.getPath());
		long blockSize = stat.getBlockSize(); // 获取块的大小
		long totalBlocks = stat.getBlockCount(); // 获取总的块数
		long avaliableBlocks = stat.getAvailableBlocks(); // 获取可用的块数

		String totalstr = Formatter.formatFileSize(context, totalBlocks * blockSize); // 将内存大小格式化成字符串
																						// ,注意Formatter所在的包是import
																						// android.text.format
		String avaliablestr = Formatter.formatFileSize(context, avaliableBlocks * blockSize); // 将内存大小格式化成字符串
																								// ,注意Formatter所在的包是import
																								// android.text.format
		Toast.makeText(this, "total size: " + totalstr + " avaliable size: " + avaliablestr, Toast.LENGTH_SHORT).show();

	}
}
