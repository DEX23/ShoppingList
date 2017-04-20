package com.workshop.execom.shoppinglist.activities;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.workshop.execom.shoppinglist.R;
import com.workshop.execom.shoppinglist.adapters.MainListAdapter;
import com.workshop.execom.shoppinglist.db.ORMLightHelper;
import com.workshop.execom.shoppinglist.model.Articles;
import com.workshop.execom.shoppinglist.model.ShoppingLists;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ORMLightHelper databaseHelper;
    public static String MAIN_KEY = "MAIN_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    try {
        final List<ShoppingLists> slList = getDatabaseHelper().getShoppingListsDao().queryForAll();
        final ListView listView = (ListView) findViewById(R.id.lv_list);
        final MainListAdapter adapter = new MainListAdapter(MainActivity.this, (ArrayList<ShoppingLists>) slList);

        Button add = (Button) findViewById(R.id.btn_add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText listName = (EditText) findViewById(R.id.et_nameOfList);
                ShoppingLists s = new ShoppingLists();
                s.setSlName(listName.getText().toString());
                if (s.getSlName().equals("")) {
                    Toast.makeText(MainActivity.this, "Shopping list name", Toast.LENGTH_SHORT).show();
                    return;
                }
                listName.setText("");
                try {
                    getDatabaseHelper().getShoppingListsDao().create(s);
                    refresh();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ShoppingLists s = (ShoppingLists) listView.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra(MAIN_KEY, s.getSlId());
                startActivity(intent);
                }
            });
            }catch (SQLException e){
                e.printStackTrace();
            }

        }

//     @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.detail_menu, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.add:
//
//                final Dialog dialog = new Dialog(MainActivity.this);
//                dialog.setTitle("Unesi ime nove shoping liste");
//                dialog.setContentView(R.layout.dialog_shopping_list);
//
//                Button ok = (Button)dialog.findViewById(R.id.ok);
//
//                ok.setOnClickListener(new View.OnClickListener(){
//                @Override
//                        public void onClick(View v) {
//                    EditText ime = (EditText)dialog.findViewById(R.id.et_ime);
////                    EditText kolicina = (EditText)dialog.findViewById(R.id.et_kolicina);
//
//                    ShoppingLists sl = new ShoppingLists();
//                    sl.setSlName(ime.getText().toString());
////                    a.setKolicina(kolicina.getText().toString());
//
//                    dialog.dismiss();
//                }
//            });
//                final Button cancel = (Button) dialog.findViewById(R.id.cancel);
//                cancel.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dialog.dismiss();
//                    }
//                });
//                dialog.show();
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    protected void onResume() {
        refresh();
        super.onResume();
    }

    private void refresh() {
        ListView mainList =(ListView) findViewById(R.id.lv_list);

        if (mainList!=null){
            MainListAdapter adapter = (MainListAdapter) mainList.getAdapter();
            if(adapter!= null)
            {
                try {
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

        if(databaseHelper != null) {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }
}
