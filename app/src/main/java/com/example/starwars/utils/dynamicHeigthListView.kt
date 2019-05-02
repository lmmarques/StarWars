package com.example.starwars.utils

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.view.View
import android.widget.ListView

class dynamicHeigthListView : Activity() {


    fun setListViewHeightBasedOnChildren(listView: ListView, context:Context) {
        val listAdapter = listView.adapter
        if (listAdapter != null) {
            var totalHeight = 0

            val desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.width, View.MeasureSpec.UNSPECIFIED)
            val count = listAdapter.count

            for (i in 0 until count) {
                val listItem = listAdapter.getView(i, null, listView)
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED)
                totalHeight += listItem.measuredHeight
            }

            val screenSize =context.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK //obtem a resolucao do dispositivo


            if(count>10 && screenSize== Configuration.SCREENLAYOUT_SIZE_LARGE) totalHeight= 800
            if(count>10 && screenSize== Configuration.SCREENLAYOUT_SIZE_XLARGE) totalHeight= 1300
            if(count>8 && screenSize== Configuration.SCREENLAYOUT_SIZE_NORMAL) totalHeight= 1300

            val params = listView.layoutParams
            params.height = totalHeight + listView.dividerHeight * (listAdapter.count - 1)
            listView.layoutParams = params
            listView.requestLayout()
        }
    }
}