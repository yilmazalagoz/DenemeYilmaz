package com.tarzan.rssreader;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class FeedDetailActivity extends Activity {
	
	TextView titleView;
	TextView detailView;
	private String feedurllink;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feed_detail);
		
		titleView = (TextView) findViewById(R.id.feed_title);
		detailView = (TextView) findViewById(R.id.feed_detail);
		
		Intent feeddatailactivity = getIntent();
		titleView.setText(feeddatailactivity.getStringExtra("title"));
		detailView.setText(feeddatailactivity.getStringExtra("detail"));
		
		feedurllink = feeddatailactivity.getStringExtra("feedlink");
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.feed_detail, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.paylas:
	            paylas();
	            return true;
	       
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

	private void paylas() {
		
		Toast.makeText(getApplicationContext(), feedurllink, Toast.LENGTH_SHORT).show();
		
		Intent sendIntent = new Intent(Intent.ACTION_SEND);
		sendIntent.putExtra(Intent.EXTRA_TEXT, feedurllink);
		sendIntent.setType("text/plain");
		startActivity(sendIntent);
		
	}

}
