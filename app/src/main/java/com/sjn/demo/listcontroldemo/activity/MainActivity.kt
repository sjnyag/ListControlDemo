package com.sjn.demo.listcontroldemo.activity

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.view.MenuItemCompat
import android.support.v4.view.ViewCompat
import android.support.v7.widget.SearchView
import android.text.InputType
import android.view.Menu
import android.view.View
import android.view.inputmethod.EditorInfo
import com.sjn.demo.listcontroldemo.R
import com.sjn.demo.listcontroldemo.demo.DrawerMenu
import eu.davidea.flexibleadapter.FlexibleAdapter

class MainActivity : DrawerActivity(), SearchView.OnQueryTextListener {

    private var mFab: FloatingActionButton? = null
    private var mSearchView: SearchView? = null
    var mAdapter: FlexibleAdapter<*>? = null

    override fun onQueryTextSubmit(query: String?): Boolean {
        return onQueryTextChange(query)
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        mAdapter?.let {
            if (it.hasNewSearchText(newText)) {
                it.searchText = newText
                it.filterItems(200)
            }
        }
        return true
    }

    override fun onOptionsItemSelected(itemId: Int): Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeToolbar()
        initializeFab()
        DrawerMenu.first().open(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        menu?.let { initSearchView(it) }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        if (mSearchView == null) {
            return super.onPrepareOptionsMenu(menu)
        }
        mAdapter?.let {
            if (!it.hasSearchText()) {
                mSearchView!!.isIconified = true// This also clears the text in SearchView widget
            } else {
                menu.findItem(R.id.search).expandActionView()
                mSearchView!!.setQuery(it.searchText, false)//submit = false!!!
                mSearchView!!.clearFocus()//Optionally the keyboard can be closed
            }
        }
        return super.onPrepareOptionsMenu(menu)
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

    private fun initSearchView(menu: Menu) {
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu.findItem(R.id.search)
        if (searchItem != null) {
            mSearchView = MenuItemCompat.getActionView(searchItem) as SearchView
            mSearchView!!.inputType = InputType.TYPE_TEXT_VARIATION_FILTER
            mSearchView!!.imeOptions = EditorInfo.IME_ACTION_DONE or EditorInfo.IME_FLAG_NO_FULLSCREEN
            mSearchView!!.queryHint = "Search..."
            mSearchView!!.setSearchableInfo(searchManager.getSearchableInfo(componentName))
            mSearchView!!.setOnQueryTextListener(this)
        }
    }

    private fun hideFabSilently() {
        mFab?.alpha = 0f
    }
}