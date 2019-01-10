package edu.ucsmub.kqvoting.extra

import android.util.DisplayMetrics
import android.app.Activity
import android.content.Context
import android.view.View


class Ratio {

    var mywidth = 720
    var myheight = 1280
    lateinit var ratio: Ratio
    lateinit var context: Context

    var width = 0
    var height = 0


    constructor(_context: Context) {
        context = _context
    }

    constructor() {

    }

    private fun getWidth(screenWidth: Int, i: Int): Int {
        val res = screenWidth.toFloat() / mywidth
        return (res * i).toInt()
    }


    private fun getHeight(screenHeight: Int, j: Int): Int {
        val res = screenHeight.toFloat() / myheight
        return (res * j).toInt()
    }


    fun calculateSize(view: View, activity: Activity, view_width: Int, view_height: Int) {
        this.ratio = Ratio()
        val metrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(metrics)
        val fix = view.layoutParams
        width = metrics.widthPixels
        height = metrics.heightPixels
        fix.width = ratio.getWidth(width, view_width)
        fix.height = ratio.getHeight(height, view_height)
        view.layoutParams = fix
    }

    fun calculateSize(viewArr: Array<View>, activity: Activity, view_width: Int, view_height: Int) {

        for (view in viewArr) {
            this.ratio = Ratio()
            val metrics = DisplayMetrics()
            activity.windowManager.defaultDisplay.getMetrics(metrics)
            val fix = view.layoutParams
            width = metrics.widthPixels
            height = metrics.heightPixels
            fix.width = ratio.getWidth(width, view_width)
            fix.height = ratio.getHeight(height, view_height)
            view.layoutParams = fix
        }
    }

    companion object {

        fun getScreenWidth(activity: Activity): Int {
            val metrics = DisplayMetrics()
            activity.windowManager.defaultDisplay.getMetrics(metrics)
            return metrics.widthPixels
        }

        fun getScreenHeight(activity: Activity): Int {
            val metrics = DisplayMetrics()
            activity.windowManager.defaultDisplay.getMetrics(metrics)
            return metrics.heightPixels
        }


        fun getImageWidth(slotwidth: Int): Int {
            val res = slotwidth.toFloat() / 3
            return (res - 20).toInt()
        }

    }

}