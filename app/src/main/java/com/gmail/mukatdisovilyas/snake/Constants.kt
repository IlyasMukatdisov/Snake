package com.gmail.mukatdisovilyas.snake

import android.content.res.Resources

class Constants
{
    companion object
    {
        private val displayMetrics = Resources.getSystem().displayMetrics
        val SCREEN_WIDTH = displayMetrics.widthPixels
        val SCREEN_HEIGHT = displayMetrics.heightPixels
    }
}