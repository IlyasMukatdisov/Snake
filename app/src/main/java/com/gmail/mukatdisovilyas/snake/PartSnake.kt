package com.gmail.mukatdisovilyas.snake

import android.graphics.Bitmap
import android.graphics.Rect

class PartSnake(var bm: Bitmap, var x: Int, var y: Int)
{
    lateinit var rBody: Rect
    lateinit var rTop: Rect
    lateinit var rBottom: Rect
    lateinit var rLeft: Rect
    lateinit var rRight: Rect

    fun getBody(): Rect =
        Rect(this.x, this.y, this.x + GameView.sizeOfMap, this.y + GameView.sizeOfMap)

    fun getTop(): Rect =
        Rect(
            this.x,
            this.y - 10 * Constants.SCREEN_HEIGHT / 1920,
            this.x + GameView.sizeOfMap,
            this.y
        )

    fun getBottom(): Rect =
        Rect(
            this.x,
            this.y + GameView.sizeOfMap,
            this.x + GameView.sizeOfMap,
            this.y + GameView.sizeOfMap + 10 * Constants.SCREEN_HEIGHT / 1920
        )

    fun getLeft(): Rect =
        Rect(
            this.x - 10 * Constants.SCREEN_WIDTH / 1080,
            this.y,
            this.x,
            this.y + GameView.sizeOfMap
        )

    fun getRight(): Rect =
        Rect(
            this.x + GameView.sizeOfMap,
            this.y,
            this.x + GameView.sizeOfMap + 10 * Constants.SCREEN_WIDTH / 1080,
            this.y + GameView.sizeOfMap
        )
}