package com.abrebo.rfootballclubrfc

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.abrebo.rfootballclubrfc.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        binding.imageView.startAnimation(fadeIn)

        // Animasyonun bitişini dinle
        fadeIn.setAnimationListener(object : android.view.animation.Animation.AnimationListener {
            override fun onAnimationStart(animation: android.view.animation.Animation?) {
                // Animasyon başladığında yapılacaklar (gerekirse)
            }

            override fun onAnimationEnd(animation: android.view.animation.Animation?) {
                // Animasyon tamamlandığında MainFragment'e yönlendir
                val intent=Intent(this@SplashActivity,MainActivity::class.java)
                startActivity(intent)
                finish()
            }

            override fun onAnimationRepeat(animation: android.view.animation.Animation?) {
                // Animasyon tekrarladığında yapılacaklar (genellikle gerekmez)
            }
        })
    }
}