package br.unigran.mercado_app.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.unigran.mercado_app.models.Product;

public class ProductDB {
    DBHelper db;
    private SQLiteDatabase connection;

    public ProductDB(DBHelper db) {
        this.db = db;
    }

    public void insert(Product product) {
        connection = db.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", product.getName());
        values.put("description", product.getDescription());
        values.put("price", product.getPrice());

        connection.insertOrThrow("product", null, values);
        connection.close();
    }

    public void list(List data) {
        data.clear();
        connection = db.getReadableDatabase();

        String names[] = {"id", "name", "description", "price"};
        Cursor query = connection.query(
                "product", names, null, null, null, null,
                null
        );

        while(query.moveToNext()) {
            Product product = new Product();
            product.setId(Integer.parseInt(query.getString(0)));
            product.setName(query.getString(1));
            product.setDescription(query.getString(2));
            product.setPrice(Float.parseFloat(query.getString(3)));

            data.add(product);
        }

        connection.close();
    }

    public void remove(Integer id) {
        connection = db.getWritableDatabase();
        connection.delete("product", "id=?", new String[]{ id+"" });
        connection.close();
    }
}
