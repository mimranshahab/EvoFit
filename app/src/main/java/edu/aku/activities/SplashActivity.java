package edu.aku.activities;

import android.animation.Animator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.aku.R;
import edu.aku.helperclasses.ui.helper.AnimationHelper;
import edu.aku.managers.BaseSharedPreferenceManager;

import static android.view.View.VISIBLE;

public class SplashActivity extends Activity {

    @BindView(R.id.contParentLayout)
    LinearLayout contParentLayout;
    private BaseSharedPreferenceManager basePreferenceHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        basePreferenceHelper = new BaseSharedPreferenceManager(SplashActivity.this);


        final int SPLASH_TIME_OUT = 2000;

        contParentLayout.setVisibility(View.GONE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                animateLayout(SPLASH_TIME_OUT);
            }
        }, 500);



    }

    private void animateLayout(int SPLASH_TIME_OUT) {
        AnimationHelper.fade(contParentLayout, 0, VISIBLE, VISIBLE, 1, SPLASH_TIME_OUT, new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

                    @Override
                    public void run() {

                        // This method will be executed once the timer is over
                        // Start your app main activity
                        basePreferenceHelper.putLang2(SplashActivity.this, basePreferenceHelper.getLang(), false);
                        Intent i = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(i);

                        // close this activity
                        finish();
                    }
                }, 1000);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }
}
