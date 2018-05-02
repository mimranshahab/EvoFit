package edu.aku.family_hifazat.fragments;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import edu.aku.family_hifazat.R;
import edu.aku.family_hifazat.fragments.abstracts.BaseFragment;
import edu.aku.family_hifazat.widget.AnyTextView;
import edu.aku.family_hifazat.widget.TitleBar;

/**
 * Created by aqsa.sarwar on 4/26/2018.
 */

public class GlucoseFragment extends BaseFragment {
    @BindView(R.id.txtRandomGlucose)
    AnyTextView txtRandomGlucose;
    @BindView(R.id.txtFastingGlucose)
    AnyTextView txtFastingGlucose;
    @BindView(R.id.txtSugarDDTM)
    AnyTextView txtSugarDDTM;
    @BindView(R.id.cardBloodGlucose)
    CardView cardBloodGlucose;
    @BindView(R.id.btnRecordMenually)
    AnyTextView btnRecordMenually;
    @BindView(R.id.txtGLUR)
    AnyTextView txtGLUR;
    @BindView(R.id.imgGLUFasting)
    ImageButton imgGLUFasting;
    @BindView(R.id.btnGLURandom)
    ImageButton imgGLURandom;
    @BindView(R.id.btnUpdate)
    AnyTextView btnUpdate;
    Unbinder unbinder;
    @BindView(R.id.txtFasting)
    AnyTextView txtFasting;
    @BindView(R.id.txtRandom)
    AnyTextView txtRandom;
    private boolean toggleButton;
    private boolean toggleGLUFasting;

    @Override
    public int getDrawerLockMode() {
        return DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
    }

    public static GlucoseFragment newInstance() {

        Bundle args = new Bundle();

        GlucoseFragment fragment = new GlucoseFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_blood_glucose;
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setVisibility(View.VISIBLE);
        titleBar.setTitle("Health Summary");
        titleBar.showHome(getBaseActivity());
        titleBar.showBackButton(getBaseActivity());
        titleBar.setUserDisplay(sharedPreferenceManager.getCurrentUser(), getContext());
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

    @OnClick({R.id.imgGLUFasting, R.id.btnGLURandom, R.id.btnUpdate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgGLUFasting:

                if (getTogglefasting()) {
                    setToggleFasting(false);
                    imgGLUFasting.setImageResource(R.drawable.gluco_fast_unselected);
                    txtFasting.setTextColor(getResources().getColor(R.color.c_light_grey));

                } else {
                    setToggleFasting(true);
                    imgGLUFasting.setImageResource(R.drawable.gluco_fast_selected);
                    txtFasting.setTextColor(getResources().getColor(R.color.base_reddish));
                    imgGLURandom.setImageResource(R.drawable.gluco_random_unselected);
                    txtRandom.setTextColor(getResources().getColor(R.color.c_light_grey));
                    txtGLUR.setText("Glucose Fasting (mg/dl)");
                }




                break;
            case R.id.btnGLURandom:


                if (getTogglefasting()) {
                    setToggleFasting(false);
                    imgGLURandom.setImageResource(R.drawable.gluco_random_unselected);
                    txtRandom.setTextColor(getResources().getColor(R.color.c_light_grey));

                } else {
                    setToggleFasting(true);
                    imgGLURandom.setImageResource(R.drawable.gluco_random_selected);
                    txtRandom.setTextColor(getResources().getColor(R.color.base_reddish));
                    imgGLUFasting.setImageResource(R.drawable.gluco_fast_unselected);
                    txtFasting.setTextColor(getResources().getColor(R.color.c_light_grey));
                    txtGLUR.setText("Glucose Random (mg/dl)");
                }

                break;
            case R.id.btnUpdate:
                break;
        }
    }

    public void setToggleFasting(boolean toggleButton) {
        this.toggleButton = toggleButton;
    }

    public boolean getTogglefasting() {
        return toggleGLUFasting;
    }
}
