package br.unigran.mercado_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.unigran.mercado_app.fragments.ClientEditorFragment;
import br.unigran.mercado_app.fragments.ClientListFragment;

public class ClientActivity extends AppCompatActivity {
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        backButton = findViewById(R.id.backButton);

        ClientListFragment clientListFragment = new ClientListFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.clientFragment, clientListFragment);
        transaction.commit();
    }

    public void backActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void listScreen(View view) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.clientFragment, new ClientListFragment())
                .commit();
    }

    public void editorScreen(View view) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.clientFragment, new ClientEditorFragment())
                .commit();
    }
}