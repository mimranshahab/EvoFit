package edu.aku.akuh_health_first.helperclasses.validator;

import android.widget.EditText;

import com.andreabaccega.formedittextvalidator.Validator;

/**
 * Created by khanhamza on 08-Mar-17.
 */

public class CardNumberValidation extends Validator {

    private EditText edtConfirmPassword;

    public CardNumberValidation() {
        super("CNIC number should contain 12 digits");
    }


    @Override
    public boolean isValid(EditText et) {
        return et.getText().toString().length() == 14;
    }


}
