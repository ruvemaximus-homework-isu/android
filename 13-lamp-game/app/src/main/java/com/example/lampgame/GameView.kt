package com.example.lampgame

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.random.Random

class GameView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0
) : View(context, attrs, defStyle) {

    private val gridSize = 4
    private val grid = Array(gridSize) { BooleanArray(gridSize) }
    private val paint = Paint()
    private var cellSize = 0f

    init {
        paint.style = Paint.Style.FILL_AND_STROKE
        initializeGrid()
    }

    private fun initializeGrid() {
        for (i in 0 until gridSize) {
            for (j in 0 until gridSize) {
                grid[i][j] = Random.nextBoolean()
            }
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        cellSize = (width / gridSize).toFloat()
        for (i in 0 until gridSize) {
            for (j in 0 until gridSize) {
                paint.color = if (grid[i][j]) Color.YELLOW else Color.BLACK
                val cx = j * cellSize + cellSize / 2
                val cy = i * cellSize + cellSize / 2
                canvas.drawCircle(cx, cy, cellSize / 3, paint)
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action != MotionEvent.ACTION_DOWN) {
            return super.onTouchEvent(event)
        }

        val row = (event.y / cellSize).toInt()
        val col = (event.x / cellSize).toInt()

        toggleCells(row, col)
        invalidate()

        if (checkWinCondition()) {
            (context as MainActivity).onGameWon()
        }
        return true
    }

    private fun toggleCells(row: Int, col: Int) {
        if (row in 0 until gridSize && col in 0 until gridSize) {
            grid[row][col] = !grid[row][col]

            if (row > 0) grid[row - 1][col] = !grid[row - 1][col]
            if (row < gridSize - 1) grid[row + 1][col] = !grid[row + 1][col]

            if (col > 0) grid[row][col - 1] = !grid[row][col - 1]
            if (col < gridSize - 1) grid[row][col + 1] = !grid[row][col + 1]
        }
    }

    private fun checkWinCondition(): Boolean {
        val firstCellState = grid[0][0]
        for (row in grid) {
            for (cell in row) {
                if (cell != firstCellState) {
                    return false
                }
            }
        }
        return true
    }

    fun resetGame() {
        initializeGrid()
        invalidate()
    }
}
