package example.vinek.com.splash;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Project Audi on 3/14/2017.
 */

public class SplashScreen extends Activity
{
    public  void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }//end on attached

    //called when the first activity is created
    Thread splashThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen_activity);
        StartAnimations();
    }//end on create

    private void StartAnimations()
    {
        Animation anim = AnimationUtils.loadAnimation(this,R.anim.alpha);
        anim.reset();
        LinearLayout l = (LinearLayout) findViewById(R.id.lin_lay);
        l.clearAnimation();
        l.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this,R.anim.translate);
        anim.reset();
        TextView iv = (TextView) findViewById(R.id.comrade);
        iv.clearAnimation();
        iv.startAnimation(anim);

        splashThread = new Thread()
        {
            @Override
            public void run() {
                try{
                    int waited = 0;

                    while (waited<3500)
                    {
                        sleep(1000);
                        waited+=1000;
                    }
                    Intent intent = new Intent(SplashScreen.this,login_activity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    SplashScreen.this.finish();
                } catch (InterruptedException e) {

                }
                finally {
                    SplashScreen.this.finish();
                }
            }
        };//end thread
        splashThread.start();
    }//end start animations
}//end splash screen
