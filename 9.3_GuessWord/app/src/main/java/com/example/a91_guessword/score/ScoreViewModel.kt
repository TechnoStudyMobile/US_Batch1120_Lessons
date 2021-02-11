package com.example.a91_guessword.score

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.a91_guessword.LOG_TAG

class ScoreViewModel(val score: Int) : ViewModel() {

    override fun onCleared() {
        super.onCleared()
        Log.d(LOG_TAG, "ScoreViewModel is destroyed")
    }
}