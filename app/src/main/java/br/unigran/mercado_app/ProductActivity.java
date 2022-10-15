package br.unigran.mercado_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.unigran.mercado_app.fragments.ClientEditorFragment;
import br.unigran.mercado_app.fragments.ClientListFragment;
import br.unigran.mercado_app.fragments.ProductEditorFragment;
import br.unigran.mercado_app.fragments.ProductListFragment;

public class ProductActivity extends AppCompatActivity {
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        backButton = findViewById(R.id.productBackButton);

        ProductListFragment productListFragment = new ProductListFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.productFragment, productListFragment);
        transaction.commit();
    }

    public void backActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void listScreen(View view) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.productFragment, new ProductListFragment())
                .commit();
    }

    public void editorScreen(View view) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.productFragment, new ProductEditorFragment())
                .commit();
    }
}