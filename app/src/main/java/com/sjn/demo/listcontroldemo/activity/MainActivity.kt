package com.sjn.demo.listcontroldemo.activity

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.view.ViewCompat
import android.view.View
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem
import com.sjn.demo.listcontroldemo.R
import com.sjn.demo.listcontroldemo.demo.DrawerMenu

class MainActivity : DrawerActivity() {
    private var mFab: FloatingActionButton? = null

    override fun onOptionsItemSelected(itemId: Int): Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeToolbar()
        initializeFab()
        DrawerMenu.first().open(this)
    }

    fun hideFab() {
        runOnUiThread {
            ViewCompat.animate(mFab)
                    .scaleX(0f).scaleY(0f)
                    .alpha(0f).setDuration(100)
                    .start()
        }
    }

    fun showFab() {
        runOnUiThread {
            ViewCompat.animate(mFab)
                    .scaleX(1f).scaleY(1f)
                    .alpha(1f).setDuration(200)
                    .setStartDelay(300L)
                    .start()
        }
    }

    private fun initializeFab() {
        mFab = findViewById<View>(R.id.fab) as FloatingActionButton
        hideFabSilently()
    }

    private fun hideFabSilently() {
        mFab?.alpha = 0f
    }
}