package com.workshop.execom.shoppinglist.activities;

import android.app.Dialog;
import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.workshop.execom.shoppinglist.R;
import com.workshop.execom.shoppinglist.adapters.ArticleListAdapter;
import com.workshop.execom.shoppinglist.adapters.MainListAdapter;
import com.workshop.execom.shoppinglist.db.ORMLightHelper;
import com.workshop.execom.shoppinglist.model.Articles;
import com.workshop.execom.shoppinglist.model.ShoppingLists;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;

public class SecondActivity extends AppCompatActivity {

    private ORMLightHelper databaseHelper;
    private List<ShoppingLists> sl;
    private ShoppingLists shoppingLists;
    private int key;
    private ListView listView;
    private Toolbar toolbar;
    private Articles aAdd = new Articles();
    private Articles articles;
    private ArticleListAdapter articleListAdapter;
    public static String DETAIL_KEY ="DETAIL_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        key = getIntent().getExtras().getInt(MainActivity.MAIN_KEY);

        try {
            shoppingLists = getDatabaseHelper().getShoppingListsDao().queryForId(key);

            final TextView listName = (TextView) findViewById(R.id.tv_nameListInActivittSecond);
            listName.setText(shoppingLists.getSlName());

//            list = getDatabaseHelper().getArticlesDao().queryBuilder().
//                    where().eq(Articles.TABLE_NAME_USERS, shoppingLists.getSlId()).query();
//
//            listView = (ListView) findViewById(R.id.lv_listItem);
//            final ArticleListAdapter adapter = new ArticleListAdapter(SecondActivity.this, (ArrayList<Articles>) list);
        }catch (java.sql.SQLException e) {
            e.printStackTrace();g
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:

                final Dialog dialog = new Dialog(SecondActivity.this);
                dialog.setTitle("Osnovni podaci o artiklu");
                dialog.setContentView(R.layout.dialog_article);

                Button ok = (Button)dialog.findViewById(R.id.ok);

                ok.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        EditText ime = (EditText)dialog.findViewById(R.id.et_ime);
                        EditText kolicina = (EditText)dialog.findViewById(R.id.et_kolicina);

                        Articles a = new Articles();
                        aAdd.setaName(ime.getText().toString());
                        aAdd.setaKolicina(kolicina.getText().toString());
                        if (aAdd.getaName().equals("") && aAdd.getaKolicina().equals("")) {
                            Toast.makeText(SecondActivity.this, "Unesite ime i kolicinu", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        aAdd.setaUser(shoppingLists);
                        try {
                            getDatabaseHelper().getArticlesDao().create(aAdd);
                            refresh();
                            Log.i("Sta je u bazi ", getDatabaseHelper().getArticlesDao().queryForAll().toString());
                        }catch (SQLException e) {
                            e.printStackTrace();
                        }

                        dialog.dismiss();
                    }
                });
                final Button cancel = (Button) dialog.findViewById(R.id.cancel);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        refresh();
        refreshMainList();
        try {
            Log.i("Baza Artical List", getDatabaseHelper().getShoppingListsDao().queryForAll().toString());
            getDatabaseHelper().getShoppingListsDao().update(shoppingLists);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        super.onResume();
    }

    private void refresh() {
        ListView listItem = (ListView) findViewById(R.id.lv_listItem);

        if (listItem != null) {
            ArticleListAdapter adapter = (ArticleListAdapter) listItem.getAdapter();
            if (adapter != null) {
                try {
                    adapter.clear();
                    List<Articles> list = getDatabaseHelper().getArticlesDao().queryBuilder()
                            .where()
                            .eq(Articles.TABLE_NAME_USERS, shoppingLists.getSlId())
                            .query();
                    adapter.updateAdapter((ArrayList<Articles>) list);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void refreshMainList() {
        ListView mainList = (ListView) findViewById(R.id.lv_list);

        if (mainList != null) {
            MainListAdapter adapter = (MainListAdapter) mainList.getAdapter();
            if (adapter != null) {
                try {
                    adapter.clear();
                    List<ShoppingLists> list = getDatabaseHelper().getShoppingListsDao().queryForAll();
                    adapter.updateAdapter((ArrayList<ShoppingLists>) list);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public ORMLightHelper getDatabaseHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(this, ORMLightHelper.class);
        }
        return databaseHelper;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }

    @Override
    protected void onPause() {
        refresh();
        refreshMainList();
        super.onPause();
    }
}
