package com.gmail.mukatdisovilyas.snake

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect

class Apple(var bitmap: Bitmap, var x: Int, var y: Int)
{
    lateinit var rect: Rect

    fun getR(): Rect
    {
        return Rect(this.x, this.y, this.x + GameView.sizeOfMap, this.y + GameView.sizeOfMap)
    }

    fun draw(canvas: Canvas?)
    {
        canvas?.drawBitmap(bitmap, x.toFloat(), y.toFloat(), null)
    }

    fun reset(nx: Int, ny: Int)
    {
        this.x = nx
        this.y = ny
    }
}