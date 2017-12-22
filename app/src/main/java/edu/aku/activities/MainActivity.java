
package edu.aku.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.kbeanie.imagechooser.api.ChooserType;
import com.kbeanie.imagechooser.api.ChosenImage;
import com.kbeanie.imagechooser.api.ChosenImages;
import com.kbeanie.imagechooser.api.ImageChooserListener;
import com.kbeanie.imagechooser.api.ImageChooserManager;
import edu.aku.R;
import edu.aku.fragments.HomeFragment;
import edu.aku.fragments.LeftSideMenuFragment;
import edu.aku.fragments.LoginFragment;
import edu.aku.fragments.abstracts.GenericClickableInterface;
import edu.aku.fragments.abstracts.GenericDialogFragment;
import edu.aku.managers.BaseSharedPreferenceManager;
import edu.aku.helperclasses.GooglePlaceHelper;
import edu.aku.helperclasses.PaypalHelper;
import edu.aku.callbacks.OnActivityResultInterface;
import edu.aku.helperclasses.ui.helper.TitleBar;
import edu.aku.residemenu.ResideMenu;
import edu.aku.utility.Blur;
import edu.aku.utility.Utils;

public class MainActivity extends AppCompatActivity implements ImageChooserListener {

    public DrawerLayout drawerLayout;
    ChoosePictureInterface choosePictureInterface;
    OnActivityResultInterface onActivityResultInterface;

    private TitleBar titleBar;
    private BaseSharedPreferenceManager prefHelper;

    private ImageChooserManager imageChooserManager;

    public String filePath;

    private int chooserType;
    private final static String TAG = "ICA";
    private boolean isActivityResultOver = false;
    public String originalFilePath;
    public String thumbnailFilePath;
    public String thumbnailSmallFilePath;
    private LeftSideMenuFragment leftSideMenuFragment;
//    public String priceSort = "low-to-high";
//    public String categorySort = "asc";

    private ResideMenu resideMenu;

    //For Blurred Background
    private Bitmap mDownScaled;
    private String mBackgroundFilename;
    private Bitmap background;

    private ImageView imageBlur;
    public int sortBy = 3;

    public boolean isAddressChangedInEditMode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setAndBindTitleBar();

        prefHelper = new BaseSharedPreferenceManager(this);

        setSideMenu(ResideMenu.DIRECTION_LEFT);

        imageBlur = (ImageView) findViewById(R.id.imageBlur);
        initFragments();

        isAddressChangedInEditMode = false;
        // TODO: 21-Jun-17 Edit Order Logic, Editing disabled if user opens the app
        if (prefHelper.isEditingOrder()) {
            prefHelper.removeCart();
            prefHelper.setIsEditingOrder(false);
        }

    }


    private void initFragments() {
        if (prefHelper.getUser() == null) {
            prefHelper.removeLocalData();
            prefHelper.setGuest(true);

            addDockableFragment(LoginFragment.newInstance());
        } else {
            prefHelper.setGuest(false);
            if (prefHelper.getUser().getIsVerified() && (prefHelper.getUserID() !=  0)) {
                addDockableFragment(HomeFragment.newInstance());
            } else {
                addDockableFragment(LoginFragment.newInstance());
            }
        }
    }


// RESIDE MENU ->


    public void setSideMenu(int direction) {

        resideMenu = new ResideMenu(this);
        resideMenu.setBackground(R.drawable.imgbgdark);
        resideMenu.attachToActivity(this);
        resideMenu.setScaleValue(0.56f);
        resideMenu.setShadowVisible(false);
        setMenuItemDirection(direction);
    }


    public void setMenuItemDirection(int direction) {

        if (direction == ResideMenu.DIRECTION_LEFT) {
            leftSideMenuFragment = LeftSideMenuFragment.newInstance();
            resideMenu.addMenuItem(leftSideMenuFragment, "LeftSideMenuFragment", direction);
        }
    }

    public LeftSideMenuFragment getLeftSideMenuFragment() {
        return leftSideMenuFragment;
    }


    public RelativeLayout getMainContentFrame() {
//        return R.id.content_frame;
        return (RelativeLayout) findViewById(R.id.content_frame);
    }

    public ImageView getBlurImage() {
        return imageBlur;
    }

    public void setBlurBackground() {

////        if (mBackgroundFilename == null) {
//
//        this.mDownScaled = Utils.drawViewToBitmap(this.getMainContentFrame(), Color.parseColor("#fff5f5f5"));
//
//        mBackgroundFilename = getBlurredBackgroundFilename();
//        if (!TextUtils.isEmpty(mBackgroundFilename)) {
//            //context.getMainContentFrame().setVisibility(View.VISIBLE);
//            background = Utils.loadBitmapFromFile(mBackgroundFilename);
////                if (background != null) {
//            getBlurImage().setVisibility(View.VISIBLE);
//            getBlurImage().setImageBitmap(background);
//            getBlurImage().animate().alpha(1);
////                }
//        }
////        } else {
////            getBlurImage().setVisibility(View.VISIBLE);
////            getBlurImage().setImageBitmap(background);
////            getBlurImage().animate().alpha(1);
////        }
    }

    public String getBlurredBackgroundFilename() {
        Bitmap localBitmap = Blur.fastblur(this, this.mDownScaled, 20);
        String str = Utils.saveBitmapToFile(this, localBitmap);
        this.mDownScaled.recycle();
        localBitmap.recycle();
        return str;
    }

    public void removeBlurImage() {
        getBlurImage().setVisibility(View.GONE);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }


    public ResideMenu getResideMenu() {
        return resideMenu;
    }


    public BaseSharedPreferenceManager getPrefHelper() {
        if (prefHelper == null)
            return new BaseSharedPreferenceManager(this);
        else {
            return prefHelper;
        }
    }

    public void addDrawerFragment() {
//        sidebarFragment = SidebarFragment.newInstance();
//        getSupportFragmentManager().beginTransaction().replace(R.id.containerDrawer, sidebarFragment).commit();
    }


    private void setAndBindTitleBar() {
        titleBar = (TitleBar) findViewById(R.id.titlebar);
        titleBar.setVisibility(View.GONE);
        titleBar.resetViews();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {

            if (resideMenu.isOpened()) {
                resideMenu.closeMenu();
            } else {
                super.onBackPressed();
            }

        } else {
            closeApp();
        }
    }


    private void closeApp() {
        final GenericDialogFragment genericDialogFragment = GenericDialogFragment.newInstance();

        genericDialogFragment.setTitle("Quit");
        genericDialogFragment.setMessage("Do you want to close this application?");
        genericDialogFragment.setButton1("Yes", new GenericClickableInterface() {
            @Override
            public void click() {
                genericDialogFragment.getDialog().dismiss();
                finish();
            }
        });

        genericDialogFragment.setButton2("No", new GenericClickableInterface() {
            @Override
            public void click() {
                genericDialogFragment.getDialog().dismiss();
            }
        });
        genericDialogFragment.show(getSupportFragmentManager(), null);
    }

    public void addDockableFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.contMain, fragment).addToBackStack(fragment.getClass().getSimpleName()).commit();
    }

    public TitleBar getTitleBar() {
        return titleBar;
    }

    public void chooseImage() {
        chooserType = ChooserType.REQUEST_PICK_PICTURE;
        imageChooserManager = new ImageChooserManager(this,
                ChooserType.REQUEST_PICK_PICTURE, true);
        Bundle bundle = new Bundle();
        bundle.putBoolean(Intent.EXTRA_ALLOW_MULTIPLE, true);
        imageChooserManager.setExtras(bundle);
        imageChooserManager.setImageChooserListener(this);
        imageChooserManager.clearOldFiles();
        try {
            filePath = imageChooserManager.choose();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void takePicture() {
        chooserType = ChooserType.REQUEST_CAPTURE_PICTURE;
        imageChooserManager = new ImageChooserManager(this,
                ChooserType.REQUEST_CAPTURE_PICTURE, true);
        imageChooserManager.setImageChooserListener(this);
        try {
            filePath = imageChooserManager.choose();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        Log.i(TAG, "OnActivityResult");
//        Log.i(TAG, "File Path : " + filePath);
//        Log.i(TAG, "Chooser Type: " + chooserType);

        if (requestCode == GooglePlaceHelper.REQUEST_CODE_AUTOCOMPLETE) {
            onActivityResultInterface.onActivityResultInterface(requestCode, resultCode, data);
        }

        if (requestCode == PaypalHelper.REQUEST_CODE_PAYPAL) {
            Log.d(TAG, "onActivityResult: Paypal Request ID = " + requestCode);
            PaypalHelper.getInstance().onResult(requestCode, resultCode, data);
        }

        if (resultCode == RESULT_OK
                && (requestCode == ChooserType.REQUEST_PICK_PICTURE || requestCode == ChooserType.REQUEST_CAPTURE_PICTURE)) {
            if (imageChooserManager == null) {
                reinitializeImageChooser();
            }
            imageChooserManager.submit(requestCode, data);
        }
        return;
    }

    @Override
    public void onImageChosen(final ChosenImage image) {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                Log.i(TAG, "Chosen Image: O - " + image.getFilePathOriginal());
                Log.i(TAG, "Chosen Image: T - " + image.getFileThumbnail());
                Log.i(TAG, "Chosen Image: Ts - " + image.getFileThumbnailSmall());
                isActivityResultOver = true;
                originalFilePath = image.getFilePathOriginal();
                thumbnailFilePath = image.getFileThumbnail();
                thumbnailSmallFilePath = image.getFileThumbnailSmall();
                if (image != null) {
                    Log.i(TAG, "Chosen Image: Is not null");
                    choosePictureInterface.onChoosePicture(originalFilePath, thumbnailFilePath, thumbnailSmallFilePath);

                    // loadImage(imageViewThumbnail, image.getFileThumbnail());
                } else {
                    Log.i(TAG, "Chosen Image: Is null");
                }
            }
        });

    }

    @Override
    public void onImagesChosen(final ChosenImages images) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "On Images Chosen: " + images.size());
                onImageChosen(images.getImage(0));
            }
        });
    }


    @Override
    public void onError(final String reason) {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                Log.i(TAG, "OnError: " + reason);
                Toast.makeText(MainActivity.this, reason,
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    // Should be called if for some reason the ImageChooserManager is null (Due
    // to destroying of activity for low memory situations)
    private void reinitializeImageChooser() {
        imageChooserManager = new ImageChooserManager(this, chooserType, true);
        Bundle bundle = new Bundle();
        bundle.putBoolean(Intent.EXTRA_ALLOW_MULTIPLE, true);
        imageChooserManager.setExtras(bundle);
        imageChooserManager.setImageChooserListener(this);
        imageChooserManager.reinitialize(filePath);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.i(TAG, "Saving Stuff");
        Log.i(TAG, "File Path: " + filePath);
        Log.i(TAG, "Chooser Type: " + chooserType);
        outState.putBoolean("activity_result_over", isActivityResultOver);
        outState.putInt("chooser_type", chooserType);
        outState.putString("media_path", filePath);
        outState.putString("orig", originalFilePath);
        outState.putString("thumb", thumbnailFilePath);
        outState.putString("thumbs", thumbnailSmallFilePath);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("chooser_type")) {
                chooserType = savedInstanceState.getInt("chooser_type");
            }
            if (savedInstanceState.containsKey("media_path")) {
                filePath = savedInstanceState.getString("media_path");
            }
            if (savedInstanceState.containsKey("activity_result_over")) {
                isActivityResultOver = savedInstanceState.getBoolean("activity_result_over");
                originalFilePath = savedInstanceState.getString("orig");
                thumbnailFilePath = savedInstanceState.getString("thumb");
                thumbnailSmallFilePath = savedInstanceState.getString("thumbs");
            }
        }

        Log.i(TAG, "Restoring Stuff");
        Log.i(TAG, "File Path: " + filePath);
        Log.i(TAG, "Chooser Type: " + chooserType);
        Log.i(TAG, "Activity Result Over: " + isActivityResultOver);
        super.onRestoreInstanceState(savedInstanceState);
    }

    public interface ChoosePictureInterface {
        void onChoosePicture(String originalFilePath, String thumbnailFilePath, String thumbnailSmallFilePath);
    }

    public void setChoosePictureListener(ChoosePictureInterface choosePictureInterfaceListener) {
        this.choosePictureInterface = choosePictureInterfaceListener;
    }

    public void setOnActivityResultInterface(OnActivityResultInterface onActivityResultInterface) {
        this.onActivityResultInterface = onActivityResultInterface;
    }
}