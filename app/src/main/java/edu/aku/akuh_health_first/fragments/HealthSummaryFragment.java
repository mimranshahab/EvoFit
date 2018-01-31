package edu.aku.akuh_health_first.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.fragments.abstracts.BaseFragment;
import edu.aku.akuh_health_first.helperclasses.Spanny;
import edu.aku.akuh_health_first.helperclasses.ui.helper.TitleBar;
import edu.aku.akuh_health_first.views.AnyTextView;
import edu.aku.akuh_health_first.views.CustomTypefaceSpan;

/**
 * Created by aqsa.sarwar on 1/26/2018.
 */

public class HealthSummaryFragment extends BaseFragment {
    @BindView(R.id.myTextProgressGreen)
    AnyTextView myTextProgressGreen;
    @BindView(R.id.myTextProgress)
    AnyTextView myTextProgress;
    @BindView(R.id.txtReport)
    AnyTextView txtReport;
    @BindView(R.id.txtReport1)
    AnyTextView txtReport1;
    @BindView(R.id.txtActiveMedication)
    AnyTextView txtActiveMedication;
    @BindView(R.id.txtLastVisit)
    AnyTextView txtLastVisit;
    Unbinder unbinder;
    Typeface regular, bold, light;

    public static HealthSummaryFragment newInstance() {

        Bundle args = new Bundle();

        HealthSummaryFragment fragment = new HealthSummaryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        regular = Typeface.createFromAsset(getResources().getAssets(), "fonts/Roboto-Regular_1B.ttf");
        bold = Typeface.createFromAsset(getResources().getAssets(), "fonts/Roboto-Medium_2.ttf");
        light = Typeface.createFromAsset(getResources().getAssets(), "fonts/Roboto-Light_D.ttf");

    }

    @Override
    public int getDrawerLockMode() {
        return DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_health_summary;
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setTitle("Health Summary");
        titleBar.showBackButton(getBaseActivity());
        titleBar.setCircleImageView( );
        titleBar.showHome(getBaseActivity());
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setSpannyText();
    }


    @Override
    public void setListeners() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void setSpannyText() {


        Spanny greenText = new Spanny("67.5kg",
                new CustomTypefaceSpan(bold),
                new ForegroundColorSpan(getResources().getColor(R.color.c_green)))
                .append("\nWeight",
                        new AbsoluteSizeSpan(getResources().getDimensionPixelSize(R.dimen.s14)),
                        new ForegroundColorSpan(getResources().getColor(R.color.text_color_grey)));
        Spanny redText = new Spanny("5+",
                new CustomTypefaceSpan(bold),
                new ForegroundColorSpan(getResources().getColor(R.color.base_reddish)))
                .append("\nBlood Type",
                        new AbsoluteSizeSpan(getResources().getDimensionPixelSize(R.dimen.s14)),
                        new ForegroundColorSpan(getResources().getColor(R.color.text_color_grey)));


        Spanny spanny = new Spanny("Glucose Random",
                new ForegroundColorSpan(getResources().getColor(R.color.text_color_grey))).append(" 302 mg/dl ",
                new ForegroundColorSpan(getResources().getColor(R.color.base_reddish))).append("\nMarch 04,2017 12:20",
                new ForegroundColorSpan(getResources().getColor(R.color.text_color_grey))).append(" (Panic High)",
                new ForegroundColorSpan(getResources().getColor(R.color.base_reddish))
        );

        Spanny spanny1 = new Spanny("Serum Creatinline ",
                new ForegroundColorSpan(getResources().getColor(R.color.text_color_grey))).append(" 1.1 mg/dl ",
                new ForegroundColorSpan(getResources().getColor(R.color.base_blue))).append("\nMarch 04,2017 12:20",
                new ForegroundColorSpan(getResources().getColor(R.color.text_color_grey))).append(" (Normal)",
                new ForegroundColorSpan(getResources().getColor(R.color.c_applegreen))
        );

        Spanny medi1 = new Spanny("Lipitor",
                new ForegroundColorSpan(getResources().getColor(R.color.text_color_grey)),
                new CustomTypefaceSpan(light)).append(" 10mg Oral", new CustomTypefaceSpan(bold))
                .append("\nTRASTUZUMAB ",
                        new ForegroundColorSpan(getResources().getColor(R.color.text_color_grey)),
                        new CustomTypefaceSpan(light)).append("440mg/20ml Oral", new CustomTypefaceSpan(bold))
                .append("\nTRASTUZUMAB ",
                        new ForegroundColorSpan(getResources().getColor(R.color.text_color_grey)),
                        new CustomTypefaceSpan(light)).append("440mg/20ml", new CustomTypefaceSpan(bold))
                .append("\nSodium Chloride ",
                        new ForegroundColorSpan(getResources().getColor(R.color.text_color_grey)),
                        new CustomTypefaceSpan(light)).append("440mg/20ml", new CustomTypefaceSpan(bold));

        Spanny lastVisit = new Spanny("Date: ",
                new ForegroundColorSpan(getResources().getColor(R.color.text_color_grey)),
                new CustomTypefaceSpan(light)).append(" Mar 04, 2017", new CustomTypefaceSpan(light))
                .append("\nLocation: ",
                        new ForegroundColorSpan(getResources().getColor(R.color.text_color_grey)),
                        new CustomTypefaceSpan(light)).
                        append("Stadium Road, Community Health Center", new CustomTypefaceSpan(light))
                .append("\nDoctor: ",
                        new ForegroundColorSpan(getResources().getColor(R.color.text_color_grey)),
                        new CustomTypefaceSpan(light)).append("Rabia Hassan Shaikh", new CustomTypefaceSpan(light));


        txtReport.setText(spanny);
        txtReport1.setText(spanny1);
        txtActiveMedication.setText(medi1);
        txtLastVisit.setText(lastVisit);
        myTextProgressGreen.setText(greenText);
        myTextProgress.setText(redText);


    }
}
