package com.inja.barberside.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.inja.barberside.R;

/**
 * Created by nejasix on 10/21/15.
 */
public class KeypadDialog extends DialogFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View inflated = inflater.inflate(R.layout.dialog_keypad, null);
        View keypad = inflated.findViewById(R.id.digital_pad);
        final EditText password = (EditText) inflated.findViewById(R.id.password_text);
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if ( s.length() == 4 ){
                    String password = s.toString();
                    KeypadDialog.this.dismiss();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        keypad.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_UP:
                        //Log.d("THIS",v.getWidth()+" "+v.getHeight());
                        //Log.d("THIS",event.getX()+" "+event.getY());
                        float currentX = event.getX();
                        float currentY = event.getY();
                        if ( currentX <= v.getWidth()/3 )
                        {
                            if ( currentY <= v.getHeight()/ 4)
                            {
                                password.append("1");
                            }
                            else if (currentY <= (2*v.getHeight())/4 && currentY > v.getHeight()/4)
                            {
                                password.append("4");
                            }
                            else if ( currentY >= v.getHeight()/2 && currentY < (3*v.getHeight())/4)
                            {
                                password.append("7");
                            }

                        }
                        else if ( currentX <= (2*v.getWidth())/3 && currentX > v.getWidth()/3)
                        {
                            if ( currentY <= v.getHeight()/ 4)
                            {
                                password.append("2");
                            }
                            else if (currentY <= (2*v.getHeight())/4 && currentY > v.getHeight()/4)
                            {
                                password.append("5");
                            }
                            else if ( currentY >= v.getHeight()/2 && currentY < (3*v.getHeight())/4)
                            {
                                password.append("8");
                            }
                            else if ( currentY >= (3*v.getHeight())/4)
                            {
                                password.append("0");
                            }

                        }
                        else if( currentX > (2*v.getWidth())/3 )
                        {
                            if ( currentY <= v.getHeight()/ 4)
                            {
                                password.append("3");
                            }
                            else if (currentY <= (2*v.getHeight())/4 && currentY > v.getHeight()/4)
                            {
                                password.append("6");
                            }
                            else if ( currentY >= v.getHeight()/2 && currentY < (3*v.getHeight())/4)
                            {
                                password.append("9");
                            }

                        }
                        break;

                }
                return true;
            }
        });
        builder.setView(inflated);
        return builder.create();

    }
}
