package com.example.smssend;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	EditText et_number = null;
	EditText et_content = null;
	Button bt_send = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.activity_main);

		this.et_number = (EditText) this.findViewById(R.id.et_number);
		this.et_content = (EditText) this.findViewById(R.id.et_content);
		this.bt_send = (Button) this.findViewById(R.id.bt_send);
		this.bt_send.setOnClickListener(new MyOnClickListener());
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

	private class MyOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			MainActivity.this.sendMsg(v);
		}
	}

	public void sendMsg(View v) {
		String number = this.et_number.getText().toString().trim();
		String content = this.et_content.getText().toString().trim();

		if (TextUtils.isEmpty(number) || TextUtils.isEmpty(content)) { // 号码为空时返回出错信息
			Toast.makeText(this, "号码和内容都不能为空", Toast.LENGTH_LONG).show();
			return;
		} else {
			SmsManager smsManager = SmsManager.getDefault();
			ArrayList<String> contents = smsManager.divideMessage(content); // 当短信内容过大时,将短信拆分为多段,依次发送

			for (String s : contents) {
				smsManager.sendTextMessage(number, null, s, null, null);
			}

		}

	}
}
