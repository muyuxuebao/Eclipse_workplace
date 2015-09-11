package com.example.hellolistview;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ListActivity {
	private String[] texts = new String[] { "天气", "我团", "背景" };
	// 展示的图片
	private int[] images = new int[] { R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_main);

		// 设置一个Adapter,使用自定义的Adapter
		this.setListAdapter(new TextImageAdapter(this));
	}

	/** 
	 * 自定义视图 
	 * @author 飞雪无情 
	 * 
	 */
	private class TextImageAdapter extends BaseAdapter {
		private Context mContext;

		public TextImageAdapter(Context context) {
			this.mContext = context;
		}

		@Override
		public int getCount() {
			return MainActivity.this.texts.length;
		}

		@Override
		public Object getItem(int arg0) {
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// 优化ListView
			if (convertView == null) {
				convertView = LayoutInflater.from(this.mContext).inflate(R.layout.item, null);
				ItemViewCache viewCache = new ItemViewCache();
				viewCache.mTextView = (TextView) convertView.findViewById(R.id.text);
				viewCache.mImageView = (ImageView) convertView.findViewById(R.id.image);

				convertView.setTag(viewCache);
			}

			ItemViewCache cache = (ItemViewCache) convertView.getTag();
			// 设置文本和图片，然后返回这个View，用于ListView的Item的展示
			cache.mTextView.setText(MainActivity.this.texts[position]);
			cache.mImageView.setImageResource(MainActivity.this.images[position]);
			return convertView;
		}

	}

	// 元素的缓冲类,用于优化ListView
	private static class ItemViewCache {
		public TextView mTextView;
		public ImageView mImageView;
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
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Toast.makeText(this, "你单击了" + this.texts[position], Toast.LENGTH_SHORT).show();
	}
}
