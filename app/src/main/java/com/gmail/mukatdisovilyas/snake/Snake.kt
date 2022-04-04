package com.gmail.mukatdisovilyas.snake

import android.graphics.Bitmap
import android.graphics.Canvas

class Snake(
    private var bm: Bitmap,
    private var x: Int,
    private var y: Int,
    private var length: Int,
    var move_left: Boolean,
    var move_right: Boolean,
    var move_top: Boolean,
    var move_bottom: Boolean
)
{
    var bm_head_up: Bitmap
    var bm_head_down: Bitmap
    var bm_head_left: Bitmap
    var bm_head_right: Bitmap
    var bm_body_vertical: Bitmap
    var bm_body_horizontal: Bitmap
    var bm_body_top_left: Bitmap
    var bm_body_top_right: Bitmap
    var bm_body_bottom_right: Bitmap
    var bm_body_bottom_left: Bitmap
    var bm_tail_right: Bitmap
    var bm_tail_left: Bitmap
    var bm_tail_down: Bitmap
    var bm_tail_up: Bitmap
    var arrPartSnake: ArrayList<PartSnake> = ArrayList()

    init
    {
        bm_body_bottom_left = Bitmap.createBitmap(
            bm,
            0,
            0,
            GameView.sizeOfMap,
            GameView.sizeOfMap
        )
        bm_body_bottom_right =
            Bitmap.createBitmap(
                bm,
                GameView.sizeOfMap,
                0,
                GameView.sizeOfMap,
                GameView.sizeOfMap
            )
        bm_body_horizontal = Bitmap.createBitmap(
            bm,
            2 * GameView.sizeOfMap,
            0,
            GameView.sizeOfMap,
            GameView.sizeOfMap
        )
        bm_body_top_left = Bitmap.createBitmap(
            bm,
            3 * GameView.sizeOfMap,
            0,
            GameView.sizeOfMap,
            GameView.sizeOfMap
        )
        bm_body_top_right = Bitmap.createBitmap(
            bm,
            4 * GameView.sizeOfMap,
            0,
            GameView.sizeOfMap,
            GameView.sizeOfMap
        )
        bm_body_vertical = Bitmap.createBitmap(
            bm,
            5 * GameView.sizeOfMap,
            0,
            GameView.sizeOfMap,
            GameView.sizeOfMap
        )
        bm_head_down = Bitmap.createBitmap(
            bm,
            6 * GameView.sizeOfMap,
            0,
            GameView.sizeOfMap,
            GameView.sizeOfMap
        )
        bm_head_left = Bitmap.createBitmap(
            bm,
            7 * GameView.sizeOfMap,
            0,
            GameView.sizeOfMap,
            GameView.sizeOfMap
        )
        bm_head_right = Bitmap.createBitmap(
            bm,
            8 * GameView.sizeOfMap,
            0,
            GameView.sizeOfMap,
            GameView.sizeOfMap
        )
        bm_head_up = Bitmap.createBitmap(
            bm,
            9 * GameView.sizeOfMap,
            0,
            GameView.sizeOfMap,
            GameView.sizeOfMap
        )
        bm_tail_up = Bitmap.createBitmap(
            bm,
            10 * GameView.sizeOfMap,
            0,
            GameView.sizeOfMap,
            GameView.sizeOfMap
        )
        bm_tail_right = Bitmap.createBitmap(
            bm,
            11 * GameView.sizeOfMap,
            0,
            GameView.sizeOfMap,
            GameView.sizeOfMap
        )
        bm_tail_left = Bitmap.createBitmap(
            bm,
            12 * GameView.sizeOfMap,
            0,
            GameView.sizeOfMap,
            GameView.sizeOfMap
        )
        bm_tail_down = Bitmap.createBitmap(
            bm,
            13 * GameView.sizeOfMap,
            0,
            GameView.sizeOfMap,
            GameView.sizeOfMap
        )
        arrPartSnake.add(PartSnake(bm_head_right, x, y))

        for (i in 1 until length - 1)
        {
            arrPartSnake.add(
                PartSnake(
                    bm_body_horizontal,
                    arrPartSnake[i - 1].x - GameView.sizeOfMap,
                    y
                )
            )
        }
        arrPartSnake.add(
            PartSnake(
                bm_tail_right,
                arrPartSnake[length - 2].x - GameView.sizeOfMap,
                y
            )
        )
        this.move_right = true

    }

    fun draw(canvas: Canvas?)
    {
        for (i in 0 until length)
        {
            canvas?.drawBitmap(
                arrPartSnake[i].bm,
                arrPartSnake[i].x.toFloat(),
                arrPartSnake[i].y.toFloat(),
                null
            )
        }
    }

    fun set_move_left(move_left: Boolean)
    {
        s()
        this.move_left = move_left
    }

    fun set_move_rigth(move_right: Boolean)
    {
        s()
        this.move_right = move_right
    }

    fun set_move_top(move_top: Boolean)
    {
        s()
        this.move_top = move_top
    }

    fun set_move_bottom(move_bottom: Boolean)
    {
        s()
        this.move_bottom = move_bottom
    }

    private fun s()
    {
        this.move_left = false
        this.move_top = false
        this.move_bottom = false
        this.move_right = false
    }

    fun update()
    {
        for (i in length - 1 downTo 1)
        {
            arrPartSnake[i].x = arrPartSnake[i - 1].x
            arrPartSnake[i].y = arrPartSnake[i - 1].y
        }

        when
        {
            move_right  ->
            {
                arrPartSnake[0].x = arrPartSnake[0].x + GameView.sizeOfMap
                arrPartSnake[0].bm = bm_head_right
            }
            move_left   ->
            {
                arrPartSnake[0].x = arrPartSnake[0].x - GameView.sizeOfMap
                arrPartSnake[0].bm = bm_head_left
            }
            move_top    ->
            {
                arrPartSnake[0].y = arrPartSnake[0].y - GameView.sizeOfMap
                arrPartSnake[0].bm = bm_head_up
            }
            move_bottom ->
            {
                arrPartSnake[0].y = arrPartSnake[0].y + GameView.sizeOfMap
                arrPartSnake[0].bm = bm_head_down
            }
        }

        for (i in 1 until length - 1)
        {
            if (arrPartSnake[i].getLeft().intersect(arrPartSnake[i + 1].getBody())
                && arrPartSnake[i].getBottom().intersect(arrPartSnake[i - 1].getBody())
                || arrPartSnake[i].getLeft().intersect(arrPartSnake[i - 1].getBody())
                && arrPartSnake[i].getBottom().intersect(arrPartSnake[i + 1].getBody())
            )
            {
                arrPartSnake[i].bm = bm_body_bottom_left
            } else if (arrPartSnake[i].getRight().intersect(arrPartSnake[i + 1].getBody())
                && arrPartSnake[i].getBottom().intersect(arrPartSnake[i - 1].getBody())
                || arrPartSnake[i].getRight().intersect(arrPartSnake[i - 1].getBody())
                && arrPartSnake[i].getBottom().intersect(arrPartSnake[i + 1].getBody())
            )
            {
                arrPartSnake[i].bm = bm_body_bottom_right
            } else if (arrPartSnake[i].getLeft().intersect(arrPartSnake[i + 1].getBody())
                && arrPartSnake[i].getTop().intersect(arrPartSnake[i - 1].getBody())
                || arrPartSnake[i].getLeft().intersect(arrPartSnake[i - 1].getBody())
                && arrPartSnake[i].getTop().intersect(arrPartSnake[i + 1].getBody())
            )
            {
                arrPartSnake[i].bm = bm_body_top_left
            } else if (arrPartSnake[i].getRight().intersect(arrPartSnake[i + 1].getBody())
                && arrPartSnake[i].getTop().intersect(arrPartSnake[i - 1].getBody())
                || arrPartSnake[i].getRight().intersect(arrPartSnake[i - 1].getBody())
                && arrPartSnake[i].getTop().intersect(arrPartSnake[i + 1].getBody())
            )
            {
                arrPartSnake[i].bm = bm_body_top_right
            } else if (arrPartSnake[i].getTop().intersect(arrPartSnake[i + 1].getBody())
                && arrPartSnake[i].getBottom().intersect(arrPartSnake[i - 1].getBody())
                || arrPartSnake[i].getTop().intersect(arrPartSnake[i - 1].getBody())
                && arrPartSnake[i].getBottom().intersect(arrPartSnake[i + 1].getBody())
            )
            {
                arrPartSnake[i].bm = bm_body_vertical
            } else if (arrPartSnake[i].getLeft().intersect(arrPartSnake[i + 1].getBody())
                && arrPartSnake[i].getRight().intersect(arrPartSnake[i - 1].getBody())
                || arrPartSnake[i].getLeft().intersect(arrPartSnake[i - 1].getBody())
                && arrPartSnake[i].getRight().intersect(arrPartSnake[i + 1].getBody())
            )
            {
                arrPartSnake[i].bm = bm_body_horizontal
            }
        }

        when
        {
            arrPartSnake[length - 1].getRight().intersect(arrPartSnake[length - 2].getBody()) ->
            {
                arrPartSnake[length - 1].bm = bm_tail_right
            }
            arrPartSnake[length - 1].getLeft().intersect(arrPartSnake[length - 2].getBody())  ->
            {
                arrPartSnake[length - 1].bm = bm_tail_left
            }
            arrPartSnake[length - 1].getTop().intersect(arrPartSnake[length - 2].getBody())   ->
            {
                arrPartSnake[length - 1].bm = bm_tail_up
            }
            arrPartSnake[length - 1].getBottom()
                .intersect(arrPartSnake[length - 2].getBody())                                ->
            {
                arrPartSnake[length - 1].bm = bm_tail_down
            }
        }

    }

    fun addPart()
    {
        val p: PartSnake = this.arrPartSnake[arrPartSnake.lastIndex]
        this.length += 1
        when (p.bm)
        {
            bm_tail_right ->
            {
                this.arrPartSnake.add(PartSnake(bm_tail_right, p.x - GameView.sizeOfMap, p.y))
            }
            bm_tail_left  ->
            {
                this.arrPartSnake.add(PartSnake(bm_tail_left, p.x + GameView.sizeOfMap, p.y))
            }
            bm_tail_up    ->
            {
                this.arrPartSnake.add(PartSnake(bm_tail_up, p.x, p.y + GameView.sizeOfMap))
            }
            bm_tail_down  ->
            {
                this.arrPartSnake.add(PartSnake(bm_tail_down, p.x, p.y - GameView.sizeOfMap))
            }
        }
    }
}