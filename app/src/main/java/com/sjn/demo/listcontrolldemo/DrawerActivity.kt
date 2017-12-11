package com.sjn.demo.listcontrolldemo

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import com.mikepenz.materialdrawer.model.BaseDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem
import kotlin.reflect.KClass

abstract class DrawerActivity : AppCompatActivity(), FragmentManager.OnBackStackChangedListener {

    private var mToolbar: Toolbar? = null
    private var mDrawer: DrawerHelper.Drawer? = null

    abstract fun onOptionsItemSelected(itemId: Int): Boolean

    private fun <T : Activity> Activity.startActivity(classRef: KClass<T>, bundle: Bundle? = null) {
        val intent = Intent(this, classRef.java).setAction(Intent.ACTION_VIEW)
        bundle?.let {
            intent.putExtra("args", bundle)
        }
        startActivity(intent)
    }

    protected fun initializeToolbar() {
        mToolbar = findViewById<View>(R.id.toolbar) as Toolbar
        if (mToolbar == null) {
            throw IllegalStateException("Layout is required to include a Toolbar with id " + "'toolbar'")
        }
        mToolbar!!.let {
            it.inflateMenu(R.menu.drawer)
            setSupportActionBar(it)
            mDrawer = DrawerHelper.Drawer(this, it, object : DrawerHelper.Drawer.Listener {
                override fun changeFragmentByDrawer(drawerItem: IDrawerItem<*, *>) {
//                    val drawerMenu = DrawerMenu.of(menu) ?: return
//                    navigateToBrowser(drawerMenu.fragment, false, menu)
                    if (drawerItem is BaseDrawerItem) {
                        setToolbarTitle(drawerItem.name.text)
                    }
                }
            })
        }
    }

    open fun setToolbarTitle(title: CharSequence?) = setTitle(title ?: mDrawer?.selectingDrawerName ?: "")

    override fun onBackStackChanged() {
        mDrawer?.updateDrawerToggleState()
    }

    override fun onResume() {
        super.onResume()
        supportFragmentManager.addOnBackStackChangedListener(this)
    }

    override fun onPause() {
        super.onPause()
        supportFragmentManager.removeOnBackStackChangedListener(this)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        mDrawer?.sync()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        mDrawer?.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        mDrawer?.let {
            if (it.onOptionItemSelected(item)) {
                return true
            }
        }
        if (item != null && item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        } else if (item != null) {
            if (!onOptionsItemSelected(item.itemId)) {
                return super.onOptionsItemSelected(item)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        mDrawer?.let {
            if (it.closeDrawer()) {
                return
            }
        }
        val fragmentManager = supportFragmentManager
        if (fragmentManager.backStackEntryCount > 0) {
            fragmentManager.popBackStack()
        } else {
            moveTaskToBack(true)
        }
    }

    override fun setTitle(title: CharSequence) {
        super.setTitle(title)
        mToolbar?.title = title
    }

    override fun setTitle(titleId: Int) {
        super.setTitle(titleId)
        mToolbar?.setTitle(titleId)
    }

}