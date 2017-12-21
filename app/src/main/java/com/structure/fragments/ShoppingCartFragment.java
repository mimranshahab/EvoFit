package com.structure.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.ctrlplusz.anytextview.AnyTextView;
import com.google.gson.Gson;
import com.structure.R;
import com.structure.adapters.listadapters.ShoppingCartlAdapter;
import com.structure.constatnts.Constants;
import com.structure.constatnts.WebServiceConstants;
import com.structure.fragments.abstracts.BaseFragment;
import com.structure.fragments.abstracts.GenericClickableInterface;
import com.structure.fragments.abstracts.GenericDialogFragment;
import com.structure.callbacks.SelectedFrequencyDataInterface;
import com.structure.helperclasses.ui.helper.TitleBar;
import com.structure.helperclasses.ui.helper.UIHelper;

import com.structure.managers.retrofit.WebServiceFactory;
import com.structure.model.AddOrder;
import com.structure.model.Order;
import com.structure.model.OrderVariable;
import com.structure.model.Products;
import com.structure.model.wrappers.ProductsWrapper;
import com.structure.model.wrappers.WebResponse;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
import static com.structure.helperclasses.DateHelper.convertToServerRequiredTimeZone;

/**
 * Created by khanhamza on 04-Mar-17.
 */

public class ShoppingCartFragment extends BaseFragment implements AdapterView.OnItemLongClickListener, SelectedFrequencyDataInterface {


    @BindView(R.id.lvShoppingCart)
    ListView lvShoppingCart;
    @BindView(R.id.txtCurrencyType)
    AnyTextView txtCurrencyType;
    @BindView(R.id.txtTotalPrice)
    AnyTextView txtTotalPrice;
    @BindView(R.id.txtServicePrice)
    AnyTextView txtServicePrice;
    @BindView(R.id.btnContinueShopping)
    AnyTextView btnContinueShopping;
    @BindView(R.id.btnPlaceOrder)
    AnyTextView btnPlaceOrder;
    @BindView(R.id.btnAddress)
    AnyTextView btnAddress;
    @BindView(R.id.btnSchedule)
    AnyTextView btnSchedule;
    @BindView(R.id.contCheckout)
    LinearLayout contCheckout;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.btnStartShopping)
    AnyTextView btnStartShopping;
    @BindView(R.id.txtMinimumPrice)
    AnyTextView txtMinimumPrice;
    @BindView(R.id.contEmptyShoppingCart)
    LinearLayout contEmptyShoppingCart;
    private ShoppingCartlAdapter adapterProductDetail;
    private static final String ARRAY_LIST_KEY = "ARRAY_LIST_KEY";
    private Gson gson = new Gson();
    private ArrayList<Products> arrProducts;
    private GenericDialogFragment removeFromCartDialog;
    Calendar myCalendar;
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    boolean isDateandTimeSelected = false;
    private boolean isScheduleSelectedInEditModeAddressChanged = true;
    private String userSelectedDateString = "";
    private String availableDeliveryTimeInString;
    OrderVariable orderVariable;
    Date currentDateFromServer = new Date();
    private int isInstant = 0;
    private boolean isWebCallDone;
    private boolean isFirstTimeOnThisScreen;

    private Call<WebResponse<OrderVariable>> callCartDetail;
    private Call<WebResponse<AddOrder>> addOrderCall;
    private int indexTimeSlot = 0;
    private String selectedTime = "";
    private int indexFrequency = 0;
    private String additionalNotes = "";


    public static ShoppingCartFragment newInstance() {

        Bundle args = new Bundle();
        Gson gson = new Gson();
        ShoppingCartFragment fragment = new ShoppingCartFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_shopping_cart;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        removeFromCartDialog = GenericDialogFragment.newInstance();
        arrProducts = new ArrayList<Products>();
        arrProducts.clear();
        myCalendar = Calendar.getInstance();
        isFirstTimeOnThisScreen = true;
        isScheduleSelectedInEditModeAddressChanged = !getMainActivity().isAddressChangedInEditMode;
        isWebCallDone = false;



        if (prefHelper.isEditingOrder()) {
            if (prefHelper.getEditableOrder() == null) return;

            Order order = prefHelper.getEditableOrder();
            this.isDateandTimeSelected = true;
            this.indexTimeSlot = order.deliveryHour;
            this.selectedTime = order.deliveryHourString;
            this.indexFrequency = order.frequency;
            this.additionalNotes = order.additionalNotes;
            this.isInstant = order.isInstant;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindViews();
        setCart();
        if (isFirstTimeOnThisScreen) {
            getOrderVariables();
        } else {
            setOnScreenData();
        }
        bindData();
        setAdapter();
        updateCart();
        adapterProductDetail.notifyDataSetChanged();



    }

    private void setOnScreenData() {
        txtMinimumPrice.setText(String.valueOf(orderVariable.minimumOrder + " AED"));
        txtServicePrice.setText(String.valueOf("incl. " + orderVariable.serviceFee + " service fee"));
        availableDeliveryTimeInString = orderVariable.deliveryTimeInString;
        txtTotalPrice.setText(getFormattedNumber((prefHelper.getCart().getTotalPrice() + orderVariable.serviceFee), 2));

        Log.d(TAG, "onResponse: CURRENT TIME " + orderVariable.getServerTime());

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            currentDateFromServer = format.parse(orderVariable.serverTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void setCart() {
        arrProducts.clear();
        arrProducts.addAll(prefHelper.getCart().products);
    }

    private void bindData() {

        if (prefHelper.getCart().products.isEmpty() || prefHelper.getCart().products.size() < 1) {
            contEmptyShoppingCart.setVisibility(View.VISIBLE);
            lvShoppingCart.setVisibility(View.GONE);
            contCheckout.setVisibility(View.GONE);
        } else {
            lvShoppingCart.setVisibility(View.VISIBLE);
            contCheckout.setVisibility(View.VISIBLE);
            contEmptyShoppingCart.setVisibility(View.GONE);
            txtCurrencyType.setText(prefHelper.getCart().products.get(0).getCurrencyType());
            if (orderVariable != null) {
                txtTotalPrice.setText(getFormattedNumber((prefHelper.getCart().getTotalPrice() + orderVariable.serviceFee), 2));
            }

        }


    }

    private void getOrderVariables() {
        callCartDetail = WebServiceFactory.getInstance(prefHelper.getUser().token).getOrderVariable(prefHelper.getUserID());
        callCartDetail.enqueue(new Callback<WebResponse<OrderVariable>>() {
            @Override
            public void onResponse(Call<WebResponse<OrderVariable>> call, Response<WebResponse<OrderVariable>> response) {
                if (response == null || response.body() == null) return;

                if (response.body().isSuccess()) {
                    orderVariable = response.body().result;
                    setOnScreenData();
                    isWebCallDone = true;
                }
            }

            @Override
            public void onFailure(Call<WebResponse<OrderVariable>> call, Throwable t) {
                if (!callCartDetail.isCanceled()) {
                    t.printStackTrace();
                }
            }
        });
    }


    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setVisibility(View.VISIBLE);
        titleBar.setTitle(getString(R.string.myCart));
        titleBar.showBackButton(getMainActivity());
    }

    @Override
    public void setListeners() {

        btnPlaceOrder.setOnClickListener(this);
        btnStartShopping.setTag(R.id.btnStartShopping);
        lvShoppingCart.setOnItemLongClickListener(this);
        btnStartShopping.setOnClickListener(this);
        btnStartShopping.setTag(R.id.btnStartShopping);

        btnContinueShopping.setOnClickListener(this);
        btnContinueShopping.setTag(R.id.btnContinueShopping);

        btnAddress.setOnClickListener(this);
        btnAddress.setTag(R.id.btnAddress);

        btnSchedule.setOnClickListener(this);
        btnSchedule.setTag(R.id.btnSchedule);

        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDateLabel();
            }
        };
    }


    private void updateDateLabel() {
        String myFormat = "dd-MMMM-yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        userSelectedDateString = sdf.format(this.myCalendar.getTime());


        String currentDateFromServerFormat = "dd-MMMM-yyyy";
        SimpleDateFormat sdf2 = new SimpleDateFormat(currentDateFromServerFormat);
        String currentDateFromServerString = sdf2.format(currentDateFromServer);


        if (userSelectedDateString.equals(currentDateFromServerString)) {
            isInstant = 1;
        } else {
            isInstant = 0;
        }

        String currentHourFormat = "H";
        SimpleDateFormat sdf3 = new SimpleDateFormat(currentHourFormat);
        String currentHour;
        currentHour = sdf3.format(currentDateFromServer);

        FrequencySelectionFragment frequencySelectionFragment = FrequencySelectionFragment.newInstance(userSelectedDateString, availableDeliveryTimeInString, isInstant, currentHour);
        frequencySelectionFragment.setSelectedDate(this);
        isWebCallDone = true;
        getMainActivity().addDockableFragment(frequencySelectionFragment);

    }

    private void bindViews() {
        btnPlaceOrder.setTag(btnPlaceOrder.getId());
    }

    private void updateCart() {
        ProductsWrapper cart = prefHelper.getCart();

        for (int indexCartItem = 0; indexCartItem < cart.products.size(); indexCartItem++) {
            for (int indexProduct = 0; indexProduct < arrProducts.size(); indexProduct++) {
                if (cart.products.get(indexCartItem).id == arrProducts.get(indexProduct).id) {
                    arrProducts.get(indexProduct).itemQuantityInCart = cart.products.get(indexCartItem).itemQuantityInCart;
                    if (cart.products.get(indexCartItem).itemQuantityInCart < 1) {
                        prefHelper.deleteItem(indexCartItem);
                        setCart();
                        return;
                    }
                }
            }
        }
    }


    private void deleteFromCart(Products products) {
        ProductsWrapper cart = prefHelper.getCart();
        for (int indexCartItem = 0; indexCartItem < cart.products.size(); indexCartItem++) {
            if (cart.products.get(indexCartItem).id == products.id) {
                prefHelper.deleteItem(indexCartItem);
                setCart();
                return;
            }
        }
    }


    private void setAdapter() {

        ProductsWrapper cart = prefHelper.getCart();
        for (Products itemCart : cart.products) {
            for (int indexProduct = 0; indexProduct < arrProducts.size(); indexProduct++) {
                if (itemCart.id == arrProducts.get(indexProduct).id) {
                    arrProducts.get(indexProduct).itemQuantityInCart = itemCart.itemQuantityInCart;
                }
            }
        }

        adapterProductDetail = new ShoppingCartlAdapter(getActivity(), arrProducts, this);

        lvShoppingCart.setAdapter(adapterProductDetail);
        updateCart();
    }

    private void dummyData() {
        for (int i = 0; i < 7; i++) {
            if (i % 2 == 0) {
                Products products = new Products();
                products.isFavorite = "1";
                products.productName = "Cheetos";
                products.productDescription = "250 GMS";
                products.quantity = 4;
                products.price = 1.06f;
                products.setItemQuantityInCart(2);
                products.productImage = Constants.CHIPS_IMAGES[i];
                arrProducts.add(products);
            } else {
                Products products = new Products();
                products.isFavorite = "1";
                products.productImage = Constants.CHIPS_IMAGES[i];
                products.productName = "Lays";
                products.setItemQuantityInCart(2);
                products.quantity = 4;
                products.productDescription = "320 GMS";
                products.price = 1.56f;
                arrProducts.add(products);
            }
        }
    }

    @Override
    public void onClick(View v) {
        final int position = (int) v.getTag();

        switch (v.getId()) {

            case R.id.btnContinueShopping:
                emptyBackStack();
                getMainActivity().addDockableFragment(HomeFragment.newInstance());
                break;


            case R.id.btnAddress:
                final GenericDialogFragment genericDialogFragment = new GenericDialogFragment();
                genericPopUp(genericDialogFragment, getString(R.string.warning), getString(R.string.yourCartWillBeReset), getString(R.string.agree), getString(R.string.cancel), new GenericClickableInterface() {
                    @Override
                    public void click() {
                        genericDialogFragment.dismiss();
                        prefHelper.removeCart();
                        getMainActivity().addDockableFragment(MyAddressesFragment.newInstance());
                    }
                }, new GenericClickableInterface() {
                    @Override
                    public void click() {
                        genericDialogFragment.dismiss();
                    }
                });
                break;


            case R.id.btnSchedule: {
                if (orderVariable == null) break;


                if (prefHelper.getCart().getTotalPrice() < orderVariable.minimumOrder) {
                    UIHelper.showToast(getContext(), "Minimum orderVariable price should be greater than " + orderVariable.minimumOrder);
                } else {
                    if (isWebCallDone)
                        showDateDilaog();
                }
                break;
            }
            case R.id.btnPlaceOrder: {

                if (orderVariable == null) break;

                if (prefHelper.getCart().getTotalPrice() < orderVariable.minimumOrder) {
                    UIHelper.showToast(getContext(), "Minimum orderVariable price should be greater than " + orderVariable.minimumOrder);
                } else {

                    if (isDateandTimeSelected && isScheduleSelectedInEditModeAddressChanged) {

                        if (prefHelper.isEditingOrder()) {
                            editOrderPopup();
                        } else {
                            addOrder();
                        }
                    } else {
                        UIHelper.showToast(getContext(), "Please select Schedule");
                    }
                }
                break;

            }

            case R.id.btnStartShopping: {
                emptyBackStack();
                getMainActivity().addDockableFragment(HomeFragment.newInstance());
                break;
            }


            case R.id.btnIncrement: {
                if (adapterProductDetail.getItem(position).getItemQuantityInCart() >= adapterProductDetail.getItem(position).quantity) {
                    UIHelper.showToast(getContext(), "Stock limit reached.");
                } else {
                    prefHelper.addItem(adapterProductDetail.getItem(position));
                    updateCart();
                    bindData();
                    adapterProductDetail.notifyDataSetChanged();
                }
                break;
            }

            case R.id.btnDecrement: {
                if ((adapterProductDetail.getItem(position).getItemQuantityInCart() < 1)) {
                    UIHelper.showToast(getContext(), "Can't be less than 0");
                } else {
                    prefHelper.decItem(adapterProductDetail.getItem(position));
                    updateCart();
                    bindData();
                    adapterProductDetail.notifyDataSetChanged();
                }
                break;
            }


            case R.id.imgDelete: {

                genericPopUp(removeFromCartDialog, getString(R.string.delete), getString(R.string.areYouSureToDeleteThisItemFromCart), getString(R.string.yes), getString(R.string.no), new GenericClickableInterface() {
                    @Override
                    public void click() {

                        deleteFromCart(arrProducts.get(position));
                        bindData();
                        adapterProductDetail.notifyDataSetChanged();
                        removeFromCartDialog.dismiss();
                    }
                }, new GenericClickableInterface() {
                    @Override
                    public void click() {
                        removeFromCartDialog.dismiss();
                    }
                });
                break;
            }
        }
    }

    private void showDateDilaog() {
        DatePickerDialog datePickerDialog =
                new DatePickerDialog(getMainActivity(), onDateSetListener, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
        try {
            if (isFirstTimeOnThisScreen) {
                datePickerDialog.getDatePicker().setMinDate(orderVariable.getUnixTime() - 10);
                isFirstTimeOnThisScreen = false;
            } else {
                datePickerDialog.getDatePicker().setMinDate(orderVariable.getUnixTime() - 100000);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        datePickerDialog.show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

        genericPopUp(removeFromCartDialog, getString(R.string.delete), getString(R.string.areYouSureToDeleteThisItemFromCart), getString(R.string.yes), getString(R.string.no), new GenericClickableInterface() {
            @Override
            public void click() {

                deleteFromCart(arrProducts.get(position));
                bindData();
                adapterProductDetail.notifyDataSetChanged();
                removeFromCartDialog.dismiss();
            }
        }, new GenericClickableInterface() {
            @Override
            public void click() {
                removeFromCartDialog.dismiss();
            }
        });
        return false;
    }

    @Override
    public void onTimeSelectedListener(int indexTimeSlot, String selectedTime, int indexFrequency, String additionalNotes) {
        this.isDateandTimeSelected = true;
        this.isScheduleSelectedInEditModeAddressChanged = true;

        this.indexTimeSlot = indexTimeSlot + 1;
        this.selectedTime = selectedTime;
        this.indexFrequency = indexFrequency + 1;
        this.additionalNotes = additionalNotes;

        Log.d(TAG, "onTimeSelectedListener: indexTimeSlot" + indexTimeSlot);
        Log.d(TAG, "onTimeSelectedListener: selectedTime" + selectedTime);
        Log.d(TAG, "onTimeSelectedListener: indexFrequency" + indexFrequency);
        Log.d(TAG, "onTimeSelectedListener: additionalNotes" + additionalNotes);
    }


    private void addOrder() {

        String deliveryDate = convertToServerRequiredTimeZone(this.myCalendar.getTime());
        Log.d(TAG, "onTimeSelectedListener: deliveryDate" + deliveryDate);
        Log.d(TAG, "onTimeSelectedListener: isInstant" + isInstant);

        if (prefHelper.isEditingOrder()) {
            addOrderCall = WebServiceFactory.getInstance(prefHelper.getUserToken()).
                    addOrder(prefHelper.getPrdocutIDs(), prefHelper.getPrdocutQuantites(), prefHelper.getSelectedAddress().getAddressID(), deliveryDate,
                            additionalNotes, prefHelper.getUserID(), WebServiceConstants.PAYMENT_TYPE_CASH_ON_DELIVERY, indexTimeSlot, selectedTime, indexFrequency, isInstant,
                            prefHelper.getEditableOrder().id);
            Log.d(TAG, "addOrder: order ID " + prefHelper.getEditableOrder().id);
        } else {
            addOrderCall = WebServiceFactory.getInstance(prefHelper.getUserToken()).
                    addOrder(prefHelper.getPrdocutIDs(), prefHelper.getPrdocutQuantites(), prefHelper.getSelectedAddress().getAddressID(), deliveryDate,
                            additionalNotes, prefHelper.getUserID(), WebServiceConstants.PAYMENT_TYPE_CASH_ON_DELIVERY, indexTimeSlot, selectedTime, indexFrequency, isInstant, 0
                    );
            Log.d(TAG, "addOrder: order ID " + 0);
        }

        addOrderCall.enqueue(new Callback<WebResponse<AddOrder>>() {
                    @Override
                    public void onResponse(Call<WebResponse<AddOrder>> call, Response<WebResponse<AddOrder>> response) {
                        if (response.body().isSuccess()) {
                            UIHelper.showToast(getContext(), response.body().message);
                            getMainActivity().isAddressChangedInEditMode = false;
                            prefHelper.setIsEditingOrder(false);

                            prefHelper.removeCart();
                            popStackTill(1);
                            getMainActivity().addDockableFragment(OrderSummaryFragment.newInstance(userSelectedDateString, response.body().result.orderID));
                        } else {
                            UIHelper.showToast(getContext(), response.body().message);
                        }
                    }

                    @Override
                    public void onFailure(Call<WebResponse<AddOrder>> call, Throwable t) {
                        if (!addOrderCall.isCanceled())
                            t.printStackTrace();
                    }
                });
    }

    @Override
    public void onDestroy() {
        if (callCartDetail != null) {
            callCartDetail.cancel();
        }
        if (addOrderCall != null) {
            addOrderCall.cancel();
        }
        super.onDestroy();
    }


    private void editOrderPopup() {
        final GenericDialogFragment genericDialogFragment = GenericDialogFragment.newInstance();

        genericDialogFragment.setTitle("Edit Order");
        genericDialogFragment.setMessage("You are editing Order No. " + prefHelper.getEditableOrder().id);

        genericDialogFragment.setButton1(getString(R.string.confirm), new GenericClickableInterface() {
            @Override
            public void click() {
                genericDialogFragment.getDialog().dismiss();
                addOrder();
            }
        });

        genericDialogFragment.setButton2(getString(R.string.cancel), new GenericClickableInterface() {
            @Override
            public void click() {
                genericDialogFragment.getDialog().dismiss();
            }
        });
        genericDialogFragment.show(getFragmentManager(), null);
    }


}
