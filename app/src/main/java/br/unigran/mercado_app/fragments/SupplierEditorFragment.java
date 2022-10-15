package br.unigran.mercado_app.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import br.unigran.mercado_app.R;
import br.unigran.mercado_app.database.ClientDB;
import br.unigran.mercado_app.database.DBHelper;
import br.unigran.mercado_app.database.SupplierDB;
import br.unigran.mercado_app.models.Supplier;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SupplierEditorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SupplierEditorFragment extends Fragment {
    TextView nameTextView;
    TextView birthDateTextView;
    TextView addressTextView;
    TextView phoneTextView;

    Button submitButton;

    DBHelper db;
    SupplierDB supplierDB;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SupplierEditorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SupplierEditorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SupplierEditorFragment newInstance(String param1, String param2) {
        SupplierEditorFragment fragment = new SupplierEditorFragment();
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

    public void submitSupplier() {
        Supplier supplier = new Supplier();

        supplier.setName(nameTextView.getText().toString());
        supplier.setBirth_date(birthDateTextView.getText().toString());
        supplier.setPhone(phoneTextView.getText().toString());
        supplier.setAddress(addressTextView.getText().toString());

        supplierDB.insert(supplier);

        // Toaster message
        Toast.makeText(getActivity(), "Fornecedor criado com sucesso!!!", Toast.LENGTH_SHORT)
                .show();

        // Redirect to list fragment
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.supplierFragment, new SupplierListFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_supplier_editor, container, false);

        db = new DBHelper(getActivity());
        supplierDB = new SupplierDB(db);

        nameTextView = view.findViewById(R.id.supplierNameEditText);
        birthDateTextView = view.findViewById(R.id.supplierBirthDateEditText);
        addressTextView = view.findViewById(R.id.supplierAddressEditText);
        phoneTextView = view.findViewById(R.id.supplierPhoneEditText);

        submitButton = view.findViewById(R.id.supplierSubmitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitSupplier();
            }
        });

        return view;
    }
}