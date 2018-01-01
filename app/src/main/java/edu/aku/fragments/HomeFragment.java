package edu.aku.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ctrlplusz.anytextview.AnyTextView;
import com.nostra13.universalimageloader.core.ImageLoader;
import edu.aku.R;
import edu.aku.adapters.listadapters.HomeAdapter;
import edu.aku.fragments.abstracts.BaseFragment;
import edu.aku.fragments.abstracts.GenericClickableInterface;
import edu.aku.fragments.abstracts.GenericDialogFragment;
import edu.aku.helperclasses.ui.helper.TitleBar;
import edu.aku.helperclasses.ui.helper.UIHelper;
import edu.aku.libraries.imageloader.LazyLoading;

import edu.aku.managers.retrofit.WebServiceFactory;
import edu.aku.models.Category;
import edu.aku.models.wrappers.AddressWrapper2;
import edu.aku.models.wrappers.NotificationWrapper;
import edu.aku.models.wrappers.WebResponse;
import edu.aku.residemenu.ResideMenu;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends BaseFragment {

    @BindView(R.id.txtAddress)
    AnyTextView txtAddress;
    @BindView(R.id.contAddress)
    LinearLayout contAddress;
    Unbinder unbinder;
    @BindView(R.id.txtEmpty)
    TextView txtEmpty;
    private GridView gvHome;
    private HomeAdapter adapterCategory;
    private ArrayList<Category> arrCategory;
    private boolean isCallsDone;
    private Call<WebResponse<AddressWrapper2>> getSelectedAddressCall;
    private Call<WebResponse<NotificationWrapper>> callNotifications;

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void setTitlebar(final TitleBar titleBar) {

        titleBar.resetViews();
        titleBar.setVisibility(View.VISIBLE);
        titleBar.showTitleImage();
        titleBar.showSidebar(getMainActivity());

        titleBar.setRightButton2(R.drawable.imgsearch, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getMainActivity().addDockableFragment(SearchFragment.newInstance());
            }
        });

        titleBar.setRightButton(R.drawable.imgcart, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getMainActivity().addDockableFragment(ShoppingCartFragment.newInstance());
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        isCallsDone = false;
        arrCategory = new ArrayList<Category>();
        arrCategory.clear();
        adapterCategory = new HomeAdapter(getActivity(), arrCategory);

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setResideMenu();
        bindViews(view);
        setAdapter();


    }

    @Override
    public void onResume() {
        super.onResume();
    }


    private void setAdapter() {
        gvHome.setEmptyView(txtEmpty);
        gvHome.setAdapter(adapterCategory);
    }

    private void bindData() {
        txtAddress.setText(prefHelper.getSelectedAddress().getAddressString());
    }

    private void bindViews(View view) {
        gvHome = (GridView) view.findViewById(R.id.gvHome);
    }

    @Override
    public void setListeners() {
        gvHome.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        getMainActivity().addDockableFragment(ProductTabFragment.newInstance(adapterCategory.getItem(position).categoryName, adapterCategory.getItem(position).subCategories));
    }

    @Override
    public void onClick(View v) {

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

    @OnClick(R.id.contAddress)
    public void onViewClicked() {
        getMainActivity().addDockableFragment(MyAddressesFragment.newInstance());
//        showNextBuildToast();
    }


    private void getSelectedAddresses() {

        getSelectedAddressCall = WebServiceFactory.getInstance(prefHelper.getUser().token).getSelectedAddress(prefHelper.getUserID());
        getSelectedAddressCall.enqueue(new Callback<WebResponse<AddressWrapper2>>() {
            @Override
            public void onResponse(Call<WebResponse<AddressWrapper2>> call, Response<WebResponse<AddressWrapper2>> response) {
                if (response == null || response.body() == null) {
                    return;
                }
                if (response.body().isSuccess()) {
                    prefHelper.putSelectedAddress(response.body().result.address);
                    bindData();
                } else if (response.body().message.trim().equalsIgnoreCase("No Address Found")) {
                    selectDefaultAddressPopup();
                } else {
                    UIHelper.showToast(getContext(), response.body().message);
                }
            }

            @Override
            public void onFailure(Call<WebResponse<AddressWrapper2>> call, Throwable t) {
                if (!getSelectedAddressCall.isCanceled())
                    t.printStackTrace();
            }
        });
    }


    private void selectDefaultAddressPopup() {
        final GenericDialogFragment genericDialogFragment = GenericDialogFragment.newInstance();
        genericDialogFragment.setTitle(getString(R.string.address));
        genericDialogFragment.setMessage("Please add your delivery location.");
        genericDialogFragment.setButton1(getString(R.string.ok), new GenericClickableInterface() {
            @Override
            public void click() {
                genericDialogFragment.getDialog().dismiss();
                getMainActivity().addDockableFragment(MyAddressesFragment.newInstance());
            }
        });

        genericDialogFragment.show(getFragmentManager(), null);
    }



    @Override
    public void onDestroy() {

        if (getSelectedAddressCall != null) {
            getSelectedAddressCall.cancel();
        }
        super.onDestroy();
    }
}
