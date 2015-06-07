package android.post.testing;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.widget.ImageView;

public class SplashScreen extends Activity {


    SharedPreferences mPrefs;
    final String welcomeScreenShownPref = "welcomeScreenShown";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        Boolean welcomeScreenShown = mPrefs.getBoolean(welcomeScreenShownPref, false);

        if (!welcomeScreenShown) {
            setContentView(R.layout.activity_splash_screen);
            ImageView img = (ImageView) findViewById(R.id.imageView1);
            img.setBackgroundResource(R.drawable.splash_screen);
            AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();
            frameAnimation.start();
            SharedPreferences.Editor editor = mPrefs.edit();
            editor.putBoolean(welcomeScreenShownPref, true);
            editor.commit();
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {

                public void run() {
                    // This method will be executed once the timer is over
                    Intent i = new Intent(SplashScreen.this, MyActivity.class);
                    startActivity(i);
                    // close this activity
                    finish();
                }
            }, 1500);

        } else {
            Intent i = new Intent(SplashScreen.this, MyActivity.class);
            startActivity(i);
            // close this activity
            finish();
        }
    }
}