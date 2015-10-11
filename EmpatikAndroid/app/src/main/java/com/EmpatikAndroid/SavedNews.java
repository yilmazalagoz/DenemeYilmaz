package com.EmpatikAndroid;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SavedNews extends AppCompatActivity {
    private List<Haber> haberList = new ArrayList<Haber>();
    private Haber haber;
    private Intent intent;
    private ListView listView;
    private HaberAdapter haberAdapter;
    private Database db;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_news);

        db = new Database(getApplicationContext());
        haberList = db.getAllHaber();

        if(!haberList.isEmpty()) {
            listView = (ListView) findViewById(R.id.savedNews_listView);
            haberAdapter = new HaberAdapter(this, haberList);
            listView.setAdapter(haberAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    haber = haberAdapter.getItem(position);
                    intent = new Intent(SavedNews.this, NewsDetails.class);

                    intent.putExtra("kayitli",true);
                    intent.putExtra("id",haber.getId());
                    intent.putExtra("baslik",haber.getBaslik());
                    intent.putExtra("resim",haber.getResim());
                    intent.putExtra("url",haber.getUrl());
                    startActivity(intent);
                }
            });

        }
    }

    private class Callback extends WebViewClient {  //HERE IS THE MAIN CHANGE.

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return (false);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_saved_news, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.itemHome:
                Intent i = new Intent(SavedNews.this, MainActivity.class);
                startActivity(i);
                return true;

            case R.id.itemStar:
                Intent iStar = new Intent(SavedNews.this, SavedNews.class);
                startActivity(iStar);
                return true;

            case R.id.itemSave:
                Intent iSave = new Intent(SavedNews.this, SavedNews.class);
                startActivity(iSave);
                return true;

            case R.id.itemSearch:

                return true;

            case R.id.itemHelp:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Yardım menüsü yakın zamanda gelecektir.\nSabrınız için teşekkürler")
                        .setPositiveButton("Öyle Olsun", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                AlertDialog d = builder.create();
                d.show();
                return true;

            case R.id.itemAbout:
                AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                builder2.setMessage("Proje Ekibi:\nYılmaz Alagöz\nÖmer Faruk Özertürk\nSaliha Yeşilyurt" +
                        "\nHabib Salik\nHabibe Yorulmaz")
                        .setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                AlertDialog d2 = builder2.create();
                d2.show();
                return true;

            case R.id.action_settings:
                //openSettings();
                return true;

            case R.id.itemExit:
                System.exit(0);

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
