package com.jankrb.fff_app.ui.home

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.jankrb.fff_app.MainActivity
import com.jankrb.fff_app.R
import com.jankrb.fff_app.Settings
import com.jankrb.fff_app.utils.FontCache

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private val createdAts: MutableList<String> = mutableListOf()
    private val titles: MutableList<String> = mutableListOf()
    private val descriptions: MutableList<String> = mutableListOf()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        // Home Sync Btn
        var sync_btn: Button = root.findViewById(R.id.sync_data_btn)

        val gd = GradientDrawable(
                GradientDrawable.Orientation.BL_TR, intArrayOf(Color.parseColor(Settings.gradientStart), Color.parseColor(Settings.gradientStop)))
        gd.cornerRadius = 50f
        sync_btn?.background = gd

        // Stats Box
        var statsTV: TextView = root.findViewById(R.id.stats_box_title)
        statsTV.typeface = FontCache.getTypeface("Montserrat-Medium.ttf", root.context)

        // Recent Information ScrollView
        // Test Element
        titles.add("Title 1")
        titles.add("Title 2")
        titles.add("Title 3")
        titles.add("Title 4")
        descriptions.add("Description 1")
        descriptions.add("Description 2")
        descriptions.add("Description 3")
        descriptions.add("Description 4")
        createdAts.add("Created At 1")
        createdAts.add("Created At 2")
        createdAts.add("Created At 3")
        createdAts.add("Created At 4")

        // Reverse that latest is up
        titles.reverse()
        descriptions.reverse()
        createdAts.reverse()

        // Load ScrollView
        val infoAdapter = HomeListAdapter((activity as MainActivity), titles.toTypedArray(), descriptions.toTypedArray(), createdAts.toTypedArray())
        val listView = root.findViewById<ListView>(R.id.home_recent_informations)
        listView.adapter = infoAdapter

        return root
    }
}