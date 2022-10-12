package br.unigran.mercado_app.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.unigran.mercado_app.models.Supplier;

public class SupplierDB {
    DBHelper db;
    private SQLiteDatabase connection;

    public SupplierDB(DBHelper db) {
        this.db = db;
    }

    public void insert(Supplier supplier) {
        connection = db.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", supplier.getName());
        values.put("birth_date", supplier.getBirth_date());
        values.put("address", supplier.getAddress());
        values.put("phone", supplier.getPhone());

        connection.insertOrThrow("supplier", null, values);
        connection.close();
    }

    public void list(List data) {
        data.clear();
        connection = db.getReadableDatabase();

        String names[] = {"id", "name", "birth_date", "address", "phone"};
        Cursor query = connection.query(
                "supplier", names, null, null, null, null,
                null
        );

        while(query.moveToNext()) {
            Supplier supplier = new Supplier();
            supplier.setId(Integer.parseInt(query.getString(0)));
            supplier.setName(query.getString(1));
            supplier.setBirth_date(query.getString(2));
            supplier.setAddress(query.getString(3));
            supplier.setPhone(query.getString(4));

            data.add(supplier);
        }

        connection.close();
    }

    public void remove(Integer id) {
        connection = db.getWritableDatabase();
        connection.delete("supplier", "id=?", new String[]{ id+"" });
        connection.close();
    }
}
