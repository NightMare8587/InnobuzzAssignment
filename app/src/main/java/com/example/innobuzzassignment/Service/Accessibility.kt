package com.example.innobuzzassignment.Service

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent
import android.widget.Toast

class Accessibility : AccessibilityService() {
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (event?.eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            val packageName: String = java.lang.String.valueOf(event.packageName)
            if (packageName == "com.whatsapp") {
                Toast.makeText(applicationContext, "WhatsApp Launched.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onInterrupt() {

    }


}