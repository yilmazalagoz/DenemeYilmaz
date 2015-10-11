package com.EmpatikAndroid;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class NewsList extends AppCompatActivity {
    private Button buttonGeriGit;
    private List<Haber> haberList = new ArrayList<Haber>();
    private Haber haber;
    private Intent intent;
    private ListView listView;
    private HaberAdapter haberAdapter;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);

        if(!haberList.isEmpty())
        haberList.clear();

        if(!MainActivity.addedListKategori.isEmpty()){

            for(int i=0; i<MainActivity.addedListKategori.size();i++){
                KategoriObject k = MainActivity.addedListKategori.get(i);

                if(k.getName().toString()=="Yasam"){
                    haber = new Haber();
                    haber.setBaslik("Yasam");
                    haber.setResim("one");
                    haber.setUrl("http://www.saglikozel.com/");
                    haberList.add(haber);
                }

                else if(k.getName().toString()=="Finans"){
                    haber = new Haber();
                    haber.setBaslik("Finans");
                    haber.setResim("two");
                    haber.setUrl("http://www.doviz.com/");
                    haberList.add(haber);
                }

                else if(k.getName().toString()=="Teknoloji"){
                    haber = new Haber();
                    haber.setBaslik("Teknoloji");
                    haber.setResim("three");
                    haber.setUrl("http://www.teknoloji-haber.net");
                    haberList.add(haber);
                }

                else if(k.getName().toString()=="Kültür-Sanat"){
                    haber = new Haber();
                    haber.setBaslik("Kültür-Sanat");
                    haber.setResim("four");
                    haber.setUrl("http://sanatonline.net/");
                    haberList.add(haber);
                }

                else if(k.getName().toString()=="Eğitim"){
                    haber = new Haber();
                    haber.setBaslik("Eğitim");
                    haber.setResim("five");
                    haber.setUrl("http://www.guncelegitim.com/");
                    haberList.add(haber);
                }
            }

            listView = (ListView) findViewById(R.id.newsList_listView);
            haberAdapter = new HaberAdapter(this, haberList );
            listView.setAdapter(haberAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    haber = haberAdapter.getItem(position);
                    intent = new Intent(NewsList.this, NewsDetails.class);

                    intent.putExtra("kayitli",false);
                    intent.putExtra("baslik",haber.getBaslik());
                    intent.putExtra("resim",haber.getResim());
                    intent.putExtra("url",haber.getUrl());
                    startActivity(intent);
                }
            });
        }


        buttonGeriGit = (Button) findViewById(R.id.buttonGeriGit);
        buttonGeriGit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NewsList.this, MainActivity.class);
                startActivity(i);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_news_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.itemHome:
                Intent i = new Intent(NewsList.this, MainActivity.class);
                startActivity(i);
                return true;

            case R.id.itemStar:
                Intent iStar = new Intent(NewsList.this, SavedNews.class);
                startActivity(iStar);
                return true;

            case R.id.itemSave:
                Intent iSave = new Intent(NewsList.this, SavedNews.class);
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
                AlertDialog.Builder builder3 = new AlertDialog.Builder(this);
                builder3.setMessage("Ayarlar menüsü yakın zamanda gelecektir.\nSabrınız için teşekkürler")
                        .setPositiveButton("Öyle Olsun", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                return true;

            case R.id.itemExit:
                System.exit(0);

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
