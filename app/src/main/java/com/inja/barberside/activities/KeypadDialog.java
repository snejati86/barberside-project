package com.inja.barberside.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.inja.barberside.R;
import com.inja.barberside.provider.barber.BarberCursor;
import com.inja.barberside.provider.barber.BarberSelection;

/**
 * Created by nejasix on 10/21/15.
 */
public class KeypadDialog extends DialogFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View inflated = inflater.inflate(R.layout.dialog_keypad, null);

        final EditText password = (EditText) inflated.findViewById(R.id.password_text);
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 4) {
                    BarberSelection barberSelection = new BarberSelection();
                    barberSelection.password(Integer.valueOf(s.toString()));
                    BarberCursor barberCursor = barberSelection.query(getActivity().getContentResolver());

                    if ( barberCursor.getCount()>0){
                        barberCursor.moveToFirst();
                        BarberList barberList = (BarberList) getActivity();
                        barberList.barberMode(barberCursor.getId());
                        Toast.makeText(getActivity(),"Found barber "+barberCursor.getName(),Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getActivity(),"Not found",Toast.LENGTH_SHORT).show();
                    }
                    KeypadDialog.this.dismiss();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        ImageButton imageButton1 = (ImageButton)inflated.findViewById(R.id.button_one);
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password.append("1");
            }
        });
        ImageButton imageButton2 = (ImageButton)inflated.findViewById(R.id.button_two);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password.append("2");
            }
        });
        ImageButton imageButton3 = (ImageButton)inflated.findViewById(R.id.button_three);
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password.append("3");
            }
        });
        ImageButton imageButton4 = (ImageButton)inflated.findViewById(R.id.button_four);
        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password.append("4");
            }
        });
        ImageButton imageButton5 = (ImageButton)inflated.findViewById(R.id.button_five);
        imageButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password.append("5");
            }
        });
        ImageButton imageButton6 = (ImageButton)inflated.findViewById(R.id.button_six);
        imageButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password.append("6");
            }
        });
        ImageButton imageButton7 = (ImageButton)inflated.findViewById(R.id.button_seven);
        imageButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password.append("7");
            }
        });
        ImageButton imageButton8 = (ImageButton)inflated.findViewById(R.id.button_eight);
        imageButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password.append("8");
            }
        });
        ImageButton imageButton9 = (ImageButton)inflated.findViewById(R.id.button_nine);
        imageButton9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password.append("9");
            }
        });
        ImageButton imageButton0 = (ImageButton)inflated.findViewById(R.id.button_zero);
        imageButton0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password.append("0");
            }
        });

        builder.setView(inflated);
        return builder.create();

    }
}
