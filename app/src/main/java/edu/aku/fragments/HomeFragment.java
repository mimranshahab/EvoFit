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
import edu.aku.models.wrappers.CategoriesWrapper;
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
    private Call<WebResponse<CategoriesWrapper>> getCategoryCall;
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
        titleBar.setTxtCircle(prefHelper.getCart().getProducts().size());
    }

//    public void showNextBuildToast() {
//        UIHelper.showToast(getContext(), "This feature will be implemented in next build");
//    }

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
        getNotificationsList();

        if (isCallsDone) {
            bindData();
        } else {
            if (prefHelper.getSelectedAddress() == null) {
                getSelectedAddresses();
            } else {
                bindData();
                getCategory();
            }
        }
    }

    private void setResideMenu() {
        getResideMenu().openMenu(ResideMenu.DIRECTION_LEFT);
        getResideMenu().closeMenu();

        if (prefHelper.getUser() != null && getMainActivity().getLeftSideMenuFragment().txtUserName != null) {
            getMainActivity().getLeftSideMenuFragment().txtUserName.setText(prefHelper.getUser().userName);
            getMainActivity().getLeftSideMenuFragment().scrollToTop();

            if (prefHelper.getUser().userProfilePictureURL != null) {
                ImageLoader.getInstance().displayImage(prefHelper.getUser().userProfilePictureURL, getMainActivity().getLeftSideMenuFragment().imgProfile, LazyLoading.options);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }
//
//    private void dummyData() {
//        arrCategory.clear();
//        for (int i = 0; i < 6; i++) {
//            ArrayList<Category> arrSubCategory = new ArrayList<Category>();
//            arrSubCategory.clear();
//            int offsetForDummyData = i * 10;
//            for (int j = 0; j < Constants.PRODUCT_TABS.length; j++) {
//                arrSubCategory.add(new Category((j + offsetForDummyData), Constants.PRODUCT_TABS[j], "Details dummy", Constants.CHIPS_IMAGES[j]));
//            }
//            if (i % 2 == 0) {
//                arrCategory.add(new Category(i, "Snacks & Chocolates", "Chocolates, Peanuts, Cookies", Constants.CATEGORY_IMAGES[i], arrSubCategory));
//            } else {
//                arrCategory.add(new Category(i, "Groceries & Staples", "Flour, Oil, Rice, Honey", Constants.CATEGORY_IMAGES[i], arrSubCategory));
//            }
//        }
//    }


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
                    getCategory();

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


    private void getCategory() {
        getCategoryCall = WebServiceFactory.getInstance(prefHelper.getUser().token).getCategories(prefHelper.getUserID(),
                prefHelper.getSelectedAddress().getLatitude(),
                prefHelper.getSelectedAddress().getLongitude(), prefHelper.getSelectedAddress().getLocationName());

        getCategoryCall.enqueue(new Callback<WebResponse<CategoriesWrapper>>() {
            @Override
            public void onResponse(Call<WebResponse<CategoriesWrapper>> call,
                                   Response<WebResponse<CategoriesWrapper>> response) {

                if (response == null || response.body() == null) {
                    return;
                }

                if (response.body().isSuccess()) {
                    arrCategory.clear();
                    arrCategory.addAll(response.body().result.categories);
                    adapterCategory.notifyDataSetChanged();
                    isCallsDone = true;
                } else {
                    UIHelper.showToast(getContext(), response.body().message);
                }
            }

            @Override
            public void onFailure(Call<WebResponse<CategoriesWrapper>> call, Throwable t) {
                if (!getCategoryCall.isCanceled())
                    t.printStackTrace();
            }
        });
    }


    private void getNotificationsList() {

        callNotifications = WebServiceFactory.getInstance(prefHelper.getUserToken()).getNotifications(prefHelper.getUserID());

        callNotifications.enqueue(new Callback<WebResponse<NotificationWrapper>>() {
            @Override
            public void onResponse(Call<WebResponse<NotificationWrapper>> call, Response<WebResponse<NotificationWrapper>> response) {
                if (response == null || response.body() == null) return;

                if (response.body().isSuccess()) {
                    if (getMainActivity() != null && getMainActivity().getLeftSideMenuFragment() != null && getMainActivity().getLeftSideMenuFragment().txtNotifications != null)

                        if (response.body().result.notifications.size() > 0) {
                            getMainActivity().getLeftSideMenuFragment().txtNotifications.setVisibility(View.VISIBLE);
                            getMainActivity().getLeftSideMenuFragment().txtNotifications.setText(String.valueOf(response.body().result.notifications.size()));
                        }
                         else {
                            getMainActivity().getLeftSideMenuFragment().txtNotifications.setVisibility(View.GONE);
                        }
                }
            }

            @Override
            public void onFailure(Call<WebResponse<NotificationWrapper>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onDestroy() {

        if (getSelectedAddressCall != null) {
            getSelectedAddressCall.cancel();
        }
        if (getCategoryCall != null) {
            getCategoryCall.cancel();
        }
        super.onDestroy();
    }
}
