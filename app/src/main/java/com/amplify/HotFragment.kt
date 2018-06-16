package com.amplify

import android.Manifest
import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.amplify.model.Post
import com.amplify.requests.AsyncFetchPosts
import com.amplify.requests.OnTaskCompleted
import com.amplify.requests.RequestPosts
import com.amplify.utils.location.LocationLiveData
import com.sp.loylap.activities.BaseActivity
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.fragment_hot.view.*

/**
 * A placeholder fragment containing a simple view.
 */
class HotFragment : Fragment(), RequestPosts {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_hot, container, false)

        RxPermissions(activity as BaseActivity).requestEach(Manifest.permission.ACCESS_FINE_LOCATION)
                .subscribe { permission ->
                    when {
                        permission.granted -> subscribeToLocation()
                        permission.shouldShowRequestPermissionRationale -> Toast.makeText(context, "Denied permission without ask never again", Toast.LENGTH_LONG).show()
                        else -> Toast.makeText(context, "Denied permission with ask never again", Toast.LENGTH_LONG).show()
                    }
                }

        val recyclerView = rootView.hot_recycler_view
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        //recyclerView.adapter =

        return rootView
    }

    private fun attemptHotFetch() {

        (activity as BaseActivity).showUIForLongRunningTask(true, R.string.getting_posts_around_you)
        AsyncFetchPosts(context, this, "relevant", 0.0, 0.0, 500)
    }

    override fun onPostsSuccess(posts: MutableList<Post>) {
        (activity as BaseActivity).hideUIForLongRunningTask()
        Toast.makeText(context, "Posts retrieved!", Toast.LENGTH_LONG).show()
    }

    override fun onPostsFailure(result: OnTaskCompleted.Result, errorMessage: String) {
        (activity as BaseActivity).hideUIForLongRunningTask()
        Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
    }

    private fun subscribeToLocation() {
        //LocationLiveData is instantiated here because it relies on the Android SDK
        LocationLiveData(activity as Context).observe(this, Observer {
            Log.d("Main Activity", "location found!!!! $it")
            it?.let {
                //Fetch the list of posts now based on current fragment (umm actually the fragment should be doing this)
                attemptHotFetch()
            }
        })
    }


    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        fun newInstance(sectionNumber: Int): HotFragment {
            val fragment = HotFragment()
            val args = Bundle()
            args.putInt(ARG_SECTION_NUMBER, sectionNumber)
            fragment.arguments = args
            return fragment
        }
    }
}