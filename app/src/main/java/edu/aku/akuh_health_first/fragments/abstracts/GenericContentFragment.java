package edu.aku.akuh_health_first.fragments.abstracts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.fragments.DropDownUserInfo;
import edu.aku.akuh_health_first.helperclasses.ui.helper.TitleBar;


/**
 * Created by khanhamza on 21-Feb-17.
 */

public class GenericContentFragment extends BaseFragment {

    public static final String KEY_DATA = "data";
    public static final String KEY_TITLE = "title";

    private String key = "";
    private String title = "";
    private TextView txtViewContent;


    public static GenericContentFragment newInstance(String key, String mTitle) {
        Bundle args = new Bundle();
        GenericContentFragment fragment = new GenericContentFragment();
        args.putString(KEY_DATA, key);
        args.putString(KEY_TITLE, mTitle);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_generic_content;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        key = getArguments().getString(KEY_DATA);
        title = getArguments().getString(KEY_TITLE);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bindViews(view);

//        bindData(key);
    }

    private void bindViews(View view) {
        txtViewContent = (TextView) view.findViewById(R.id.txtViewContent_generic_content);
        txtViewContent.setText(key);
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setVisibility(View.VISIBLE);
        titleBar.setTitle(title);
        titleBar.showBackButton(getBaseActivity());
    }



    @Override
    public void setListeners() {

    }

    @Override
    public int getDrawerLockMode() {
        return DrawerLayout.LOCK_MODE_UNLOCKED;
    }


//    private void bindData(String key) {
//
//        showProgress();
//
//        WebServiceFactory.getInstance("0").getStaticPageData(0, key)
//                .enqueue(new Callback<WebResponse<Content>>() {
//                    @Override
//                    public void onResponse(Call<WebResponse<Content>> call, Response<WebResponse<Content>> responseCode) {
//
//                        if (responseCode.body().isSuccess()) {
//                            txtViewContent.setText(Html.fromHtml(responseCode.body().result.content));
//                            dismissProgress();
//                        } else {
//                            UIHelper.showToast(getContext(), responseCode.body().message);
//                            dismissProgress();
//                        }
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<WebResponse<Content>> call, Throwable t) {
//                        t.printStackTrace();
//                        dismissProgress();
//                    }
//                });
//    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
