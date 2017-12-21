package com.structure.managers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;

import com.google.gson.Gson;
import com.structure.activities.MainActivity;
import com.structure.managers.SharedPreferenceManager;
import com.structure.model.Order;
import com.structure.model.Products;
import com.structure.model.UserModel;
import com.structure.model.extramodels.AddressModel;
import com.structure.model.wrappers.ProductsWrapper;

import java.util.ArrayList;
import java.util.Locale;

public class BaseSharedPreferenceManager extends SharedPreferenceManager {
    private static final String FILENAME = "prefrences";
    private static final String KEY_USER = "user";
    private static final String KEY_GUEST = "guest";
    private static final String KEY_CART = "cart";
    private static final String KEY_FIREBASE_TOKEN = "firebase";
    private static final String KEY_DEFAULT_LANG = "LANGUAGE";
    private static final String KEY_SELECTED_ADDRESS = "SELECTED_ADDRESS";
    private static final String KEY_IS_EDITING = "IS_EDITING";
    private static final String KEY_EDITING_ORDER = "EDITING_ORDER_ID";

    ProductsWrapper productsWrapper;
    private final Context context;


    public BaseSharedPreferenceManager(Context context) {
        this.context = context;
    }

    public ProductsWrapper getCart() {

        if (TextUtils.isEmpty(getStringPreference(context, FILENAME, KEY_CART))) {
            productsWrapper = new ProductsWrapper();
            productsWrapper.products = new ArrayList<Products>();
//            productsWrapper.totalPrice = 0.0f;
        } else {
            productsWrapper = new Gson().fromJson(getStringPreference(context, FILENAME, KEY_CART), ProductsWrapper.class);

            ((MainActivity) context).getTitleBar().setTxtCircle(productsWrapper.getProducts().size());
        }
        return productsWrapper;
    }

    public void putCart(ProductsWrapper cart) {
        putStringPreference(context, FILENAME, KEY_CART, new Gson().toJson(cart));
    }

    public String getCurrencyType() {
        return productsWrapper.products.get(0).getCurrencyType();
    }


    public void addItem(Products products) {
        ProductsWrapper productsWrapper = getCart();
        productsWrapper = incIfItemInCart(products, productsWrapper);
        putStringPreference(context, FILENAME, KEY_CART, new Gson().toJson(productsWrapper));
    }


    public String getPrdocutIDs() {
        StringBuilder sb = new StringBuilder();
        for (Products item : getCart().products) {
            if (sb.length() > 0) sb.append(',');
            sb.append(item.id);
        }
        return sb.toString();
    }


    public String getPrdocutQuantites() {
        StringBuilder sb = new StringBuilder();
        for (Products item : getCart().products) {
            if (sb.length() > 0) sb.append(',');
            sb.append(item.itemQuantityInCart);
        }
        return sb.toString();
    }


    public void deleteItem(int index) {
        productsWrapper.products.remove(index);
        putStringPreference(context, FILENAME, KEY_CART, new Gson().toJson(productsWrapper));
    }

    public void decItem(Products products) {
        ProductsWrapper productsWrapper = getCart();
        productsWrapper = decIfItemInCart(products, productsWrapper);
        putStringPreference(context, FILENAME, KEY_CART, new Gson().toJson(productsWrapper));
    }


    private ProductsWrapper incIfItemInCart(Products products, ProductsWrapper productsWrapper) {
        for (Products item : productsWrapper.products) {
            if (item.id == products.id) {
                int index = productsWrapper.products.indexOf(item);
                productsWrapper.products.get(index).itemQuantityInCart++;
                return productsWrapper;
            }
        }
        products.itemQuantityInCart++;
        productsWrapper.products.add(products);
        return productsWrapper;
    }

    private ProductsWrapper decIfItemInCart(Products products, ProductsWrapper productsWrapper) {
        for (Products item : productsWrapper.products) {
            if (item.id == products.id) {
                int index = productsWrapper.products.indexOf(item);
                productsWrapper.products.get(index).itemQuantityInCart--;
                return productsWrapper;
            }
        }
        return productsWrapper;
    }


    public UserModel getUser() {
        return new Gson().fromJson(
                getStringPreference(context, FILENAME, KEY_USER), UserModel.class);
    }

    public void putUser(UserModel userModel) {
        putStringPreference(context, FILENAME, KEY_USER, new Gson().toJson(userModel));
    }

    public AddressModel getSelectedAddress() {
        return new Gson().fromJson(
                getStringPreference(context, FILENAME, KEY_SELECTED_ADDRESS), AddressModel.class);
    }


    public void putSelectedAddress(AddressModel addressModel) {
        putStringPreference(context, FILENAME, KEY_SELECTED_ADDRESS, new Gson().toJson(addressModel));
    }


    public Order getEditableOrder() {
        return new Gson().fromJson(
                getStringPreference(context, FILENAME, KEY_EDITING_ORDER), Order.class);
    }


    public void putEditableOrder(Order order) {
        putStringPreference(context, FILENAME, KEY_EDITING_ORDER, new Gson().toJson(order));
    }


    public void setGuest(boolean isGuest) {
        putBooleanPreference(context, FILENAME, KEY_GUEST, isGuest);
    }

    public boolean isGuest() {
        return getBooleanPreference(context, FILENAME, KEY_GUEST);
    }

    public void setIsEditingOrder(boolean isEditingOrder) {
        putBooleanPreference(context, FILENAME, KEY_IS_EDITING, isEditingOrder);
    }

    public boolean isEditingOrder() {
        return getBooleanPreference(context, FILENAME, KEY_IS_EDITING);
    }

    public void putFirebaseToken(String token) {
        putStringPreference(context, FILENAME, KEY_FIREBASE_TOKEN, token);
    }

    public String getFirebaseToken() {
        return getStringPreference(context, FILENAME, KEY_FIREBASE_TOKEN);
    }

    public String getUserToken() {
        if (isGuest() || getUser().token == null) {
            return "abc123";
        } else {
            return getUser().token;
        }
    }

    public int getUserID() {
//        if (isGuest()) {
//            return 0;
//        } else {
            return getUser().userID;
//        }
    }

    public void removeCart() {
        removePreference(context, FILENAME, KEY_CART);
    }


    public void removeAddress() {
        removePreference(context, FILENAME, KEY_SELECTED_ADDRESS);
    }

    public void removeLocalData() {
        removePreference(context, FILENAME, KEY_USER);
        removePreference(context, FILENAME, KEY_GUEST);
        removePreference(context, FILENAME, KEY_CART);
        removePreference(context, FILENAME, KEY_SELECTED_ADDRESS);
        removePreference(context, FILENAME, KEY_IS_EDITING);
        removePreference(context, FILENAME, KEY_EDITING_ORDER);

    }


    /**
     * LANGUAGE SECTION
     */


    public void putLang(Activity activity, String lang, boolean isRestartRequired) {
        Log.v("lang", "|" + lang);
        Resources resources = context.getResources();

        if (lang.equals("ar"))
            lang = "ar";
        else
            lang = "en";

        putStringPreference(context, FILENAME, KEY_DEFAULT_LANG, lang);

        DisplayMetrics dm = resources.getDisplayMetrics();
        android.content.res.Configuration conf = resources.getConfiguration();
        conf.locale = new Locale(lang);
        resources.updateConfiguration(conf, dm);

        activity.recreate();

        if (isRestartRequired) {
            Intent intent = new Intent(activity, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            activity.finish();
            activity.startActivity(intent);
        }
    }


    public void putLang2(Activity activity, String lang, boolean isRestartRequired) {
        Log.v("lang", "|" + lang);
        Resources resources = context.getResources();

        if (lang.equals("ar"))
            lang = "ar";
        else
            lang = "en";

        putStringPreference(context, FILENAME, KEY_DEFAULT_LANG, lang);

        DisplayMetrics dm = resources.getDisplayMetrics();
        android.content.res.Configuration conf = resources.getConfiguration();
        conf.locale = new Locale(lang);
        resources.updateConfiguration(conf, dm);


        if (isRestartRequired) {
            activity.recreate();
        }
    }


    public String getLang() {
        return getStringPreference(context, FILENAME, KEY_DEFAULT_LANG);
    }

    public boolean isLanguageArabic() {
        return getLang().equalsIgnoreCase("ar");
    }


}


