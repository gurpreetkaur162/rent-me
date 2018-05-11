package com.example.gs.rentme;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.gs.rentme.data_model.Product_detail;
import com.example.gs.rentme.data_model.createaccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class RentFragment extends Fragment {
    String sdescription="no Description", sname, sloc, sprice, squantity, srentType;
    EditText  description,name, loc, price, quantity;

    Spinner category_spinner ;
    RadioGroup renttype;
    Button submit;
    public RentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v =inflater.inflate(R.layout.fragment_rent, container, false);
        name=v.findViewById(R.id.product_name);

        description =v.findViewById(R.id.product_description);

        category_spinner = v.findViewById(R.id.category_spinner);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.categories_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        category_spinner.setAdapter(adapter);

        loc=v.findViewById(R.id.product_loc);
        price=v.findViewById(R.id.price);
        quantity=v.findViewById(R.id.product_quantity);
        renttype=v.findViewById(R.id.rent);
        submit=v.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sname = name.getText().toString();
                sdescription = description.getText().toString();
                sloc = loc.getText().toString();
                srentType =((RadioButton)v.findViewById(renttype.getCheckedRadioButtonId())).getText().toString();
                sprice = price.getText().toString();
                squantity = quantity.getText().toString();
                if (sname.length() >= 3 ) {

                } else {
                    name.setError("Enter name");
                    return;
                }
                if (srentType.length() >= 3 ) {

                } else {
                    name.setError("Select rent type");
                    return;
                }
                if (squantity.length() >= 1 ) {

                } else {
                    name.setError("Enter Quantity");
                    return;
                }
                if (price.length() >= 2) {

                } else {
                    Toast.makeText(getActivity(), "Enter price", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (sloc.length() >= 3) {

                } else {
                    Toast.makeText(getActivity(), "Enter Location", Toast.LENGTH_SHORT).show();
                    return;
                }

                String category = category_spinner.getSelectedItem().toString() ;

                final ProgressDialog progress_bar = new ProgressDialog(getActivity() );
                progress_bar.setTitle("please wait");
                progress_bar.setMessage("Adding product");
                progress_bar.show();

                final FirebaseAuth f_auth = FirebaseAuth.getInstance();



                          Product_detail data = new Product_detail( sname, sloc,  sprice, squantity, srentType , sdescription , category , "yes" );

                          Intent i = new Intent(getActivity(), selectoption.class);

                            i.putExtra("data" , data);


                            startActivity(i);

                            getActivity().finish();
            }
        });
        return v;
    }




}
