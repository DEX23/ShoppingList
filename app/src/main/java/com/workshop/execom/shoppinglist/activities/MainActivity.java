package com.workshop.execom.shoppinglist.activities;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.workshop.execom.shoppinglist.R;
import com.workshop.execom.shoppinglist.model.Articles;
import com.workshop.execom.shoppinglist.model.ShoppingLists;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

     @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:

                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setTitle("Unesi ime nove shoping liste");
                dialog.setContentView(R.layout.dialog_shopping_list);

                Button ok = (Button)dialog.findViewById(R.id.ok);

                ok.setOnClickListener(new View.OnClickListener(){
                @Override
                        public void onClick(View v) {
                    EditText ime = (EditText)dialog.findViewById(R.id.et_ime);
//                    EditText kolicina = (EditText)dialog.findViewById(R.id.et_kolicina);

                    ShoppingLists sl = new ShoppingLists();
                    sl.setSlName(ime.getText().toString());
//                    a.setKolicina(kolicina.getText().toString());

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
}
