package com.example.innobuzzassignment

import android.accessibilityservice.AccessibilityService
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.innobuzzassignment.Service.Accessibility
import com.example.innobuzzassignment.fragments.DetailedPostFrag
import com.example.innobuzzassignment.fragments.DisplayFrag


class MainActivity : AppCompatActivity() {
    private lateinit var displayFrag: DisplayFrag
    private lateinit var detailedPostFrag: DetailedPostFrag
    private lateinit var frameLayout: FrameLayout
    private lateinit var requestAccessbility : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        restartAccessibilityService(this,Accessibility::class.java)
        initialise()


        supportFragmentManager.beginTransaction().replace(frameLayout.id,displayFrag).commit()

        requestAccessbility.setOnClickListener {

            if(checkAccessibilityPermission())
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this, "Requesting Permission", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initialise() {
        displayFrag = DisplayFrag()
        detailedPostFrag = DetailedPostFrag()
        frameLayout = findViewById(R.id.fragmentContainer)
        requestAccessbility = findViewById(R.id.requestAccessbilityButton)
    }

    private fun checkAccessibilityPermission(): Boolean {
        var accessEnabled = 0
        try {
            accessEnabled =
                Settings.Secure.getInt(this.contentResolver, Settings.Secure.ACCESSIBILITY_ENABLED)
        } catch (e: Settings.SettingNotFoundException) {
            e.printStackTrace()
        }
        return if (accessEnabled == 0) {
            // if not construct intent to request permission
            val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            // request permission via start activity for result
            startActivity(intent)
            false
        } else {
            true
        }
    }
}