package com.workshop.execom.shoppinglist.activities;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.workshop.execom.shoppinglist.R;

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


//    @Override
//    public boolean onOptionsItemSelected(final MenuItem) {
//        switch (item.getItemId()){
//            case R.id.add_actor:
//                final Dialog dialog =new Dialog(this);
//                dialog.setContentView(R.menu.detail_menu);
//
//        }
//    }
}
