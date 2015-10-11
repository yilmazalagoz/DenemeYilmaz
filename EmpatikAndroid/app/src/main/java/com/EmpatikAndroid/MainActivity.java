package com.EmpatikAndroid;

import android.app.AlertDialog;
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
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private Button buttonHaberSec;
    ArrayList<KategoriObject> listKategori;
    public static ArrayList<KategoriObject> addedListKategori = new ArrayList<KategoriObject>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, 1);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        addedListKategori.clear();
        listKategori = getListItemData();

        KategoriAdapter kategoriAdapter = new KategoriAdapter(MainActivity.this, listKategori);
        recyclerView.setAdapter(kategoriAdapter);


        buttonHaberSec = (Button) findViewById(R.id.buttonHaberSec);
        buttonHaberSec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, NewsList.class);
                startActivity(i);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.itemHome:
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                startActivity(i);
                return true;

            case R.id.itemStar:
                Intent iStar = new Intent(MainActivity.this, SavedNews.class);
                startActivity(iStar);
                return true;

            case R.id.itemSave:
                Intent iSave = new Intent(MainActivity.this, SavedNews.class);
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

    public ArrayList<KategoriObject> getListItemData() {
        ArrayList<KategoriObject> listViewItems = new ArrayList<KategoriObject>();
        listViewItems.add(new KategoriObject("Yasam", R.drawable.one));
        listViewItems.add(new KategoriObject("Finans", R.drawable.two));
        listViewItems.add(new KategoriObject("Teknoloji", R.drawable.three));
        listViewItems.add(new KategoriObject("Kültür-Sanat", R.drawable.four));
        listViewItems.add(new KategoriObject("Eğitim", R.drawable.five));
        /*
        listViewItems.add(new KategoriObject("Yasam", R.drawable.one));
        listViewItems.add(new KategoriObject("Finans", R.drawable.two));
        listViewItems.add(new KategoriObject("Teknoloji", R.drawable.three));
        listViewItems.add(new KategoriObject("Kültür-Sanat", R.drawable.four));
        listViewItems.add(new KategoriObject("Eğtim", R.drawable.five));
        listViewItems.add(new KategoriObject("Yasam", R.drawable.one));
        listViewItems.add(new KategoriObject("Finans", R.drawable.two));
        listViewItems.add(new KategoriObject("Teknoloji", R.drawable.three));
        listViewItems.add(new KategoriObject("Kültür-Sanat", R.drawable.four));
        listViewItems.add(new KategoriObject("Eğtim", R.drawable.five));
        listViewItems.add(new KategoriObject("Yasam", R.drawable.one));
        listViewItems.add(new KategoriObject("Finans", R.drawable.two));
        listViewItems.add(new KategoriObject("Teknoloji", R.drawable.three));
        listViewItems.add(new KategoriObject("Kültür-Sanat", R.drawable.four));
        listViewItems.add(new KategoriObject("Eğtim", R.drawable.five));
        */
        return listViewItems;
    }
}
