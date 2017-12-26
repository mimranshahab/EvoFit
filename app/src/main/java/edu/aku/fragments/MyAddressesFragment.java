package edu.aku.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import edu.aku.R;
import edu.aku.adapters.listadapters.MyAddressesAdapter;
import edu.aku.fragments.abstracts.BaseFragment;
import edu.aku.fragments.abstracts.GenericClickableInterface;
import edu.aku.fragments.abstracts.GenericDialogFragment;
import edu.aku.helperclasses.ui.helper.TitleBar;
import edu.aku.helperclasses.ui.helper.UIHelper;

import edu.aku.managers.retrofit.WebServiceFactory;
import edu.aku.models.extramodels.AddressModel;
import edu.aku.models.wrappers.AddressWrapper;
import edu.aku.models.wrappers.WebResponse;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by khanhamza on 27-Feb-17.
 */

public class MyAddressesFragment extends BaseFragment implements AddAdressFragment.OnAddressSelected {


    @BindView(R.id.lvMyAddresses)
    ListView lvMyAddresses;
    @BindView(R.id.txtEmpty)
    TextView txtEmpty;
    private MyAddressesAdapter adapterMyAddresses;
    private int position;
    private ArrayList<AddressModel> arrAddress;
    private AddAdressFragment addAdressFragment;
    private Call<WebResponse<AddressWrapper>> getAddressCall;
    private Call<WebResponse<Object>> selectDefaultAddressCall;
    private Call<WebResponse<Object>> deleteAddressCall;

    public static MyAddressesFragment newInstance() {

        Bundle args = new Bundle();
        MyAddressesFragment fragment = new MyAddressesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_my_addresses;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrAddress = new ArrayList<AddressModel>();
        arrAddress.clear();
        adapterMyAddresses = new MyAddressesAdapter(getActivity(), arrAddress, this);
        getAddresses();
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindViews(view);
        setListeners();
        setAdapter();
    }


    // For future use
//    private void dummyData() {
//        arrAddress.clear();
//        arrAddress.add(new AddressModel("United Arab Emirates", "City1", "11th Street", "Pacific tower", "", "", "",
//                "Building", 0, 38.34232, -121.23123, "Roseville", 0));
//        arrAddress.add(new AddressModel("United Arab Emirates", "City2", "12th Street", "Pacific tower", "", "", "",
//                "Building", 0, 38.34232, -121.23123, "Roseville",  0));
//        arrAddress.add(new AddressModel("United Arab Emirates", "City3", "13th Street", "Pacific tower", "", "", "",
//                "Building", 0, 38.34232, -121.23123, "Roseville",  0));
//    }


    @Override
    public void onResume() {
        super.onResume();
        adapterMyAddresses.notifyDataSetChanged();
    }

    @Override
    public void setTitlebar(final TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setVisibility(View.VISIBLE);
        titleBar.setTitle(getString(R.string.address));
        titleBar.showBackButton(getMainActivity());
        addAdressFragment = AddAdressFragment.newInstance(MyAddressesFragment.this, false);
        titleBar.setRightButton(R.drawable.imgadd, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMainActivity().addDockableFragment(addAdressFragment);
            }
        });
    }

    @Override
    public void setListeners() {
        lvMyAddresses.setOnItemClickListener(this);
    }

    private void bindViews(View view) {
    }

    private void setAdapter() {
        lvMyAddresses.setEmptyView(txtEmpty);
        lvMyAddresses.setAdapter(adapterMyAddresses);
        adapterMyAddresses.notifyDataSetChanged();
    }


    @Override
    public void onClick(View v) {

        int position = (int) v.getTag();
        this.position = position;

        switch (v.getId()) {

            case R.id.btnEditAddress: {
                addAdressFragment = AddAdressFragment.newInstance(this, true);
                addAdressFragment.setAddressModelFromEdit(adapterMyAddresses.getItem(position));
                getMainActivity().addDockableFragment(addAdressFragment);
                break;
            }
            case R.id.btnDeleteAddress: {
//                if (arrAddress.size() > 1) {
                if (adapterMyAddresses.getItem(position).getIsDefault()) {
                    UIHelper.showToast(getContext(), "UserModel cannot remove default address.");
                } else {
                    deletePopup(position);
                }
                break;
            }

//            case R.id.imgDefaultAddress: {
//                int addressID = adapterMyAddresses.getItem(position).getAddressID();
//                selectDefaultAddress(addressID);
//                break;
//            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        selectDefaultAddress(adapterMyAddresses.getItem(position).getAddressID(), position);
    }

    private void deleteCartWarningPopup(final int position) {
        final GenericDialogFragment genericDialogFragment = new GenericDialogFragment();
        genericPopUp(genericDialogFragment, getString(R.string.warning), getString(R.string.yourCartWillBeReset), getString(R.string.agree), getString(R.string.cancel), new GenericClickableInterface() {
            @Override
            public void click() {
                genericDialogFragment.dismiss();
                prefHelper.removeCart();
                prefHelper.putSelectedAddress(adapterMyAddresses.getItem(position));
                emptyBackStack();
                getMainActivity().addDockableFragment(HomeFragment.newInstance());
            }
        }, new GenericClickableInterface() {
            @Override
            public void click() {
                genericDialogFragment.dismiss();
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    private void deletePopup(final int position) {
        final GenericDialogFragment genericDialogFragment = GenericDialogFragment.newInstance();
        genericDialogFragment.setTitle(getString(R.string.delete));
        genericDialogFragment.setMessage(getString(R.string.doYouWantToRemove));
        genericDialogFragment.setButton1(getString(R.string.yes), new GenericClickableInterface() {
            @Override
            public void click() {
                genericDialogFragment.getDialog().dismiss();
                deleteAddress(position);

            }
        });

        genericDialogFragment.setButton2(getString(R.string.no), new GenericClickableInterface() {
            @Override
            public void click() {
                genericDialogFragment.getDialog().dismiss();
            }
        });
        genericDialogFragment.show(getFragmentManager(), null);
    }


    private void getAddresses() {

        getAddressCall = WebServiceFactory.getInstance(prefHelper.getUser().token).getAddresses(prefHelper.getUserID());
        getAddressCall.enqueue(new Callback<WebResponse<AddressWrapper>>() {
            @Override
            public void onResponse(Call<WebResponse<AddressWrapper>> call, Response<WebResponse<AddressWrapper>> response) {
                if (response == null || response.body() == null) {
                    return;
                }
                if (response.body().isSuccess()) {
                    arrAddress.clear();
                    arrAddress.addAll(response.body().result.addresses);
                    adapterMyAddresses.notifyDataSetChanged();
                } else if (response.body().message.trim().equalsIgnoreCase("No Address Found")) {
                    getMainActivity().addDockableFragment(AddAdressFragment.newInstance(MyAddressesFragment.this, false));
                } else {
                    UIHelper.showToast(getContext(), response.body().message);
                }
            }

            @Override
            public void onFailure(Call<WebResponse<AddressWrapper>> call, Throwable t) {
                if (!getAddressCall.isCanceled())
                    t.printStackTrace();
            }
        });
    }


    private void selectDefaultAddress(int addressID, final int position) {

        selectDefaultAddressCall = WebServiceFactory.getInstance(prefHelper.getUser().token).selectAddress(addressID, prefHelper.getUserID());
        selectDefaultAddressCall.enqueue(new Callback<WebResponse<Object>>() {
            @Override
            public void onResponse(Call<WebResponse<Object>> call, Response<WebResponse<Object>> response) {
                if (response.body().isSuccess()) {


                    if (getMainActivity() == null) {
                        return;
                    }

                    getMainActivity().isAddressChangedInEditMode = true;

                    if (prefHelper.getCart().getProducts().isEmpty()) {
                        getMainActivity().getTitleBar().txtCircle.setVisibility(View.GONE);
                        prefHelper.putSelectedAddress(adapterMyAddresses.getItem(position));
                        emptyBackStack();

                        getMainActivity().addDockableFragment(HomeFragment.newInstance());
                    } else {
                        getMainActivity().getTitleBar().txtCircle.setVisibility(View.GONE);
                        deleteCartWarningPopup(MyAddressesFragment.this.position);
                    }

                } else {
                    UIHelper.showToast(getContext(), response.body().message);
                }
            }

            @Override
            public void onFailure(Call<WebResponse<Object>> call, Throwable t) {
                if (!selectDefaultAddressCall.isCanceled())
                    t.printStackTrace();
            }
        });
    }


    private void deleteAddress(final int position) {

        deleteAddressCall = WebServiceFactory.getInstance(prefHelper.getUser().token).deleteAddress(prefHelper.getUserID(), adapterMyAddresses.getItem(position).getAddressID());
        deleteAddressCall.enqueue(new Callback<WebResponse<Object>>() {
            @Override
            public void onResponse(Call<WebResponse<Object>> call, Response<WebResponse<Object>> response) {
                if (response == null || response.body() == null) {
                    return;
                }
                if (response.body().isSuccess()) {
                    arrAddress.remove(position);
                    adapterMyAddresses.notifyDataSetChanged();
                    UIHelper.showToast(getContext(), response.body().message);
                } else {
                    UIHelper.showToast(getContext(), response.body().message);
                }
            }

            @Override
            public void onFailure(Call<WebResponse<Object>> call, Throwable t) {
                if (!deleteAddressCall.isCanceled())
                    t.printStackTrace();
            }
        });
    }

    @Override
    public void onAddressSelected(AddressModel addressModel) {

        if (addressModel == null){
            return;
        }

        if (addAdressFragment.getIsFromEditAddress()) {
            arrAddress.set(position, addressModel);
        } else {
            arrAddress.add(addressModel);
        }
        adapterMyAddresses.notifyDataSetChanged();
//        UIHelper.showToast(getContext(), addressModel.getLocationName());
    }

    @Override
    public void onDestroy() {
        if (getAddressCall != null) {
            getAddressCall.cancel();
        }
        if (selectDefaultAddressCall != null) {
            selectDefaultAddressCall.cancel();
        }
        if (deleteAddressCall != null) {
            deleteAddressCall.cancel();
        }
        super.onDestroy();
    }
}
