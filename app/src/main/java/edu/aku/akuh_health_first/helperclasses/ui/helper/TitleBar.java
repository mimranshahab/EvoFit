package edu.aku.akuh_health_first.helperclasses.ui.helper;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.support.v4.view.GravityCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ctrlplusz.anytextview.AnyTextView;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.activities.BaseActivity;
import edu.aku.akuh_health_first.activities.HomeActivity;

/**
 * Created by khanhamza on 02-Mar-17.
 */

public class TitleBar extends RelativeLayout {

    public TextView txtCircle;
    CircleImageView circleImageView;
    edu.aku.akuh_health_first.views.AnyTextView txtUserName;
    edu.aku.akuh_health_first.views.AnyTextView txtMRN;
    RelativeLayout contDropDown;


    private ImageView imgTitle;

    private AnyTextView txtTitle;
    private ImageButton btnLeft1;
    private ImageButton btnRight3;
    private ImageButton btnRight2;
    //change Right button
    public ImageView btnRight1;


    private TextView txtClearAll;

    private RelativeLayout containerTitlebar1;


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
    }

    private void bindViews() {
        imgTitle = (ImageView) findViewById(R.id.imgTitle);
        txtTitle = (AnyTextView) findViewById(R.id.txtTitle);
        btnLeft1 = (ImageButton) findViewById(R.id.btnLeft1);
        btnRight3 = (ImageButton) findViewById(R.id.btnRight3);
        btnRight2 = (ImageButton) findViewById(R.id.btnRight2);
        btnRight1 = (ImageView) findViewById(R.id.btnRight1);
        txtClearAll = (TextView) findViewById(R.id.txtClearAll);
        txtCircle = (TextView) findViewById(R.id.txtCircle);
        circleImageView = (CircleImageView) findViewById(R.id.circleImageView);
        containerTitlebar1 = (RelativeLayout) findViewById(R.id.containerTitlebar1);
        contDropDown = (RelativeLayout) findViewById(R.id.contDropDown);
        txtMRN = (edu.aku.akuh_health_first.views.AnyTextView) findViewById(R.id.txtMRN);
        txtUserName = (edu.aku.akuh_health_first.views.AnyTextView) findViewById(R.id.txtUserName);

    }

    public void resetViews() {
        imgTitle.setVisibility(GONE);
        circleImageView.setVisibility(GONE);
        txtTitle.setVisibility(GONE);
        btnLeft1.setVisibility(GONE);
        btnRight3.setVisibility(GONE);
        btnRight2.setVisibility(GONE);
        btnRight1.setVisibility(INVISIBLE);
        txtClearAll.setVisibility(GONE);
        containerTitlebar1.setVisibility(VISIBLE);
        txtCircle.setVisibility(GONE);
        contDropDown.setVisibility(GONE);
    }

    public void setSearchField(final BaseActivity mActivity, TextView.OnEditorActionListener onEditorActionListener) {
        containerTitlebar1.setVisibility(GONE);
    }


    public void closeSearchField(final BaseActivity mActivity) {
        containerTitlebar1.setVisibility(VISIBLE);

        if (mActivity != null) {
            mActivity.getSupportFragmentManager().popBackStack();
        }
    }

    public void setTitle(String title) {
        this.imgTitle.setVisibility(GONE);
        txtTitle.setVisibility(View.VISIBLE);
        txtTitle.setText(title);
    }


    public void showBackButton(final Activity mActivity) {
        this.btnLeft1.setVisibility(VISIBLE);
        this.btnLeft1.setImageResource(R.drawable.ic_back);
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
        this.btnLeft1.setImageResource(R.drawable.menu_icon);
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

    public void setCircleImageView() {
        this.circleImageView.setVisibility(VISIBLE);
        this.circleImageView.setImageResource(R.drawable.user_image);
        this.circleImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                showAndHideDropDown();
            }
        });
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


    public void showHome(final BaseActivity activity) {
        this.btnRight2.setVisibility(VISIBLE);
        btnRight2.setImageResource(R.drawable.ic_action_home);
        this.btnRight2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (activity instanceof HomeActivity) {
                    activity.reload();
                } else {
                    activity.clearAllActivitiesExceptThis(HomeActivity.class);
                }
            }
        });
    }


    public void showAndHideDropDown() {
        int height = this.containerTitlebar1.getHeight();

        if (contDropDown.getVisibility() == View.VISIBLE) {

            contDropDown.animate()
                    .translationY(-height)
                    .setDuration(300)
                    .setListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            contDropDown.setVisibility(View.GONE);
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    })
                    .start();
        } else {
            contDropDown.setVisibility(View.VISIBLE);
            contDropDown.animate()
                    .translationY(height)
                    .setDuration(300)
                    .setListener(null)
                    .start();
        }

    }

}
