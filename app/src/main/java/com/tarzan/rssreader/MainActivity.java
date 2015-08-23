package com.tarzan.rssreader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	ListView lv;
	private ArrayAdapter<String> myAdapter = null;
	List<String> myList = null;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			
		}else{
			String filename = "data.json";
            File path = Environment.getExternalStorageDirectory();
			File file = new File(path, filename);
			FileOutputStream fos;
			if(!file.exists()){
			try {
				fos = new FileOutputStream(file);
				byte[] data = new String("[]").getBytes();
				fos.write(data);
				fos.flush();
			    fos.close(); 
			    
			    Toast.makeText(getApplicationContext(), "File is created!" , Toast.LENGTH_LONG).show();

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		    
			}
			          
            
		}
		
		
		lv = (ListView)findViewById(R.id.listView1);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int pos,long arg3) {
				
				Intent feedactivity = new Intent(getBaseContext(),FeedActivity.class);
				feedactivity.putExtra("source",((TextView) v).getText().toString());
				startActivity(feedactivity);
				
				
			}
		});


		setupListView();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.kaynakekle:
	            kaynakekle();
	            return true;
	       
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

	private void kaynakekle() {
		final AlertDialog.Builder alert = new AlertDialog.Builder(this);

		alert.setTitle("Add RSS Source");
		alert.setMessage("Paste RSS link and wait...");

		// Set an EditText view to get user input 
		final EditText input = new EditText(this);
		alert.setView(input);

		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int whichButton) {
			try {
				dosyaOlustur(input.getText().toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }
		});

		alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		  public void onClick(DialogInterface dialog, int whichButton) {
			  alert.create().cancel();
		  }
		});

		alert.show();
		
	}
	
	
	
	private void dosyaOlustur(String link) throws IOException, JSONException{
		
		if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			Toast.makeText(getApplicationContext(),  "Memory Error!" , Toast.LENGTH_LONG).show();
		}else if(!link.startsWith("http")){
			Toast.makeText(getApplicationContext(),  "Invalid Source!" , Toast.LENGTH_LONG).show();
		}else {
			
			
			String filename = "data.json";
            File path = Environment.getExternalStorageDirectory();
			File file = new File(path, filename);
			StringBuilder text = new StringBuilder();
			String line;

				BufferedReader bfr =  new BufferedReader(new FileReader(file));
				while ((line = bfr.readLine()) != null) {
			        text.append(line);
			        text.append('\n');
			    }
				
				String output = text.toString().replace(" ", "");

				JSONArray sources = new JSONArray(output);
				JSONObject source = new JSONObject();
				source.put("url", link);
				sources.put(source);
				FileOutputStream fos = new FileOutputStream(file);
				byte[] data = new String(sources.toString()).getBytes();
				fos.write(data);
				fos.flush();
				fos.close();				
				setupListView();
				
				Toast.makeText(getApplicationContext(),  "URL Added" , Toast.LENGTH_LONG).show();
			
			}
			
		}
	
	
	private List<String> kayitlilinkler() throws IOException, JSONException{
		
		if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			
		} else {
			String filename = "data.json";
            File path = Environment.getExternalStorageDirectory();
			File file = new File(path, filename);
				
			StringBuilder text = new StringBuilder();
			String line;

				BufferedReader bfr =  new BufferedReader(new FileReader(file));
				while ((line = bfr.readLine()) != null) {
			        text.append(line);
			        text.append('\n');
			    }

				List<String> myList = new ArrayList<String>();
				
				JSONArray sources = new JSONArray(text.toString());
				
				for (int i = 0; i < sources.length(); i++) {
					JSONObject kayit = (JSONObject) sources.get(i);
					
					myList.add(kayit.getString("url"));
				}

				return myList;

		}
		
		return null;
		
	}
	

	public void setupListView(){

		try {
			myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, kayitlilinkler());
			
			Toast.makeText(getApplicationContext(),  kayitlilinkler().toString() , Toast.LENGTH_LONG).show();
			
			if(myAdapter!=null)
				lv.setAdapter(myAdapter);

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}
