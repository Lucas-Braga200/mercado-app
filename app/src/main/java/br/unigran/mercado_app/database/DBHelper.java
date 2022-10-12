package br.unigran.mercado_app.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context content) {
        super(content, "MercadoApp", null,  1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "create table client(" +
                        "id integer primary key autoincrement," +
                        "name varchar(120));"
        );

        sqLiteDatabase.execSQL(
                "create table product(" +
                        "id integer primary key autoincrement," +
                        "name varchar(120)," +
                        "description varchar," +
                        "price real);"
        );

        sqLiteDatabase.execSQL(
                "create table supplier(" +
                        "id integer primary key autoincrement," +
                        "name varchar(120)," +
                        "birth_date varchar(10)," +
                        "address varchar(200)," +
                        "phone varchar(16));"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
