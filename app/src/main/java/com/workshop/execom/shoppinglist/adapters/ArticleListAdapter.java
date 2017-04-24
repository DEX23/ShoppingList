package com.workshop.execom.shoppinglist.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.workshop.execom.shoppinglist.R;
import com.workshop.execom.shoppinglist.db.ORMLightHelper;
import com.workshop.execom.shoppinglist.model.Articles;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by user on 24.4.2017.
 */

public class ArticleListAdapter extends BaseAdapter {
    private ORMLightHelper databaseHelper;
    private Context context;
    private ArrayList<Articles> articles;

    public ArticleListAdapter(Context context, ArrayList<Articles> articles){
        this.context = context;
        this.articles = articles;
    }

    @Override
    public int getCount() {
        return articles.size();
    }

    @Override
    public Object getItem(int position) {
        return articles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        TextView name = (TextView) convertView.findViewById(R.id.tv_articleName);
        name.setText(articles.get(position).getaName());

        TextView amount = (TextView) convertView.findViewById(R.id.tv_amount);
        amount.setText(articles.get(position).getaKolicina());

        TextView purchasedStatus = (TextView) convertView.findViewById(R.id.tv_purchasedOrNot);

        purchasedStatus.setText(articles.get(position).getPurchasedStatus());
        if (purchasedStatus.getText().toString().equals("Purchased")) {
            purchasedStatus.setText(R.string.purchased);
        } else {
            purchasedStatus.setText(R.string.not_purchased);
        }

        ImageButton deleteButton = (ImageButton) convertView.findViewById(R.id.ib_delete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                dialog.setTitle("Delete article?");
                dialog.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            getDatabaseHelper().getArticlesDao().delete(articles.remove(position));
                            notifyDataSetChanged();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        return convertView;
    }


    private ORMLightHelper getDatabaseHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(context, ORMLightHelper.class);
        }
        return databaseHelper;
    }

    public void updateAdapter(ArrayList<Articles> list) {
        this.articles = list;
        notifyDataSetChanged();
    }

    public void clear(){
        articles.clear();
    }

    public void totalClear(int Ime) {
        articles.remove(Ime);
    }
}
