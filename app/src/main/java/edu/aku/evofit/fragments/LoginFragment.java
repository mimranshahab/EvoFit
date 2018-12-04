package edu.aku.evofit.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.kaopiz.kprogresshud.KProgressHUD;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import edu.aku.evofit.BaseApplication;
import edu.aku.evofit.R;
import edu.aku.evofit.activities.HomeActivity;
import edu.aku.evofit.fragments.abstracts.BaseFragment;
import edu.aku.evofit.helperclasses.ui.helper.UIHelper;
import edu.aku.evofit.widget.AnyEditTextView;
import edu.aku.evofit.widget.AnyTextView;
import edu.aku.evofit.widget.TitleBar;

public class LoginFragment extends BaseFragment {


    Unbinder unbinder;
    @BindView(R.id.imgLogo)
    ImageView imgLogo;
    @BindView(R.id.edtUserName)
    AnyEditTextView edtUserName;
    @BindView(R.id.edtPassword)
    AnyEditTextView edtPassword;
    @BindView(R.id.btnLogin)
    AnyTextView btnLogin;
    private KProgressHUD mDialog;

    public static LoginFragment newInstance() {

        Bundle args = new Bundle();

        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getDrawerLockMode() {
        return DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_login;
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {


        titleBar.resetViews();
        titleBar.setVisibility(View.VISIBLE);
        titleBar.setTitle("Login");
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


    @OnClick({R.id.btnLogin, R.id.btnSignUp})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:

                sendLoginCall();

                break;
            case R.id.btnSignUp:
                getBaseActivity().addDockableFragment(SignUpFragment.newInstance(), true);
                break;
        }
    }


    private void sendLoginCall() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.setFirestoreSettings(BaseApplication.getFirebaseSetting());
        mDialog = UIHelper.getProgressHUD(getContext());
        mDialog.show();


        CollectionReference users = db.collection("users");

        Query query = users.whereEqualTo("email", edtUserName.getStringTrimmed()).whereEqualTo("password", edtPassword.getStringTrimmed());

        query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                if (queryDocumentSnapshots.getDocuments().isEmpty()) {
                    UIHelper.showToast(getContext(), "INVALID EMAIL OR PASSWORD");
                } else {
                    getBaseActivity().openActivity(HomeActivity.class);
                    getBaseActivity().finish();
                }

                mDialog.dismiss();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                UIHelper.showToast(getContext(), e.getLocalizedMessage());
                mDialog.dismiss();

            }
        });

    }
}
