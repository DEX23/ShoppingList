package com.workshop.execom.shoppinglist.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import static com.workshop.execom.shoppinglist.model.ShoppingLists.FIELD_NAME_ID;
import static com.workshop.execom.shoppinglist.model.ShoppingLists.TABLE_ARTICLE_NAME;

/**
 * Created by user on 17.4.2017.
 */
@DatabaseTable (tableName = ShoppingLists.TABLE_NAME_USERS)
public class ShoppingLists {
    public static final String TABLE_NAME_USERS = "shopping lists";
    public static final String FIELD_NAME_ID = "id";
    public static final String TABLE_ARTICLE_NAME = "name";
    public static final String TABLE_ARTICLE_ARTICLES = "articles";

    @DatabaseField(columnName = FIELD_NAME_ID, generatedId = true)
    private int slId;

    @DatabaseField(columnName = TABLE_ARTICLE_NAME)
    private String slName;

    @ForeignCollectionField(columnName = ShoppingLists.TABLE_ARTICLE_ARTICLES, eager = true)
    private ForeignCollection<Articles> articles;

    public ShoppingLists() {
    }

    public int getSlId() {
        return slId;
    }

    public String getSlName() {
        return slName;
    }

    public ForeignCollection<Articles> getArticles() {
        return articles;
    }

    public void setSlId(int slId) {
        this.slId = slId;
    }

    public void setSlName(String slName) {
        this.slName = slName;
    }

    public void setArticles(ForeignCollection<Articles> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        return "ShoppingLists{" +
                "slId=" + slId +
                ", slName='" + slName + '\'' +
                ", articles=" + articles +
                '}';
    }
}
