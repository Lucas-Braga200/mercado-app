package br.unigran.mercado_app.fragments;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import br.unigran.mercado_app.R;
import br.unigran.mercado_app.database.ClientDB;
import br.unigran.mercado_app.database.DBHelper;
import br.unigran.mercado_app.models.Client;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ClientEditorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClientEditorFragment extends Fragment {
    TextView nameTextView;

    Button submitButton;

    DBHelper db;
    ClientDB clientDB;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ClientEditorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ClientEditorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ClientEditorFragment newInstance(String param1, String param2) {
        ClientEditorFragment fragment = new ClientEditorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public void submitClient() {
        Client client = new Client();

        client.setName(nameTextView.getText().toString());

        clientDB.insert(client);

        // Toaster message
        Toast.makeText(getActivity(), "Cliente criado com sucesso!!!", Toast.LENGTH_SHORT)
                .show();

        // Redirect to list fragment
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.clientFragment, new ClientListFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_client_editor, container, false);

        db = new DBHelper(getActivity());
        clientDB = new ClientDB(db);

        nameTextView = view.findViewById(R.id.nameEditText);

        submitButton = view.findViewById(R.id.submitButton);

        // Set On click events
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitClient();
            }
        });

        return view;
    }
}