package com.example.myapplication

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity(),
    ProfileFragment.ProfileListener,
    DevicesFragment.DeviceListener,
    SecurityFragment.SecurityListener {

    private lateinit var tvStatus: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tvStatus = findViewById(R.id.tvStatus)

        findViewById<ImageButton>(R.id.btnHome).setOnClickListener {
            replaceFragment(MainMenuFragment(), false)
        }
        findViewById<ImageButton>(R.id.btnProfile).setOnClickListener {
            replaceFragment(ProfileFragment(), true)
        }
        findViewById<ImageButton>(R.id.btnDevices).setOnClickListener {
            replaceFragment(DevicesFragment(), true)
        }
        findViewById<ImageButton>(R.id.btnSecurity).setOnClickListener {
            replaceFragment(SecurityFragment(), true)
        }

        if (savedInstanceState == null) {
            replaceFragment(MainMenuFragment(), false)
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
                if (currentFragment !is MainMenuFragment) {
                    replaceFragment(MainMenuFragment(), false)
                } else {
                    isEnabled = false
                    onBackPressedDispatcher.onBackPressed()
                }
            }
        })
    }

    private fun replaceFragment(fragment: Fragment, addToBackStack: Boolean) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        if (addToBackStack) {
            transaction.addToBackStack(null)
        }
        transaction.commit()
    }

    override fun onNameChanged(name: String) {
        tvStatus.text = "Nome de usuário alterado para: $name"
    }

    override fun onDeviceToggled(name: String, active: Boolean) {
        val status = if (active) "LIGADO" else "DESLIGADO"
        tvStatus.text = "$name agora está $status"
    }

    override fun onSecurityToggled(active: Boolean) {
        val status = if (active) "Ativada" else "Desativada"
        tvStatus.text = "Segurança 2FA está $status"
    }
}
