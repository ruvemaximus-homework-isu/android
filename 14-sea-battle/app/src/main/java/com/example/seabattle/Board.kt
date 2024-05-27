package com.example.seabattle

import kotlin.random.Random

const val SIZE = 10
const val EMPTY = 0
const val SHIP = 1

val SHIPS = listOf(
    4 to 1, // 1 линкор
    3 to 2, // 2 эсминца
    2 to 3, // 3 крейсера
    1 to 4  // 4 подводные лодки
)

fun Boolean.toInt() = if (this) 1 else 0

class Board(var data: Array<Array<Int>> = Array(SIZE) { Array(SIZE) { EMPTY } }) {

    private fun canPlaceShip(row: Int, col: Int, length: Int, horizontal: Boolean): Boolean {
        val colLimit = col + length * horizontal.toInt() + 1 * (!horizontal).toInt()
        val rowLimit = row + length * (!horizontal).toInt() + 1 * horizontal.toInt()

        if (horizontal && col + length > SIZE || !horizontal && row + length > SIZE) {
            return false
        }

        for (i in row - 1..rowLimit) {
            for (j in col - 1..colLimit) {
                if (i in 0 until SIZE && j in 0 until SIZE && data[i][j] == SHIP) {
                    return false
                }
            }
        }

        return true
    }

    private fun placeShip(row: Int, col: Int, length: Int, horizontal: Boolean) {
        for (i in 0 until length) {
            data[row + i * (!horizontal).toInt()][col + i * horizontal.toInt()] = SHIP
        }
    }

    fun placeAllShips() {
        for ((length, count) in SHIPS) {
            repeat(count) {
                var placed = false
                while (!placed) {
                    val row = Random.nextInt(SIZE)
                    val col = Random.nextInt(SIZE)

                    val horizontal = Random.nextBoolean()

                    if (canPlaceShip(row, col, length, horizontal)) {
                        placeShip(row, col, length, horizontal)
                        placed = true
                    }
                }
            }
        }
    }
}