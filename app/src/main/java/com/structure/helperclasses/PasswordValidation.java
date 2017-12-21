package com.structure.helperclasses;

import android.widget.EditText;

import com.andreabaccega.formedittextvalidator.Validator;

/**
 * Created by khanhamza on 08-Mar-17.
 */

public class PasswordValidation extends Validator {

    private EditText edtConfirmPassword;

    public PasswordValidation() {
        super("Password must not be less than 6");
    }

    public PasswordValidation(EditText edtConfirmPassword) {
        super("Password not match");
        this.edtConfirmPassword = edtConfirmPassword;
    }

    @Override
    public boolean isValid(EditText et) {
        if (edtConfirmPassword != null) {
            return et.getText().toString().equals(edtConfirmPassword.getText().toString());
        } else {
            return et.getText().toString().length() >= 6;
        }
    }


}
