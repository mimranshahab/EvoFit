package edu.aku.akuh_health_first.views;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.ctrlplusz.anytextview.Util;

public class AnyTextView extends AppCompatTextView {



    public AnyTextView(Context context) {
        super(context);
    }

    public AnyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Util.setTypeface(attrs, this);
    }

    public AnyTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        Util.setTypeface(attrs, this);
    }



    public String getStringTrimmed(){
        return  getText().toString().trim() ;
    }
}
