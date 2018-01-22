package edu.aku.akuh_health_first.helperclasses.ui.helper;

import android.content.Context;
import android.content.res.ColorStateList;
import android.media.Image;
import android.support.v4.view.GravityCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ctrlplusz.anytextview.AnyEditTextView;
import com.ctrlplusz.anytextview.AnyTextView;

import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.activities.BaseActivity;

import butterknife.BindView;

/**
 * Created by khanhamza on 02-Mar-17.
 */

public class TitleBar extends RelativeLayout {

    @BindView(R.id.txtCircle)
    public TextView txtCircle;


    private ImageView imgTitle;

    private AnyTextView txtTitle;
    private ImageButton btnLeft1;
    private ImageButton btnRight3;
    private ImageButton btnRight2;
    //change Right button
    public ImageView btnRight1;


    private AnyEditTextView edtSearchField;
    private ImageButton btnLeftSearchField;
    private ImageButton btnRightSearchField;
    private TextView txtClearAll;

    private RelativeLayout containerTitlebar1;
    private RelativeLayout containerTitlebar2;


    public TitleBar(Context context) {
        super(context);
        initLayout(context);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayout(context);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayout(context);
    }


    private void initLayout(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.titlebar_main, this);
        bindViews();
        containerTitlebar2.setVisibility(GONE);
    }

    private void bindViews() {
        imgTitle = (ImageView) findViewById(R.id.imgTitle);
        txtTitle = (AnyTextView) findViewById(R.id.txtTitle);
        edtSearchField = (AnyEditTextView) findViewById(R.id.edtSearchField);
        btnLeft1 = (ImageButton) findViewById(R.id.btnLeft1);
        btnRight3 = (ImageButton) findViewById(R.id.btnRight3);
        btnRight2 = (ImageButton) findViewById(R.id.btnRight2);
        btnRight1 = (ImageView) findViewById(R.id.btnRight1);
        btnLeftSearchField = (ImageButton) findViewById(R.id.btnLeftSearchField);
        btnRightSearchField = (ImageButton) findViewById(R.id.btnRightSearchField);
        txtClearAll = (TextView) findViewById(R.id.txtClearAll);
        txtCircle = (TextView) findViewById(R.id.txtCircle);

        containerTitlebar1 = (RelativeLayout) findViewById(R.id.containerTitlebar1);
        containerTitlebar2 = (RelativeLayout) findViewById(R.id.containerTitlebar2);
    }

    public void resetViews() {
        imgTitle.setVisibility(GONE);
        edtSearchField.setVisibility(GONE);
        txtTitle.setVisibility(GONE);
        btnLeft1.setVisibility(GONE);
        btnRight3.setVisibility(GONE);
        btnRight2.setVisibility(GONE);
        btnRight1.setVisibility(GONE);
        txtClearAll.setVisibility(GONE);
        btnLeftSearchField.setVisibility(GONE);
        btnRightSearchField.setVisibility(GONE);
        containerTitlebar2.setVisibility(GONE);
        containerTitlebar1.setVisibility(VISIBLE);
        txtCircle.setVisibility(GONE);
    }

    public void setSearchField(final BaseActivity mActivity, TextView.OnEditorActionListener onEditorActionListener) {
        containerTitlebar1.setVisibility(GONE);
        containerTitlebar2.setVisibility(VISIBLE);
        edtSearchField.setVisibility(VISIBLE);
        edtSearchField.setOnEditorActionListener(onEditorActionListener);
    }

    public String getEdtSearchFieldText() {
        return edtSearchField.getText().toString();
    }


    public void closeSearchField(final BaseActivity mActivity) {
        containerTitlebar1.setVisibility(VISIBLE);

        containerTitlebar2.setVisibility(GONE);
        edtSearchField.setVisibility(GONE);
        if (mActivity != null) {
            mActivity.getSupportFragmentManager().popBackStack();
        }
    }

    public void setTitle(String title) {
        this.imgTitle.setVisibility(GONE);
        txtTitle.setVisibility(View.VISIBLE);
        txtTitle.setText(title);
    }


    public void showBackButton(final BaseActivity mActivity) {
        this.btnLeft1.setVisibility(VISIBLE);
        this.btnLeft1.setImageResource(R.drawable.imgback);
        btnLeft1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mActivity != null) {
//                    mActivity.getSupportFragmentManager().popBackStack();
                    mActivity.onBackPressed();
                }

            }
        });
    }


    public void showTitleImage() {
        this.imgTitle.setVisibility(VISIBLE);
        this.txtTitle.setVisibility(GONE);
    }


    public void showSidebar(final BaseActivity mActivity) {
        this.btnLeft1.setVisibility(VISIBLE);
        this.btnLeft1.setImageResource(R.drawable.imgside_nav);
        btnLeft1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mActivity != null) {
                    mActivity.getDrawerLayout().openDrawer(GravityCompat.START);
                }

            }
        });
    }

    public void setTextButton(String text, OnClickListener onClickListener) {
        this.txtClearAll.setVisibility(VISIBLE);
        this.txtClearAll.setText(text);
        this.txtClearAll.setOnClickListener(onClickListener);
    }


    public void setRightButton(int drawable, OnClickListener onClickListener) {
        this.btnRight1.setVisibility(VISIBLE);
        this.btnRight1.setImageResource(drawable);
        this.btnRight1.setOnClickListener(onClickListener);
    }


    public void setRightButton(int drawable, OnClickListener onClickListener, int colorToTint) {
        this.btnRight1.setVisibility(VISIBLE);
        this.btnRight1.setImageResource(drawable);
        this.btnRight1.setColorFilter(colorToTint);
        this.btnRight1.setOnClickListener(onClickListener);
    }

    public void setTxtCircle(int numberOfItems) {
        if (numberOfItems > 0 && btnRight1.getVisibility() == VISIBLE) {
            txtCircle.setVisibility(VISIBLE);
            txtCircle.setText(String.valueOf(numberOfItems));
            if (numberOfItems > 99) {
                txtCircle.setText("99");
            }
        } else {
            txtCircle.setVisibility(GONE);
            txtCircle.setText("0");
        }
    }


    public void setRightButton3(int drawable, OnClickListener onClickListener) {
        this.btnRight3.setVisibility(VISIBLE);
        btnRight3.setImageResource(drawable);
        this.btnRight3.setOnClickListener(onClickListener);
    }

    public void setRightButton2(int drawable, OnClickListener onClickListener) {
        this.btnRight2.setVisibility(VISIBLE);
        btnRight2.setImageResource(drawable);
        this.btnRight2.setOnClickListener(onClickListener);
    }

    public void setLeftButtonSearchBar(int drawable, OnClickListener onClickListener) {
        this.btnLeftSearchField.setVisibility(VISIBLE);
        this.btnLeftSearchField.setImageResource(drawable);
        this.btnLeftSearchField.setOnClickListener(onClickListener);
    }

    public void setRightButtonSearchBar(int drawable, OnClickListener onClickListener) {
        this.btnRightSearchField.setVisibility(VISIBLE);
        this.btnRightSearchField.setImageResource(drawable);
        this.btnRightSearchField.setOnClickListener(onClickListener);
        edtSearchField.setText("");
    }


}
