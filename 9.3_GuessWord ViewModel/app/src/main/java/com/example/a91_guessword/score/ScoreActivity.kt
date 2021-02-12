package com.example.a91_guessword.score

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.a91_guessword.R
import com.example.a91_guessword.SCORE_INTENT_NAME
import kotlinx.android.synthetic.main.activity_score.*

class ScoreActivity : AppCompatActivity() {

    private lateinit var scoreViewModel: ScoreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        val score = intent.getIntExtra(SCORE_INTENT_NAME,0)

        val factory = ScoreViewModelFactory(score)
        scoreViewModel = ViewModelProvider(this, factory).get(ScoreViewModel::class.java)

        text_view_score.text = scoreViewModel.score.toString()

        button_go_back.setOnClickListener {
            finish()
        }
    }
}