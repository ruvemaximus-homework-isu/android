package com.example.seabattle

import android.os.Bundle
import android.widget.GridLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.seabattle.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.gridLayout.setOnClickListener {
            generateBoard()
        }

        generateBoard()
    }

    private fun generateBoard() {
        val board = Board()

        board.placeAllShips()

        for (i in 0 until 10) {
            for (j in 0 until 10) {
                val color = resources.getColor(
                    when (board.data[i][j]) {
                        1 -> android.R.color.background_dark
                        0 -> android.R.color.darker_gray
                        else -> android.R.color.holo_red_dark
                    }, null
                )

                val textView = TextView(this).apply {
                    layoutParams = GridLayout.LayoutParams().apply {
                        width = 0
                        height = 0
                        rowSpec = GridLayout.spec(i, 1f)
                        columnSpec = GridLayout.spec(j, 1f)
                    }
                    gravity = android.view.Gravity.CENTER
                    text = board.data[i][j].toString()
                    setBackgroundColor(color)
                }

                binding.gridLayout.addView(textView)
            }
        }
    }
}