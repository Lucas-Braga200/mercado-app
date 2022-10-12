package br.unigran.mercado_app.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.unigran.mercado_app.models.Client;

public class ClientDB {
    DBHelper db;
    private SQLiteDatabase connection;

    public ClientDB(DBHelper db) {
        this.db = db;
    }

    public void insert(Client client) {
        connection = db.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", client.getName());

        connection.insertOrThrow("client", null, values);
        connection.close();
    }

    public void list(List data) {
        data.clear();
        connection = db.getReadableDatabase();

        String names[] = {"id", "name"};
        Cursor query = connection.query(
            "client", names, null, null, null, null,
            null
        );

        while(query.moveToNext()) {
            Client client = new Client();
            client.setId(Integer.parseInt(query.getString(0)));
            client.setName(query.getString(1));

            data.add(client);
        }

        connection.close();
    }

    public void remove(Integer id) {
        connection = db.getWritableDatabase();
        connection.delete("client", "id=?", new String[]{ id+"" });
        connection.close();
    }
}
