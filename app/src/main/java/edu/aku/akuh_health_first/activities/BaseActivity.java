package edu.aku.akuh_health_first.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.fragments.LeftSideMenuFragment;
import edu.aku.akuh_health_first.fragments.abstracts.GenericClickableInterface;
import edu.aku.akuh_health_first.fragments.abstracts.GenericDialogFragment;
import edu.aku.akuh_health_first.helperclasses.ui.helper.TitleBar;
import edu.aku.akuh_health_first.models.LaboratoryModel;


import static edu.aku.akuh_health_first.constatnts.AppConstants.JSON_STRING_KEY;
import static edu.aku.akuh_health_first.constatnts.AppConstants.LABORATORY_MODEL;


public abstract class BaseActivity extends AppCompatActivity {

    protected DrawerLayout drawerLayout;
    protected TitleBar titleBar;
    private LeftSideMenuFragment leftSideMenuFragment;

//    private ResideMenu resideMenu;

//    //For Blurred Background
//    private Bitmap mDownScaled;
//    private String mBackgroundFilename;
//    private Bitmap background;
//    private ImageView imageBlur;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getViewId());
        setAndBindTitleBar();
        drawerLayout = (DrawerLayout) findViewById(getDrawerLayoutId());

        addDrawerFragment();

        //        SlidingMenu menu = new SlidingMenu(this);
//        menu.setMode(SlidingMenu.LEFT);
//        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
////        menu.setShadowWidthRes(R.dimen.shadow_width);
////        menu.setShadowDrawable(R.drawable.shadow);
//        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
//        menu.setFadeDegree(0.35f);
//        menu.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);
////        menu.setMenu(R.layout.fragment_sidebar);
//        menu.setMenu(leftSideMenuFragment, getSupportFragmentManager());
//        imageBlur = (ImageView) findViewById(R.id.imageBlur);
        // check if user is registered or not
    }

    protected abstract int getViewId();

    protected abstract int getTitlebarLayoutId();

    protected abstract int getDrawerLayoutId();

    protected abstract int getDockableFragmentId();

    protected abstract int getDrawerFragmentId();

    protected abstract int getPermanentViewId();

    public DrawerLayout getDrawerLayout() {
        return drawerLayout;
    }


    public void addDrawerFragment() {
        leftSideMenuFragment = LeftSideMenuFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(getDrawerFragmentId(), leftSideMenuFragment).commit();
    }


    private void setAndBindTitleBar() {
        titleBar = (TitleBar) findViewById(getTitlebarLayoutId());
        titleBar.setVisibility(View.GONE);
        titleBar.resetViews();
    }

//    @Override
//    public void onBackPressed() {
//        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
//
//            if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
//                drawerLayout.closeDrawer(Gravity.LEFT);
//            } else {
//                super.onBackPressed();
//            }
//
//        } else {
//            closeApp();
//        }
//    }


    public void closeApp() {
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
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
        fragmentTransaction.replace(getDockableFragmentId(), fragment).addToBackStack(fragment.getClass().getSimpleName())
                .commit();
    }

    public void replacePermanentFramgment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);

        fragmentTransaction.replace(getPermanentViewId(), fragment).addToBackStack(fragment.getClass().getSimpleName())
                .commit();
    }

    public TitleBar getTitleBar() {
        return titleBar;
    }

    public void openActivity(Class<?> tClass) {
        Intent i = new Intent(this, tClass);
        startActivity(i);
    }

    public void openActivity(Class<?> tClass, LaboratoryModel object) {
        Intent i = new Intent(this, tClass);
        i.putExtra(LABORATORY_MODEL, object);
        startActivity(i);
    }

    public void openActivity(Class<?> tClass, String object) {
        Intent i = new Intent(this, tClass);
        i.putExtra(JSON_STRING_KEY, object);
        startActivity(i);
    }


    public LeftSideMenuFragment getLeftSideMenuFragment() {
        return leftSideMenuFragment;
    }

    public void reload() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

    public void clearAllActivitiesExceptThis(Class<?> cls) {
        Intent intents = new Intent(this, cls);
        intents.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intents);
        finish();
    }
    // RESIDE MENU ->


//    public void setSideMenu(int direction) {
//        resideMenu = new ResideMenu(this);
//        resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);
//        resideMenu.setBackground(R.drawable.imgmainbg);
//        resideMenu.attachToActivity(this);
//        resideMenu.setScaleValue(0.56f);
//        resideMenu.setShadowVisible(false);
//        setMenuItemDirection(direction);
//    }
//
//
//    public void setMenuItemDirection(int direction) {
//
//        if (direction == ResideMenu.DIRECTION_LEFT) {
//            leftSideMenuFragment = LeftSideMenuFragment.newInstance();
//            resideMenu.addMenuItem(leftSideMenuFragment, "LeftSideMenuFragment", direction);
//        }
//    }
//
//    public LeftSideMenuFragment getLeftSideMenuFragment() {
//        return leftSideMenuFragment;
//    }
//
//
//    public RelativeLayout getMainContentFrame() {
////        return R.id.content_frame;
//        return (RelativeLayout) findViewById(R.id.content_frame);
//    }
//
////    public ImageView getBlurImage() {
////        return imageBlur;
////    }
//
////    public void setBlurBackground() {
////
////////        if (mBackgroundFilename == null) {
//////
//////        this.mDownScaled = Utils.drawViewToBitmap(this.getMainContentFrame(), Color.parseColor("#fff5f5f5"));
//////
//////        mBackgroundFilename = getBlurredBackgroundFilename();
//////        if (!TextUtils.isEmpty(mBackgroundFilename)) {
//////            //context.getMainContentFrame().setVisibility(View.VISIBLE);
//////            background = Utils.loadBitmapFromFile(mBackgroundFilename);
////////                if (background != null) {
//////            getBlurImage().setVisibility(View.VISIBLE);
//////            getBlurImage().setImageBitmap(background);
//////            getBlurImage().animate().alpha(1);
////////                }
//////        }
////////        } else {
////////            getBlurImage().setVisibility(View.VISIBLE);
////////            getBlurImage().setImageBitmap(background);
////////            getBlurImage().animate().alpha(1);
////////        }
//////    }
//////
//////    public String getBlurredBackgroundFilename() {
//////        Bitmap localBitmap = Blur.fastblur(this, this.mDownScaled, 20);
//////        String str = Utils.saveBitmapToFile(this, localBitmap);
//////        this.mDownScaled.recycle();
//////        localBitmap.recycle();
//////        return str;
//////    }
//////
//////    public void removeBlurImage() {
//////        getBlurImage().setVisibility(View.GONE);
//////    }
////
//
//
////    @Override
////    public boolean dispatchTouchEvent(MotionEvent ev) {
////        return resideMenu.dispatchTouchEvent(ev);
////    }
//
//
//    public ResideMenu getResideMenu() {
//        return resideMenu;
//    }


}