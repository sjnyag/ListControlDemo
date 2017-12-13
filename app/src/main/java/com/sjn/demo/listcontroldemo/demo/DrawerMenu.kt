package com.sjn.demo.listcontroldemo.demo

import android.support.design.widget.AppBarLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import com.sjn.demo.listcontroldemo.R
import com.sjn.demo.listcontroldemo.activity.DrawerActivity
import com.sjn.demo.listcontroldemo.demo.actionmode.ActionModeFragment
import com.sjn.demo.listcontroldemo.demo.endlessscroll.EndlessScrollFragment
import com.sjn.demo.listcontroldemo.demo.expand.ExpandFragment
import com.sjn.demo.listcontroldemo.demo.fastscroll.FastScrollFragment
import com.sjn.demo.listcontroldemo.demo.filter.FilterFragment
import com.sjn.demo.listcontroldemo.demo.stickyheader.StickyHeaderFragment
import com.sjn.demo.listcontroldemo.demo.swipetorefresh.SwipeToRefreshFragment

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
    },
    FAST_SCROLL(R.id.fast_scroll) {
        override fun open(fragmentActivity: FragmentActivity) {
            fragmentActivity.navigateToBrowser(FastScrollFragment(), false)
        }
    },
    FILTER(R.id.filter) {
        override fun open(fragmentActivity: FragmentActivity) {
            fragmentActivity.navigateToBrowser(FilterFragment(), false)
        }
    },
    STICKY_HEADER(R.id.sticky_header) {
        override fun open(fragmentActivity: FragmentActivity) {
            fragmentActivity.navigateToBrowser(StickyHeaderFragment(), false)
        }
    },
    EXPAND(R.id.expand) {
        override fun open(fragmentActivity: FragmentActivity) {
            fragmentActivity.navigateToBrowser(ExpandFragment(), false)
        }
    },
    ENDLESS_SCROLL(R.id.endless_scroll) {
        override fun open(fragmentActivity: FragmentActivity) {
            fragmentActivity.navigateToBrowser(EndlessScrollFragment(), false)
        }
    },
    SWIPE_TO_REFRESH(R.id.swipe_to_refresh) {
        override fun open(fragmentActivity: FragmentActivity) {
            fragmentActivity.navigateToBrowser(SwipeToRefreshFragment(), false)
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
