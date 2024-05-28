package com.example.e_commerce.ui.home

import android.animation.ObjectAnimator
import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.e_commerce.R
import com.example.e_commerce.data.datasurce.datastore.UserPreferenceDataSource
import com.example.e_commerce.data.repository.user.UserRepositoryPreferenceIml
import com.example.e_commerce.ui.home.common.UserViewModel
import com.example.e_commerce.ui.login.AuthActivity
import com.example.e_commerce.ui.login.viewmodel.LogInViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val userViewModel: UserViewModel by viewModels {
        LogInViewModel.LogInViewModelFactory(
            UserRepositoryPreferenceIml(
                UserPreferenceDataSource(
                    this
                )
            )
        )
    }

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        initSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        lifecycleScope.launch(Dispatchers.Main) {
            val isLogin = userViewModel.isUserLogin().first()
            Log.d("TAG", "onCreate is LogIn $isLogin")
            if (isLogin) {
                setContentView(R.layout.activity_main)
            } else {
                getAuthActivity()
            }
        }


    }

    fun getAuthActivity() {
        val intent = Intent(this, AuthActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val option = ActivityOptions.makeCustomAnimation(
            this, android.R.anim.fade_in, android.R.anim.fade_out
        )
        startActivity(intent, option.toBundle())
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