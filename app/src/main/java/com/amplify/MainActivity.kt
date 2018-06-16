package com.amplify

import android.Manifest

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import android.app.SearchManager
import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Handler
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.*
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import com.amplify.utils.location.LocationLiveData
import com.sp.loylap.activities.BaseActivity
import com.tbruyelle.rxpermissions2.RxPermissions


class MainActivity : BaseActivity() {

    /**
     * The [android.support.v4.view.PagerAdapter] that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * [android.support.v4.app.FragmentStatePagerAdapter].
     */
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_relevant -> {
                //Load hot
                val fragment = HotFragment.newInstance(1)
                supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, fragment, fragment::class.java.simpleName)
                        .commitAllowingStateLoss()

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_recent -> {
                //Load recent
                val fragment = RecentFragment.newInstance(1)
                supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, fragment, fragment::class.java.simpleName)
                        .commitAllowingStateLoss()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_paid -> {
                //Load paid
                val fragment = PaidFragment.newInstance(1)
                supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, fragment, fragment::class.java.simpleName)
                        .commitAllowingStateLoss()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_mine -> {
                //Load mine
                val fragment = MineFragment.newInstance(1)
                supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, fragment, fragment::class.java.simpleName)
                        .commitAllowingStateLoss()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.selectedItemId = R.id.navigation_relevant

        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayShowTitleEnabled(false)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)

        // Associate searchable configuration with the SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = getString(R.string.search_hint)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        //if (id == R.id.action_settings) {
          //  return true
        //}

        return super.onOptionsItemSelected(item)
    }

}
