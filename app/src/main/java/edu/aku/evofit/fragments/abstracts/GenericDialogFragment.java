package edu.aku.evofit.fragments.abstracts;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import edu.aku.evofit.R;
import edu.aku.evofit.callbacks.GenericClickableInterface;
import edu.aku.evofit.widget.AnyTextView;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by khanhamza on 21-Feb-17.
 */

public class GenericDialogFragment extends DialogFragment implements View.OnClickListener {

    GenericClickableInterface genericClickableInterfaceBtn1;
    GenericClickableInterface genericClickableInterfaceBtn2;

    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";

    int VISIBILITY_BTN1 = GONE;
    int VISIBILITY_BTN2 = GONE;


    private String title;
    private String message;

    private AnyTextView btn1;
    private AnyTextView btn2;

    String btn1Caption = "";
    String btn2Caption = "";


    public TextView txtViewMessage;
    TextView txtViewTitle;
    ImageView imgSeperatorLine;


    public GenericDialogFragment() {
    }

    public static GenericDialogFragment newInstance() {
        GenericDialogFragment frag = new GenericDialogFragment();

        Bundle args = new Bundle();
//        args.putString(KEY_TITLE, title);
//        args.putString(KEY_MESSAGE,message);
        frag.setArguments(args);

        return frag;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        title = getArguments().getString(KEY_TITLE);
//        message = getArguments().getString(KEY_MESSAGE);

        return inflater.inflate(R.layout.fragment_generic_popup, container);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtViewMessage = (TextView) view.findViewById(R.id.txtMessage);
        txtViewTitle = (TextView) view.findViewById(R.id.txtTitle);
        imgSeperatorLine = (ImageView) view.findViewById(R.id.imgSeperatorLine);

        btn1 = (AnyTextView) view.findViewById(R.id.btnButton1);
        btn2 = (AnyTextView) view.findViewById(R.id.btnButton2);


        bindData(title, message);
        setListeners();


    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);

        // request a window without the title
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    private void setListeners() {
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }


    private void bindData(String title, String message) {

        if (title == null || title.isEmpty()) {
            txtViewTitle.setVisibility(GONE);
            imgSeperatorLine.setVisibility(GONE);
        } else {
            imgSeperatorLine.setVisibility(VISIBLE);
            txtViewTitle.setVisibility(VISIBLE);
            txtViewTitle.setText(title);
        }
        txtViewMessage.setText(message);

        btn1.setText(btn1Caption);
        btn1.setVisibility(VISIBILITY_BTN1);

        btn2.setText(btn2Caption);
        btn2.setVisibility(VISIBILITY_BTN2);
    }

    public void setButton1(String caption, GenericClickableInterface genericClickableInterface) {
        this.genericClickableInterfaceBtn1 = genericClickableInterface;
        btn1Caption = caption;
        if (caption == null) {
            VISIBILITY_BTN1 = GONE;
        } else {
            VISIBILITY_BTN1 = VISIBLE;
        }
    }


    public void setButton2(String caption, GenericClickableInterface genericClickableInterface) {
        this.genericClickableInterfaceBtn2 = genericClickableInterface;
        btn2Caption = caption;
        if (caption == null) {
            VISIBILITY_BTN2 = GONE;
        } else {
            VISIBILITY_BTN2 = VISIBLE;

        }
    }


    public void setButton1Visibility(int VISIBILITY_BTN1) {
        this.VISIBILITY_BTN1 = VISIBILITY_BTN1;
    }

    public void setButton2Visibility(int VISIBILITY_BTN2) {
        this.VISIBILITY_BTN2 = VISIBILITY_BTN2;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnButton1: {
                genericClickableInterfaceBtn1.click();
                break;
            }

            case R.id.btnButton2: {
                genericClickableInterfaceBtn2.click();
                break;
            }
        }
    }


}

