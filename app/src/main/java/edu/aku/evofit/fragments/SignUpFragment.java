package edu.aku.evofit.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kaopiz.kprogresshud.KProgressHUD;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import edu.aku.evofit.BaseApplication;
import edu.aku.evofit.R;
import edu.aku.evofit.constatnts.WebServiceConstants;
import edu.aku.evofit.fragments.abstracts.BaseFragment;
import edu.aku.evofit.helperclasses.ui.helper.UIHelper;
import edu.aku.evofit.models.receiving_model.UserDetailModel;
import edu.aku.evofit.widget.AnyEditTextView;
import edu.aku.evofit.widget.AnyTextView;
import edu.aku.evofit.widget.TitleBar;

public class SignUpFragment extends BaseFragment {


    @BindView(R.id.imgLogo)
    ImageView imgLogo;
    @BindView(R.id.edtUserName)
    AnyEditTextView edtUserName;
    @BindView(R.id.edtEmail)
    AnyEditTextView edtEmail;
    @BindView(R.id.edtPassword)
    AnyEditTextView edtPassword;
    @BindView(R.id.edtDOB)
    AnyEditTextView edtDOB;
    @BindView(R.id.edtWeight)
    AnyEditTextView edtWeight;
    @BindView(R.id.edtHeight)
    AnyEditTextView edtHeight;
    @BindView(R.id.rbMale)
    RadioButton rbMale;
    @BindView(R.id.rbFemale)
    RadioButton rbFemale;
    @BindView(R.id.rbA)
    RadioButton rbA;
    @BindView(R.id.rbB)
    RadioButton rbB;
    @BindView(R.id.rbO)
    RadioButton rbO;
    @BindView(R.id.btnSignUp)
    AnyTextView btnSignUp;
    Unbinder unbinder;

    KProgressHUD mDialog;

    public static SignUpFragment newInstance() {

        Bundle args = new Bundle();

        SignUpFragment fragment = new SignUpFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getDrawerLockMode() {
        return DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_signup;
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setVisibility(View.VISIBLE);
        titleBar.setTitle("Sign Up");

        titleBar.showBackButton(getBaseActivity());
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

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

    @OnClick(R.id.btnSignUp)
    public void onViewClicked() {


        UserDetailModel userDetailModel = new UserDetailModel();

        userDetailModel.setEmail(edtEmail.getStringTrimmed());
        userDetailModel.setPassword(edtPassword.getStringTrimmed());
        userDetailModel.setFull_name(edtUserName.getStringTrimmed());


        sendSignUpCall(userDetailModel);

    }


    private void sendSignUpCall(UserDetailModel userModel) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.setFirestoreSettings(BaseApplication.getFirebaseSetting());
        mDialog = UIHelper.getProgressHUD(getContext());
        mDialog.show();


        db.collection("users").document(userModel.getEmail())
                .set(userModel)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                        mDialog.dismiss();
                        getBaseActivity().addDockableFragment(HomeFragment.newInstance(), false);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                        mDialog.dismiss();
                        UIHelper.showToast(getContext(), e.getLocalizedMessage());
                    }
                });


    }
}
