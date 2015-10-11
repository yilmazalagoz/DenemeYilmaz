package com.EmpatikAndroid;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class NewsDetails extends AppCompatActivity {

    private WebView webView;
    private ImageButton imgDelete,imgStar,imgSave,imgShare;
    private String baslik, resim, url;
    private Database db;
    private Haber haber;
    private Bundle bundle;
    private Boolean kayitliMi;
    private Intent intent;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        db = new Database(getApplicationContext());
        haber = new Haber();
        imgDelete = (ImageButton) findViewById(R.id.imageButtonDelete);
        imgStar = (ImageButton) findViewById(R.id.imageButtonStar);
        imgSave = (ImageButton) findViewById(R.id.imageButtonSave);
        imgShare = (ImageButton) findViewById(R.id.imageButtonShare);
        webView = (WebView) findViewById(R.id.webViewDetails);

        kayitliMi = getIntent().getBooleanExtra("kayitli", false);
        if(kayitliMi)
            id = getIntent().getIntExtra("id",0);

        baslik = getIntent().getStringExtra("baslik");
        resim = getIntent().getStringExtra("resim");
        url = getIntent().getStringExtra("url");

        if(!kayitliMi) imgDelete.setVisibility(View.INVISIBLE);
        if(kayitliMi) imgSave.setVisibility(View.INVISIBLE);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new Callback());
        webView.loadUrl(url);

        if(kayitliMi) {

            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        haber = db.getHaber(id);
                        db.haberSil(haber);
                        Toast.makeText(v.getContext(), "Haber silindi.", Toast.LENGTH_SHORT).show();

                        intent = new Intent(NewsDetails.this, SavedNews.class);
                        startActivity(intent);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        imgStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Toast.makeText(v.getContext(), "Haber favorilere alındı.", Toast.LENGTH_SHORT).show();

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        if(!kayitliMi) {
            imgSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Toast.makeText(v.getContext(), "Haber kaydedildi.", Toast.LENGTH_SHORT).show();

                        haber.setBaslik(baslik);
                        haber.setResim(resim);
                        haber.setUrl(url);
                        db.haberEkle(haber);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

        }
        imgShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Haber Paylaşıldı.", Toast.LENGTH_SHORT).show();
            }
        });
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
        getMenuInflater().inflate(R.menu.menu_news_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.itemHome:
                Intent i = new Intent(NewsDetails.this, MainActivity.class);
                startActivity(i);
                return true;

            case R.id.itemStar:
                Intent iStar = new Intent(NewsDetails.this, SavedNews.class);
                startActivity(iStar);
                return true;

            case R.id.itemSave:
                Intent iSave = new Intent(NewsDetails.this, SavedNews.class);
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
