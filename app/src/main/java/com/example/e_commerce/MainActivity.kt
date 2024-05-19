package com.example.e_commerce

import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.lifecycle.lifecycleScope
import com.training.ecommerce.utils.CrashlyticsUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        initSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        lifecycleScope.launch(Dispatchers.Main){
            Log.d("TAG" , "Crash in main ")
            CrashlyticsUtils.sendLogToCrashlytics("crash button clicked " , "button" , "clicked")
        }


    }



    //create splashScreen above android12
    fun initSplashScreen() {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.S) {
            initSplashScreen()
            //add callBack that`s called when the splashScreen in anim to the app content
            splashScreen.setOnExitAnimationListener { splashScreenView ->
                //create custom anim
                val slidUp = ObjectAnimator.ofFloat(
                    splashScreenView, View.TRANSLATION_Y, 0f, -splashScreenView.height.toFloat()
                )
                slidUp.interpolator = AnticipateInterpolator()
                slidUp.duration = 3000L

                //remove splashScreen at the end
                slidUp.doOnEnd { splashScreenView.remove() }

                //run your splash
                slidUp.start()
            }
        } else {
            setTheme(R.style.Theme_ECommerce)
        }
    }

}