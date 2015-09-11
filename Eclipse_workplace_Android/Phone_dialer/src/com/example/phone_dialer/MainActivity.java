package com.example.phone_dialer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	EditText editText = null;
	Button button = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.activity_main);

		this.editText = (EditText) this.findViewById(R.id.et_number);

		this.button = (Button) this.findViewById(R.id.bt_dail);

		this.button.setOnClickListener(new MyOnClickListener());
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
			MainActivity.this.dailTel(v);
		}
	}

	public void dailTel(View view) {
		Intent intent = new Intent();
		String number = this.editText.getText().toString().trim(); // 去除空格

		if (TextUtils.isEmpty(number)) { // 号码为空时返回出错信息
			Toast.makeText(this, "号码不能为空", Toast.LENGTH_LONG).show();
			return;
		}

		intent.setAction(Intent.ACTION_CALL);
		intent.setData(Uri.parse("tel:" + number));

		this.startActivity(intent);
	}
}
