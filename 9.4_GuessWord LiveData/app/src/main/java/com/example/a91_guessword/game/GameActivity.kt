package com.example.a91_guessword.game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.a91_guessword.R
import com.example.a91_guessword.SCORE_INTENT_NAME
import com.example.a91_guessword.score.ScoreActivity
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity() {

    private lateinit var gameViewModel: GameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        gameViewModel = ViewModelProvider(this).get(GameViewModel::class.java)

        button_next.setOnClickListener {
            gameViewModel.next()
        }

        button_skip.setOnClickListener {
            gameViewModel.skip()
        }

        //Observing LiveData
        gameViewModel.wordLiveData.observe(this, Observer {
            // Will come here if Only Activity visible
            if (it == null){
                navigateToScoreScreen()
            } else {
                text_view_word.text = it
            }
        })

    }

    override fun onResume() {
        super.onResume()
        gameViewModel.resetWords()
    }

    private fun navigateToScoreScreen() {
        val scoreIntent = Intent(this, ScoreActivity::class.java)
        scoreIntent.putExtra(SCORE_INTENT_NAME, gameViewModel.score)
        startActivity(scoreIntent)
    }
}