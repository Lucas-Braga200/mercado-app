package br.unigran.mercado_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import br.unigran.mercado_app.fragments.ClientListFragment;

public class ClientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        ClientListFragment clientListFragment = new ClientListFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.clientFragment, clientListFragment);
        transaction.commit();
    }

    public void listScreen(View view) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.clientFragment, new ClientListFragment())
                .commit();
    }
}