
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

public class MainActivity extends AppCompatActivity {

    public DrawerLayout drawerLayout;
     OnActivityResultInterface onActivityResultInterface;

    private TitleBar titleBar;
    private BaseSharedPreferenceManager prefHelper;
    private LeftSideMenuFragment leftSideMenuFragment;

    private ResideMenu resideMenu;

//    //For Blurred Background
//    private Bitmap mDownScaled;
//    private String mBackgroundFilename;
//    private Bitmap background;
//    private ImageView imageBlur;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setAndBindTitleBar();

        prefHelper = new BaseSharedPreferenceManager(this);

        setSideMenu(ResideMenu.DIRECTION_LEFT);

//        imageBlur = (ImageView) findViewById(R.id.imageBlur);

        // check if user is registered or not
        initFragments();

    }


    private void initFragments() {
        if (prefHelper.getUser() == null) {
            prefHelper.removeLocalData();
            prefHelper.setGuest(true);
            addDockableFragment(LoginFragment.newInstance());
        } else {
            prefHelper.setGuest(false);
            if (prefHelper.getUser().getIsVerified() && (prefHelper.getUserID() != 0)) {
                addDockableFragment(HomeFragment.newInstance());
            } else {
                addDockableFragment(LoginFragment.newInstance());
            }
        }
    }


// RESIDE MENU ->


    public void setSideMenu(int direction) {
        resideMenu = new ResideMenu(this);
        resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);
        resideMenu.setBackground(R.drawable.imgmainbg);
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

//    public ImageView getBlurImage() {
//        return imageBlur;
//    }

//    public void setBlurBackground() {
//
//////        if (mBackgroundFilename == null) {
////
////        this.mDownScaled = Utils.drawViewToBitmap(this.getMainContentFrame(), Color.parseColor("#fff5f5f5"));
////
////        mBackgroundFilename = getBlurredBackgroundFilename();
////        if (!TextUtils.isEmpty(mBackgroundFilename)) {
////            //context.getMainContentFrame().setVisibility(View.VISIBLE);
////            background = Utils.loadBitmapFromFile(mBackgroundFilename);
//////                if (background != null) {
////            getBlurImage().setVisibility(View.VISIBLE);
////            getBlurImage().setImageBitmap(background);
////            getBlurImage().animate().alpha(1);
//////                }
////        }
//////        } else {
//////            getBlurImage().setVisibility(View.VISIBLE);
//////            getBlurImage().setImageBitmap(background);
//////            getBlurImage().animate().alpha(1);
//////        }
////    }
////
////    public String getBlurredBackgroundFilename() {
////        Bitmap localBitmap = Blur.fastblur(this, this.mDownScaled, 20);
////        String str = Utils.saveBitmapToFile(this, localBitmap);
////        this.mDownScaled.recycle();
////        localBitmap.recycle();
////        return str;
////    }
////
////    public void removeBlurImage() {
////        getBlurImage().setVisibility(View.GONE);
////    }
//



//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        return resideMenu.dispatchTouchEvent(ev);
//    }



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

    public void setOnActivityResultInterface(OnActivityResultInterface onActivityResultInterface) {
        this.onActivityResultInterface = onActivityResultInterface;
    }
}