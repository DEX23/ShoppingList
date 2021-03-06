package com.workshop.execom.shoppinglist.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.table.TableUtils;
import com.workshop.execom.shoppinglist.model.Articles;
import com.workshop.execom.shoppinglist.model.ShoppingLists;

import java.sql.SQLException;

/**
 * Created by user on 17.4.2017.
 */

public class ORMLightHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "shoppinglist.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<ShoppingLists, Integer> mShopingListsDao = null;
    private Dao<Articles, Integer> mArticlesDao = null;

    public ORMLightHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource){
        try{
            TableUtils.createTable(connectionSource, Articles.class);
            TableUtils.createTable(connectionSource, ShoppingLists.class);
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Articles.class, true);
            TableUtils.dropTable(connectionSource, ShoppingLists.class, true);
            onCreate(database,connectionSource);
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Dao<Articles, Integer> getArticlesDao() throws SQLException {
        if (mArticlesDao == null) {
            mArticlesDao = getDao(Articles.class);
        }
        return mArticlesDao;
    }

    public Dao<ShoppingLists, Integer> getShoppingListsDao() throws SQLException {
        if (mShopingListsDao == null) {
            mShopingListsDao = getDao(ShoppingLists.class);
        }
        return mShopingListsDao;
    }

    @Override
    public void close() {
        mArticlesDao = null;
        mShopingListsDao = null;

        super.close();
    }

}
