package com.gmail.mukatdisovilyas.snake

import android.content.Context
import android.graphics.*
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.Nullable
import kotlin.random.Random

class GameView(context: Context, @Nullable attrs: AttributeSet) : View(context)
{
    private var bmGrass1: Bitmap = BitmapFactory.decodeResource(this.resources, R.drawable.grass)
    private var bmGrass2: Bitmap = BitmapFactory.decodeResource(this.resources, R.drawable.grass03)
    private var bmSnake: Bitmap = BitmapFactory.decodeResource(this.resources, R.drawable.snake1)
    private var bmApple: Bitmap = BitmapFactory.decodeResource(this.resources, R.drawable.apple)
    private var h: Int = 21
    private var w: Int = 12

    private var arrGrass: ArrayList<Grass> = ArrayList()
    private var snake: Snake

    private var runnable: Runnable

    private var move: Boolean = false
    private var mx: Float = 0f
    private var my: Float = 0f

    private var apple: Apple

    companion object
    {
        var sizeOfMap: Int = 75 * Constants.SCREEN_WIDTH / 1080
    }

    init
    {
        bmGrass1 = Bitmap.createScaledBitmap(bmGrass1, sizeOfMap, sizeOfMap, true)
        bmGrass2 = Bitmap.createScaledBitmap(bmGrass2, sizeOfMap, sizeOfMap, true)
        bmSnake = Bitmap.createScaledBitmap(bmSnake, 14 * sizeOfMap, sizeOfMap, true)
        bmApple = Bitmap.createScaledBitmap(bmApple, sizeOfMap, sizeOfMap, true)
        for (i in 0 until h)
        {
            for (j in 0 until w)
            {
                if ((i + j) % 2 == 0)
                {
                    arrGrass.add(
                        Grass(
                            bm = bmGrass1,
                            x = j * sizeOfMap + Constants.SCREEN_WIDTH / 2 - (w / 2) * sizeOfMap,
                            y = i * sizeOfMap + 100 * Constants.SCREEN_HEIGHT / 1920,
                            height = sizeOfMap,
                            width = sizeOfMap
                        )
                    )
                } else
                {
                    arrGrass.add(
                        Grass(
                            bm = bmGrass2,
                            x = j * sizeOfMap + Constants.SCREEN_WIDTH / 2 - (w / 2) * sizeOfMap,
                            y = i * sizeOfMap + 100 * Constants.SCREEN_HEIGHT / 1920,
                            height = sizeOfMap,
                            width = sizeOfMap
                        )
                    )
                }
            }
        }
        snake = Snake(
            bmSnake,
            x = arrGrass[126].x,
            y = arrGrass[126].y,
            length = 4,
            move_bottom = false,
            move_left = false,
            move_right = false,
            move_top = false
        )

        apple = Apple(bmApple, arrGrass[randomApple()[0]].x, arrGrass[randomApple()[1]].y)


        runnable = Runnable {
            kotlin.run {
                invalidate()
            }
        }
    }

    override fun draw(canvas: Canvas?)
    {
        super.draw(canvas)
        canvas?.drawColor(Color.GREEN)
        for (i in 0 until arrGrass.size)
        {
            canvas?.drawBitmap(
                arrGrass[i].bm,
                arrGrass[i].x.toFloat(),
                arrGrass[i].y.toFloat(),
                null
            )
        }
        snake.update()
        snake.draw(canvas)
        apple.draw(canvas)
        if (snake.arrPartSnake[0].getBody().intersect(apple.getR())){
            randomApple()
            apple.reset(arrGrass[randomApple()[0]].x,arrGrass[randomApple()[1]].y)
            snake.addPart()
        }
        Handler(Looper.getMainLooper()).postDelayed(runnable, 100)

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean
    {

        when (event?.actionMasked)
        {
            MotionEvent.ACTION_MOVE ->
            {
                if (!move)
                {
                    mx = event.x
                    my = event.y
                    move = true
                } else if (mx - (event.x) > 100 * Constants.SCREEN_WIDTH / 1080 && !snake.move_right
                )
                {
                    mx = event.x
                    my = event.y ?: 0f
                    snake.set_move_left(true)
                } else if ((event.x) - mx > 100 * Constants.SCREEN_WIDTH / 1080 && !snake.move_left
                )
                {
                    mx = event.x
                    my = event.y
                    snake.set_move_rigth(true)
                } else if (my - (event.y) > 100 * Constants.SCREEN_WIDTH / 1080 && !snake.move_bottom
                )
                {
                    mx = event.x
                    my = event.y
                    snake.set_move_top(true)
                } else if ((event.y) - my > 100 * Constants.SCREEN_WIDTH / 1080 && !snake.move_top
                )
                {
                    mx = event.x
                    my = event.y
                    snake.set_move_bottom(true)
                }

            }
            MotionEvent.ACTION_UP   ->
            {
                mx = 0f
                my = 0f
                move = false
            }
        }

        return true
    }

    private fun randomApple(): ArrayList<Int>
    {
        val xy: ArrayList<Int> = ArrayList(2)
        val r = Random
        xy.add(0, r.nextInt(arrGrass.size - 1))
        xy.add(1, r.nextInt(arrGrass.size - 1))
        var rect =
            Rect(
                arrGrass[xy[0]].x,
                arrGrass[xy[1]].y,
                arrGrass[xy[0]].x + sizeOfMap,
                arrGrass[xy[1]].y + sizeOfMap
            )
        var check = true
        while (check)
        {
            check = false
            for (i in 0 until snake.arrPartSnake.size)
            {
                if (rect.intersect(snake.arrPartSnake[i].getBody()))
                {
                    check = true
                    xy[0] = r.nextInt(arrGrass.size - 1)
                    xy[1] = r.nextInt(arrGrass.size - 1)
                    rect = Rect(
                        arrGrass[xy[0]].x,
                        arrGrass[xy[1]].y,
                        arrGrass[xy[0]].x + sizeOfMap,
                        arrGrass[xy[1]].y + sizeOfMap
                    )
                }
            }
        }
        return xy
    }

}