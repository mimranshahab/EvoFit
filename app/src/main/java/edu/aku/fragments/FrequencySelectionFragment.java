package edu.aku.fragments;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.andreabaccega.widget.FormEditText;
import com.ctrlplusz.anytextview.AnyTextView;
import edu.aku.R;
import edu.aku.fragments.abstracts.BaseFragment;
import edu.aku.callbacks.SelectedFrequencyDataInterface;
import edu.aku.helperclasses.ui.helper.KeyboardHide;
import edu.aku.helperclasses.ui.helper.TitleBar;
import edu.aku.helperclasses.ui.helper.UIHelper;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.content.ContentValues.TAG;

/**
 * Created by shehrozmirza on 5/15/2017.
 */

public class FrequencySelectionFragment extends BaseFragment implements RadioGroup.OnCheckedChangeListener {


    boolean isFrequencySet = false, isSlotSet = false;
    int indexFrequency, indexSlot;
    public final static int ONE_TIME = 0;
    public final static int WEEKLY = 1;
    public final static int MONTHLY = 2;
    Unbinder unbinder;
    @BindView(R.id.rgTimeSelection)
    RadioGroup rgTimeSelection;
    @BindView(R.id.rgFrequencySelection)
    RadioGroup rgFrequencySelection;
    @BindView(R.id.edtAdditionalNotes)
    FormEditText edtAdditionalNotes;
    @BindView(R.id.btnDone)
    AnyTextView btnDone;
    SelectedFrequencyDataInterface selectedData;
    private String selectedDate = "0";
    private String deliveryTime;
    private int isInstant;
    private String currentHour;
    private int currentHourInt;


    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_frequency_selection;
    }

    public static FrequencySelectionFragment newInstance(String date) {
        Bundle args = new Bundle();
        FrequencySelectionFragment fragment = new FrequencySelectionFragment();
        fragment.selectedDate = date;
        fragment.setArguments(args);
        return fragment;
    }

    public static FrequencySelectionFragment newInstance(String date, String deliveryTime, int isInstant, String currentHour) {
        Bundle args = new Bundle();
        FrequencySelectionFragment fragment = new FrequencySelectionFragment();
        fragment.selectedDate = date;
        fragment.deliveryTime = deliveryTime;
        fragment.isInstant = isInstant;
        fragment.currentHour = currentHour;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setListeners() {
        rgTimeSelection.setOnCheckedChangeListener(this);
        rgFrequencySelection.setOnCheckedChangeListener(this);
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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (currentHour != null)
            currentHourInt = Integer.parseInt(currentHour);
        setRadioButtons();
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setTitle(selectedDate);
        titleBar.showBackButton(getMainActivity());
        titleBar.setVisibility(View.VISIBLE);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

        switch (group.getId()) {
            case R.id.rgTimeSelection:
                isSlotSet = true;
                indexSlot = rgTimeSelection.indexOfChild(rgTimeSelection.findViewById(checkedId));
                break;

            case R.id.rgFrequencySelection:
                isFrequencySet = true;
                indexFrequency = rgFrequencySelection.indexOfChild(rgFrequencySelection.findViewById(checkedId));
                break;
        }
    }

    public void setSelectedDate(SelectedFrequencyDataInterface selectedData) {
        this.selectedData = selectedData;
    }

    public void setSelectedDate(int indexTimeSlot, int indexFrequency) {
        RadioButton rbSlot = (RadioButton) rgTimeSelection.
                findViewById(rgTimeSelection.getChildAt(indexTimeSlot).getId());

        String selectedTime;
        if (indexTimeSlot >= 12) {
            selectedTime = rbSlot.getText().toString() + " PM";
        } else {
            selectedTime = rbSlot.getText().toString() + " AM";
        }
        selectedData.onTimeSelectedListener(indexTimeSlot, selectedTime,
                indexFrequency, edtAdditionalNotes.getText().toString());
    }


    private void setRadioButtons() {
        List<String> items = Arrays.asList(deliveryTime.split("\\s*,\\s*"));

        if (isInstant == 1) {
            for (int i = 0; i < items.size(); i++) {

                Log.d(TAG, "Outside: " + currentHourInt);
                Log.d(TAG, "setRadioButtons: " + i);
                if (Integer.valueOf(items.get(i)) > (currentHourInt + 1)) {
                    Log.d(TAG, "Inside: " + currentHourInt);
                    RadioButton rb = (RadioButton) rgTimeSelection.findViewById(rgTimeSelection.getChildAt(Integer.valueOf(items.get(i)) - 1).getId());
                    rb.setVisibility(View.VISIBLE);
                }
            }
        } else {
            for (int i = 0; i < items.size(); i++) {
                RadioButton rb = (RadioButton) rgTimeSelection.findViewById(rgTimeSelection.getChildAt(Integer.valueOf(items.get(i)) - 1).getId());
                rb.setVisibility(View.VISIBLE);
            }
        }
    }

    @OnClick(R.id.btnDone)
    public void onViewClicked() {
        if (isSlotSet && isFrequencySet) {
            setSelectedDate(indexSlot, indexFrequency);
            popBackStack();
        } else {
            UIHelper.showToast(getMainActivity(), "Please Select Slot and Frequency");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        KeyboardHide.hideSoftKeyboard(getMainActivity(), getView());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
