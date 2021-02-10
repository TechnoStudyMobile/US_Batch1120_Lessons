package com.example.a91_guessword

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*


// Our View in MVVM (Model View ViewModel) architecture pattern
class MainActivity : AppCompatActivity() {

    //private var count = 0
    private lateinit var scoreViewModel: ScoreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scoreViewModel = ViewModelProvider(this).get(ScoreViewModel::class.java)
        text_view_count.text = scoreViewModel.retrieveScore()

        // You can shorten it using lambda
        button_increase.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                //count++
                scoreViewModel.increaseBy(1)
                text_view_count.text = scoreViewModel.retrieveScore()
            }
        })
    }
}