package com.workshop.execom.shoppinglist.model;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import static com.workshop.execom.shoppinglist.model.Articles.FIELD_NAME_KOLICINA;
import static com.workshop.execom.shoppinglist.model.Articles.FIELD_NAME_NAME;
import static com.workshop.execom.shoppinglist.model.Articles.FIELD_NAME_USER;

/**
 * Created by user on 13.4.2017.
 */
@DatabaseTable (tableName = Articles.TABLE_NAME_USERS)
public class Articles {

    public static final String TABLE_NAME_USERS = "articles";
    public static final String FIELD_NAME_ID = "id";
    public static final String FIELD_NAME_NAME = "ime artikla";
    public static final String FIELD_NAME_KOLICINA = "kolicina artikla";
    public static final String FIELD_NAME_USER = "user";

    @DatabaseField(columnName = FIELD_NAME_ID, generatedId = true)
    private int aId;

    @DatabaseField(columnName = FIELD_NAME_NAME)
    private String aName;

    @DatabaseField(columnName = FIELD_NAME_KOLICINA)
    private String aKolicina;

    @DatabaseField(columnName = FIELD_NAME_USER, foreign = true, foreignAutoRefresh = true)
    private ShoppingLists aUser;

    public Articles(){
    }

    public int getaId() {
        return aId;
    }

    public String getaName() {
        return aName;
    }

    public String getaKolicina() {
        return aKolicina;
    }

    public ShoppingLists getaUser() {
        return aUser;
    }

    public void setaId(int aId) {
        this.aId = aId;
    }

    public void setaName(String aName) {
        this.aName = aName;
    }

    public void setaKolicina(String aKolicina) {
        this.aKolicina = aKolicina;
    }

    public void setaUser(ShoppingLists aUser) {
        this.aUser = aUser;
    }

    @Override
    public String toString() {
        return "Articles{" +
                "aId=" + aId +
                ", aName='" + aName + '\'' +
                ", aKolicina='" + aKolicina + '\'' +
                ", aUser=" + aUser +
                '}';
    }
}

