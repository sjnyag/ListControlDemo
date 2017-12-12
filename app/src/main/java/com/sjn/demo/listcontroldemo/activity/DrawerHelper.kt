package com.sjn.demo.listcontroldemo.activity

import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.support.v4.media.MediaMetadataCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import com.mikepenz.materialdrawer.AccountHeader
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.Nameable
import com.sjn.demo.listcontroldemo.R
import com.sjn.demo.listcontroldemo.demo.DrawerMenu
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target

object DrawerHelper {

    class Drawer(private val activity: AppCompatActivity, toolbar: Toolbar, private val listener: Listener) {
        interface Listener {
            fun changeFragmentByDrawer(drawerItem: IDrawerItem<*, *>)
            fun setToolbarTitle(title: CharSequence?)
        }

        private var mDrawer: com.mikepenz.materialdrawer.Drawer? = null
        private var mDrawerToggle: ActionBarDrawerToggle? = null
        private var mNextDrawerMenu: IDrawerItem<*, *>? = null
        private var mSelectingDrawerMenu: Long = 0
        private var mAccountHeader: AccountHeader? = null

        init {
            mSelectingDrawerMenu = currentMenuId
            createDrawer(toolbar)
        }

        private val currentMenuId: Long
            get() = if (mDrawer != null) {
                mDrawer!!.currentSelection
            } else DrawerMenu.first().menuId.toLong()

        val selectingDrawerName: String
            get() = if (mDrawer != null && mDrawer!!.getDrawerItem(mDrawer!!.currentSelection) is Nameable<*>) {
                (mDrawer!!.getDrawerItem(mDrawer!!.currentSelection) as Nameable<*>).name.getText(activity)
            } else activity.getString(R.string.app_name)

        fun updateDrawerToggleState() {
            if (mDrawerToggle == null) {
                return
            }
            val isRoot = activity.supportFragmentManager.backStackEntryCount == 0
            mDrawerToggle?.isDrawerIndicatorEnabled = isRoot
            activity.supportActionBar?.let {
                it.setDisplayShowHomeEnabled(!isRoot)
                it.setDisplayHomeAsUpEnabled(!isRoot)
                it.setHomeButtonEnabled(!isRoot)
            }
            if (isRoot) {
                mDrawerToggle?.syncState()
            }
        }

        private fun createDrawer(toolbar: Toolbar) {
            createAccountHeader()
            mAccountHeader?.let {
                mDrawer = DrawerBuilder().withActivity(activity)
                        .withAccountHeader(it)
                        .withToolbar(toolbar)
                        .inflateMenu(R.menu.drawer)
                        .withSelectedItem(mSelectingDrawerMenu)
                        .withOnDrawerNavigationListener {
                            //this method is only called if the Arrow icon is shown. The hamburger is automatically managed by the MaterialDrawer
                            //if the back arrow is shown. close the activity
                            activity.onBackPressed()
                            //return true if we have consumed the event
                            true
                        }
                        .withOnDrawerItemClickListener { _, _, drawerItem ->
                            mNextDrawerMenu = drawerItem
                            false
                        }
                        .withOnDrawerListener(object : com.mikepenz.materialdrawer.Drawer.OnDrawerListener {
                            override fun onDrawerOpened(view: View) {}

                            override fun onDrawerClosed(view: View) {
                                mNextDrawerMenu?.let {
                                    if (mSelectingDrawerMenu == it.identifier) {
                                        return
                                    }
                                    mSelectingDrawerMenu = it.identifier
                                    listener.setToolbarTitle(selectingDrawerName)
                                    mDrawer?.setSelection(mSelectingDrawerMenu)
                                    listener.changeFragmentByDrawer(it)
                                }
                            }

                            override fun onDrawerSlide(view: View, v: Float) {}
                        })
                        .build()
            }
            mDrawer?.let {
                mDrawerToggle = it.actionBarDrawerToggle
                it.setSelection(mSelectingDrawerMenu)
                listener.setToolbarTitle(selectingDrawerName)
            }
        }

        private fun createAccountHeader() {
            mAccountHeader = AccountHeaderBuilder()
                    .withSelectionListEnabledForSingleProfile(false)
                    .withActivity(activity)
                    .withHeaderBackground(R.drawable.drawer_header).build()
        }

        fun sync() {
            mDrawerToggle?.syncState()
        }

        fun onConfigurationChanged(newConfig: Configuration) {
            mDrawerToggle?.onConfigurationChanged(newConfig)
        }

        fun setSelection(selection: Long) {
            mDrawer?.setSelection(selection)
        }

        fun closeDrawer(): Boolean {
            mDrawer?.let {
                if (it.isDrawerOpen) {
                    it.closeDrawer()
                    return true
                }
            }
            return false
        }

        fun onOptionItemSelected(item: MenuItem?): Boolean {
            if (mDrawerToggle != null && mDrawerToggle!!.onOptionsItemSelected(item)) {
                return true
            }
            return false
        }
    }

    class AccountHeaderLoader(private val accountHeader: AccountHeader, private val metadata: MediaMetadataCompat) : Target {
        override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
            val profileDrawerItem = createProfileItem(metadata)
            profileDrawerItem.withIcon(bitmap)
            accountHeader.clear()
            accountHeader.addProfiles(profileDrawerItem)
        }

        override fun onBitmapFailed(errorDrawable: Drawable?) {
            createDefaultHeader(metadata, accountHeader)
        }

        override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
            createDefaultHeader(metadata, accountHeader)
        }
    }

    private fun createProfileItem(metadata: MediaMetadataCompat): ProfileDrawerItem {
        val profileDrawerItem = ProfileDrawerItem()
        metadata.description?.let {
            it.title?.let {
                profileDrawerItem.withName(it.toString())
            }
            it.subtitle?.let {
                profileDrawerItem.withEmail(it.toString())
            }
        }
        return profileDrawerItem
    }

    private fun createDefaultHeader(metadata: MediaMetadataCompat, accountHeader: AccountHeader) {
        val profileDrawerItem = createProfileItem(metadata)
        profileDrawerItem.withIcon(R.mipmap.ic_launcher)
        accountHeader.clear()
        accountHeader.addProfiles(profileDrawerItem)
    }

}