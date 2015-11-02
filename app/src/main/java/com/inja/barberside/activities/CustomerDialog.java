package com.inja.barberside.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.inja.barberside.R;
import com.inja.barberside.provider.barber.BarberCursor;
import com.inja.barberside.provider.barber.BarberSelection;
import com.inja.barberside.provider.customer.CustomerColumns;
import com.inja.barberside.provider.customer.CustomerContentValues;

import java.util.Date;


/**
 * This main
 */
public class CustomerDialog extends DialogFragment implements AdapterView.OnItemSelectedListener {

    private MainActivity parent;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }

    @Override
    public void onAttach(Activity activity) {
        parent = (MainActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View inflated = inflater.inflate(R.layout.dialog_customer, null);
        Spinner spinner = (Spinner) inflated.findViewById(R.id.barber_spinner);
        spinner.setOnItemSelectedListener(this);
        //spinner.setOnItemClickListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.barbers_list, android.R.layout.simple_spinner_dropdown_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        final TextView phoneNumber = (TextView) inflated.findViewById(R.id.input_phone);
        phoneNumber.addTextChangedListener(new PhoneNumberTextWatcher());
        phoneNumber.setFilters(new InputFilter[]{new PhoneNumberFilter(), new InputFilter.LengthFilter(12)});
        builder.setView(inflated);
        builder.setPositiveButton("ADD ME", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (validateInput()) {
                    final CustomerContentValues customerContentValues = new CustomerContentValues();
                    TextView textView = (TextView) inflated.findViewById(R.id.input_name);
                    customerContentValues.putName(textView.getText().toString());
                    Spinner spinner = (Spinner) inflated.findViewById(R.id.barber_spinner);
                    BarberSelection barberSelection = new BarberSelection();
                    barberSelection.nameLike(spinner.getSelectedItem().toString());
                    BarberCursor barberCursor = barberSelection.query(getActivity().getContentResolver());
                    barberCursor.moveToFirst();
                    customerContentValues.putBarber(barberCursor.getId());
                    customerContentValues.putSigned(new Date().getTime());
                    TextView phone = (TextView) inflated.findViewById(R.id.input_phone);
                    customerContentValues.putPhone(Long.valueOf(phone.getText().toString().replace("-", "")));
                    new AlertDialog.Builder(getActivity())
                            .setTitle("Confirmation")
                            .setMessage(textView.getText().toString() + " with " + spinner.getSelectedItem().toString() + " at number " + phone.getText().toString())
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {
                                    parent.getContentResolver().insert(CustomerColumns.CONTENT_URI, customerContentValues.values());
                                }
                            })
                            .setNegativeButton(android.R.string.no, null).show();
                }
                else{
                    //ERROR CHECK.
                }
            }

            private boolean validateInput() {
                return true;
            }
        });
        return builder.create();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
