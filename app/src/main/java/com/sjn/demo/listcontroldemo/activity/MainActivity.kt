package com.sjn.demo.listcontroldemo.activity

import android.os.Bundle
import com.sjn.demo.listcontroldemo.R
import com.sjn.demo.listcontroldemo.demo.DrawerMenu

class MainActivity : DrawerActivity() {
    override fun onOptionsItemSelected(itemId: Int): Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeToolbar()
        DrawerMenu.first().open(this)
    }
}