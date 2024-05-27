package com.example.lampgame

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var gameView: GameView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gameView = findViewById(R.id.gameView)
    }


    fun onGameWon() {
        AlertDialog.Builder(this)
            .setTitle("Congratulations!")
            .setMessage("You've won the game!")
            .setPositiveButton("OK") { _, _ ->
                gameView.resetGame()
            }
            .show()
    }
}
