package edu.aku.family_hifazat.activities;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;

import edu.aku.family_hifazat.constatnts.AppConstants;
import edu.aku.family_hifazat.helperclasses.ui.helper.AnimationHelper;
import butterknife.BindView;
import butterknife.ButterKnife;
import edu.aku.family_hifazat.R;
import edu.aku.family_hifazat.managers.SharedPreferenceManager;

import static android.view.View.VISIBLE;

public class SplashActivity extends Activity {

    @BindView(R.id.contParentLayout)
    LinearLayout contParentLayout;
    private final int SPLASH_TIME_OUT = 2000;
    private final int ANIMATIONS_DELAY = 200;
    private final int ANIMATIONS_TIME_OUT = 250;
    private final int FADING_TIME = 500;
    private boolean hasAnimationStarted = false;

//    private final int SPLASH_TIME_OUT = 200;
//    private final int ANIMATIONS_DELAY = 50;
//    private final int ANIMATIONS_TIME_OUT = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);


        contParentLayout.setVisibility(View.INVISIBLE);


    }

    private void animateSplashLayout(final boolean isOnlySplasAnimation) {

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        ObjectAnimator translationY = ObjectAnimator.ofFloat(contParentLayout, "y", metrics.heightPixels / 2 - contParentLayout.getHeight() / 2); // metrics.heightPixels or root.getHeight()
        translationY.setDuration(1);
        translationY.start();

//        Animation anim = AnimationUtils.loadAnimation(this, R.anim.translate_to_mid);
//        anim.setInterpolator((new AccelerateDecelerateInterpolator()));
//        anim.setFillAfter(true);
//        contParentLayout.setAnimation(anim);
//
//        contParentLayout.setTranslationY(500);

        AnimationHelper.fade(contParentLayout, 0, VISIBLE, VISIBLE, 0.7f, FADING_TIME, new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                if (isOnlySplasAnimation) {
                    translateAnimation();
                } else {
                    changeActivity(HomeActivity.class);
                }
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });


    }

    private void translateAnimation() {


        contParentLayout.animate().

                setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {
                        contParentLayout.setVisibility(VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        contParentLayout.setTranslationY(0);
                        contParentLayout.setAlpha(1);
                        changeActivity(MainActivity.class);
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                })
                .alpha(1)
                .translationY(0)
                .setDuration(SPLASH_TIME_OUT)
                .start();
    }

    private void changeActivity(final Class activityClass) {
        new Handler().postDelayed(new Runnable() {

    /*
     * Showing splash screen with a timer. This will be useful when you
     * want to show case your app logo / company
     */

            @Override
            public void run() {


                Intent i;
                // This method will be executed once the timer is over
                // Start your app main activity
                i = new Intent(SplashActivity.this, activityClass);

                startActivity(i);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                // close this activity
                finish();
            }
        }, ANIMATIONS_TIME_OUT);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && !hasAnimationStarted) {
            hasAnimationStarted = true;


            if (SharedPreferenceManager.getInstance(getApplicationContext()).getString(AppConstants.KEY_CARD_NUMBER) == null
                    || SharedPreferenceManager.getInstance(getApplicationContext()).getString(AppConstants.KEY_CARD_NUMBER).isEmpty()) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        animateSplashLayout(true);
                    }
                }, ANIMATIONS_DELAY);

            } else {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        animateSplashLayout(false);
                    }
                }, ANIMATIONS_DELAY);
            }


        }
    }
}