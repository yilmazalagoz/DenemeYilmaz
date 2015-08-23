package com.tarzan.rssreader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

public class FeedActivity extends Activity {
//yilmaz değişiklik
	ProgressDialog loadingDialog;
	private ListView feedlist;
	public ArrayAdapter<String> customadapter;
	List<String> myList = new ArrayList<String>();
	private String source;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feed);
		setupActionBar();

		//by omer changed

		//by yilmaz changed

		Intent feedactivity = getIntent();
		source = feedactivity.getStringExtra("source");
		setTitle(source);
		
		feedlist = (ListView)findViewById(R.id.feedlist);
		
		new GetFeed().execute(source);
	}
	
	
	public class GetFeed extends AsyncTask<String, Void, Elements>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loadingDialog = new ProgressDialog(FeedActivity.this);
            loadingDialog.setTitle("Tarzan RSS Reader");
            loadingDialog.setMessage("Loading feed...");
            loadingDialog.setIndeterminate(false);
            loadingDialog.show();
            //habip sendeyiz
        }
		
		@Override
		protected Elements doInBackground(String... params) {
			
			Document document;
			try {
				document = Jsoup.connect(params[0]).get();
	            return document.select("channel > item ");
                //last changes ever //changed by yılmaz
				//last changes again
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
            
			return null;
		}
		
	    @Override
        protected void onPostExecute(final Elements result) {

	    	
	    	
	    	for(Element item:result)
	    		myList.add(item.select("title").text());
	    	
	    	
	    	
	    	customadapter = new ArrayAdapter<String>(FeedActivity.this, android.R.layout.simple_list_item_1, myList);
	    	feedlist.setAdapter(customadapter);
	    	
	    	
	    	feedlist.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int pos, long arg3) {
					
					
					Intent feeddetailactivity = new Intent(FeedActivity.this,FeedDetailActivity.class);
					feeddetailactivity.putExtra("title",result.get(pos).select("title").text());
					feeddetailactivity.putExtra("detail",result.get(pos).select("description").text());					
					feeddetailactivity.putExtra("feedlink",result.get(pos).select("link").toString());					

					Toast.makeText(getApplicationContext(), result.get(pos).select("link").text(), Toast.LENGTH_SHORT).show();

					
					startActivity(feeddetailactivity);
					
				}
			});
	    	
            loadingDialog.dismiss();
        }


		
		
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.feed, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		case R.id.yenile:
			new GetFeed().execute(source);
		}
		return super.onOptionsItemSelected(item);
	}

}
