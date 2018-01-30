package edu.aku.akuh_health_first.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.views.AnyTextView;

/**
 * Created by aqsa.sarwar on 1/30/2018.
 */

public class DropDownUserInfo extends LinearLayout {

    public DropDownUserInfo(Context context) {
        super(context);
        initLayout(context);
    }

    public DropDownUserInfo(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayout(context);
    }

    public DropDownUserInfo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayout(context);
    }


    private void initLayout(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.drop_down_user_info, this);

    }
}


