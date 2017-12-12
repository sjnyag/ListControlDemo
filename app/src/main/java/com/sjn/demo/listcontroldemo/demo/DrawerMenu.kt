package com.sjn.demo.listcontroldemo.demo

import android.support.design.widget.AppBarLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import com.sjn.demo.listcontroldemo.R
import com.sjn.demo.listcontroldemo.activity.DrawerActivity
import com.sjn.demo.listcontroldemo.demo.actionmode.ActionModeFragment
import com.sjn.demo.listcontroldemo.demo.base.BaseFragment

enum class DrawerMenu(val menuId: Int) {
    HOME(R.id.navigation_home) {
        override fun open(fragmentActivity: FragmentActivity) {
            fragmentActivity.navigateToBrowser(BaseFragment(), false)
        }
    },
    ACTION_MODE(R.id.action_mode) {
        override fun open(fragmentActivity: FragmentActivity) {
            fragmentActivity.navigateToBrowser(ActionModeFragment(), false)
        }
    };

    abstract fun open(fragmentActivity: FragmentActivity)

    open fun FragmentActivity.navigateToBrowser(fragment: Fragment, addToBackStack: Boolean) {
        if (!addToBackStack) {
            supportFragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment, DrawerActivity.FRAGMENT_TAG)
        if (addToBackStack) {
            transaction.addToBackStack(null)
        }
        transaction.commit()
        findViewById<AppBarLayout>(R.id.app_bar).setExpanded(true, true)
    }

    companion object {

        fun first(): DrawerMenu {
            return HOME
        }

        fun of(menuId: Long): DrawerMenu? {
            return DrawerMenu.values().firstOrNull { it.menuId.toLong() == menuId }
        }
    }

}
