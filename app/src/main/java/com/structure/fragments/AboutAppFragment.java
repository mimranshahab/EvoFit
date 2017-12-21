package com.structure.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.ctrlplusz.anytextview.AnyTextView;
import com.structure.R;
import com.structure.fragments.abstracts.BaseFragment;
import com.structure.helperclasses.ui.helper.KeyboardHide;
import com.structure.helperclasses.ui.helper.TitleBar;
import com.structure.helperclasses.ui.helper.UIHelper;

import com.structure.managers.retrofit.WebServiceFactory;
import com.structure.model.Content;
import com.structure.model.wrappers.WebResponse;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by shehrozmirza on 5/15/2017.
 */

public class AboutAppFragment extends BaseFragment {

    @BindView(R.id.txtTitle)
    AnyTextView txtTitle;
    @BindView(R.id.txtContent)
    AnyTextView txtContent;
    Unbinder unbinder;
    private Call<WebResponse<ArrayList<Content>>> aboutAppCall;

    @Override
    protected int getFragmentLayout() {
        return R.layout.fratgment_aboutus;
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setTitle(getString(R.string.aboutApp));
        titleBar.showBackButton(getMainActivity());
        titleBar.setVisibility(View.VISIBLE);

    }

    public static AboutAppFragment newInstance() {
        Bundle args = new Bundle();
        AboutAppFragment fragment = new AboutAppFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showContent();
    }

    private void showContent() {
        aboutAppCall = WebServiceFactory.getInstance(prefHelper.getUser().token).getStaticPageData(prefHelper.getUserID(), "terms");
        aboutAppCall.enqueue(new Callback<WebResponse<ArrayList<Content>>>() {
            @Override
            public void onResponse(Call<WebResponse<ArrayList<Content>>> call, Response<WebResponse<ArrayList<Content>>> response) {

                if (response.body().isSuccess()) {
                    txtContent.setText(Html.fromHtml(response.body().result.get(0).content));
//                            txtContent.setText(response.body().result.get(0).content);
                } else {
                    UIHelper.showToast(getContext(), response.body().message);
                }
            }

            @Override
            public void onFailure(Call<WebResponse<ArrayList<Content>>> call, Throwable t) {
                if (!aboutAppCall.isCanceled()) {
                    t.printStackTrace();
                }
            }
        });
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
    public void onResume() {
        super.onResume();
        KeyboardHide.hideSoftKeyboard(getMainActivity(), getView());
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

    @Override
    public void onDestroy() {
        if (aboutAppCall != null) {
            aboutAppCall.cancel();
        }
        super.onDestroy();
    }
}
