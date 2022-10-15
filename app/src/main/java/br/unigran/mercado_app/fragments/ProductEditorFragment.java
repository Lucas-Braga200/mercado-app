package br.unigran.mercado_app.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import br.unigran.mercado_app.R;
import br.unigran.mercado_app.database.DBHelper;
import br.unigran.mercado_app.database.ProductDB;
import br.unigran.mercado_app.models.Client;
import br.unigran.mercado_app.models.Product;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductEditorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductEditorFragment extends Fragment {
    TextView nameTextView;
    TextView descriptionTextView;
    TextView priceTextView;

    Button submitButton;

    DBHelper db;
    ProductDB productDB;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProductEditorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductEditorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductEditorFragment newInstance(String param1, String param2) {
        ProductEditorFragment fragment = new ProductEditorFragment();
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
        Product product = new Product();

        product.setName(nameTextView.getText().toString());
        product.setDescription(descriptionTextView.getText().toString());
        product.setPrice(Float.valueOf(priceTextView.getText().toString()));

        productDB.insert(product);

        // Toaster message
        Toast.makeText(getActivity(), "Produto criado com sucesso!!!", Toast.LENGTH_SHORT)
                .show();

        // Redirect to list fragment
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.productFragment, new ProductListFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_editor, container, false);

        db = new DBHelper(getActivity());
        productDB = new ProductDB(db);

        nameTextView = view.findViewById(R.id.productNameEditText);
        descriptionTextView = view.findViewById(R.id.productDescriptionEditText);
        priceTextView = view.findViewById(R.id.productPriceEditText);

        submitButton = view.findViewById(R.id.productSubmitButton);

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