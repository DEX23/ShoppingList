package com.workshop.execom.shoppinglist.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.workshop.execom.shoppinglist.R;
import com.workshop.execom.shoppinglist.model.ShoppingLists;

import java.util.ArrayList;

/**
 * Created by user on 19.4.2017.
 */

public class MainListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ShoppingLists> shoppingLists;

    public MainListAdapter(Context context, ArrayList<ShoppingLists> shoppingLists) {
        this.context = context;
        this.shoppingLists = shoppingLists;
    }

    @Override
    public int getCount() {
        return shoppingLists.size();
    }
    @Override
    public Object getItem(int position) {
        return shoppingLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.adapter_main_list,parent,false);
        }

        TextView nameOfList = (TextView)convertView.findViewById(R.id.tv_listName );
        nameOfList.setText(shoppingLists.get(position).getSlName());

        TextView complete = (TextView)convertView.findViewById(R.id.tv_mainCompleted);
        complete.setText(shoppingLists.get(position).getComplete());

        TextView protect = (TextView)convertView.findViewById(R.id.tv_protected);
        protect.setText(shoppingLists.get(position).getProtect());

        return convertView;
    }

    public void updateAdapter(ArrayList<ShoppingLists> list) {
        this.shoppingLists=list;
        notifyDataSetChanged();
    }

}
